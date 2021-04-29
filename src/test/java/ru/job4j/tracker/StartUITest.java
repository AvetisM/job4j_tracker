package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class StartUITest {
    @Test
    public void whenCreateItem() {
        Item item = new Item("Item name");
        Input in = new StubInput(
                new String[] {"0", item.getName(), "1"}
        );
        Output out = new StubOutput();

        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(out),
                new ExitProgram()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[] {"0", Integer.toString(item.getId()), replacedName, "1"}
        );
        Output out = new StubOutput();
        UserAction[] actions = {
                new ReplaceItem(out),
                new ExitProgram()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[] {"0", Integer.toString(item.getId()), "1"}
        );
        Output out = new StubOutput();
        UserAction[] actions = {
                new DeleteItem(out),
                new ExitProgram()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenShowAllItems() {
        Item item = new Item("New item");
        Tracker tracker = new Tracker();
        tracker.add(item);
        Input in = new StubInput(
                new String[]{"0", "1"}
        );
        Output out = new StubOutput();
        UserAction[] actions = {
                new ShowAllItems(out),
                new ExitProgram()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is("Menu:\r\n"
                + "0. Show all items\r\n"
                + "1. Exit Program\r\n"
                + "=== Show all items ===\r\n"
                + item + "\r\n"
                + "Menu:\r\n"
                + "0. Show all items\r\n"
                + "1. Exit Program\r\n"));
    }

    @Test
    public void whenFindItemById() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("New item"));
        Input in = new StubInput(
                new String[] {"0", Integer.toString(item.getId()), "1"}
        );
        Output out = new StubOutput();
        UserAction[] actions = {
                new FindItemById(out),
                new ExitProgram()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is("Menu:\r\n"
                + "0. Find item by id\r\n"
                + "1. Exit Program\r\n"
                + "=== Find item by id ====\r\n"
                + item + "\r\n"
                + "Menu:\r\n"
                + "0. Find item by id\r\n"
                + "1. Exit Program\r\n"));
    }

    @Test
    public void whenFindItemByName() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("New item"));
        Input in = new StubInput(
                new String[] {"0", item.getName(), "1"}
        );
        Output out = new StubOutput();
        UserAction[] actions = {
                new FindItemByName(out),
                new ExitProgram()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is("Menu:\r\n"
                + "0. Find items by name\r\n"
                + "1. Exit Program\r\n"
                + "=== Find items by name ====\r\n"
                + item + "\r\n"
                + "Menu:\r\n"
                + "0. Find items by name\r\n"
                + "1. Exit Program\r\n"));
    }
}