package ru.nsu.bayramov.dbservice.operations.search.dto;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteria {
    private List<String> lastNames = new ArrayList<>();
    private List<ProductCount> productCounts = new ArrayList<>();
    private List<MinMaxPrice> minMaxPrice = new ArrayList<>();
    private List<Integer> badCustomers = new ArrayList<>();

    public List<String> getLastNames() {
        return lastNames;
    }

    public List<ProductCount> getProductCounts() {
        return productCounts;
    }

    public List<MinMaxPrice> getMinMaxPrice() {
        return minMaxPrice;
    }

    public List<Integer> getBadCustomers() {
        return badCustomers;
    }
}
