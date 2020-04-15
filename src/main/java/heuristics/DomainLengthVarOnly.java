package heuristics;


import problems.CspProblem;
import variables.Variable;

public class DomainLengthVarOnly<T extends Variable> extends DefinitionOrder<T> {

    public DomainLengthVarOnly(CspProblem problem) {
        super(problem);
        problem.getVariables().sort(Variable.SortByDomainLength);
    }
}