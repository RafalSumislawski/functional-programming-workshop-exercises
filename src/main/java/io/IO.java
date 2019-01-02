package io;

import io.vavr.*;
import io.vavr.concurrent.Future;
import io.vavr.control.Try;

public class IO<A> {

    private Lazy<Future<A>> it;

    private IO(Lazy<Future<A>> it) {
        this.it = it;
    }

    public <B> IO<B> map(CheckedFunction1<A, B> f){
        return new IO<B>(it.map(fut -> fut.mapTry(f)));
    }

    public <B> IO<B> mapTry(CheckedFunction1<A, Try<B>> f){
        return new IO<B>(it.map(fut -> fut.transformValue(t -> t.flatMapTry(f))));
    }

    public <B> IO<B> flatMap(CheckedFunction1<A, IO<B>> f){
        return new IO<B>(Lazy.of(() -> it.get().flatMapTry(a -> f.apply(a).it.get())));
    }

    public IO<Try<A>> attempt(){
        return new IO<Try<A>>(Lazy.of(() -> it.get().transformValue(Try::success)));
    }

    public IO<A> handleErrorWith(CheckedFunction1<Throwable, IO<A>> f){
        return attempt().flatMap( t -> {
            if(t.isSuccess()) return IO.pure(t.get());
            else return f.apply(t.getCause());
        });
    }

    public static <A> IO<A> of(CheckedFunction0<A> f){
        return new IO<A>(Lazy.of(() -> Future.of(f)));
    }

    public static IO<Void> of(CheckedRunnable r){
        return new IO<Void>(Lazy.of(() -> Future.of(() -> {r.run(); return null;})));
    }

    public static <A> IO<A> pure(A a){
        return new IO<A>(Lazy.of(() -> Future.successful(a)));
    }

    public static <A> Try<A> unsafeRunSync(IO<A> io){
        return io.it.get().await().getValue().get();
    }

}
