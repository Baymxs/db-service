package ru.nsu.bayramov.dbservice.operations.search;

import ru.nsu.bayramov.dbservice.operations.OperationResult;
import ru.nsu.bayramov.dbservice.operations.search.dto.personswithcriteria.PersonsWithBadCustomersCountCriteria;
import ru.nsu.bayramov.dbservice.operations.search.dto.personswithcriteria.PersonsWithLastNameCriteria;
import ru.nsu.bayramov.dbservice.operations.search.dto.personswithcriteria.PersonsWithMinMaxPriceCriteria;
import ru.nsu.bayramov.dbservice.operations.search.dto.personswithcriteria.PersonsWithProductAndCountCriteria;

import java.util.List;

public class SearchOperationResult extends OperationResult {
    private List<PersonsWithLastNameCriteria> personsWithLastNameCriteria;

    private List<PersonsWithProductAndCountCriteria> personsWithProductAndCountCriteria;

    private List<PersonsWithMinMaxPriceCriteria> personsWithMinMaxPriceCriteria;

    private List<PersonsWithBadCustomersCountCriteria> personsWithBadCustomersCountCriteria;

    public SearchOperationResult() {
        type = "search";
    }

    public void setPersonsWithLastNameCriteria(List<PersonsWithLastNameCriteria> personsWithLastNameCriteria) {
        this.personsWithLastNameCriteria = personsWithLastNameCriteria;
    }

    public void setPersonsWithProductAndCountCriteria(List<PersonsWithProductAndCountCriteria> personsWithProductAndCountCriteria) {
        this.personsWithProductAndCountCriteria = personsWithProductAndCountCriteria;
    }

    public void setPersonsWithMinMaxPriceCriteria(List<PersonsWithMinMaxPriceCriteria> personsWithMinMaxPriceCriteria) {
        this.personsWithMinMaxPriceCriteria = personsWithMinMaxPriceCriteria;
    }

    public void setPersonsWithBadCustomersCountCriteria(List<PersonsWithBadCustomersCountCriteria> personsWithBadCustomersCountCriteria) {
        this.personsWithBadCustomersCountCriteria = personsWithBadCustomersCountCriteria;
    }
}
