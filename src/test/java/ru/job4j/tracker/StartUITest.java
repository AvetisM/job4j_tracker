package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class StartUITest {
    @Test
    public void whenCreateItem() {
        Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        Output out = new StubOutput();

        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(out),
                new ExitProgram()
        };
        new StartUI().init(in, tracker, actions);
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
        new StartUI().init(in, tracker, actions);
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
        new StartUI().init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenShowAllItems() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("New item"));
        Item[] items = {item};
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        Output out = new StubOutput();
        UserAction[] actions = {
                new ShowAllItems(out),
                new ExitProgram()
        };
        new StartUI().init(in, tracker, actions);
        Assert.assertArrayEquals(tracker.findAll(), items);
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
        new StartUI().init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(item.getName()));
    }

    @Test
    public void whenFindItemByName() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("New item"));
        Item[] items = {item};
        Input in = new StubInput(
                new String[] {"0", item.getName(), "1"}
        );
        Output out = new StubOutput();
        UserAction[] actions = {
                new FindItemByName(out),
                new ExitProgram()
        };
        new StartUI().init(in, tracker, actions);
        Assert.assertArrayEquals(tracker.findByName(item.getName()), items);
    }

}