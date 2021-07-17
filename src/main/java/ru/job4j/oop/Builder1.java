package ru.job4j.oop;

import java.util.Date;

public class Builder1 extends Engineer {
    private String typeOfBuilding;

    public Builder1() {

    }

    public Builder1(String name, String surname, String education,
                   Date birthday, String lastProject, String typeOfBuilding) {
        super(name, surname, education, birthday, lastProject);
        this.typeOfBuilding = typeOfBuilding;
    }

    public String getTypeOfBuilding() {
        return typeOfBuilding;
    }
}
