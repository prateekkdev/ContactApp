package com.dev.prateekk.pcontact.dagger.modules;

import com.dev.prateekk.pcontact.dagger.PApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by prateek.kesarwani on 13/05/17.
 */

@Module
public class PNetworkModule {

    @Provides
    @PApplicationScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient
                .Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @PApplicationScope
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }
}