package ru.nsu.bayramov.dbservice.operations.stat;

import com.google.gson.Gson;
import ru.nsu.bayramov.dbservice.operations.Operation;
import ru.nsu.bayramov.dbservice.utils.DatabaseQueries;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class StatOperation extends Operation {
    public StatOperation() throws SQLException, IOException, ClassNotFoundException {
    }

    public void execute(String inputFileName, String outputFileName) throws IOException, SQLException, ClassNotFoundException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.json"));
             FileWriter writer = new FileWriter("output.json")) {
            Gson gson = new Gson();

            StatCriteria statCriteria = gson.fromJson(bufferedReader, StatCriteria.class);

            StatOperationResult statOperationResult = new StatOperationResult(DatabaseQueries.getStat(
                    statCriteria.getStartDate(), statCriteria.getEndDate()), DatabaseQueries.getTotalExpense(
                    statCriteria.getStartDate(), statCriteria.getEndDate()), DatabaseQueries.getAvgExpense(
                    statCriteria.getStartDate(), statCriteria.getEndDate()));

            gson.toJson(statOperationResult, writer);
        }
    }
}
