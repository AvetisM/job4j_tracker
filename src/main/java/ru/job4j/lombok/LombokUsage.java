package ru.job4j.lombok;

import java.util.Arrays;

public class LombokUsage {

    public static void main(String[] args) {

        var permission = Permission.of()
                .id(1)
                .name("ADMIN")
                .rules(Arrays.asList("rule1", "rule1", "rule3"))
                .build();
        System.out.println(permission);

    }
}
