package data.mocks;

import data.protocols.database.AddExpenseRepository;
import domain.entity.Expense;

public class AddExpenseRepositorySpy implements AddExpenseRepository {

    private Expense params;
    private int result = 1;

    @Override
    public int add(Expense newExpense) {
        this.params = newExpense;
        return this.result;
    }
}
