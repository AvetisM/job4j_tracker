package ru.job4j.tracker.store;

import org.hamcrest.core.IsNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.SqlTracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in =
                     SqlTrackerTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenReplaceItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item itemFirst = new Item("itemFirst");
        Item itemSecond = new Item("itemSecond");
        tracker.add(itemFirst);
        int id = itemFirst.getId();
        tracker.replace(id, itemSecond);
        assertThat(tracker.findById(id).getName(), is("itemSecond"));
    }

    @Test
    public void whenDeleteItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("itemDelete");
        tracker.add(item);
        int id = item.getId();
        tracker.delete(id);
        assertThat(tracker.findById(item.getId()), is(IsNull.nullValue()));
    }

    @Test
    public void whenSaveItemAndFindName() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("itemTest"));
        tracker.add(new Item("itemTest1"));
        tracker.add(new Item("itemTest2"));
        assertThat(tracker.findByName("itemTest"), is(List.of(item)));
    }

    @Test
    public void whenSaveItemAndFindAll() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("itemTest"));
        Item item1 = tracker.add(new Item("itemTest1"));
        Item item2 = tracker.add(new Item("itemTest2"));
        assertThat(tracker.findAll(), is(List.of(item, item1, item2)));
    }
}