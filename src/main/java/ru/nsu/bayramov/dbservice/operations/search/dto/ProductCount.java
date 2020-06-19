package ru.nsu.bayramov.dbservice.operations.search.dto;

public class ProductCount {
    private String name;
    private int count;

    public ProductCount(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
