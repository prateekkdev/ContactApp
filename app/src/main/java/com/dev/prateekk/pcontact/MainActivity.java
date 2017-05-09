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

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ArrayList<PContactsRequest> contactsRequests;

    Subscription subscription;

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


        PNetworkService.Client.getService().fetchContacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "Error doOnError", Toast.LENGTH_SHORT).show();
                    }
                })
                .doOnNext(new Consumer<ArrayList<PContactsRequest>>() {
                    @Override
                    public void accept(@NonNull ArrayList<PContactsRequest> pContactsRequests) throws Exception {
                        Toast.makeText(MainActivity.this, "Success doOnNext", Toast.LENGTH_SHORT).show();
                    }
                })
                .subscribe(new Subscriber<ArrayList<PContactsRequest>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Toast.makeText(MainActivity.this, "Subscribed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ArrayList<PContactsRequest> pContactsRequests) {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable t) {
                        Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(MainActivity.this, "Complete", Toast.LENGTH_SHORT).show();
                    }
                });

                /*

                .doOnRequest(new LongConsumer() {
                    @Override
                    public void accept(long t) throws Exception {
                        Toast.makeText(MainActivity.this, "doOnRequest", Toast.LENGTH_SHORT).show();
                    }
                })

                 */

        /*
        contactsCall.doOnNext(new Consumer<ArrayList<PContactsRequest>>() {
            @Override
            public void accept(@NonNull ArrayList<PContactsRequest> pContactsRequests) throws Exception {

            }
        }).doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {

            }
        });
        */
    }

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