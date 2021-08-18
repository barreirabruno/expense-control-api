package domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Expense {

    private String id;
    private BigDecimal price;
    private String description;
    private LocalDateTime date;

    public Expense( BigDecimal price, String description, LocalDateTime date) {
        this.id = UUID.randomUUID().toString();
        this.price = price;
        this.description = description;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
