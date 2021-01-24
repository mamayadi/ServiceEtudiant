package com.example.serviceetudiant;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
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
        displayView(R.id.activity_main_drawer_news);
        User connectedUser = session.getConnectedUser();
        Toast.makeText(getApplicationContext(), connectedUser.getNom() + " " + connectedUser.getPasswd(), Toast.LENGTH_LONG).show();
    }

    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (viewId) {
            case R.id.activity_main_drawer_news:
                fragment = new AcceuilFragment();
                viewIsAtHome = true;
                title  = "News";
                break;

            case R.id.activity_main_drawer_demande:
                fragment = new DemandeServiceFragment();
                viewIsAtHome = false;
                title  = "Demande service";
                break;
            case R.id.activity_main_drawer_history:
                fragment = new History();
                viewIsAtHome = false;
                title  = "History";
                break;
            case R.id.activity_main_drawer_profile:
                fragment = new AcceuilFragment();
                viewIsAtHome = false;
                title  = "Profile";
                break;
            case R.id.activity_main_drawer_settings:
                fragment = new AcceuilFragment();
                viewIsAtHome = false;
                title  = "Settings";
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
        }else {
            super.onBackPressed();
        }
        if (!viewIsAtHome) { //if the current view is not the News fragment
            displayView(R.id.activity_main_drawer_news); //display the News fragment
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