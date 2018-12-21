package io;

import io.vavr.CheckedFunction0;
import io.vavr.Function1;
import io.vavr.Lazy;
import io.vavr.concurrent.Future;
import io.vavr.control.Try;

public class IO<A> {

    private Lazy<Future<A>> it;

    private  IO(Lazy<Future<A>> it) {
        this.it = it;
    }

    public <B> IO<B> map(Function1<A, B> f){
        return new IO<B>(it.map(fut -> fut.map(f)));
    }

    public <B> IO<B> mapTry(Function1<A, Try<B>> f){
        return new IO<B>(it.map(fut -> fut.transformValue(t -> t.flatMap(f))));
    }

    public <B> IO<B> flatMap(Function1<A, IO<B>> f){
        return new IO<B>(Lazy.of(() -> it.get().flatMap(a -> f.apply(a).it.get())));
    }

    public IO<Try<A>> attempt(){
        return new IO<Try<A>>(Lazy.of(() -> it.get().transformValue(Try::success)));
    }

    static <A> IO<A> of(CheckedFunction0<A> f){
        return new IO<A>(Lazy.of(() -> Future.of(f)));
    }

    static <A> IO<A> pure(A a){
        return new IO<A>(Lazy.of(() -> Future.successful(a)));
    }
}
