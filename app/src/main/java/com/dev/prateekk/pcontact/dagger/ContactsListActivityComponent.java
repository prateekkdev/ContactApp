package com.dev.prateekk.pcontact.dagger;

import com.dev.prateekk.pcontact.ContactsListAdapter;
import com.dev.prateekk.pcontact.dagger.modules.ContactsListActivityModule;

import dagger.Component;

/**
 * Created by prateek.kesarwani on 22/05/17.
 */

@ContactsListActivityScope
@Component(modules = {ContactsListActivityModule.class})
public interface ContactsListActivityComponent {
    ContactsListAdapter getContactsAdapter();
}