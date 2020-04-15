package algorithms;

import heuristics.Heuristic;
import problems.CspProblem;
import utils.ResultData;
import variables.Variable;

import java.util.*;

public class ForwardCheckAlgorithm extends Algorithm {

    private int searchedNodes;
    private int backtracks;
    private Stack<Variable> checkedVariables;
    private long startTime = 0;
    private Map<Variable, Map<Variable, List<String>>> deletedFromDomainsByVariable;
    ResultData result = new ResultData();

    public ForwardCheckAlgorithm(CspProblem problem, Heuristic<Variable> chooseNextVariable, Heuristic<String> chooseNextValue) {
        super(problem, chooseNextVariable, chooseNextValue);
        searchedNodes = 0;
        backtracks = 0;
        checkedVariables = new Stack<>();
        deletedFromDomainsByVariable = new HashMap<>();
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
                    result.setBacktrackFirst(backtracks);
                    result.setNodesFirst(searchedNodes);

                }

                var = checkedVariables.pop();

            }


            while (true) {
                Collections.sort(var.getDomain());
                value = chooseNextValue.getNext(var.getValue(), var.getDomain());
                searchedNodes++;

                if (value == null) {
                    backtracks++;
                    if (checkedVariables.empty()) {
                        result.setTimeAll((System.currentTimeMillis() - startTime) / 1000.0);
                        result.setBacktrackAll(backtracks);
                        result.setNodesAll(searchedNodes);
                        notEnd = false;
                        break;

                    } else {
                        var.setValue(var.getInitValue());
                        var.setSet(false);
                        var = checkedVariables.pop();
                        backDomains(var);

                    }
                } else {
                    var.setValue(value);
                    updateDomains(var);
                    boolean good = checkDomains();
                    if (good) {
                        var.setSet(true);
                        checkedVariables.push(var);

                        break;

                    } else {

                        backtracks++;

                        backDomains(var);
                    }
                }
            }
        }


    }

    private boolean checkDomains() {
        for (Variable var : problem.getVariables()) {
            if (var.getDomain().isEmpty()) {
                return false;
            }
        }

        return true;
    }

    private void updateDomains(Variable var) {

        deletedFromDomainsByVariable.put(var, new HashMap<>());

        for (Variable variable : problem.getVariables()) {

            if (variable != var) {


                deletedFromDomainsByVariable.get(var).put(variable, new LinkedList<>());

                List<String> toRemove = new LinkedList<>();
                for (String value : variable.getDomain()) {

                    String currentValue = variable.getValue();
                    variable.setValue(value);

                    if (!problem.checkConstraints(variable)) {

                        deletedFromDomainsByVariable.get(var).get(variable).add(value);
                        toRemove.add(value);
                    }

                    variable.setValue(currentValue);

                }

                for (String value : toRemove) {
                    variable.getDomain().remove(value);
                }

            }

        }

    }

    private void backDomains(Variable var) {

        Map<Variable, List<String>> variablesToUpdate = deletedFromDomainsByVariable.get(var);


        for (Map.Entry<Variable, List<String>> entry : variablesToUpdate.entrySet()) {

            for (String value : entry.getValue()) {

                entry.getKey().getDomain().add(value);

            }
        }

        deletedFromDomainsByVariable.remove(var);


    }

}
