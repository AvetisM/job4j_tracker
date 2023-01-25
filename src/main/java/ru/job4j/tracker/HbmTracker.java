package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Timestamp;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {

    private static final String REPLACE_ITEM =
            "UPDATE items SET name = :fName, created = :fCreated WHERE id = :fId";
    private static final String DELETE_ITEM = "DELETE Item WHERE id = :fId";
    private static final String FIND_ALL_ITEMS = "FROM Item";
    private static final String FIND_ITEM_BY_NAME = "FROM Item as i WHERE i.name like :fName";
    private static final String FIND_BY_ITEM_ID = "FROM Item as i WHERE i.id = :fId";

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        try {
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rls = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(REPLACE_ITEM)
                    .setParameter("fId", id)
                    .setParameter("fName", item.getName())
                    .setParameter("fCreated", Timestamp.valueOf(item.getCreated()))
                    .executeUpdate();
            session.getTransaction().commit();
            rls = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
        return rls;
    }

    @Override
    public boolean delete(int id) {
        boolean rls = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(DELETE_ITEM)
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            rls = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
        return rls;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        Query<Item> query = session.createQuery(FIND_ALL_ITEMS, Item.class);
        List<Item> rls = query.list();
        session.close();
        return rls;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        Query<Item> query = session.createQuery(FIND_ITEM_BY_NAME, Item.class);
        query.setParameter("fName", "%" + key + "%");
        List<Item> rls = query.list();
        session.close();
        return rls;
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        Query<Item> query = session.createQuery(FIND_BY_ITEM_ID, Item.class);
        query.setParameter("fId", id);
        Item rls = query.uniqueResult();
        session.close();
        return rls;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
