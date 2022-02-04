import com.squareup.okhttp.Response;
import common.HttpRequestBaseFunctionality;
import entity.Ingredient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IngredientClient extends HttpRequestBaseFunctionality {

    public List<Ingredient> getIngredients() throws IOException {
        Response response = this.getResponse("ingredient", "GET", null);
        List<Ingredient> result = new ArrayList<>();
        for(Object g : new JSONArray(response.body().string())){
            result.add(convertToIngredient((JSONObject)g));
        }
        return result;
    }

    public Ingredient getIngredientById(int id) throws IOException {
        Response response = this.getResponse("ingredient/" + id, "GET", null);
        return convertToIngredient(new JSONObject(response.body().string()));
    }

    public Ingredient addIngredient(Ingredient ingredient) throws IOException {
        Response response = this.getResponse("ingredient", "POST", new JSONObject(ingredient.toString()));
        return convertToIngredient(new JSONObject(response.body().string()));
    }

    private Ingredient convertToIngredient(JSONObject json){
        return new Ingredient(json.getInt("id"),json.getString("description"),json.getDouble("price"), LocalDate.parse(json.getString("expirationDate")));
    }
}
