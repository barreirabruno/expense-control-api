package data.protocols.database;

import domain.entity.Expense;

public interface AddExpenseRepository {
    int add(Expense newExpense);
}
