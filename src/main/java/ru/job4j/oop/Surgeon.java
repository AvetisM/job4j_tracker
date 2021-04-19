package ru.job4j.oop;

import java.util.Date;

public class Surgeon extends Doctor {
    private int numberOfOperations;

    public Surgeon() {

    }

    public Surgeon(String name, String surname, String education, Date birthday, Diagnosis diagnosis, int numberOfOperations) {
        super(name, surname, education, birthday, diagnosis);
        this.numberOfOperations = numberOfOperations;
    }

    public int getNumberOfOperations() {
        return numberOfOperations;
    }
}
