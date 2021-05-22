package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int rls = 0;
        int maxLength = Math.max(o1.length(), o2.length());

        for (int index = 0; index < maxLength; index++) {
            int o1Index = index < o1.length() ? index : o1.length() - 1;
            int o2Index = index < o2.length() ? index : o2.length() - 1;

            rls = Integer.compare(o1Index, o2Index);
            if (rls != 0) {
                break;
            }
            rls = Character.compare(o1.charAt(index), o2.charAt(index));
            if (rls != 0) {
                break;
            }
        }
        return rls;
    }
}
