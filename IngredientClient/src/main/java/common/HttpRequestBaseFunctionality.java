package common;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.json.JSONObject;

import java.io.IOException;

public class HttpRequestBaseFunctionality {

    private final String BASE_URL = "http://localhost:8080/";
    private final String APPLICATION_JSON = "application/json";

    protected Response getResponse(String url, String method, JSONObject body) throws IOException {
        RequestBody requestBody = null;
        if(body!=null)
            requestBody = RequestBody.create(MediaType.parse(APPLICATION_JSON), body.toString());
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + url)
                .method(method.toUpperCase(), requestBody)
                .addHeader("Content-Type", APPLICATION_JSON)
                .build();
        return client.newCall(request).execute();
    }

}
