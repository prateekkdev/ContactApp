package com.dev.prateekk.pcontact.home;

import dagger.Component;

/**
 * Created by prateek.kesarwani on 22/05/17.
 */

@ContactsListActivityScope
@Component(modules = {ContactsListActivityModule.class})
public interface ContactsListActivityComponent {
    // ContactsListAdapter getContactsAdapter();

    void inject(ContactsListActivity activity);
}