import Algorithms.Algorithm;
import Algorithms.BacktrackAlgorithm;
import heuristics.DefinitionOrder;
import heuristics.DomainSize;
import heuristics.Heuristic;
import problems.CspProblem;
import problems.JolkaCspProblem;
import problems.SudokuCspProblem;
import utils.DataReader;
import utils.DataWriter;
import utils.ResultData;
import variables.Variable;

import static constances.Constances.*;

public class Main {


    public static void main(String[] args){


        DataReader reader = new DataReader();
        DataWriter writer = new DataWriter();
        CspProblem jolka = new JolkaCspProblem(reader.getJolkaMatrix(FILES_PATH + "Jolka/puzzle" + JOLKA_NUMBER), reader.getJolkaWords(FILES_PATH + "Jolka/words"+ JOLKA_NUMBER));
        CspProblem sudoku = new SudokuCspProblem(reader.getSudokuMatrix(FILES_PATH + "Sudoku.csv", SUDOKU_NUMBER));
        Heuristic<String> chooseValueJolka = new DefinitionOrder<>();
        Heuristic<Variable> chooseVariableJolka = new DomainSize<>();
        Algorithm jolkaBacktrack = new BacktrackAlgorithm(jolka, chooseVariableJolka, chooseValueJolka);
        Heuristic<String> chooseValueSudoku = new DefinitionOrder<>();
        Heuristic<Variable> chooseVariableSudoku = new DomainSize<>();
        Algorithm sudokuBacktrack = new BacktrackAlgorithm(sudoku, chooseVariableSudoku, chooseValueSudoku);

        System.out.println("START JOLKA");
        ResultData jolkaResult = jolkaBacktrack.solveProblem();
        System.out.println("START SUDOKU");
        ResultData sudokuResult = sudokuBacktrack.solveProblem();
        System.out.println("ZAPISYWANIE");
        writer.writeSolution(jolkaResult);
        writer.writeSolution(sudokuResult);

        //wyniki są zapisywane do pliku w katalogu resources/Results
        //ścieżki do lików i numery problemów są możliwe do zmiany w klasie Constances

        // nie radzi sobie z jolka nr.3







    }
}
