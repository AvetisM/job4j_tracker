package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int rls = 0;
        int minLength = Math.min(o1.length(), o2.length());

        for (int index = 0; index < minLength; index++) {
            rls = Character.compare(o1.charAt(index), o2.charAt(index));
            if (rls != 0) {
                break;
            }
        }
        if (rls == 0) {
            rls = Integer.compare(o1.length(), o2.length());
        }
        return rls;
    }
}
