package com.example.serviceetudiant;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.serviceetudiant.model.ServiceUser;
import com.example.serviceetudiant.model.User;
import com.example.serviceetudiant.services.ApiService;
import com.example.serviceetudiant.utils.SessionManager;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AcceuilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AcceuilFragment extends Fragment {
    SessionManager session;
    ApiService apiService = new ApiService();
    TextView welcomeTxt;
    TextView totalDemander;
    TextView totalTerminer;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AcceuilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AcceuilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AcceuilFragment newInstance(String param1, String param2) {
        AcceuilFragment fragment = new AcceuilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_acceuil, container, false);
        session = new SessionManager(v.getContext());
        User connectedUser = session.getConnectedUser();
        List<ServiceUser> serviceUserList = apiService.getServiceUserById(connectedUser.getId());
        welcomeTxt = (TextView) v.findViewById(R.id.welcomeTxt);
        totalDemander = (TextView) v.findViewById(R.id.totalDemander);
        totalTerminer = (TextView) v.findViewById(R.id.totalTerminer);
        welcomeTxt.setText("Bienvenue,\n"+ connectedUser.getFullName());
        int totalServiceDemander = 0;
        int totalServiceTerminer = 0;
        for (ServiceUser serviceUser: serviceUserList) {
            if(serviceUser.getEtatDemande().equalsIgnoreCase("demander")){
                totalServiceDemander +=1;
            }
            if(serviceUser.getEtatDemande().equalsIgnoreCase("terminer")){
                totalServiceTerminer +=1;
            }
        }
        totalDemander.setText(String.format("%d", totalServiceDemander));
        totalTerminer.setText(String.format("%d", totalServiceTerminer));

        // Inflate the layout for this fragment
        return v;
    }
}