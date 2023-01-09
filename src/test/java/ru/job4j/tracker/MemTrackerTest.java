package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MemTrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        MemTracker memTracker = new MemTracker();
        Item item = new Item();
        item.setName("test1");
        memTracker.add(item);
        Item result = memTracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplace() {
        MemTracker memTracker = new MemTracker();
        Item bug = new Item("Bug");
        memTracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        memTracker.replace(id, bugWithDesc);
        assertThat(memTracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        MemTracker memTracker = new MemTracker();
        Item bug = new Item("Bug");
        memTracker.add(bug);
        int id = bug.getId();
        memTracker.delete(id);
        assertThat(memTracker.findById(id), is(nullValue()));
    }

    @Test
    public void whenDeleteMockito() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item deleteItem = new Item("Delete item");
        tracker.add(deleteItem);
        DeleteItem del = new DeleteItem(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);
        del.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(),
                is("=== Delete item ====" + ln + "Заявка удалена успешно." + ln));
        assertThat(tracker.findById(deleteItem.getId()), is(nullValue()));
    }

    @Test
    public void whenFindByIdMockito() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item newItem = new Item("New item");
        tracker.add(newItem);
        FindItemById find = new FindItemById(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(newItem.getId());
        find.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by id ====" + ln + newItem + ln));
    }

    @Test
    public void whenFindByNameMockito() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item newItem = new Item("New item");
        tracker.add(newItem);
        FindItemByName find = new FindItemByName(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(newItem.getName());
        find.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by name ====" + ln + newItem + ln));
    }
}