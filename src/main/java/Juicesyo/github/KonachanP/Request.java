package Juicesyo.github.KonachanP;

import okhttp3.*;
import java.io.IOException;

public class Request {
    public static String content;
    public static String KonachanP(String page) {
        //String page="1";
        String url = "https://konachan.net/post?page=" + page;
        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            content = response.body().string();
        } catch (IOException e) {
            //e.printStackTrace();

        }
        return content;
    }
/*
    public static InputStream result;
    public static InputStream Image_Input(String url) {
        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
             result = response.body().byteStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
*/
}