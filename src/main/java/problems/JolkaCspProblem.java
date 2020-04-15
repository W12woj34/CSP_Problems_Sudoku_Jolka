package problems;

import constraints.Constraint;
import constraints.JolkaConstraint;
import variables.Variable;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static constances.Constances.JOLKA_NUMBER;

public class JolkaCspProblem extends CspProblem {

    private ArrayList<String> words;

    public JolkaCspProblem(ArrayList<ArrayList<String>> matrix, ArrayList<String> words) {
        super(matrix);
        this.words = words;
        constraints = new JolkaConstraint();
        defineVariables();
        name = "Jolka_" + JOLKA_NUMBER;

    }

    @Override
    protected void defineVariables() {

        //define horizontal vars
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j).equals("_")) {

                    Variable var = new Variable(new ArrayList<>(), "", new ArrayList<>(), new ArrayList<>(), "");
                    int k = j;
                    for (; k < matrix.get(i).size(); k++) {
                        if (matrix.get(i).get(k).equals("#")) {
                            break;
                        }
                    }
                    int len = k - j;
                    if (len > 1) {
                        var.getDomain().addAll(words.stream().filter(w -> w.length() == len).collect(Collectors.toList()));
                        for (int l = 0; l < len; l++) {
                            var.setValue(var.getValue() + "_");
                            var.setInitValue(var.getValue() + "_");
                            var.getyIndex().add(i);
                            var.getxIndex().add(j + l);
                        }
                        variables.add(var);
                    }
                    j = k - 1;

                }
            }
        }

        //define vertical
        ArrayList<ArrayList<String>> matrixT = transposeMatrix(matrix);

        for (int i = 0; i < matrixT.size(); i++) {
            for (int j = 0; j < matrixT.get(i).size(); j++) {
                if (matrixT.get(i).get(j).equals("_")) {

                    Variable var = new Variable(new ArrayList<>(), "", new ArrayList<>(), new ArrayList<>(), "");
                    int k = j;
                    for (; k < matrixT.get(i).size(); k++) {
                        if (matrixT.get(i).get(k).equals("#")) {
                            break;
                        }
                    }
                    int len = k - j;
                    if (len > 1) {
                        var.getDomain().addAll(words.stream().filter(w -> w.length() == len).collect(Collectors.toList()));
                        for (int l = 0; l < len; l++) {
                            var.setValue(var.getValue() + "_");
                            var.setInitValue(var.getValue() + "_");
                            var.getxIndex().add(i);
                            var.getyIndex().add(j + l);
                        }
                        variables.add(var);
                    }
                    j = k - 1;

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

    private ArrayList<ArrayList<String>> transposeMatrix(ArrayList<ArrayList<String>> matrix) {

        ArrayList<ArrayList<String>> matrixT = new ArrayList<>();

        for (int i = 0; i < matrix.get(0).size(); i++) {
            matrixT.add(new ArrayList<>());
        }

        for (ArrayList<String> strings : matrix) {
            for (int j = 0; j < strings.size(); j++) {
                matrixT.get(j).add(strings.get(j));
            }
        }

        return matrixT;
    }


}
