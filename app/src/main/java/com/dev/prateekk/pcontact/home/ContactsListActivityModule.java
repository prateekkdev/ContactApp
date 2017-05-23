package com.dev.prateekk.pcontact.home;

import com.dev.prateekk.pcontact.network.PContactsListRequest;

import java.util.ArrayList;

import dagger.Module;

/**
 * Created by prateek.kesarwani on 22/05/17.
 */

@ContactsListActivityScope
@Module
public class ContactsListActivityModule {

    private final ArrayList<PContactsListRequest> requestsList;

    public ContactsListActivityModule(ArrayList<PContactsListRequest> requestsList) {
        this.requestsList = requestsList;
    }

    /*
    @Provides
    @ContactsListActivityScope
    public ContactsListAdapter contactsListActivity() {
        return new ContactsListAdapter(requestsList);
    }
    */
}
