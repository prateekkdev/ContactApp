package com.dev.prateekk.pcontact;

import android.app.Application;
import android.content.Context;

import com.dev.prateekk.pcontact.dagger.DaggerPApplicationComponent;
import com.dev.prateekk.pcontact.dagger.PApplicationComponent;
import com.dev.prateekk.pcontact.network.PContactService;

import javax.inject.Inject;

/**
 * Created by prateek.kesarwani on 13/05/17.
 */

public class PApplication extends Application {

    private PApplicationComponent applicationComponent;

    public static PApplication get(Context context) {
        return (PApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerPApplicationComponent.builder().build();
    }

    public PApplicationComponent component() {
        return applicationComponent;
    }

}