package data.usecases;

import data.protocols.database.AddExpenseRepository;
import domain.entity.Expense;
import domain.usecases.AddExpense;

public class DbAddExpense implements AddExpense {

    private AddExpenseRepository addExpenseRepository;

    public DbAddExpense(AddExpenseRepository addExpenseRepository) {
        this.addExpenseRepository = addExpenseRepository;
    }

    @Override
    public boolean add(Expense newExpense) {
       int insertNewExpense = this.addExpenseRepository.add(newExpense);
       boolean insertionSuccessfull = insertNewExpense > 0;
       return insertionSuccessfull;
    }
}
