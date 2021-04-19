package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFullName("Mkhitaryants Avetis");
        student.setGroup("3344");
        student.setEntryDate(new Date());

        System.out.println(student.getFullName() + " " + student.getGroup()
                + " " + student.getEntryDate());
    }
}
