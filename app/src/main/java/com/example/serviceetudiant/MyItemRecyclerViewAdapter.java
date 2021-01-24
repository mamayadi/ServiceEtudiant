package com.example.serviceetudiant;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.serviceetudiant.model.Service;
import com.example.serviceetudiant.model.ServiceUser;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ServiceUser}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<ServiceUser> mValues;
    private final List<Service> services;

    public MyItemRecyclerViewAdapter(List<ServiceUser> items, List<Service> services) {
        mValues = items;
        this.services = services;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(String.valueOf(position + 1));
        holder.mContentView.setText(
                "Service : " + getServiceLibelle(mValues.get(position).getIdService()) + "\n" +
                        "Etat : " + mValues.get(position).getEtatDemande() + "\n" +
                        "Date demande : " + mValues.get(position).getDateDemande() + "\n"
        );
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public ServiceUser mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public String getServiceLibelle(int id) {
        Service foundedService = null;
        for (Service service : services) {
            if (service.getId() == id) {
                foundedService = service;
                break;
            }
        }
        if (foundedService != null) {
            return foundedService.getLibelle();
        }
        return "";
    }
}