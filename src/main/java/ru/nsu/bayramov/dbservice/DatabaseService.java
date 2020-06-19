package ru.nsu.bayramov.dbservice;

import ru.nsu.bayramov.dbservice.arguments.CommandLineParser;
import ru.nsu.bayramov.dbservice.operations.Operation;
import ru.nsu.bayramov.dbservice.operations.search.SearchOperation;
import ru.nsu.bayramov.dbservice.operations.stat.StatOperation;
import ru.nsu.bayramov.dbservice.utils.DatabaseQueries;

import java.io.IOException;
import java.sql.SQLException;

class DatabaseService {
    private String operationName;

    private String inputFileName;
    private String outputFileName;

    private Operation operation;

    private boolean setOptions(String[] args) {
        CommandLineParser commandLineParser = new CommandLineParser(args);

        if (commandLineParser.getHelp()) {
            commandLineParser.showHelp();
            return false;
        }

        operationName = commandLineParser.getOperationName();

        inputFileName = commandLineParser.getInputFileName();
        outputFileName = commandLineParser.getOutputFileName();

        return true;
    }

    void start(String[] args) throws SQLException, IOException, ClassNotFoundException {
        if (setOptions(args)) {
            if (operationName.equals("search")) {
                operation = new SearchOperation();
            } else if (operationName.equals("stat")) {
                operation = new StatOperation();
            } else {
                throw new IllegalArgumentException("Unknown operation");
            }
        }
        operation.execute(inputFileName, outputFileName);
    }

    void loadDatabaseDump() throws SQLException, IOException, ClassNotFoundException {
        DatabaseQueries.loadDatabaseDump();
    }
}
