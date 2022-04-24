package com.qianhang.community;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpProvider {
    public static void main(String[] args) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+"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")
                .build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println("000");
            String user=response.body().string();
            System.out.println(user);
            System.out.println("111");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
