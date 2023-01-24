package ru.vsu.cs.solopanov.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<List<String>> stringArrayToListList(String[] inputArray) {
        List<List<String>> result = new ArrayList<>();

        for (String i : inputArray) {
            List<String> currentList = new ArrayList<>();
            for (String j : i.split(" ")) {
                currentList.add(j);
            }
            result.add(currentList);
        }
        return result;
    }

    public static void write(String filename, String[][] x) throws IOException {
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter(filename));
        for (int i = 0; i < x[0].length; i++) {
            // Maybe:
            outputWriter.write(x[0][i] + " ");
            // Or:
//            outputWriter.write(x[i]);
//            outputWriter.newLine();
        }
        outputWriter.write(x[1][0] + "");
        outputWriter.flush();
        outputWriter.close();
    }
}
