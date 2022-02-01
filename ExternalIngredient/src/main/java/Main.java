import entity.Ingredient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main (String [ ] args) throws IOException {

        IngredientClient ingredientClient = new IngredientClient();
        List<Ingredient> result = ingredientClient.getIngredients();
        Ingredient pollo = new Ingredient("Pollo",52.20);
        ingredientClient.addIngredient(pollo);
    }
}
