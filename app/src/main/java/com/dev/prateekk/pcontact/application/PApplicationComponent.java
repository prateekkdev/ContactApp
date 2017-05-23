package com.dev.prateekk.pcontact.application;

import dagger.Component;

/**
 * Created by prateek.kesarwani on 13/05/17.
 */

@Component(modules = {PContactServiceModule.class})
@PApplicationScope
public interface PApplicationComponent {
    void inject(PApplication application);
}
