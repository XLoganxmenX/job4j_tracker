package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {

    private Connection connection;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection connection) {
        this.connection = connection;
    }

    private void init() {
        try (InputStream input = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(input);
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

    @Override
    public Item add(Item item) {
        try (PreparedStatement statement =
                     connection.prepareStatement("INSERT INTO items(name, created) VALUES(?, ?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("UPDATE items SET name = ?, created = ? WHERE id = ?")) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            statement.setInt(3, id);
            rsl = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsl;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement =
                     connection.prepareStatement("DELETE FROM items WHERE id = ?")) {
            statement.setInt(1, id);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> itemsFromDB = null;
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM items")) {

            try (ResultSet resultSet = statement.executeQuery()) {
                itemsFromDB = extractItems(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemsFromDB;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> itemsFromDB = null;
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM items WHERE name = ?")) {
            statement.setString(1, key);

            try (ResultSet resultSet = statement.executeQuery()) {
                itemsFromDB = extractItems(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemsFromDB;
    }

    @Override
    public Item findById(int id) {
        Item itemFromDB = null;
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM items WHERE id = ?")) {
            statement.setInt(1, id);

            try (ResultSet resultItem = statement.executeQuery()) {
                itemFromDB = extractItems(resultItem).get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemFromDB;
    }

    private Item createItem(ResultSet queryResult) throws SQLException {
        return new Item(
                queryResult.getInt("id"),
                queryResult.getString("name"),
                queryResult.getTimestamp("created").toLocalDateTime()
        );
    }

    private List<Item> extractItems(ResultSet queryResult) throws SQLException {
        List<Item> itemsFromDB = null;
        if (!queryResult.next()) {
            throw new SQLException("Items not found");
        } else {
            itemsFromDB = new LinkedList<>();
            do {
                itemsFromDB.add(createItem(queryResult));
            } while (queryResult.next());
        }
        return itemsFromDB;
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        try (Store tracker = new SqlTracker()) {
            tracker.add(new Item("newTestItem"));
            tracker.add(new Item("newTestItem"));
            tracker.add(new Item("Item"));

            System.out.println(tracker.findById(1));

            System.out.println(tracker.findByName("newTestItem"));
            System.out.println(tracker.findAll());
            tracker.delete(1);
            System.out.println(tracker.findAll());
            tracker.replace(2, new Item("replacedItem"));
            System.out.println(tracker.findAll());
        }
    }
}
