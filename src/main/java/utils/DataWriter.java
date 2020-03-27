package utils;

import java.io.*;
import java.util.ArrayList;

import static constances.Constances.FILES_PATH;

public class DataWriter {


    public void writeSolution(ResultData data) {

        ArrayList<String> toWrite = new ArrayList<>();
        toWrite.add("Nazwa problemu: " + data.getName());
        toWrite.add("Czas do znalezienia pierwszego rozwiązania: " + data.getTimeFirst());
        toWrite.add("Liczba odwiedzonych węzłów drzewa do znalezienia pierwszego rozwiązania: " + data.getNodesFirst());
        toWrite.add("Liczba nawrotów wykonanych do znalezienia pierwszego rozwiązania: " + data.getBacktrackFirst());
        toWrite.add("Całkowity czas działania metody: " + data.getTimeAll());
        toWrite.add("Całkowita liczba odwiedzonych węzłów drzewa: " + data.getNodesAll());
        toWrite.add("Całkowita liczba nawrotów: " + data.getBacktrackAll());
        toWrite.add("Liczba rozwiązań: " + data.getResults().size());
        toWrite.add(" ");
        toWrite.add("Rozwiązania: ");
        saveSolutions(data.getResults(), toWrite);

        try {
            File file = new File(FILES_PATH + "Results/" + data.getName() + "_Result.txt");
            FileOutputStream outputStream = new FileOutputStream(file);
            PrintWriter writer = new PrintWriter(outputStream);

            for (String elem : toWrite) {
                writer.println(elem);
            }
            writer.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void saveSolutions(ArrayList<String[][]> matrixes, ArrayList<String> toWrite) {

        for (String[][] result : matrixes) {
            String[][] toPrint = transposeMatrix(result);
            for (String[] strings : toPrint) {
                StringBuilder row = new StringBuilder();
                for (String string : strings) {
                    row.append(string);
                }
                toWrite.add(row.toString());
            }
            toWrite.add(" ");
        }

    }

    private String[][] transposeMatrix(String[][] matrix) {

        String[][] temp = new String[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                temp[j][i] = matrix[i][j];
        return temp;
    }
}
