package ru.nsu.bayramov.dbservice.operations.stat.dto;

public class DataInterval {
    private int startDate;
    private int endDate;

    public DataInterval(int startDate, int endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getStartDate() {
        return startDate;
    }

    public int getEndDate() {
        return endDate;
    }
}
