package com.example.newsapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class NetworkAPI {
    private static NetworkAPI mInstance;
    private static final String BASE_URL = "https://newsapi.org/";
    private Retrofit mRetrofit;

    private NetworkAPI() { // build Retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkAPI getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkAPI();
        }
        return mInstance;
    }
    public PlaceHolderAPI getJSONApi() {
        return mRetrofit.create(PlaceHolderAPI.class);
    }
}
