package entity;

public class Ingredient {

    private Integer id;

    private String description;

    private Double price;

    public Ingredient(Integer id, String description, Double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public Ingredient(String description, Double price) {
        this(null, description, price);
    }

    public Ingredient(String description) {
        this(null, description, null);
    }

    @Override
    public String toString() {
        return "{'description': '" + description + "', 'price': '" + price +"'}";
    }
}
