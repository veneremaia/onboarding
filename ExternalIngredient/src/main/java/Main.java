import entity.Ingredient;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        IngredientClient ingredientClient = new IngredientClient(args[0]);
        List<Ingredient> result = ingredientClient.getIngredients();
        Ingredient pollo = new Ingredient("Harina", 2.20, LocalDate.parse("2024-02-15"));
        ingredientClient.addIngredient(pollo);
        System.out.println(ingredientClient.getIngredientById(1));
        System.out.println(ingredientClient.getIngredientById(1));
        Map<String, String> params = new HashMap<>();
        params.put("page","2");
        params.put("size","4");
        params.put("sort","description");
        System.out.println(ingredientClient.getIngredients(params));
    }
}
