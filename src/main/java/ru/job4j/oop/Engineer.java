package ru.job4j.oop;

import java.util.Date;

public class Engineer extends Profession {
    private String lastProject;

    public Engineer() {

    }

    public Engineer(String name, String surname, String education,
                                 Date birthday, String lastProject) {
        super(name, surname, education, birthday);
        this.lastProject = lastProject;
    }

    public String getLastProject() {
        return lastProject;
    }

    public void makeProject() {

    }
}
