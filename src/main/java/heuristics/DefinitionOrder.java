package heuristics;

import problems.CspProblem;

import java.util.List;

public class DefinitionOrder<T> extends Heuristic<T> {


    public DefinitionOrder(CspProblem problem) {
        super(problem);
    }

    @Override
    public T getNext(T prev, List<T> list) {

        if (prev == null) {
            return list.get(0);
        } else if (list.indexOf(prev) == list.size() - 1) {
            return null;
        } else {
            return list.get(list.indexOf(prev) + 1);
        }
    }

}
