package com.dev.prateekk.pcontact.home;

import com.dev.prateekk.pcontact.network.PContactRequest;

/**
 * Created by prateek.kesarwani on 23/05/17.
 */

public interface ContactsListContract {

    interface View {
        void showProgress();

        void hideProgress();

        void onResult();

        void onError();
    }

    interface Presenter {

        void fetchDetails();

        void onItemClick(PContactRequest contactRequest);
    }

}
