package data.usecases;

import data.mocks.AddExpenseRepositorySpy;
import domain.ExpenseMock;
import domain.entity.Expense;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class SystemUnderTest {

    private AddExpenseRepositorySpy addExpenseRepositorySpy = Mockito.mock(AddExpenseRepositorySpy.class);
    private DbAddExpense dbAddExpense = new DbAddExpense(addExpenseRepositorySpy);

    public DbAddExpense makeSut() {
        return dbAddExpense;
    }

    public AddExpenseRepositorySpy getAddExpenseRepositorySpy() {
        return addExpenseRepositorySpy;
    }

    public DbAddExpense getDbAddExpense() {
        return dbAddExpense;
    }
}

public class DbAddExpenseTest {

    @Test
    public void should_call_AddExpenseRepository_with_correct_values() {
        Expense newExpense = new ExpenseMock().mockExpense();
        SystemUnderTest systemUnderTest = new SystemUnderTest();
        DbAddExpense dbAddExpense = systemUnderTest.makeSut();
        dbAddExpense.add(newExpense);
        Mockito.verify(systemUnderTest.getAddExpenseRepositorySpy(), Mockito.times(1)).add(newExpense);
    }
}
