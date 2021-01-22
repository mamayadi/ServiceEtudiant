package com.example.serviceetudiant.services;

import com.example.serviceetudiant.model.ServiceUser;
import com.example.serviceetudiant.model.User;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class ApiService {

    String url = "http://localhost/serviceetudiant/";

    public JSONObject login(String login, String passwd) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url + "login.php");
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("login", login);
            jsonBody.put("passwd", passwd);
            httppost.setHeader("Content-type", "application/json");
            HttpResponse response = httpclient.execute(httppost);
            String jsonString = EntityUtils.toString(response.getEntity());
            JSONObject userBody = new JSONObject(jsonString);
            return userBody;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject signup(User user) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url + "signup.php");
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("iu", user.getIu());
            jsonBody.put("nom", user.getNom());
            jsonBody.put("prenom", user.getPrenom());
            jsonBody.put("id_etablissement", user.getId_etablissement());
            jsonBody.put("login", user.getLogin());
            jsonBody.put("passwd", user.getPasswd());
            jsonBody.put("autorisation", user.getAutorisation());
            httppost.setHeader("Content-type", "application/json");
            HttpResponse response = httpclient.execute(httppost);
            String jsonString = EntityUtils.toString(response.getEntity());
            JSONObject userBody = new JSONObject(jsonString);
            return userBody;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getAllServices() {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url + "service.php");
            HttpResponse response = httpclient.execute(httpget);
            String jsonString = EntityUtils.toString(response.getEntity());
            JSONArray serviceJsonArr = new JSONArray(jsonString);
            return serviceJsonArr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getAllEtablissement() {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url + "etablissement.php");
            HttpResponse response = httpclient.execute(httpget);
            String jsonString = EntityUtils.toString(response.getEntity());
            JSONArray etablissementJsonArr = new JSONArray(jsonString);
            return etablissementJsonArr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getServiceUserById(int userId) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url + "serviceuser.php?id="+userId);
            HttpResponse response = httpclient.execute(httpget);
            String jsonString = EntityUtils.toString(response.getEntity());
            JSONArray serviceJsonArr = new JSONArray(jsonString);
            return serviceJsonArr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject addServiceUser(ServiceUser serviceUser) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url + "serviceuser.php");
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("id_service", serviceUser.getIdService());
            jsonBody.put("id_user", serviceUser.getIdUser());
            jsonBody.put("date_demande", serviceUser.getDateDemande());
            jsonBody.put("etat_demande", serviceUser.getEtatDemande());
            httppost.setHeader("Content-type", "application/json");
            HttpResponse response = httpclient.execute(httppost);
            String jsonString = EntityUtils.toString(response.getEntity());
            JSONObject serviceUserObject = new JSONObject(jsonString);
            return serviceUserObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
