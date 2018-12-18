package immutable;

import io.vavr.collection.Seq;
import io.vavr.collection.Vector;

import java.util.ArrayList;
import java.util.List;

public class ImutableExamples {

    public static void main(String[] args) {

    }

    List<String> f1(List<String> l) {
        l.add("aaa");
        l.remove("bbb");
        return l;
    }

    List<String> f2(List<String> l1) {
        List<String> l2 = new ArrayList<>(l1);
        l2.add("aaa");
        l2.remove("bbb");
        return l2;
    }

    Seq<String> f3(Vector<String> s1) {
        Seq<String> s2 = s1.append("aaa");
        Seq<String> s3 = s2.remove("bbb");
        return s3;
    }

    Seq<String> f4(Vector<String> s) {
        return s.append("aaa")
                .remove("bbb");
    }
}
