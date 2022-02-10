import entity.Ingredient;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        IngredientClient ingredientClient = new IngredientClient(args[0]);
        List<Ingredient> result = ingredientClient.getIngredients();
        Ingredient pollo = new Ingredient("Harina", 2.20, LocalDate.parse("2024-02-15"));
        ingredientClient.addIngredient(pollo);
        System.out.println(ingredientClient.getIngredientById(1));
        System.out.println(ingredientClient.getIngredientById(1));
    }
}
