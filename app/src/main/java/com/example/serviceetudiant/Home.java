package com.example.serviceetudiant;

import androidx.appcompat.app.ActionBarDrawerToggle;

import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviceetudiant.model.User;
import com.example.serviceetudiant.utils.SessionManager;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // Session Manager Class
    SessionManager session;

    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private TextView usernameTxt;

    private boolean viewIsAtHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Session Manager
        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();
        displayView(R.id.activity_main_drawer_home);
        User connectedUser = session.getConnectedUser();

        View headerView = navigationView.getHeaderView(0);
        usernameTxt = (TextView) headerView.findViewById(R.id.userFullName);
        usernameTxt.setText(connectedUser.getFullName());
        Toast.makeText(getApplicationContext(), "Bienvenue " + connectedUser.getFullName(), Toast.LENGTH_LONG).show();
    }

    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (viewId) {
            case R.id.activity_main_drawer_home:
                fragment = new AcceuilFragment();
                viewIsAtHome = true;
                title = "Acceuil";
                break;

            case R.id.activity_main_drawer_demande:
                fragment = new DemandeServiceFragment();
                viewIsAtHome = false;
                title = "Demande de service";
                break;
            case R.id.activity_main_drawer_history:
                fragment = new History();
                viewIsAtHome = false;
                title = "Historique";
                break;
            case R.id.activity_main_drawer_profile:
                fragment = new AcceuilFragment();
                viewIsAtHome = false;
                title = "Profil";
                break;
            case R.id.activity_main_drawer_logout:
                viewIsAtHome = false;
                AlertDialog.Builder boite = new AlertDialog.Builder(Home.this);
                boite.setTitle("Déconnexion");
                boite.setIcon(R.drawable.baseline_exit_to_app_white_24dp);
                boite.setMessage("Êtes-vous sûr de vous déconnecter");
                boite.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        session.logoutUser();
                        Toast.makeText(getApplicationContext(), "Veuillez vous reconnecter", Toast.LENGTH_LONG).show();
                        session.checkLogin();
                    }
                });
                boite.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                boite.show();
                break;
            default:
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.activity_main_frame_layout, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        if (!viewIsAtHome) { //if the current view is not the News fragment
            displayView(R.id.activity_main_drawer_home); //display the News fragment
        } else {
            moveTaskToBack(true);  //If view is in News fragment, exit application
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // 4 - Handle Navigation Item Click
       /* int id = item.getItemId();

        switch (id) {
            case R.id.activity_main_drawer_home:
                break;
            case R.id.activity_main_drawer_profile:
                break;
            case R.id.activity_main_drawer_demmande_de_service:
                break;
            case R.id.activity_main_drawer_history:
                break;
            case R.id.activity_main_drawer_logout:
                break;
            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);*/
        displayView(item.getItemId());
        return true;
    }

    // ---------------------
    // CONFIGURATION
    // ---------------------

    // 1 - Configure Toolbar
    private void configureToolBar() {
        this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(this.toolbar);
    }

    // 2 - Configure Drawer Layout
    private void configureDrawerLayout() {
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, this.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // 3 - Configure NavigationView
    private void configureNavigationView() {
        this.navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}