package constraints;

import variables.Variable;

import java.util.ArrayList;

public abstract class Constraint {

    public abstract boolean check(Variable var, ArrayList<Variable> variables);
}
