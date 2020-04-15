package heuristics;


import problems.CspProblem;
import variables.Variable;

public class DomainSizeOrderVarOnly<T extends Variable> extends DefinitionOrder<T> {

    public DomainSizeOrderVarOnly(CspProblem problem) {
        super(problem);
        problem.getVariables().sort(Variable.SortByDomainSize);
    }
}