package com.example.serviceetudiant;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.serviceetudiant.model.Service;
import com.example.serviceetudiant.model.ServiceUser;
import com.example.serviceetudiant.model.User;
import com.example.serviceetudiant.services.ApiService;
import com.example.serviceetudiant.utils.SessionManager;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class History extends Fragment {
    SessionManager session;
    private ApiService apiService = new ApiService();
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public History() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static History newInstance(int columnCount) {
        History fragment = new History();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_list, container, false);
        session = new SessionManager(getActivity().getApplicationContext());
        User user = session.getConnectedUser();
        List<ServiceUser> serviceUserList = apiService.getServiceUserById(user.getId());
        List<Service> serviceList = apiService.getAllServices();
        mColumnCount = serviceUserList.size();
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(serviceUserList, serviceList));
        }
        return view;
    }
}