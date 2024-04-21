package org.example;

import java.io.File;
import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Not enough arguments passed!\nUsage: <./gradlew run --args \"<fileName> ...\"");
            System.exit(1);
        } else {
            for (String s : args) {
                try {
                    File file = new File(s);
                    System.out.println(s + ": " + JSONParser.checkPolicy(file));
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }


        }
    }
}
