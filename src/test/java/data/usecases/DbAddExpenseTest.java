package data.usecases;

import data.mocks.AddExpenseRepositorySpy;
import domain.ExpenseMock;
import domain.entity.Expense;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

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

    @Test
    public void should_return_true_on_success() {
        Expense newExpense = new ExpenseMock().mockExpense();
        SystemUnderTest systemUnderTest = new SystemUnderTest();
        AddExpenseRepositorySpy mock = systemUnderTest.getAddExpenseRepositorySpy();
        Mockito.when(mock.add(newExpense)).thenReturn(1);
        assertTrue(systemUnderTest.makeSut().add(newExpense));
        Mockito.verify(mock).add(newExpense);
    }

    @Test
    public void should_return_false_if_AddExpenseRepository_fails() {
        Expense newExpense = new ExpenseMock().mockExpense();
        SystemUnderTest systemUnderTest = new SystemUnderTest();
        when(systemUnderTest.getAddExpenseRepositorySpy().add(newExpense)).thenReturn(0);
        DbAddExpense dbAddExpense = systemUnderTest.makeSut();
        assertEquals(false, dbAddExpense.add(newExpense));
    }

}
