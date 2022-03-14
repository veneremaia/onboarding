import com.squareup.okhttp.Response;
import common.HttpRequestBaseFunctionality;
import entity.Ingredient;
import entity.ResponseInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class IngredientClient extends HttpRequestBaseFunctionality {

    private final String URL_INGREDIENT = "/ingredient";

    private final String API = "API";

    private final String CACHE = "Cache";

    private Map<Integer, Ingredient> cache;

    public IngredientClient(String baseUrl) {
        super(baseUrl);
        this.cache = new HashMap<>();
    }

    public ResponseInfo<List<Ingredient>> getIngredients() throws IOException {
       return getIngredients(Optional.empty());
    }

    public ResponseInfo<List<Ingredient>> getIngredients(Optional<Map<String, String>> params) throws IOException {
        Response response = this.getResponse(URL_INGREDIENT, "GET", Optional.empty(), params);
        List<Ingredient> ingredients = new ArrayList<>();
        for (Object g : new JSONArray(response.body().string())) {
            ingredients.add(convertToIngredient((JSONObject) g));
        }
        ResponseInfo<List<Ingredient>> result = new ResponseInfo<>(ingredients, Long.parseLong(response.headers().get("time")), API);
        return result;
    }

    public ResponseInfo<Ingredient> getIngredientById(int id) throws IOException {
        if (cache.containsKey(id)) {
            return new ResponseInfo<>(cache.get(id),0,CACHE);
        }
        Response response = this.getResponse(URL_INGREDIENT + "/" + id, "GET", Optional.empty(), Optional.empty());
        Ingredient ingredient = convertToIngredient(new JSONObject(response.body().string()));
        cache.put(ingredient.getId(), ingredient);
        return new ResponseInfo<>(ingredient,Long.parseLong(response.headers().get("time")), API);
    }

    public Ingredient addIngredient(Ingredient ingredient) throws IOException {
        Response response = this.getResponse(URL_INGREDIENT, "POST", Optional.of(new JSONObject(ingredient.toString())), Optional.empty());
        return convertToIngredient(new JSONObject(response.body().string()));
    }

    private Ingredient convertToIngredient(JSONObject json) {
        return new Ingredient(json.getInt("id"), json.getString("description"), json.getDouble("price"), LocalDate.parse(json.getString("expirationDate")));
    }
}
