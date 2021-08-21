package infra.database;

import data.protocols.database.AddExpenseRepository;
import domain.entity.Expense;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneId;

public class AddExpenseRepositoryImpl implements AddExpenseRepository {
    private Connection databaseConnection;

    public AddExpenseRepositoryImpl(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public int add(Expense newExpense) {
        int numberOfLinesAffected = 0;
        try {
            Date expenseDateToDatabase = Date.valueOf(newExpense.getDate().toLocalDate());
            String rawQuery = "INSERT INTO expense_control (exp_id, exp_price, exp_description, exp_date) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = this.databaseConnection.prepareStatement(rawQuery);
            statement.setString(1, newExpense.getId());
            statement.setBigDecimal(2, newExpense.getPrice());
            statement.setString(3, newExpense.getDescription());
            statement.setDate(4, expenseDateToDatabase);
            numberOfLinesAffected = statement.executeUpdate();
            this.databaseConnection.close();
        } catch (SQLException e) {
            System.out.println("[INFRA LAYER ADDEXPENSEREPOSITORYIMPL]");
            e.printStackTrace();
        }
        return numberOfLinesAffected;
    }
}
