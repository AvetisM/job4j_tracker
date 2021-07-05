package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {

        ArrayList<Person> result = new ArrayList<>();

        Predicate<Person> pName = p -> p.getName().contains(key);
        Predicate<Person> pSurname = p -> p.getSurname().contains(key);
        Predicate<Person> pPhone = p -> p.getPhone().contains(key);
        Predicate<Person> pAddress = p -> p.getAddress().contains(key);

        Predicate<Person> combine = pName.or(pSurname.or(pPhone.or(pAddress)));

        for (Person person:persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
