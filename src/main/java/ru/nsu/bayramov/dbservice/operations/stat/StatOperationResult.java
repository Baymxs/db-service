package ru.nsu.bayramov.dbservice.operations.stat;

import ru.nsu.bayramov.dbservice.operations.OperationResult;
import ru.nsu.bayramov.dbservice.operations.stat.dto.PersonPurchase;

import java.util.List;

public class StatOperationResult extends OperationResult {
    private List<PersonPurchase> personPurchases;
    private int totalExpense;
    private float avgExpense;

    public StatOperationResult(List<PersonPurchase> personPurchases, int totalExpense, float avgExpense) {
        type="stat";
        this.personPurchases = personPurchases;
        this.totalExpense = totalExpense;
        this.avgExpense = avgExpense;
    }
}
