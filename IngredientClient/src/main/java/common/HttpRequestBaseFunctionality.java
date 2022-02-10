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

public class HttpRequestBaseFunctionality {

    private final String APPLICATION_JSON = "application/json";
    private String baseUrl;

    public HttpRequestBaseFunctionality(String baseUrl){
        this.baseUrl = baseUrl;
    }
    protected Response getResponse(String url, String method, JSONObject body, Map<String,String>params) throws IOException {
        RequestBody requestBody = null;
        if(body!=null)
            requestBody = RequestBody.create(MediaType.parse(APPLICATION_JSON), body.toString());
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder httpBuilder = HttpUrl.parse(baseUrl + url).newBuilder();
        if (params != null) {
            for(Map.Entry<String, String> param : params.entrySet()) {
                httpBuilder.addQueryParameter(param.getKey(),param.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .method(method.toUpperCase(), requestBody)
                .addHeader("Content-Type", APPLICATION_JSON)
                .build();
        return client.newCall(request).execute();
    }

}
