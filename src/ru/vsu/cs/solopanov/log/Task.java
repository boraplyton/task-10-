package ru.vsu.cs.solopanov.log;

import java.util.*;

import static java.lang.Integer.parseInt;

public class Task {
    public static String[][] listToString(List<List<String>> matrix, double currentSum) {
        String[][] result = new String[2][matrix.size()];

        for (int i = 0; i < result[0].length; i++) {
            result[0][i] = matrix.get(i).get(0);
        }
        result[1][0] = String.valueOf(currentSum);

        for (int i = 1; i < result[0].length; i++) {
            result[1][i] = "";
        }
        return result;
    }
    public static List<List<String>> sort(List<List<String>> matrix) {
        List<List<String>> result = new ArrayList<>();

        while (matrix.size() > 0) {
            int minValue = parseInt(matrix.get(0).get(1));
            int indexMinValue = 0;
            for (int j = 1; j < matrix.size(); j++) {
                if (parseInt(matrix.get(j).get(1)) < minValue) {
                    minValue = parseInt(matrix.get(j).get(1));
                    indexMinValue = j;
                }
            }
            result.add(matrix.get(indexMinValue));
            matrix.remove(indexMinValue);
        }
        return result;

    }



    public static String[][] task(List<Candy> candies, double sum){
        List<List<String>> result = new ArrayList<>();



        String[][] total = listToString(result, currentSum);
        return result;
    }
    public static String[][] solve(List<List<String>> matrix, double sum) {
        List<List<String>> result = new ArrayList<>();

        matrix = sort(matrix);

        double currentSum = 0;
        for (int i = 0; i < matrix.size(); i++) {                        // Создаем массив из минимальных сумм
            if (parseInt(matrix.get(i).get(1)) + currentSum <= sum) {
                currentSum += parseInt(matrix.get(i).get(1));
                result.add(matrix.get(i));
            }
        }

        for (int i = result.size() - 1; i >= 0; i--) {
            currentSum -= parseInt(result.get(i).get(1));
            for (int j = matrix.size() - 1; j >= 0; j--) {
                if (sum - parseInt(matrix.get(j).get(1)) >= currentSum) {
                    result.set(i, matrix.get(j));
                    matrix.remove(j);
                    break;
                }
                matrix.remove(j);

            }
            currentSum += parseInt(result.get(i).get(1));
        }
        String[][] total = listToString(result, currentSum);
        return total;
    }
}
