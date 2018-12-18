package tasks;

import static out.Print.println;

public class Task1 {

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
        int b = getB();
        int a = getA();
        int aPlusB = a + b;

        println(a + " + " + b + " = " + aPlusB);
    }

    static void rt3() {
        int a = getA();
        int b = getB();
        int aPlusB = a + b;

        println(a + " + " + b + " = " + aPlusB);
    }

    static int getA(){
        return 7;
    }

    static int getB() {
        return 6;
    }
}
