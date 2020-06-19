package ru.nsu.bayramov.dbservice.operations.stat;

import java.util.Date;

public class StatCriteria {
    private Date startDate;
    private Date endDate;

    public StatCriteria(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
