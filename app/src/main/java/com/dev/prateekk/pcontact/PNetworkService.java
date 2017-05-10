package com.dev.prateekk.pcontact;

import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by prateek.kesarwani on 09/05/17.
 */

public interface PNetworkService {

    String BASE_URL = "http://gojek-contacts-app.herokuapp.com/";

    @GET("contacts.json")
    Observable<ArrayList<PContactsListRequest>> fetchContactsList();

    @GET("/contacts/{id}.json ")
    Observable<PContactRequest> fetchContact(@Path("id") Integer id);


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