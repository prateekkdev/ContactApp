package com.dev.prateekk.pcontact.application;

import android.app.Application;
import android.content.Context;

import com.dev.prateekk.pcontact.network.PContactService;

import javax.inject.Inject;

/**
 * Created by prateek.kesarwani on 13/05/17.
 */

public class PApplication extends Application {

    private PApplicationComponent applicationComponent;

    @Inject
    PContactService contactService;

    public static PApplication get(Context context) {
        return (PApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerPApplicationComponent.builder().build();
        applicationComponent.inject(this);
    }

    public PContactService contactService() {
        return contactService;
    }
}