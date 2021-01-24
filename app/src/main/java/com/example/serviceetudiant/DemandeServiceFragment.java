package com.example.serviceetudiant;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.serviceetudiant.model.Service;
import com.example.serviceetudiant.model.ServiceUser;
import com.example.serviceetudiant.services.ApiService;
import com.example.serviceetudiant.utils.SessionManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DemandeServiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DemandeServiceFragment extends Fragment {

    private ApiService apiService = new ApiService();
    private SessionManager session;

    Spinner serviceInput;
    Button btnDemande;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DemandeServiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DemandeServiceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DemandeServiceFragment newInstance(String param1, String param2) {
        DemandeServiceFragment fragment = new DemandeServiceFragment();
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
        session = new SessionManager(getActivity().getApplicationContext());
        final List<Service> services = apiService.getAllServices();
        View v = inflater.inflate(R.layout.fragment_demande_service, container, false);
        serviceInput = (Spinner) v.findViewById(R.id.serviceInput);
        btnDemande = (Button) v.findViewById(R.id.btnDemande);
        List<String> servicesLibelleArr = new ArrayList<String>();
        for (Service service : services) {
            servicesLibelleArr.add(service.getLibelle());
        }
        ArrayAdapter<String> inputAdapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.select_dialog_singlechoice, servicesLibelleArr);
        serviceInput.setAdapter(inputAdapter);
        btnDemande.setOnClickListener(new View.OnClickListener() {
             @Override
              public void onClick(View v) {
                 Service selectedService = null;
                 for (Service service: services) {
                     if(service.getLibelle().equals(serviceInput.getSelectedItem().toString())){
                         selectedService = service;
                         break;
                     }
                 }
                 if(selectedService != null){
                     ServiceUser serviceUser = new ServiceUser(selectedService.getId(), session.getConnectedUser().getId(), new Date().toString(), "Demander" );
                     ServiceUser serviceUserSubmitted = apiService.addServiceUser(serviceUser);
                     if(serviceUserSubmitted != null){
                         Toast.makeText(getContext(), "Service demander avec succées", Toast.LENGTH_LONG).show();
                     } else {
                         Toast.makeText(getContext(), "un erreur est survenue", Toast.LENGTH_LONG).show();
                     }
                 }
              }
        });
        // Inflate the layout for this fragment
        return v;
    }
}