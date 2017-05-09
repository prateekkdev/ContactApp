package com.dev.prateekk.pcontact;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by prateek.kesarwani on 09/05/17.
 */

public interface PNetworkService {

    String BASE_URL = "http://gojek-contacts-app.herokuapp.com/";

    @GET("contacts.json")
    Call<ArrayList<PContactsRequest>> fetchContacts();

    class Client {

        // Ideally, even this should not be static
        private static PNetworkService networkServcice;

        public static PNetworkService getService() {

            if (networkServcice == null) {

                Retrofit retrofit = new Retrofit.Builder()
                        .client(new OkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();

                networkServcice = retrofit.create(PNetworkService.class);
            }

            return networkServcice;
        }
    }
}