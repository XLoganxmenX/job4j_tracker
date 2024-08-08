package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import java.util.List;

public class HbmTracker implements Store {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                session.save(item);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
            return item;
        }
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                Query query = session.createQuery("UPDATE Item SET name = :fName, created = :fCreated WHERE id = :fId")
                        .setParameter("fName", item.getName())
                        .setParameter("fCreated", item.getCreated())
                        .setParameter("fId", id);
                result = query.executeUpdate() > 0;
                if (result) {
                    item.setId(id);
                }
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
        return result;
    }

    @Override
    public void delete(int id) {
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                Item item = new Item();
                item.setId(id);
                session.delete(item);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public List<Item> findAll() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            List<Item> items = session.createQuery("from Item", Item.class).list();
            session.getTransaction().commit();
            session.close();
            return items;
        }
    }

    @Override
    public List<Item> findByName(String key) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Query<Item> query = session.createQuery("from User WHERE name LIKE :fName", Item.class)
                    .setParameter("fName",  "%" + key + "%");
            List<Item> items = query.getResultList();
            session.getTransaction().commit();
            return items;
        }
    }

    @Override
    public Item findById(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Item item = session.get(Item.class, id);
            session.getTransaction().commit();
            session.close();
            return item;
        }
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
