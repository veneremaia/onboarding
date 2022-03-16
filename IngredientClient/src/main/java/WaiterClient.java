import common.HttpRequestBaseFunctionality;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Optional;

public class WaiterClient extends HttpRequestBaseFunctionality {

    private final String URL_WAITER = "/waiter";


    public WaiterClient(String baseUrl) {
        super(baseUrl);
    }

    public Boolean saveWaiter(String id, String name) throws IOException {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("id",id)
                .addFormDataPart("name",name)
                .build();
        Response response = this.getResponse(URL_WAITER, "POST", Optional.of(body), Optional.empty());
        return Boolean.getBoolean(response.body().string());
    }
}
