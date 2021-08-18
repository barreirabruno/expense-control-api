package domain.usecases;

import domain.entity.Expense;

public interface AddExpense {
    boolean add(Expense newExpense);
}
