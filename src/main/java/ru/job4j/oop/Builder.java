package ru.job4j.oop;

import java.util.Date;

public class Builder extends Engineer {
    private String typeOfBuilding;

    public Builder() {

    }

    public Builder(String name, String surname, String education, Date birthday, String lastProject, String typeOfBuilding) {
        super(name, surname, education, birthday, lastProject);
        this.typeOfBuilding = typeOfBuilding;
    }

    public String getTypeOfBuilding() {
        return typeOfBuilding;
    }
}
