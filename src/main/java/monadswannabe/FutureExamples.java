package monadswannabe;

import io.vavr.concurrent.Future;
import org.javaync.io.AsyncFiles;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static out.Print.*;

public class FutureExamples {

    public static void main(String[] args){
        asyncPrintLineToConsole("Please enter a file name:")
            .flatMap(voidek -> asyncReadLineFromConsole())
            .flatMap(fileNameFromUser -> asyncReadWholeFile(fileNameFromUser))
            .onComplete(t ->{
                if(t.isSuccess()) println(t.get());
                else println("failed to load the file due to: "+ t.getCause().getMessage());
            });
    }

    static final ExecutorService blockingIo = Executors.newCachedThreadPool();

    static Future<Void> asyncPrintLineToConsole(String s){
        return Future.run(blockingIo, () -> asyncPrintLineToConsole(s));
    }

    static Future<String> asyncReadLineFromConsole(){
        return Future.of(blockingIo, () -> new Scanner(System.in).nextLine());
    }

    static Future<String> asyncReadWholeFile(String filePath){
        return Future.fromCompletableFuture(AsyncFiles.readAll(filePath));
    }

}
