package ru.job4j.oop;

import java.util.Date;

public class Doctor extends Profession {
    private Diagnosis diagnosis;

    public Doctor() {

    }

    public Doctor(String name, String surname, String education,
                               Date birthday, Diagnosis diagnosis) {
        super(name, surname, education, birthday);
        this.diagnosis = diagnosis;
    }

    public Diagnosis heal(Patient patient) {
        return diagnosis;
    }
}
