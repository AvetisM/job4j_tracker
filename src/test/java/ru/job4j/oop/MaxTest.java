package ru.job4j.oop;

import org.junit.Assert;
import org.junit.Test;

public class MaxTest {

    @Test
    public void maxOf3() {
        int first = 6;
        int second = 10;
        int third = 15;
        int expected = 15;
        int rsl = Max.max(first, second, third);
        Assert.assertEquals(expected, rsl);
    }

    @Test
    public void maxOf4() {
        int first = 6;
        int second = 10;
        int third = 15;
        int fourth = 20;
        int expected = 20;
        int rsl = Max.max(first, second, third, fourth);
        Assert.assertEquals(expected, rsl);
    }
}