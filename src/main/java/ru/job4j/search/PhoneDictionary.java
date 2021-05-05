package ru.job4j.search;

import java.util.ArrayList;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        ArrayList<Person> result = new ArrayList<>();

        StringBuilder stringBuilder = new StringBuilder();
        for (Person person:persons) {
            stringBuilder.append(person.getName());
            stringBuilder.append(person.getAddress());
            stringBuilder.append(person.getSurname());
            stringBuilder.append(person.getPhone());
            if (stringBuilder.toString().contains(key)) {
                result.add(person);
            }
            stringBuilder.setLength(0);
        }
        return result;
    }
}
