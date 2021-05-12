package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UniqueTextTest {
    @Test
    public void isEquals() {
        String origin = "My cat eats a mouse and milk";
        String text = "My cat eats a milk and mouse";
        assertThat(UniqueText.isEquals(origin, text), is(true));
    }

    @Test
    public void isNotEquals() {
        String origin = "My cat eats a mouse";
        String text = "A mouse is eaten by a cat";
        assertThat(UniqueText.isEquals(origin, text), is(false));
    }

}