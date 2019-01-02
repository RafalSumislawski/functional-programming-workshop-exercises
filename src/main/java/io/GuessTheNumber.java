package io;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GuessTheNumber {

    public static void main(String[] args) {
        IO.unsafeRunSync(new GuessTheNumber().run());
        System.exit(0);
    }

    private IO<Void> run() {
        return randomInt(10).flatMap(magicNumber ->
                printString("Guess the number (from 0 to 9):").flatMap(x ->
                        letPlayerGuess(magicNumber)
                )
        );
    }

    private IO<Void> letPlayerGuess(int magicNumber) {
        return readNumberFromConsole().flatMap(guessedNumber ->
                handleGuess(magicNumber, guessedNumber)
        );
    }

    private IO<Integer> readNumberFromConsole() {
        return readLine().flatMap(inputLine ->
                IO.of(() -> Integer.parseInt(inputLine))
                        .handleErrorWith(x -> retryReadingNumberFromConsole())
        );
    }

    private IO<Integer> retryReadingNumberFromConsole() {
        return printString("Please provide a valid number!").flatMap(x ->
                readNumberFromConsole()
        );
    }

    private IO<Void> handleGuess(int magicNumber, int guessedNumber) {
        if (guessedNumber == magicNumber) return handleCorrectGuess();
        else if (guessedNumber < magicNumber) return handleTooLowGuess(magicNumber);
        else return handleToHighGuess(magicNumber);
    }

    private IO<Void> handleCorrectGuess() {
        return printString("You won!");
    }

    private IO<Void> handleTooLowGuess(int magicNumber) {
        return printString("Too low! Try again:").flatMap(x ->
                letPlayerGuess(magicNumber)
        );
    }


    private IO<Void> handleToHighGuess(int magicNumber) {
        return printString("Too high! Try again:").flatMap(x ->
                letPlayerGuess(magicNumber)
        );
    }

    private IO<Integer> randomInt(int mod) {
        return IO.of(() -> ThreadLocalRandom.current().nextInt(mod));
    }

    private IO<Void> printString(String string) {
        return IO.of(() -> System.out.println(string));
    }

    private IO<String> readLine() {
        return IO.of(() -> new Scanner(System.in).nextLine());
    }
}
