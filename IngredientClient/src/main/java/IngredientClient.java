import com.squareup.okhttp.Response;
import common.HttpRequestBaseFunctionality;
import entity.Ingredient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientClient extends HttpRequestBaseFunctionality {

    private final String URL_INGREDIENT = "/ingredient";

    private Map<Integer, Ingredient> cache;

    public IngredientClient(String baseUrl) {
        super(baseUrl);
        this.cache = new HashMap<>();
    }

    public List<Ingredient> getIngredients() throws IOException {
       return getIngredients(null);
    }

    public List<Ingredient> getIngredients(Map<String, String> params) throws IOException {
        Response response = this.getResponse(URL_INGREDIENT, "GET", null, params);
        List<Ingredient> result = new ArrayList<>();
        for (Object g : new JSONArray(response.body().string())) {
            result.add(convertToIngredient((JSONObject) g));
        }
        return result;
    }

    public Ingredient getIngredientById(int id) throws IOException {
        if (cache.containsKey(id)) {
            cache.get(id).setReadFrom("cache");
            return cache.get(id);
        }
        Response response = this.getResponse(URL_INGREDIENT + "/" + id, "GET", null, null);
        Ingredient result = convertToIngredient(new JSONObject(response.body().string()));
        cache.put(result.getId(), result);
        return result;
    }

    public Ingredient addIngredient(Ingredient ingredient) throws IOException {
        Response response = this.getResponse(URL_INGREDIENT, "POST", new JSONObject(ingredient.toString()), null);
        return convertToIngredient(new JSONObject(response.body().string()));
    }

    private Ingredient convertToIngredient(JSONObject json) {
        return new Ingredient(json.getInt("id"), json.getString("description"), json.getDouble("price"), LocalDate.parse(json.getString("expirationDate")));
    }
}
