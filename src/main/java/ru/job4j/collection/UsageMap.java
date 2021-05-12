package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> emails = new HashMap<>();
        emails.put("ivan@yandex.ru", "Ivan Ivanov");
        emails.put("petr@yandex.ru", "Petr Petrov");
        emails.put("vasiliy@yandex.ru", "Vasiliy Vasiliev");
        for (String key : emails.keySet()) {
            System.out.println(key + " - " + emails.get(key));
        }
    }
}

