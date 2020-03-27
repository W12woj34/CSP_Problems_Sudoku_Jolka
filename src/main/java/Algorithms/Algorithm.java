package Algorithms;

import heuristics.Heuristic;
import problems.CspProblem;
import utils.ResultData;
import variables.Variable;

import java.util.ArrayList;

public abstract class Algorithm {

    protected Heuristic<Variable> chooseNextVariable;
    protected Heuristic<String> chooseNextValue;

    public Algorithm(CspProblem problem, Heuristic<Variable> chooseNextVariable, Heuristic<String> chooseNextValue) {
        this.chooseNextVariable = chooseNextVariable;
        this.chooseNextValue = chooseNextValue;
        this.problem = problem;
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
}
