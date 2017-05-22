package com.dev.prateekk.pcontact.dagger.modules;

import com.dev.prateekk.pcontact.ContactsListAdapter;
import com.dev.prateekk.pcontact.PContactsListRequest;
import com.dev.prateekk.pcontact.dagger.ContactsListActivityScope;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

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

    @Provides
    @ContactsListActivityScope
    public ContactsListAdapter contactsListActivity() {
        return new ContactsListAdapter(requestsList);
    }
}
