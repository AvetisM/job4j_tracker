package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void whenSortIncrease() {
        List<Item> items = Arrays.asList(
                new Item("Fix bugs", 4),
                new Item("Impl task", 2),
                new Item("Reboot server", 1));

        Collections.sort(items);
        assertThat(items.get(0).getId(), is(1));
    }

    @Test
    public void whenSortDecrease() {
        List<Item> items = Arrays.asList(
                new Item("Fix bugs", 1),
                new Item("Impl task", 5),
                new Item("Reboot server", 12));

        Collections.sort(items, Collections.reverseOrder());
        assertThat(items.get(0).getId(), is(12));
    }

    @Test
    public void whenSortByNameIncrease() {
        List<Item> items = Arrays.asList(
                new Item("Fix bugs", 4),
                new Item("Impl task", 2),
                new Item("Reboot server", 1));

        Collections.sort(items, new ItemSortByNameIncrease());
        assertThat(items.get(0).getName(), is("Fix bugs"));
    }

    @Test
    public void whenSortByNameDecrease() {
        List<Item> items = Arrays.asList(
                new Item("Fix bugs", 4),
                new Item("Impl task", 2),
                new Item("Reboot server", 1));

        Collections.sort(items, new ItemSortByNameDecrease());
        assertThat(items.get(0).getName(), is("Reboot server"));
    }
}