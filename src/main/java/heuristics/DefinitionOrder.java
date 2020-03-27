package heuristics;

import java.util.List;

public class DefinitionOrder<T> extends Heuristic<T> {


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
