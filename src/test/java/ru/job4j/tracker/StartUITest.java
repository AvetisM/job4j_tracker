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
                new String[]{"0", item.getName(), "1"}
        );
        Output out = new StubOutput();

        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(out),
                new ExitProgram()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[]{"0", Integer.toString(item.getId()), replacedName, "1"}
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
                new String[]{"0", Integer.toString(item.getId()), "1"}
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
        assertThat(out.toString(), is("Menu:" + System.lineSeparator()
                + "0. Show all items" + System.lineSeparator()
                + "1. Exit Program" + System.lineSeparator()
                + "=== Show all items ===" + System.lineSeparator()
                + item + System.lineSeparator()
                + "Menu:" + System.lineSeparator()
                + "0. Show all items" + System.lineSeparator()
                + "1. Exit Program" + System.lineSeparator()));
    }

    @Test
    public void whenFindItemById() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("New item"));
        Input in = new StubInput(
                new String[]{"0", Integer.toString(item.getId()), "1"}
        );
        Output out = new StubOutput();
        UserAction[] actions = {
                new FindItemById(out),
                new ExitProgram()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is("Menu:" + System.lineSeparator()
                + "0. Find item by id" + System.lineSeparator()
                + "1. Exit Program" + System.lineSeparator()
                + "=== Find item by id ====" + System.lineSeparator()
                + item + System.lineSeparator()
                + "Menu:" + System.lineSeparator()
                + "0. Find item by id" + System.lineSeparator()
                + "1. Exit Program" + System.lineSeparator()));
    }

    @Test
    public void whenFindItemByName() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("New item"));
        Input in = new StubInput(
                new String[]{"0", item.getName(), "1"}
        );
        Output out = new StubOutput();
        UserAction[] actions = {
                new FindItemByName(out),
                new ExitProgram()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is("Menu:" + System.lineSeparator()
                + "0. Find items by name" + System.lineSeparator()
                + "1. Exit Program" + System.lineSeparator()
                + "=== Find items by name ====" + System.lineSeparator()
                + item + System.lineSeparator()
                + "Menu:" + System.lineSeparator()
                + "0. Find items by name" + System.lineSeparator()
                + "1. Exit Program" + System.lineSeparator()));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"-1", "0"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = new UserAction[]{
                new ExitProgram()
        };
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Exit Program" + ln
                        + "Wrong input, you can select: 0 .. 0" + ln
                        + "Menu:" + ln
                        + "0. Exit Program" + ln
                )
        );
    }
}