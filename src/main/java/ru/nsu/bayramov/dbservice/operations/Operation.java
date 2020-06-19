package ru.nsu.bayramov.dbservice.operations;

import ru.nsu.bayramov.dbservice.JdbcConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class Operation {
    public Connection connection;

    public Operation() throws SQLException, IOException, ClassNotFoundException {
        connection = JdbcConnection.getInstance().getConnection();
    }

    public abstract void execute(String inputFileName, String outputFileName) throws IOException, SQLException, ClassNotFoundException;
}
