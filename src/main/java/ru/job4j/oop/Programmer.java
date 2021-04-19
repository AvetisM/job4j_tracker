package ru.job4j.oop;

import java.util.Date;

public class Programmer extends Engineer {
    private String programmingLanguage;

    public Programmer() {

    }

    public Programmer(String name, String surname, String education, Date birthday, String lastProject, String programmingLanguage) {
        super(name, surname, education, birthday, lastProject);
        this.programmingLanguage = programmingLanguage;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }
}
