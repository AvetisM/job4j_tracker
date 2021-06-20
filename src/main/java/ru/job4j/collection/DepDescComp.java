package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {

        String[] o1Array = o1.split("/");
        String[] o2Array = o2.split("/");

        int res = o2Array[0].compareTo(o1Array[0]);

        if (res != 0) {
            return res;
        }

        int length = Math.max(o1Array.length, o2Array.length);

        for (int i = 1; i < length; i++) {
            String val1 = o1Array.length - 1 < i ? " " : o1Array[i];
            String val2 = o2Array.length - 1 < i ? " " : o2Array[i];
            res = val1.compareTo(val2);
            if (res != 0) {
                break;
            }
        }
        return res;
    }
}