package com.example.serviceetudiant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviceetudiant.model.Etablissement;
import com.example.serviceetudiant.model.Service;
import com.example.serviceetudiant.model.User;
import com.example.serviceetudiant.services.ApiService;
import com.example.serviceetudiant.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    private SessionManager session;
    private ApiService apiService = new ApiService();

    private List<Etablissement> etablissementList;

    TextView iuInput;
    TextView nomInput;
    TextView prenomInput;
    TextView loginInput;
    TextView passwdInput;
    Spinner etablissementSpinner;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        StrictMode.enableDefaults();
        session = new SessionManager(getApplicationContext());
        etablissementList = apiService.getAllEtablissement();

        iuInput = (TextView) findViewById(R.id.iuInput);
        nomInput = (TextView) findViewById(R.id.nomInput);
        prenomInput = (TextView) findViewById(R.id.prenomInput);
        loginInput = (TextView) findViewById(R.id.signupLoginInput);
        passwdInput = (TextView) findViewById(R.id.signupPasswordInput);
        etablissementSpinner = (Spinner) findViewById(R.id.signupSpinner);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        if(etablissementList != null){
            List<String> etablissementsLibelleArr = new ArrayList<String>();
            for (Etablissement etablissement : etablissementList) {
                etablissementsLibelleArr.add(etablissement.getLibelle());
            }
            ArrayAdapter<String> inputAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.select_dialog_singlechoice, etablissementsLibelleArr);
            etablissementSpinner.setAdapter(inputAdapter);
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Etablissement selectedEtablissement = null;
                for (Etablissement etablissement : etablissementList) {
                    if (etablissement.getLibelle().equals(etablissementSpinner.getSelectedItem().toString())) {
                        selectedEtablissement = etablissement;
                        break;
                    }
                }
                if (selectedEtablissement != null) {
                    User user = new User(
                            Integer.valueOf(iuInput.getText().toString()),
                            nomInput.getText().toString(),
                            prenomInput.getText().toString(),
                            selectedEtablissement.getId(),
                            loginInput.getText().toString(),
                            passwdInput.getText().toString(),
                            "Etudiant"
                    );
                    User signedUpUser = apiService.signup(user);
                    if (signedUpUser != null) {
                        session.createLoginSession(signedUpUser);
                        Intent ind = new Intent(SignUpActivity.this, Home.class);
                        startActivity(ind);
                    } else {
                        Toast.makeText(getApplicationContext(), "Il y a un erreur lors de l'insertion", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "SVP selectionnée un etablissement", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}