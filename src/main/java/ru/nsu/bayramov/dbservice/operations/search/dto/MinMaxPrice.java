package ru.nsu.bayramov.dbservice.operations.search.dto;

public class MinMaxPrice {
    private int min;
    private int max;

    public MinMaxPrice(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
