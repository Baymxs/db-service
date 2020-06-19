package ru.nsu.bayramov.dbservice.utils;

import ru.nsu.bayramov.dbservice.JdbcConnection;
import ru.nsu.bayramov.dbservice.operations.search.dto.MinMaxPrice;
import ru.nsu.bayramov.dbservice.operations.search.dto.Person;
import ru.nsu.bayramov.dbservice.operations.stat.dto.PersonPurchase;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseQueries {
    public static void loadDatabaseDump() throws SQLException, IOException, ClassNotFoundException {
        dropTables();
        createTables();
        entryTables();
    }

    private static void createTables() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JdbcConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();

        String buyersTableQuery = "CREATE TABLE buyers " +
                "(id SERIAL PRIMARY KEY, name VARCHAR(50) NOT NULL, surname VARCHAR(50) NOT NULL)";

        String productsTableQuery = "CREATE TABLE products " +
                "(id SERIAL PRIMARY KEY, name VARCHAR(50) NOT NULL, price INTEGER NOT NULL)";

        String purchasesTableQuery = "CREATE TABLE purchases " +
                "(id SERIAL PRIMARY KEY, buyer_id INTEGER NOT NULL, date TIMESTAMP NOT NULL)";

        String productsPurchasesTableQuery = "CREATE TABLE products_purchases " +
                "(product_id INTEGER NOT NULL, purchase_id INTEGER NOT NULL)";

        statement.executeUpdate(buyersTableQuery);
        statement.executeUpdate(productsTableQuery);
        statement.executeUpdate(purchasesTableQuery);
        statement.executeUpdate(productsPurchasesTableQuery);
    }

    private static void entryTables() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JdbcConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();

        String buyersEntryQuery = "INSERT INTO buyers VALUES " +
                "(DEFAULT, 'Нижад', 'Байрамов'), " +
                "(DEFAULT, 'Григорий', 'Караваев'), " +
                "(DEFAULT, 'Антон', 'Караваев'), " +
                "(DEFAULT, 'Дмитрий', 'Онищук'), " +
                "(DEFAULT, 'Данил', 'Синицын')";

        String productsEntryQuery = "INSERT INTO products VALUES " +
                "(DEFAULT, 'Молоко', 47), " +
                "(DEFAULT, 'Мюсли', 110), " +
                "(DEFAULT, 'Куриное филе', 220), " +
                "(DEFAULT, 'Курина приправа', 13)," +
                "(DEFAULT, 'Гречневая крупа', 56), " +
                "(DEFAULT, 'Кефир', 37), " +
                "(DEFAULT, 'Консервированные ананасы', 97), " +
                "(DEFAULT, 'Хлеб', 27)";

        String purchasesEntryQuery = "INSERT INTO purchases VALUES " +
                "(DEFAULT, 1, '2020-06-01 11:00'), " +
                "(DEFAULT, 2, '2020-06-01 12:00'), " +
                "(DEFAULT, 3, '2020-06-02 16:00'), " +
                "(DEFAULT, 4, '2020-06-02 18:00')," +
                "(DEFAULT, 5, '2020-06-03 09:00')";

        String productsPurchasesEntryQuery = "INSERT INTO products_purchases VALUES " +
                "(1, 1), " +
                "(1, 1), " +
                "(2, 1), " +
                "(2, 1), " +
                "(3, 1), " +
                "(3, 1), " +
                "(4, 1), " +
                "(5, 1), " +
                "(6, 1), " +
                "(7, 1), " +
                "(8, 1), " +

                "(1, 2), " +
                "(1, 2), " +
                "(3, 2), " +
                "(3, 2), " +
                "(5, 2), " +
                "(5, 2), " +
                "(7, 2), " +

                "(2, 3), " +
                "(4, 3), " +
                "(6, 3), " +
                "(8, 3), " +

                "(3, 4), " +
                "(4, 4), " +
                "(7, 4), " +
                "(8, 4), " +

                "(1, 5), " +
                "(2, 5), " +
                "(5, 5), " +
                "(8, 5)";

        statement.executeUpdate(buyersEntryQuery);
        statement.executeUpdate(productsEntryQuery);
        statement.executeUpdate(purchasesEntryQuery);
        statement.executeUpdate(productsPurchasesEntryQuery);
    }

    private static void dropTables() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JdbcConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();

        String dropBuyersTableQuery = "DROP TABLE buyers";
        String dropProductsTableQuery = "DROP TABLE products";
        String dropPurchasesTableQuery = "DROP TABLE purchases";
        String dropProductsPurchasesTableQuery = "DROP TABLE products_purchases";

        statement.executeUpdate(dropBuyersTableQuery);
        statement.executeUpdate(dropProductsTableQuery);
        statement.executeUpdate(dropPurchasesTableQuery);
        statement.executeUpdate(dropProductsPurchasesTableQuery);
    }

    public static List<Person> findByLastName(String surname) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JdbcConnection.getInstance().getConnection();

        List<Person> persons = new ArrayList<>();

        String findByLastNameTableQuery = "SELECT name FROM buyers WHERE surname = ?";

        PreparedStatement statement = connection.prepareStatement(findByLastNameTableQuery);
        statement.setString(1, surname);

        statement.execute();

        ResultSet statementResultSet = statement.getResultSet();
        while (statementResultSet.next()) {
            persons.add(new Person(surname, statementResultSet.getString("name")));
        }

        return persons;
    }

    public static List<Person> findByProductAndCount(String product, int count) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JdbcConnection.getInstance().getConnection();

        List<Person> persons = new ArrayList<>();

        String findByProductAndCountTableQuery = "SELECT b.name, b.surname FROM products_purchases\n" +
                "INNER JOIN purchases AS a ON a.id=products_purchases.purchase_id\n" +
                "INNER JOIN buyers AS b ON b.id=a.buyer_id\n" +
                "INNER JOIN products AS c ON c.id=products_purchases.product_id\n" +
                "GROUP BY products_purchases.product_id, b.id, c.id, c.name\n" +
                "HAVING COUNT(1) >= ? AND c.name=?";

        PreparedStatement statement = connection.prepareStatement(findByProductAndCountTableQuery);
        statement.setInt(1, count);
        statement.setString(2, product);

        statement.execute();

        ResultSet statementResultSet = statement.getResultSet();
        while (statementResultSet.next()) {
            persons.add(new Person(statementResultSet.getString("surname"), statementResultSet.getString("name")));
        }

        return persons;
    }

    public static List<Person> findBetweenMinAndMaxPrice(MinMaxPrice minMaxPrice) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JdbcConnection.getInstance().getConnection();

        List<Person> persons = new ArrayList<>();

        String findBetweenMinAndMaxPriceTableQuery = "SELECT b.name, b.surname FROM products_purchases\n" +
                "INNER JOIN purchases AS a ON a.id=products_purchases.purchase_id\n" +
                "INNER JOIN buyers AS b ON b.id=a.buyer_id\n" +
                "INNER JOIN products AS c ON c.id=products_purchases.product_id\n" +
                "GROUP BY b.id\n" +
                "HAVING SUM(c.price) BETWEEN ? AND ?";

        PreparedStatement statement = connection.prepareStatement(findBetweenMinAndMaxPriceTableQuery);
        statement.setInt(1, minMaxPrice.getMin());
        statement.setInt(2, minMaxPrice.getMax());

        statement.execute();

        ResultSet statementResultSet = statement.getResultSet();
        while (statementResultSet.next()) {
            persons.add(new Person(statementResultSet.getString("surname"), statementResultSet.getString("name")));
        }

        return persons;
    }

    public static List<Person> getBadCustomers(Integer badCustomers) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JdbcConnection.getInstance().getConnection();

        List<Person> persons = new ArrayList<>();

        String findBadCustomersTableQuery = "SELECT b.name, b.surname FROM products_purchases\n" +
                "INNER JOIN buyers AS b ON b.id=products_purchases.purchase_id\n" +
                "GROUP BY b.id\n" +
                "ORDER BY COUNT(*)\n" +
                "LIMIT ?";

        PreparedStatement statement = connection.prepareStatement(findBadCustomersTableQuery);
        statement.setInt(1, badCustomers);

        statement.execute();

        ResultSet statementResultSet = statement.getResultSet();
        while (statementResultSet.next()) {
            persons.add(new Person(statementResultSet.getString("surname"), statementResultSet.getString("name")));
        }

        return persons;
    }

    public static List<PersonPurchase> getStat(Date startDate, Date endDate) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JdbcConnection.getInstance().getConnection();

        List<PersonPurchase> personPurchases = new ArrayList<>();

        String findAllPersonsTableQuery = "SELECT id, name, surname FROM buyers";

        String getStatTableQuery = "SELECT c.name, c.price*COUNT(*) as total FROM products_purchases\n" +
                "INNER JOIN purchases AS a ON a.id=products_purchases.purchase_id\n" +
                "INNER JOIN buyers AS b ON b.id=a.buyer_id\n" +
                "INNER JOIN products AS c ON c.id=products_purchases.product_id\n" +
                "WHERE b.id=? AND a.date BETWEEN ? AND ?\n" +
                "GROUP BY c.id";

        PreparedStatement personsStatement = connection.prepareStatement(findAllPersonsTableQuery);
        personsStatement.execute();

        ResultSet personsStatementResultSet = personsStatement.getResultSet();

        java.sql.Date startSqlDate = new java.sql.Date(startDate.getTime());
        java.sql.Date endSqlDate = new java.sql.Date(endDate.getTime());

        while (personsStatementResultSet.next()) {
            PreparedStatement statStatement = connection.prepareStatement(getStatTableQuery);
            statStatement.setInt(1, personsStatementResultSet.getInt("id"));
            statStatement.setDate(2, startSqlDate);
            statStatement.setDate(3, endSqlDate);

            statStatement.execute();

            ResultSet statResultSet = statStatement.getResultSet();

            Map<String, Integer> productExpenses = new HashMap<>();
            int totalExpense = 0;

            while (statResultSet.next()) {
                productExpenses.put(statResultSet.getString("name"), statResultSet.getInt("total"));
                totalExpense += statResultSet.getInt("total");
            }

            personPurchases.add(new PersonPurchase(new Person(personsStatementResultSet.getString("surname"),
                    personsStatementResultSet.getString("name")), productExpenses, totalExpense));
        }

        return personPurchases;
    }

    public static int getTotalExpense(Date startDate, Date endDate) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JdbcConnection.getInstance().getConnection();


        String totalExpense = "SELECT SUM(c.price) as total_expense FROM products_purchases\n" +
                "INNER JOIN purchases AS a ON a.id=products_purchases.purchase_id\n" +
                "INNER JOIN buyers AS b ON b.id=a.buyer_id\n" +
                "INNER JOIN products AS c ON c.id=products_purchases.product_id\n" +
                "WHERE a.date BETWEEN ? AND ?\n";

        java.sql.Date startSqlDate = new java.sql.Date(startDate.getTime());
        java.sql.Date endSqlDate = new java.sql.Date(endDate.getTime());

        PreparedStatement statement = connection.prepareStatement(totalExpense);

        statement.setDate(1, startSqlDate);
        statement.setDate(2, endSqlDate);

        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        resultSet.next();

        return resultSet.getInt("total_expense");
    }

    public static float getAvgExpense(Date startDate, Date endDate) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JdbcConnection.getInstance().getConnection();

        String avgExpense = "SELECT 1.0*SUM(c.price)/((SELECT COUNT(buyer_id) FROM purchases WHERE purchases.date BETWEEN ? AND ?)) as avg_expense\n" +
                "FROM products_purchases\n" +
                "INNER JOIN purchases AS a ON a.id=products_purchases.purchase_id\n" +
                "INNER JOIN buyers AS b ON b.id=a.buyer_id\n" +
                "INNER JOIN products AS c ON c.id=products_purchases.product_id\n" +
                "WHERE a.date BETWEEN ? AND ?";

        java.sql.Date startSqlDate = new java.sql.Date(startDate.getTime());
        java.sql.Date endSqlDate = new java.sql.Date(endDate.getTime());

        PreparedStatement statement = connection.prepareStatement(avgExpense);

        statement.setDate(1, startSqlDate);
        statement.setDate(2, endSqlDate);
        statement.setDate(3, startSqlDate);
        statement.setDate(4, endSqlDate);

        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        resultSet.next();

        return resultSet.getFloat("avg_expense");
    }
}
