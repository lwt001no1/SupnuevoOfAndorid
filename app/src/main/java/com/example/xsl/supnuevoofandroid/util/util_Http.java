package com.example.xsl.supnuevoofandroid.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class util_Http {

    private static String ip = "http://192.168.1.150:8080/";
    private static String url = ip + "supnuevo/func/";
    private static String json = "{\"loginName:\"supnuevo\",\"password:\"1\",\"loginType:\"1\"}";
    private static String sessionId = "";
    private static String setCookie = "";
    private static HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

    public static String post() throws Exception {
        String s = "";
        //创建一个OKHttpClient对象
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json;charset=utf-8"), json);
        //创建Request对象
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        //创建一个Call对象
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("postError" + response);
        Log.i("postInfo", response.body().toString());
        s = response.body().toString();
        return s;
    }

    public static String post_Asyn(Map<String, String> params, final String u) throws Exception {
        JSONObject object = new JSONObject();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            object.put(entry.getKey(), entry.getValue());
        }
        String s = "";
        //创建一个OKHttpClient对象
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());
        //创建Request对象
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url.host(), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                }).build();
        final Request request = new Request.Builder()
                .url(url + u)
                .post(requestBody)
                .build();


        //创建一个Call对象
        Call call = client.newCall(request);
        Response response = client.newCall(request).execute();
        String sss = response.body().string();//response.body().string()只能调用一次哦

        return sss;
    }

    private static void saveSessionId(Headers headers) {
        setCookie = headers.get("Set-Cookie");
        String[] ss = setCookie.split(";");
        String[] ss1 = ss[0].split("=");
        sessionId = ss1[1];
    }
}
