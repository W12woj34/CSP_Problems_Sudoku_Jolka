package algorithms;

import heuristics.Heuristic;
import problems.CspProblem;
import utils.ResultData;
import variables.Variable;

import java.util.ArrayList;


public abstract class Algorithm {

    protected Heuristic<Variable> chooseNextVariable;
    protected Heuristic<String> chooseNextValue;
    protected ArrayList<ArrayList<Variable>> results;

    public Algorithm(CspProblem problem, Heuristic<Variable> chooseNextVariable, Heuristic<String> chooseNextValue) {
        this.chooseNextVariable = chooseNextVariable;
        this.chooseNextValue = chooseNextValue;
        this.problem = problem;
        this.results = new ArrayList<>();
    }

    CspProblem problem;

    public abstract ResultData solveProblem();

    public Heuristic<Variable> getChooseNextVariable() {
        return chooseNextVariable;
    }

    public void setChooseNextVariable(Heuristic<Variable> chooseNextVariable) {
        this.chooseNextVariable = chooseNextVariable;
    }

    public Heuristic<String> getChooseNextValue() {
        return chooseNextValue;
    }

    public void setChooseNextValue(Heuristic<String> chooseNextValue) {
        this.chooseNextValue = chooseNextValue;
    }

    public CspProblem getProblem() {
        return problem;
    }

    public void setProblem(CspProblem problem) {
        this.problem = problem;
    }

    protected void saveResult() {
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


    protected ArrayList<String[][]> convertResultsToListOfMatrix() {

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

    protected void setResultDataInitParams(ResultData result){
        result.setName(problem.getName());
        result.setAlgorithm(this.getClass().toString().replace("class algorithms.", " "));
        result.setVariableHeuristic(this.chooseNextVariable.getClass().toString().replace("class heuristics.", " "));
        result.setValueHeuristic(this.chooseNextValue.getClass().toString().replace("class heuristics.", " "));
    }
}
