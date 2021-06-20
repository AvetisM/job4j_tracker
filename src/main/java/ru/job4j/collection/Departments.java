package ru.job4j.collection;

import java.util.*;

public class Departments {

    public static List<String> fillGaps(List<String> deps) {
        Set<String> tmp = new LinkedHashSet<>();
        for (String value : deps) {
            String start = "";
            for (String el : value.split("/")) {
                start = start.equals("") ? el : start + "/" + el;
                tmp.add(start);
            }
        }
        return new ArrayList<>(tmp);
    }

    public static void sortAsc(List<String> orgs) {
        Comparator<String> cmpDepAscComp = new DepAscComp();
        orgs.sort(cmpDepAscComp);
    }

    public static void sortDesc(List<String> orgs) {
        Comparator<String> cmpDepDescComp = new DepDescComp();
        orgs.sort(cmpDepDescComp);
    }
}