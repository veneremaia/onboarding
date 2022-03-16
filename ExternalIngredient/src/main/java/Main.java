import entity.Ingredient;
import entity.ResponseInfo;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws IOException {

        // Se le pasa la url base al crear el cliente
        WaiterClient waiterClient = new WaiterClient(args[0]);
        IngredientClient ingredientClient = new IngredientClient(args[0]);

        // Add mozo
        waiterClient.saveWaiter("1","Juan");
        // Add ingredient
        Ingredient pollo = new Ingredient("Harina", 2.20, LocalDate.parse("2024-02-15"));
        ingredientClient.addIngredient(pollo);
        // Get ingredient by api
        ResponseInfo<Ingredient> ingredient1 = ingredientClient.getIngredientById(1);
        System.out.println(
                "Response data: " + ingredient1.getTime() + " from " + ingredient1.getReadFrom()
                        + " waiter: " + ingredient1.getWaiter() + " Number of queries: " + ingredient1.getQueryCount());
        System.out.println(
                "Resultado: " + ingredient1.getResult());
        // Get ingredient by cache
        ResponseInfo<Ingredient> ingredient2 = ingredientClient.getIngredientById(1);
        System.out.println(
                "Response data: " + ingredient2.getTime() + " from " + ingredient2.getReadFrom() );
        System.out.println(
                "Resultado: " + ingredient2.getResult());

        // Parametros
        Map<String, String> params = new HashMap<>();
        params.put("page","2");
        params.put("size","4");
        params.put("sort","description");
        System.out.println(ingredientClient.getIngredients(Optional.of(params)).getResult());
    }
}
