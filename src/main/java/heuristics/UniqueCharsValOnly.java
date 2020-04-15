package heuristics;

import javafx.util.Pair;
import problems.CspProblem;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class UniqueCharsValOnly<T extends String> extends Heuristic<T> {

    public UniqueCharsValOnly(CspProblem problem) {
        super(problem);
    }

    @Override
    public T getNext(T prev, List<T> list) {


        List<Long> distinctInList = new LinkedList<>();
        for (T string : list) {

            StringBuilder sb = new StringBuilder();
            string.chars().distinct().forEach(c -> sb.append((char) c));
            distinctInList.add((long) sb.length());
        }

        List<Pair<T, Long>> valuesWithDistinct = new LinkedList<>();

        for (int i = 0; i < list.size(); i++) {
            valuesWithDistinct.add(new Pair<>(list.get(i), distinctInList.get(i)));

        }

        valuesWithDistinct.sort(Comparator.comparing(Pair::getValue));

        List<T> resultList = new LinkedList<>();


        for (Pair<T, Long> pair : valuesWithDistinct) {
            resultList.add(pair.getKey());
        }


        if (prev == null) {
            return resultList.get(0);
        } else if (resultList.indexOf(prev) == resultList.size() - 1) {
            return null;
        } else {
            return resultList.get(resultList.indexOf(prev) + 1);
        }
    }
}

