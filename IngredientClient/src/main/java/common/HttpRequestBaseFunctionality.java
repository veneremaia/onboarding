package common;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class HttpRequestBaseFunctionality {

    private final String APPLICATION_JSON = "application/json";
    private final String baseUrl;

    public HttpRequestBaseFunctionality(String baseUrl){
        this.baseUrl = baseUrl;
    }

    protected Response getResponse(String url, String method, Optional<JSONObject> body, Optional<Map<String,String>> params) throws IOException {
        RequestBody requestBody = body.map(b -> RequestBody.create(MediaType.parse(APPLICATION_JSON), b.toString()))
                .orElse(null);
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder httpBuilder = HttpUrl.parse(baseUrl + url).newBuilder();
        params.ifPresent(p-> p.forEach(httpBuilder::addQueryParameter));
        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .method(method.toUpperCase(), requestBody)
                .addHeader("Content-Type", APPLICATION_JSON)
                .build();
        return client.newCall(request).execute();
    }

}
