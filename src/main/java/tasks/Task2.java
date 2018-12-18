package tasks;

import static out.Print.println;

public class Task2 {

    public static void main(String[] args) {
        v2();
    }

    static void v1() {
        char a = 'a';
        char b = nextLetter(a);

        println(a);
        println(b);
    }

    static char nextLetter(char in) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static void v2() {
        char[] a = new char[]{'a'};
        char[] b = nextLetter(a);

        println(a);
        println(b);
    }

    static char[] nextLetter(char[] in) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
