package heuristics;

import java.util.List;

public abstract class Heuristic<T> {

    public abstract T getNext(T prev, List<T> list);
}
