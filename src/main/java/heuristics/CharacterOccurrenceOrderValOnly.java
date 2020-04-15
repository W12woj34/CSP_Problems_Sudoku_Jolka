package heuristics;

import javafx.util.Pair;
import problems.CspProblem;

import java.util.*;
import java.util.stream.Collectors;

public class CharacterOccurrenceOrderValOnly<T extends String> extends Heuristic<T> {

    List<String> words;
    Map<Character, Long> charsCounts;

    public CharacterOccurrenceOrderValOnly(CspProblem problem, List<String> words) {
        super(problem);
        this.words = words;
        List<Character> chars = new ArrayList<>();

        for (String string : words) {
            for (int i = 0; i < string.length(); i++) {
                chars.add(string.charAt(i));
            }
        }

        charsCounts =
                chars.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

    }

    @Override
    public T getNext(T prev, List<T> list) {


        List<Long> occurrencesInList = new LinkedList<>();
        long occurrences;
        for (T string : list) {
            occurrences = 0L;
            for (int i = 0; i < string.length(); i++) {
                occurrences += occurrences + charsCounts.get(string.charAt(i));
            }

            occurrencesInList.add(occurrences);
        }

        List<Pair<T, Long>> valuesWithOccurrences = new LinkedList<>();

        for (int i = 0; i < list.size(); i++) {
            valuesWithOccurrences.add(new Pair<>(list.get(i), occurrencesInList.get(i)));

        }

        valuesWithOccurrences.sort(Comparator.comparing(p -> -p.getValue()));

        List<T> resultList = new LinkedList<>();


        for (Pair<T, Long> pair : valuesWithOccurrences) {
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
