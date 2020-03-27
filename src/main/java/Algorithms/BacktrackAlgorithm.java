package Algorithms;

import heuristics.DomainSize;
import heuristics.Heuristic;
import problems.CspProblem;
import utils.ResultData;
import variables.Variable;

import java.util.ArrayList;
import java.util.Stack;

public class BacktrackAlgorithm extends Algorithm {

    private int searchedNodes;
    private int backtracks;
    Stack<Variable> checkedVariables;
    ArrayList<ArrayList<Variable>> results;
    long startTime = 0;

    public BacktrackAlgorithm(CspProblem problem, Heuristic<Variable> chooseNextVariable, Heuristic<String> chooseNextValue) {
        super(problem, chooseNextVariable, chooseNextValue);
        searchedNodes = 0;
        backtracks = 0;
        checkedVariables = new Stack<>();
        results = new ArrayList<>();
        if (chooseNextVariable instanceof DomainSize) {
            problem.getVariables().sort(Variable.SortByDomainLength);
        }
    }

    @Override
    public ResultData solveProblem() {

        ResultData result = new ResultData();
        result.setName(problem.getName());
        startTime = System.currentTimeMillis();
        run(result);
        result.setResults(convertResultsToListOfMatrix());
        return result;
    }

    private void run(ResultData result) {

        Variable var = null;
        String value;
        outer:
        while (true) {

            var = chooseNextVariable.getNext(var, problem.getVariables());


            if (var == null) {

                saveResult();

                if (results.size() == 1) {
                    result.setTimeFirst((System.currentTimeMillis() - startTime) / 1000.0);
                    result.setNodesFirst(searchedNodes);
                    result.setBacktrackFirst(backtracks);
                }

                var = checkedVariables.pop();

            }


            while (true) {
                value = chooseNextValue.getNext(var.getValue(), var.getDomain());
                searchedNodes++;

                if (value == null) {
                    backtracks++;
                    if (checkedVariables.empty()) {
                        result.setTimeAll((System.currentTimeMillis() - startTime) / 1000.0);
                        result.setNodesAll(searchedNodes);
                        result.setBacktrackAll(backtracks);
                        break outer;

                    } else {
                        var.setValue(var.getInitValue());
                        var.setSet(false);
                        var = checkedVariables.pop();

                    }
                } else {
                    var.setValue(value);
                    boolean good = problem.checkConstraints(var, problem.getVariables());
                    if (good) {
                        var.setSet(true);
                        checkedVariables.push(var);

                        break;

                    } else {
                        backtracks++;

                    }
                }
            }
        }


    }


    private void saveResult() {
        results.add(new ArrayList<>());
        int resultId = results.size() - 1;
        for (int i = 0; i < problem.getVariables().size(); i++) {
            try {
                results.get(resultId).add((Variable) problem.getVariables().get(i).clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }


    private ArrayList<String[][]> convertResultsToListOfMatrix() {

        ArrayList<String[][]> matrixes = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            String[][] solution = new String[problem.getMatrix().get(0).size()][problem.getMatrix().size()];
            matrixes.add(solution);
            for (int j = 0; j < results.get(i).size(); j++) {
                for (int k = 0; k < results.get(i).get(j).getValue().length(); k++) {
                    String val = Character.toString(results.get(i).get(j).getValue().charAt(k));
                    matrixes.get(i)[results.get(i).get(j).getxIndex().get(k)][results.get(i).get(j).getyIndex().get(k)] = val;
                }
            }
            for (int q = 0; q < matrixes.get(i).length; q++) {
                for (int w = 0; w < matrixes.get(i)[q].length; w++) {
                    if (matrixes.get(i)[q][w] == null) {
                        matrixes.get(i)[q][w] = "#";
                    }
                }

            }
        }

        return matrixes;

    }

}
