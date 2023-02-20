package com.example.tiki.app_canhbao.severs;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {
    private ServerData api;

    private static RetrofitAPI instance = null;

    private RetrofitAPI (){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ServerData.class);
    }

    public static synchronized RetrofitAPI getInstance() {
        if (instance == null) {
            instance = new RetrofitAPI();
        }
        return instance;
    }

    public ServerData getApiNotification() {
        return api;
    }
}
