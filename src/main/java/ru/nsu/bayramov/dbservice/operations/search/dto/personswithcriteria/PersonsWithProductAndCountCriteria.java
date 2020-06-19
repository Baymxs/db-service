package ru.nsu.bayramov.dbservice.operations.search.dto.personswithcriteria;

import ru.nsu.bayramov.dbservice.operations.search.dto.Person;
import ru.nsu.bayramov.dbservice.operations.search.dto.ProductCount;

import java.util.List;
import java.util.Map;

public class PersonsWithProductAndCountCriteria extends PersonsWithCriteria {
    private ProductCount productCount;

    public PersonsWithProductAndCountCriteria(List<Person> persons, ProductCount productCount) {
        super(persons);
        this.productCount = productCount;
    }
}
