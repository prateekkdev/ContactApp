package com.dev.prateekk.pcontact;

import java.util.ArrayList;

import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by prateek.kesarwani on 09/05/17.
 */

public interface PNetworkService {

    String BASE_URL = "http://1gojek-contacts-app.herokuapp.com/";

    @GET("contacts.json")
    Flowable<ArrayList<PContactsRequest>> fetchContacts();

    class Client {

        // Ideally, even this should not be static
        private static PNetworkService networkServcice;

        public static PNetworkService getService() {

            if (networkServcice == null) {

                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                // Setting desired log level
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(logging);

                Retrofit retrofit = new Retrofit.Builder()
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();

                networkServcice = retrofit.create(PNetworkService.class);
            }

            return networkServcice;
        }
    }
}