package entity;

import java.time.LocalDate;

public class Ingredient {

    private Integer id;

    private String description;

    private Double price;

    private LocalDate expirationDate;

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }


    public Ingredient(Integer id, String description, Double price, LocalDate expirationDate) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.expirationDate = expirationDate;
    }

    public Ingredient(String description, Double price, LocalDate expirationDate) {
        this(null, description, price, expirationDate);
    }

    public Ingredient(String description, LocalDate expirationDate) {
        this(null, description, null, expirationDate);
    }

    @Override
    public String toString() {
        return "{'description': '" + description + "', 'price': '" + price + "', 'expirationDate': '" + expirationDate + "'}";
    }
}
