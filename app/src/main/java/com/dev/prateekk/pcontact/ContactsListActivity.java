package com.dev.prateekk.pcontact;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.dev.prateekk.pcontact.databinding.ActivityContactsListBinding;
import com.dev.prateekk.pcontact.network.PContactService;

import org.reactivestreams.Subscription;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ContactsListActivity extends AppCompatActivity {

    ArrayList<PContactsListRequest> contactsRequests;

    Subscription subscription;

    PContactService contactService;

    ActivityContactsListBinding binding;

    ContactsListAdapter contactsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_contacts_list);

        binding.fab.setOnClickListener(
                (a) -> Snackbar.make(a,
                        "Replace with your own action",
                        Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());

        contactService = PApplication.get(this).component().getContactService();

        PContactsListRequest listRequest = new PContactsListRequest();
        listRequest.setFirstName("Prateek");

        ArrayList<PContactsListRequest> pContactsListRequests = new ArrayList<>();
        pContactsListRequests.add(listRequest);

        contactsListAdapter = new ContactsListAdapter(pContactsListRequests);

        binding.mainList.setLayoutManager(new LinearLayoutManager(this));
        binding.mainList.setAdapter(contactsListAdapter);

        contactService.fetchContactsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((a) -> {
                            contactsListAdapter.updateList(a);
                            Toast.makeText(ContactsListActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        },
                        (a) -> Toast.makeText(ContactsListActivity.this, "Failure", Toast.LENGTH_SHORT).show());
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
}