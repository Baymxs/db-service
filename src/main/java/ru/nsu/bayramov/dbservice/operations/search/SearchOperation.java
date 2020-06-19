package ru.nsu.bayramov.dbservice.operations.search;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import ru.nsu.bayramov.dbservice.JdbcConnection;
import ru.nsu.bayramov.dbservice.operations.Operation;
import ru.nsu.bayramov.dbservice.operations.search.dto.MinMaxPrice;
import ru.nsu.bayramov.dbservice.operations.search.dto.ProductCount;
import ru.nsu.bayramov.dbservice.operations.search.dto.SearchCriteria;
import ru.nsu.bayramov.dbservice.operations.search.dto.personswithcriteria.PersonsWithBadCustomersCountCriteria;
import ru.nsu.bayramov.dbservice.operations.search.dto.personswithcriteria.PersonsWithLastNameCriteria;
import ru.nsu.bayramov.dbservice.operations.search.dto.personswithcriteria.PersonsWithMinMaxPriceCriteria;
import ru.nsu.bayramov.dbservice.operations.search.dto.personswithcriteria.PersonsWithProductAndCountCriteria;
import ru.nsu.bayramov.dbservice.utils.DatabaseQueries;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchOperation extends Operation {
    public SearchOperation() throws SQLException, IOException, ClassNotFoundException {
    }

    public void execute(String inputFileName, String outputFileName) throws IOException, SQLException, ClassNotFoundException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.json"));
                FileWriter writer = new FileWriter("output.json")) {
            Gson gson = new Gson();

            SearchCriteria searchCriteria = gson.fromJson(bufferedReader, SearchCriteria.class);

            List<PersonsWithLastNameCriteria> personsWithLastNameCriteria = new ArrayList<>();
            List<PersonsWithProductAndCountCriteria> personsWithProductAndCountCriteria = new ArrayList<>();
            List<PersonsWithMinMaxPriceCriteria> personsWithMinMaxPriceCriteria = new ArrayList<>();
            List<PersonsWithBadCustomersCountCriteria> personsWithBadCustomersCountCriteria = new ArrayList<>();

            for (String lastName : searchCriteria.getLastNames()) {
                personsWithLastNameCriteria.add(new PersonsWithLastNameCriteria(DatabaseQueries.findByLastName(lastName), lastName));
            }

            for (ProductCount productCount : searchCriteria.getProductCounts()) {
                personsWithProductAndCountCriteria.add(new PersonsWithProductAndCountCriteria(DatabaseQueries.findByProductAndCount(productCount.getName(), productCount.getCount()),
                        productCount));
            }

            for (MinMaxPrice minMaxPrice : searchCriteria.getMinMaxPrice()) {
                personsWithMinMaxPriceCriteria.add(new PersonsWithMinMaxPriceCriteria(DatabaseQueries.findBetweenMinAndMaxPrice(minMaxPrice), minMaxPrice));
            }

            for (Integer badCustomersCount : searchCriteria.getBadCustomers()) {
                personsWithBadCustomersCountCriteria.add(new PersonsWithBadCustomersCountCriteria(DatabaseQueries.getBadCustomers(badCustomersCount), badCustomersCount));
            }

            SearchOperationResult searchOperationResult = new SearchOperationResult();

            searchOperationResult.setPersonsWithLastNameCriteria(personsWithLastNameCriteria);
            searchOperationResult.setPersonsWithProductAndCountCriteria(personsWithProductAndCountCriteria);
            searchOperationResult.setPersonsWithMinMaxPriceCriteria(personsWithMinMaxPriceCriteria);
            searchOperationResult.setPersonsWithBadCustomersCountCriteria(personsWithBadCustomersCountCriteria);

            gson.toJson(searchOperationResult, writer);
        }
    }
}
