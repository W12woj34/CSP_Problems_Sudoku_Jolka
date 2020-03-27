package problems;

import constraints.Constraint;
import variables.Variable;

import java.util.ArrayList;

public abstract class CspProblem {
    protected String name;
    protected ArrayList<ArrayList<String>> matrix;
    protected ArrayList<Variable> variables;
    protected Constraint constraints;

    public CspProblem(ArrayList<ArrayList<String>> matrix) {
        variables = new ArrayList<>();
        this.matrix = matrix;
    }

    protected abstract void defineVariables();

    public boolean checkConstraints(Variable var, ArrayList<Variable> variables) {

        return constraints.check(var, variables);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ArrayList<String>> getMatrix() {
        return matrix;
    }

    public void setMatrix(ArrayList<ArrayList<String>> matrix) {
        this.matrix = matrix;
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<Variable> variables) {
        this.variables = variables;
    }

    public Constraint getConstraints() {
        return constraints;
    }

    public void setConstraints(Constraint constraints) {
        this.constraints = constraints;
    }
}
