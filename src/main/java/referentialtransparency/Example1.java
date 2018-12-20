package referentialtransparency;

import static out.Print.*;

public class Example1 {

    public static void main(String[] args) {
        println("rt1");
        rt1();
        println("rt2");
        rt2();
        println("rt3");
        rt3();
    }

    static void rt1() {
        int a = getA();
        int b = getB();
        int aPlusB = getA() + getB();

        println(a + " + " + b + " = " + aPlusB);
    }

    static void rt2() {
        int a = getA();
        int b = getB();
        int aPlusB = a + b;

        println(a + " + " + b + " = " + aPlusB);
    }

    static void rt3() {
        int b = getB();
        int a = getA();
        int aPlusB = a + b;

        println(a + " + " + b + " = " + aPlusB);
    }

























    static int z = 7;

    static int getA() {
        return z;
    }

    static int getB() {
        return --z;
    }
}
