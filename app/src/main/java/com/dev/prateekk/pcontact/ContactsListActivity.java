package com.dev.prateekk.pcontact;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dev.prateekk.pcontact.network.PContactService;

import org.reactivestreams.Subscription;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ContactsListActivity extends AppCompatActivity {

    ArrayList<PContactsListRequest> contactsRequests;

    Subscription subscription;

    PContactService contactService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        contactService = PApplication.get(this).component().getContactService();

        contactService.fetchContactsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<PContactsListRequest>>() {
                    @Override
                    public void accept(@NonNull ArrayList<PContactsListRequest> pContactsListRequests) throws Exception {
                        Toast.makeText(ContactsListActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Toast.makeText(ContactsListActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /*
    private void makeCall() {

        // Chaining api calls, example

        contactService.fetchContactsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ArrayList<PContactsListRequest>, Integer>() {
                    @Override
                    public Integer apply(@NonNull ArrayList<PContactsListRequest> pContactsListRequests) throws Exception {
                        Toast.makeText(ContactsListActivity.this, "Got lists", Toast.LENGTH_SHORT).show();
                        return pContactsListRequests.get(0).getId();
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<Integer, Observable<PContactRequest>>() {
                    @Override
                    public Observable<PContactRequest> apply(@NonNull Integer integer) throws Exception {
                        return contactService.fetchContact(integer);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PContactRequest>() {
                    @Override
                    public void accept(@NonNull PContactRequest pContactRequest) throws Exception {
                        Toast.makeText(ContactsListActivity.this, "Success Again", Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Toast.makeText(ContactsListActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}