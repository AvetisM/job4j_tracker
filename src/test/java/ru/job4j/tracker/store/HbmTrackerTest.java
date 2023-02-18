package ru.job4j.tracker.store;
import org.hamcrest.core.IsNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import ru.job4j.tracker.HbmTracker;
import ru.job4j.tracker.Item;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HbmTrackerTest {

    private static StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private static SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @AfterClass
    public static void closeConnection() {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @After
    public void wipeTable() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("delete from Item")
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(item.getName()));
        }
    }

    @Test
    public void whenReplaceItem() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            item.setName("test2");
            tracker.replace(item.getId(), item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(item.getName()));
        }
    }

    @Test
    public void whenDeleteItem() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            tracker.delete(item.getId());
            Item result = tracker.findById(item.getId());
            assertThat(result, is(IsNull.nullValue()));
        }
    }

    @Test
    public void whenSaveItemAndFindName() {
        try (var tracker = new HbmTracker()) {
            Item item = tracker.add(new Item("first"));
            tracker.add(new Item("second"));
            tracker.add(new Item("third"));
            List<Item> expected = List.of(item);
            assertThat(tracker.findByName("first"), is(expected));
        }
    }

    @Test
    public void whenSaveItemAndFindAll() {
        try (var tracker = new HbmTracker()) {
            Item item = tracker.add(new Item("itemTest"));
            Item item1 = tracker.add(new Item("itemTest1"));
            Item item2 = tracker.add(new Item("itemTest2"));
            assertThat(tracker.findAll(), is(List.of(item, item1, item2)));
        }
    }
}