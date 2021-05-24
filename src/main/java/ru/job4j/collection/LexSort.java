package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {

        String[] leftArray = left.split("\\.");
        String[] rightArray = right.split("\\.");

        Integer leftInt = Integer.parseInt(leftArray[0]);
        Integer rightInt = Integer.parseInt(rightArray[0]);

        return leftInt.compareTo(rightInt);

    }
}
