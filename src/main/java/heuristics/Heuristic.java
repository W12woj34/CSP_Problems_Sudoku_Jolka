package heuristics;

import problems.CspProblem;

import java.util.List;

public abstract class Heuristic<T> {

    CspProblem problem;

    public Heuristic(CspProblem problem) {
        this.problem = problem;
    }

    public abstract T getNext(T prev, List<T> list);
}
