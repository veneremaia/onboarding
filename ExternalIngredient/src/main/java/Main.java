import entity.Ingredient;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws IOException {

        // Se le pasa la url base al crear el cliente
        IngredientClient ingredientClient = new IngredientClient(args[0]);
        // Add ingredient
        Ingredient pollo = new Ingredient("Harina", 2.20, LocalDate.parse("2024-02-15"));
        ingredientClient.addIngredient(pollo);
        // Get ingredient by api
        System.out.println(ingredientClient.getIngredientById(1));
        // Get ingredient by cache
        System.out.println(ingredientClient.getIngredientById(1));
        // Parametros
        Map<String, String> params = new HashMap<>();
        params.put("page","2");
        params.put("size","4");
        params.put("sort","description");
        System.out.println(ingredientClient.getIngredients(Optional.of(params)));
    }
}
