package com.dev.prateekk.pcontact.dagger;

import com.dev.prateekk.pcontact.PApplication;
import com.dev.prateekk.pcontact.dagger.modules.PContactServiceModule;

import dagger.Component;

/**
 * Created by prateek.kesarwani on 13/05/17.
 */

@Component(modules = {PContactServiceModule.class})
@PApplicationScope
public interface PApplicationComponent {
    void inject(PApplication application);
}
