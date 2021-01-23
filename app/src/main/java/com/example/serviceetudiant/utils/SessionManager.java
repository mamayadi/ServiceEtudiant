package com.example.serviceetudiant.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.serviceetudiant.MainActivity;
import com.example.serviceetudiant.model.User;

public class SessionManager {
    /**
     * Shared Preferences
     */
    SharedPreferences pref;
    /**
     * Editor for Shared preferences
     */
    SharedPreferences.Editor editor;
    /**
     * Context
     */
    Context _context;
    /**
     * Shared pref mode
     */
    int PRIVATE_MODE = 0;
    /**
     * Sharedpref file name
     */
    private static final String PREF_NAME = "AndroidHivePref";
    /**
     * All Shared Preferences Keys
     */
    private static final String IS_LOGIN = "IsLoggedIn";

    /**
     * Constructor
     */
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(User user) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        editor.putInt("user_id", user.getId());
        editor.putInt("user_iu", user.getIu());
        editor.putString("user_nom", user.getNom());
        editor.putString("user_prenom", user.getPrenom());
        editor.putString("user_login", user.getLogin());
        editor.putString("user_passwd", user.getPasswd());
        editor.putInt("user_id_etablissement", user.getId_etablissement());
        editor.putString("user_autorisation", user.getAutorisation());
        editor.commit();
    }

    /**
     * Get stored session data
     */
    public User getConnectedUser() {
        User connectedUser = new User(
                pref.getInt("user_id", -1),
                pref.getInt("user_iu", -1),
                pref.getString("user_nom", null),
                pref.getString("user_prenom", null),
                pref.getInt("user_id_etablissement", -1),
                pref.getString("user_login", null),
                pref.getString("user_passwd", null),
                pref.getString("user_autorisation", null)
        );
        return connectedUser;
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin() {
        boolean isLoggedIn = pref.getBoolean(IS_LOGIN, false);
        // Check login status
        if (!isLoggedIn) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, MainActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Staring Login Activity
            _context.startActivity(i);
        }
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, MainActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring Login Activity
        _context.startActivity(i);
    }
}
