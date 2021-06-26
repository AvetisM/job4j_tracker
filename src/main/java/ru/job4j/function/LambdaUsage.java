package ru.job4j.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaUsage {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("Абрикос", "Арбуз", "Аккордеон");

        Comparator<String> cmpText = (left, right) -> {
            System.out.println("compare - " + left + " : " + right);
           return left.compareTo(right);
        };

        list.sort(cmpText);

        Comparator<String> cmpDescSize = (left, right) -> {
            System.out.println("compare - " + right.length() + " : " + left.length());
            return Integer.compare(right.length(), left.length());
        };

        list.sort(cmpDescSize);

    }
}
