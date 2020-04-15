package algorithms;

import heuristics.Heuristic;
import problems.CspProblem;
import utils.ResultData;
import variables.Variable;


import java.util.Stack;

public class BacktrackAlgorithm extends Algorithm {

    private int searchedNodes;
    private int backtracks;
    private Stack<Variable> checkedVariables;
    private long startTime = 0;
    ResultData result = new ResultData();

    public BacktrackAlgorithm(CspProblem problem, Heuristic<Variable> chooseNextVariable, Heuristic<String> chooseNextValue) {
        super(problem, chooseNextVariable, chooseNextValue);
        searchedNodes = 0;
        backtracks = 0;
        checkedVariables = new Stack<>();
    }

    @Override
    public ResultData solveProblem() {

        setResultDataInitParams(result);
        startTime = System.currentTimeMillis();
        run(result);
        result.setResults(convertResultsToListOfMatrix());
        return result;
    }

    private void run(ResultData result) {

        boolean notEnd = true;
        Variable var = null;
        String value;
        while (notEnd) {

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
                        notEnd = false;
                        break;

                    } else {
                        var.setValue(var.getInitValue());
                        var.setSet(false);
                        var = checkedVariables.pop();

                    }
                } else {
                    var.setValue(value);
                    boolean good = problem.checkConstraints(var);
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


}
