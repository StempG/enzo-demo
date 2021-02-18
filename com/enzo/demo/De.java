package com.enzo.demo;

import okhttp3.*;

public class De {


    public static void main(String[] args) throws Exception{



        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .get()
                .url("https://www.baidu.com")
                .build();


        Call call = client.newCall(request);


        Response response = call.execute();
        ResponseBody body = response.body();

        System.out.println(body.toString());
    }




}
