package com.example.serviceetudiant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviceetudiant.model.User;
import com.example.serviceetudiant.services.ApiService;
import com.example.serviceetudiant.utils.SessionManager;

public class MainActivity extends AppCompatActivity {
    // Session Manager Class
    SessionManager session;

    Button btnLogin;
    EditText loginInput;
    EditText passwordInput;
    Button btnSignup;
    ApiService api = new ApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Session Manager
        session = new SessionManager(getApplicationContext());

        loginInput = (EditText) findViewById(R.id.loginInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button) findViewById(R.id.btnSignup);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginInput.getText().toString();
                String password = passwordInput.getText().toString();
                Intent ind = new Intent(MainActivity.this, Home.class);
                StrictMode.enableDefaults();
                User user = api.login(login, password);
                if (user != null) {
                    session.createLoginSession(user);
                    startActivity(ind);
                } else {
                    Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ind = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(ind);
            }
        });


    }

    /*public void onViewCreated(View view, Bundle savedInstanceState)
    {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
    }*/
}