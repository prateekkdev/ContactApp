package com.dev.prateekk.pcontact;

import android.app.Application;
import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by prateek.kesarwani on 13/05/17.
 */

public class PApplication extends Application {

    private GojekService gojekService;

    public static PApplication get(Context context) {
        return (PApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // Setting desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(logging)
                .build();

        // Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://gojek-contacts-app.herokuapp.com/")
                .build();

        gojekService = retrofit.create(GojekService.class);
    }

    public GojekService getGojekService() {
        return gojekService;
    }
}