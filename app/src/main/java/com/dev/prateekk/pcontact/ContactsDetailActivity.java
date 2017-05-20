package com.dev.prateekk.pcontact;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dev.prateekk.pcontact.databinding.ActivityContactsDetailBinding;

/**
 * Created by prateek.kesarwani on 17/05/17.
 */

public class ContactsDetailActivity extends AppCompatActivity {

    ActivityContactsDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_contacts_detail);
    }
}
