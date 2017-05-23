package com.dev.prateekk.pcontact.application;

import com.dev.prateekk.pcontact.network.PContactService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by prateek.kesarwani on 13/05/17.
 */

@Module(includes = {PNetworkModule.class})
public class PContactServiceModule {

    @Provides
    @PApplicationScope
    public PContactService contactService(Retrofit retrofit) {
        return retrofit.create(PContactService.class);
    }

    @Provides
    @PApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://gojek-contacts-app.herokuapp.com/")
                .build();
    }

    @Provides
    @PApplicationScope
    public Gson gson() {
        // Could alter some gson behaviour like, gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return new GsonBuilder().create();
    }
}