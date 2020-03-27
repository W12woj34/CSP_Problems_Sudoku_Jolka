package constraints;

import variables.Variable;

import java.util.ArrayList;

public class JolkaConstraint extends Constraint {

    @Override
    public boolean check(Variable var, ArrayList<Variable> variables) {

        for (int i = 0; i < var.getxIndex().size(); i++) {

            for (int j = 0; j < variables.size(); j++) {

                for (int k = 0; k < variables.get(j).getxIndex().size(); k++) {

                    if (variables.get(j).getxIndex().get(k).equals(var.getxIndex().get(i)) && variables.get(j).getyIndex().get(k).equals(var.getyIndex().get(i))) {

                        if (variables.get(j).getValue().charAt(k) != var.getValue().charAt(i) && variables.get(j).getValue().charAt(k) != '_') {
                            return false;
                        }
                    }

                }

            }

        }

        return true;
    }
}
