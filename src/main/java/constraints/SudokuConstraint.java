package constraints;

import variables.Variable;

import java.util.ArrayList;
import java.util.Collections;

public class SudokuConstraint extends Constraint {

    @Override
    public boolean check(Variable var, ArrayList<Variable> variables) {

        ArrayList<String> vertical = new ArrayList<>();
        ArrayList<String> horizontal = new ArrayList<>();
        ArrayList<String> square = new ArrayList<>();

        variables.stream().filter(v -> v.getxIndex().get(0).equals(var.getxIndex().get(0))).forEach(v -> vertical.add(v.getValue()));
        variables.stream().filter(v -> v.getyIndex().get(0).equals(var.getyIndex().get(0))).forEach(v -> horizontal.add(v.getValue()));

        int squareNumberX = var.getxIndex().get(0) / 3;
        int squareNumberY = var.getyIndex().get(0) / 3;

        variables.stream().filter(v -> v.getxIndex().get(0) >= squareNumberX * 3).filter(v -> v.getxIndex().get(0) < squareNumberX * 3 + 3)
                .filter(v -> v.getyIndex().get(0) >= squareNumberY * 3).filter(v -> v.getyIndex().get(0) < squareNumberY * 3 + 3).forEach(v -> square.add(v.getValue()));

        int occurrencesV;
        int occurrencesH;
        int occurrencesS;
        for (int i = 1; i <= 9; i++) {
            occurrencesV = Collections.frequency(vertical, Integer.toString(i));
            occurrencesH = Collections.frequency(horizontal, Integer.toString(i));
            occurrencesS = Collections.frequency(square, Integer.toString(i));
            if (occurrencesH > 1 || occurrencesV > 1 || occurrencesS > 1) {
                return false;
            }
        }
        return true;

    }
}
