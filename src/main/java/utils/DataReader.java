package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {

    public ArrayList<ArrayList<String>> getSudokuMatrix(String path, String id) {

        File file = new File(path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        ArrayList<ArrayList<String>> matrix = new ArrayList<>();


        String bufor;
        String puzzle = "";
        while (scanner != null && scanner.hasNextLine()) {

            bufor = scanner.nextLine();

            if (bufor.startsWith(id)) {

                String[] parts = bufor.split(";");
                puzzle = parts[2];
                break;
            }

        }

        int rowLength = (int) Math.sqrt(puzzle.length());
        if (puzzle.length() % rowLength != 0) {
            throw new RuntimeException();
        }


        for (int i = 0; i < rowLength; i++) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j < rowLength; j++) {
                matrix.get(i).add(String.valueOf(puzzle.charAt((rowLength * i) + j)));
            }
        }

        return matrix;

    }

    public ArrayList<ArrayList<String>> getJolkaMatrix(String path) {

        File file = new File(path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<ArrayList<String>> matrix = new ArrayList<>();

        String bufor;
        while (scanner != null && scanner.hasNextLine()) {

            bufor = scanner.nextLine();

            ArrayList<String> matrixRow = new ArrayList<>();

            for (int i = 0; i < bufor.length(); i++) {
                matrixRow.add(String.valueOf(bufor.charAt(i)));
            }

            matrix.add(matrixRow);

        }

        return matrix;
    }

    public ArrayList<String> getJolkaWords(String path) {

        File file = new File(path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> wordsList = new ArrayList<>();


        String bufor;
        while (scanner != null && scanner.hasNextLine()) {

            bufor = scanner.nextLine();

            wordsList.add(bufor);

        }

        return wordsList;

    }

}
