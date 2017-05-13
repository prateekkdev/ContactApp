package com.dev.prateekk.pcontact;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by prateek.kesarwani on 09/05/17.
 */

public interface GojekService {

    @GET("contacts.json")
    Observable<ArrayList<PContactsListRequest>> fetchContactsList();

    @GET("/contacts/{id}.json ")
    Observable<PContactRequest> fetchContact(@Path("id") Integer id);
}