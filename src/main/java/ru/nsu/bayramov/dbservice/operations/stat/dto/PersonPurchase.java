package ru.nsu.bayramov.dbservice.operations.stat.dto;

import ru.nsu.bayramov.dbservice.operations.search.dto.Person;

import java.util.Map;

public class PersonPurchase {
    private Person person;
    private Map<String, Integer> purchases;
    private int totalExpense;

    public PersonPurchase(Person person, Map<String, Integer> purchases, int totalExpense) {
        this.person = person;
        this.purchases = purchases;
        this.totalExpense = totalExpense;
    }
}
