package bai.bai.bai.demo.http;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpClient {

    private static final String HOST_TEST = "https://aptxn.credopay.info:3053/v1.0";
    public static final MediaType jsonType = MediaType.parse("application/json; charset=utf-8");
    private static Context mContext;

    public static HttpClient getInstance() {
        return Holder.httpClient;
    }

    private static class Holder {
        private static HttpClient httpClient = new HttpClient();
    }

    private OkHttpClient okHttpClient;

    private HttpClient() {
        okHttpClient = new OkHttpClient.Builder()
                .build();
    }

    public String post(String action, JSONObject objectData) throws IOException {

        RequestBody requestBody = RequestBody.create(jsonType, objectData.toString());

        Request request = new Request.Builder()
                .url(HOST_TEST + action)
                .post(requestBody)
                .build();

        Response response = okHttpClient.newCall(request).execute();

        String body = response.body().string();
        Log.d("baibai", "action = " + action + "的返回数据：" + body);
        return body;
//        Result result = new Gson().fromJson(body, Result.class);
//        return result;
    }
}
