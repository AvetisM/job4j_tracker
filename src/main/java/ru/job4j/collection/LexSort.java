package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String left, String right) {

        Integer leftInt = Integer.parseInt(left.replaceAll("[^0-9]", ""));
        Integer rightInt = Integer.parseInt(right.replaceAll("[^0-9]", ""));

        return leftInt.compareTo(rightInt);

    }
}
