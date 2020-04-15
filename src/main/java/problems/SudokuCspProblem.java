package problems;

import constraints.Constraint;
import constraints.SudokuConstraint;
import variables.Variable;

import java.util.ArrayList;
import java.util.List;

import static constances.Constances.SUDOKU_NUMBER;


public class SudokuCspProblem extends CspProblem {

    public SudokuCspProblem(ArrayList<ArrayList<String>> matrix) {

        super(matrix);
        constraints = new SudokuConstraint();
        defineVariables();
        name = "Sudoku_" + SUDOKU_NUMBER;

    }

    @Override
    protected void defineVariables() {

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {

                ArrayList<String> domain = new ArrayList<>();
                if (matrix.get(i).get(j).equals(".")) {
                    List<Integer> x = new ArrayList<>();
                    x.add(j);
                    List<Integer> y = new ArrayList<>();
                    y.add(i);
                    domain.add("1");
                    domain.add("2");
                    domain.add("3");
                    domain.add("4");
                    domain.add("5");
                    domain.add("6");
                    domain.add("7");
                    domain.add("8");
                    domain.add("9");
                    variables.add(new Variable(domain, "", x, y, ""));
                } else {
                    domain.add(matrix.get(i).get(j));
                    List<Integer> x = new ArrayList<>();
                    x.add(j);
                    List<Integer> y = new ArrayList<>();
                    y.add(i);
                    variables.add(new Variable(domain, "", x, y, ""));
                }

            }
        }

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
