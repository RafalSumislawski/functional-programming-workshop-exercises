package out;

import java.util.Arrays;

public class Print {

    public static void println(String s) {
        System.out.println(s);
    }

    public static void println(Object o) {
        System.out.println(o);
    }

    public static void println(int i) {
        System.out.println(i);
    }

    public static void println(char c) {
        System.out.println(c);
    }

    public static void println(char[] c) {
        System.out.println(Arrays.toString(c));
    }
}
