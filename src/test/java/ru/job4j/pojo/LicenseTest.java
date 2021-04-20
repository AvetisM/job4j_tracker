package ru.job4j.pojo;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LicenseTest {
    @Test
    public void eqName() {

        Date date = new Date();

        License first = new License();
        first.setCode("audio");
        first.setOwner("Avetis");
        first.setModel("Honda");
        first.setCreated(date);

        License second = new License();
        second.setCode("audio");
        second.setOwner("Avetis");
        second.setModel("Honda");
        second.setCreated(date);

        assertThat(first, is(second));
    }
}