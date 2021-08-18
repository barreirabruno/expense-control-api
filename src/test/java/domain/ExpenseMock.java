package domain;

import com.github.javafaker.Faker;
import domain.entity.Expense;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExpenseMock {

    private Faker faker = new Faker();

    public Expense mockExpense() {
        return new Expense(
                new BigDecimal(faker.number().digits(3)),
                "any_description_mock",
                LocalDateTime.now()
        );
    }
}
