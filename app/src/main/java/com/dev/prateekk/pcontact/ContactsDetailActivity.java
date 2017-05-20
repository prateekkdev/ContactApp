package com.dev.prateekk.pcontact;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by prateek.kesarwani on 17/05/17.
 */

public class ContactsDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        this.setContentView(R.layout.contacts_detail);
        // DataBindingUtil.setContentView(this, R.layout.contacts_detail);
    }
}
