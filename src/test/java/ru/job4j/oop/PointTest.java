package ru.job4j.oop;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

public class PointTest {

    @Test
    public void when283() {
        Point a = new Point(0, 0, 0);
        Point b = new Point(0, 2, 2);
        double expected =  2.829;
        double rsl = a.distanse3d(b);
        assertThat(rsl, closeTo(expected, 0.001));
    }

    @Test
    public void when5197() {
        Point a = new Point(1, 1, 1);
        Point b = new Point(4, 4, 4);
        double expected = 5.197;
        double rsl = a.distanse3d(b);
        assertThat(rsl, closeTo(expected, 0.001));
    }
}