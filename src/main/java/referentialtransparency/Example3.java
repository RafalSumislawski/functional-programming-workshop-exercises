package referentialtransparency;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static out.Print.println;

public class Example3 {

    public static void main(String[] args) {
        String s = loadFirstLine(null);
        println(s);
    }

    static String loadFirstLine(String filePath) {
        Path path = Paths.get(filePath);
        List<String> strings = null;
        try {
            strings = Files.readAllLines(path);
            return strings.get(0);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
