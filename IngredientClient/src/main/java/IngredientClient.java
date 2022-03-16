import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
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
        Headers headers = response.headers();
        ResponseInfo<List<Ingredient>> result = new ResponseInfo<>(ingredients,
                Long.parseLong(headers.get("time")),
                API,
                headers.get("waiter"),
                Integer.parseInt(headers.get("queryCount")));
        return result;
    }

    public ResponseInfo<Ingredient> getIngredientById(int id) throws IOException {
        if (cache.containsKey(id)) {
            return new ResponseInfo<>(cache.get(id),0,CACHE);
        }
        Response response = this.getResponse(URL_INGREDIENT + "/" + id, "GET", Optional.empty(), Optional.empty());
        Ingredient ingredient = convertToIngredient(new JSONObject(response.body().string()));
        cache.put(ingredient.getId(), ingredient);
        Headers headers = response.headers();
        return new ResponseInfo<>(ingredient,
                Long.parseLong(headers.get("time")),
                API,
                headers.get("waiter"),
                Integer.parseInt(headers.get("queryCount")));
    }

    public Ingredient addIngredient(Ingredient ingredient) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), String.valueOf(new JSONObject(ingredient.toString())));
        Response response = this.getResponse(URL_INGREDIENT, "POST", Optional.of(body), Optional.empty());
        return convertToIngredient(new JSONObject(response.body().string()));
    }

    private Ingredient convertToIngredient(JSONObject json) {
        return new Ingredient(json.getInt("id"), json.getString("description"), json.getDouble("price"), LocalDate.parse(json.getString("expirationDate")));
    }
}
