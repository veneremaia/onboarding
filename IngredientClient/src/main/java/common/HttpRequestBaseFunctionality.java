package common;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class HttpRequestBaseFunctionality {

    private final String APPLICATION_JSON = "application/json";
    private final String baseUrl;

    public HttpRequestBaseFunctionality(String baseUrl){
        this.baseUrl = baseUrl;
    }

    protected Response getResponse(String url, String method, Optional<RequestBody> body, Optional<Map<String,String>> params) throws IOException {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder httpBuilder = HttpUrl.parse(baseUrl + url).newBuilder();
        params.ifPresent(p-> p.forEach(httpBuilder::addQueryParameter));
        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .method(method.toUpperCase(), body.orElse(null))
                .addHeader("Content-Type", APPLICATION_JSON)
                .build();
        return client.newCall(request).execute();
    }
}
