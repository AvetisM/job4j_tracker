package ru.job4j.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Lambda {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("Абрикос", "Арбуз", "Аккордеон");

        Comparator<String> cmpText = (left, right) -> left.compareTo(right);

        list.sort(cmpText);
        for (String s : list) {
                  System.out.println(s);
        }

        Comparator<String> cmpDescSize = (left, right) -> right.length() - left.length();

        list.sort(cmpDescSize);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
