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

        MemTracker memTracker = new MemTracker();
        UserAction[] actions = {
                new CreateAction(out),
                new ExitProgram()
        };
        new StartUI(out).init(in, memTracker, actions);
        assertThat(memTracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[]{"0", Integer.toString(item.getId()), replacedName, "1"}
        );
        Output out = new StubOutput();
        UserAction[] actions = {
                new ReplaceItem(out),
                new ExitProgram()
        };
        new StartUI(out).init(in, memTracker, actions);
        assertThat(memTracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[]{"0", Integer.toString(item.getId()), "1"}
        );
        Output out = new StubOutput();
        UserAction[] actions = {
                new DeleteItem(out),
                new ExitProgram()
        };
        new StartUI(out).init(in, memTracker, actions);
        assertThat(memTracker.findById(item.getId()), is(nullValue()));
    }

  @Test
    public void whenShowAllItems() {
        Item item = new Item("New item");
        MemTracker memTracker = new MemTracker();
        memTracker.add(item);
        Input in = new StubInput(
                new String[]{"0", "1"}
        );
        Output out = new StubOutput();
        UserAction[] actions = {
                new ShowAllItems(out),
                new ExitProgram()
        };
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Menu:" + ln
                + "0. Show all items" + ln
                + "1. Exit Program" + ln
                + "=== Show all items ===" + ln
                + item + ln
                + "Menu:" + ln
                + "0. Show all items" + ln
                + "1. Exit Program" + ln));
    }

    @Test
    public void whenFindItemById() {
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("New item"));
        Input in = new StubInput(
                new String[]{"0", Integer.toString(item.getId()), "1"}
        );
        Output out = new StubOutput();
        UserAction[] actions = {
                new FindItemById(out),
                new ExitProgram()
        };
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Menu:" + ln
                + "0. Find item by id" + ln
                + "1. Exit Program" + ln
                + "=== Find item by id ====" + ln
                + item + ln
                + "Menu:" + ln
                + "0. Find item by id" + ln
                + "1. Exit Program" + ln));
    }

    @Test
    public void whenFindItemByName() {
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("New item"));
        Input in = new StubInput(
                new String[]{"0", item.getName(), "1"}
        );
        Output out = new StubOutput();
        UserAction[] actions = {
                new FindItemByName(out),
                new ExitProgram()
        };
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Menu:" + ln
                + "0. Find items by name" + ln
                + "1. Exit Program" + ln
                + "=== Find items by name ====" + ln
                + item + ln
                + "Menu:" + ln
                + "0. Find items by name" + ln
                + "1. Exit Program" + ln));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"-1", "0"}
        );
        MemTracker memTracker = new MemTracker();
        UserAction[] actions = new UserAction[]{
                new ExitProgram()
        };
        new StartUI(out).init(in, memTracker, actions);
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