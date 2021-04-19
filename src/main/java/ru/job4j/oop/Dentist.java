package ru.job4j.oop;

import java.util.Date;

public class Dentist extends Doctor {
    private boolean adult;

    public Dentist() {

    }

    public Dentist(String name, String surname, String education, Date birthday, Diagnosis diagnosis, boolean adult) {
        super(name, surname, education, birthday, diagnosis);
        this.adult = adult;
    }

    public boolean getAdult() {
        return adult;
    }
}
