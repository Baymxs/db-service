package ru.nsu.bayramov.dbservice;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnection {
    private static final Logger logger = Logger.getLogger(JdbcConnection.class);

    private static JdbcConnection jdbcConnection;

    private String user;
    private String password;
    private String url;
    private String driverName;

    private Connection connection;

    private JdbcConnection() throws IOException, SQLException, ClassNotFoundException {
        setUpOptions();
        setUpConnection();
    }

    public static JdbcConnection getInstance() throws IOException, SQLException, ClassNotFoundException {
        if (jdbcConnection == null) {
            jdbcConnection = new JdbcConnection();
        }
        return jdbcConnection;
    }

    private void setUpOptions() throws IOException {
        Properties property = new Properties();

        try (InputStream fileInputStream = getClass().getResourceAsStream("/config.properties")) {
            property.load(fileInputStream);

            user = property.getProperty("db.user");
            password = property.getProperty("db.password");
            url = property.getProperty("db.url");
            driverName = property.getProperty("db.driver");

            logger.info("Database options installed:");
            logger.info("\tDatabase user: " + user);
            logger.info("\tDatabase password: " + password);
            logger.info("\tDatabase url: " + url);
            logger.info("\tDatabase driver name: " + driverName);
        }
    }

    private void setUpConnection() throws SQLException, ClassNotFoundException {
        Class.forName(driverName);
        logger.info("Database driver installed");

        connection = DriverManager.getConnection(url, user, password);
        logger.info("Connection to the database installed");

    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
        logger.info("Connection to the database closed");
    }
}
