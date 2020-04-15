import algorithms.Algorithm;
import algorithms.BacktrackAlgorithm;
import algorithms.ForwardCheckAlgorithm;
import heuristics.*;
import problems.CspProblem;
import problems.JolkaCspProblem;
import problems.SudokuCspProblem;
import utils.DataReader;
import utils.DataWriter;
import utils.ResultData;
import variables.Variable;


import static constances.Constances.*;

public class Main {


    public static void main(String[] args) {


        DataReader reader = new DataReader();
        DataWriter writer = new DataWriter();


        CspProblem sudokuB = new SudokuCspProblem(reader.getSudokuMatrix(FILES_PATH + "Sudoku.csv", SUDOKU_NUMBER));
        CspProblem sudokuF = new SudokuCspProblem(reader.getSudokuMatrix(FILES_PATH + "Sudoku.csv", SUDOKU_NUMBER));

        Heuristic<Variable> chooseDomainSizeVarB = new DomainSizeOrderVarOnly<>(sudokuB);
        Heuristic<String> definitionOrderB = new DefinitionOrder<>(sudokuB);
        Heuristic<Variable> chooseDomainSizeVarF = new DomainLengthVarOnly<>(sudokuF);
        Heuristic<String> definitionOrderF = new DefinitionOrder<>(sudokuF);

        Algorithm sudokuBacktrack = new BacktrackAlgorithm(sudokuB, chooseDomainSizeVarB, definitionOrderB);
        Algorithm sudokuForward = new ForwardCheckAlgorithm(sudokuF, chooseDomainSizeVarF, definitionOrderF);


        CspProblem jolkaB = new JolkaCspProblem(reader.getJolkaMatrix(FILES_PATH + "Jolka/puzzle" + JOLKA_NUMBER), reader.getJolkaWords(FILES_PATH + "Jolka/words" + JOLKA_NUMBER));
        CspProblem jolkaF = new JolkaCspProblem(reader.getJolkaMatrix(FILES_PATH + "Jolka/puzzle" + JOLKA_NUMBER), reader.getJolkaWords(FILES_PATH + "Jolka/words" + JOLKA_NUMBER));

        Heuristic<Variable> chooseDomainLenVarB = new DomainLengthVarOnly<>(jolkaB);
        Heuristic<String> uniqueCharsB = new UniqueCharsValOnly<>(jolkaF);
        Heuristic<Variable> chooseDomainLenVarF = new DomainLengthVarOnly<>(jolkaF);
        Heuristic<String> uniqueCharsF = new UniqueCharsValOnly<>(sudokuF);

        Algorithm jolkaBacktrack = new BacktrackAlgorithm(jolkaB, chooseDomainLenVarB, uniqueCharsB);
        Algorithm jolkaForward = new ForwardCheckAlgorithm(jolkaF, chooseDomainLenVarF, uniqueCharsF);


        System.out.println("START SUDOKU B");
        ResultData sudokuResultB = sudokuBacktrack.solveProblem();
        System.out.println("START SUDOKU F");
        ResultData sudokuResultF = sudokuForward.solveProblem();

        System.out.println("START JOLKA B");
        ResultData jolkaResultB = jolkaBacktrack.solveProblem();
        System.out.println("START JOLKA F");
        ResultData jolkaResultF = jolkaForward.solveProblem();


        System.out.println("ZAPISYWANIE");

        writer.writeSolution(sudokuResultB);
        writer.writeSolution(sudokuResultF);

        writer.writeSolution(jolkaResultB);
        writer.writeSolution(jolkaResultF);


    }
}
