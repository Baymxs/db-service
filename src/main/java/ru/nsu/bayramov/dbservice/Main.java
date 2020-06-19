package ru.nsu.bayramov.dbservice;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseService databaseService = new DatabaseService();

            databaseService.loadDatabaseDump();
            databaseService.start(args);
        } catch (com.beust.jcommander.ParameterException ex) {
            System.err.println("Wrong command line arguments");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                JdbcConnection.getInstance().closeConnection();
            } catch (SQLException | IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
