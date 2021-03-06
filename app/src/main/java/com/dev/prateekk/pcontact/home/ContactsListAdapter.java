package com.dev.prateekk.pcontact.home;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.prateekk.pcontact.R;
import com.dev.prateekk.pcontact.details.ContactsDetailActivity;
import com.dev.prateekk.pcontact.network.PContactsListRequest;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by prateek.kesarwani on 13/05/17.
 */

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactsViewHolder> {

    private ArrayList<PContactsListRequest> pContactsListRequests;

    @Inject
    public ContactsListAdapter() {
        pContactsListRequests = new ArrayList<>();
    }

    public ContactsListAdapter(ArrayList<PContactsListRequest> pContactsListRequests) {
        this.pContactsListRequests = pContactsListRequests;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactsViewHolder(parent.inflate(parent.getContext(),
                R.layout.contacts_list_item, null));
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        holder.name.setText("" + pContactsListRequests.get(position).getFirstName());

        holder.name.setOnClickListener(a -> holder.name.getContext()
                .startActivity(new Intent(holder.name.getContext(), ContactsDetailActivity.class))
        );
    }

    @Override
    public int getItemCount() {
        return pContactsListRequests.size();
    }

    public static class ContactsViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public ContactsViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.txt_name);
        }
    }

    public void updateList(ArrayList<PContactsListRequest> pContactsListRequests) {
        this.pContactsListRequests = pContactsListRequests;
        notifyDataSetChanged();
    }
}