package com.example.serviceetudiant.services;

import android.os.StrictMode;

import com.example.serviceetudiant.model.Service;
import com.example.serviceetudiant.model.ServiceUser;
import com.example.serviceetudiant.model.User;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiService {

    String url = "http://192.168.0.4/serviceetudiant/";

    public User login(String login, String passwd) {
        try {
            StrictMode.enableDefaults();
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url + "login.php");
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("login", login);
            jsonBody.put("passwd", passwd);
            httppost.setHeader("Content-type", "application/json");

            StringEntity se = new StringEntity(jsonBody.toString());
            httppost.setEntity(se);
            HttpResponse response = httpclient.execute(httppost);
            String jsonString = EntityUtils.toString(response.getEntity());

            JSONObject userBody = new JSONObject(jsonString);
            User user = new User(userBody.getInt("id"), userBody.getInt("iu"), userBody.getString("nom"), userBody.getString("prenom"), userBody.getInt("id_etablissement"), userBody.getString("login"), userBody.getString("passwd"), userBody.getString("autorisation"));
            return user;
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

    public List<Service> getAllServices() {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url + "service.php");
            HttpResponse response = httpclient.execute(httpget);
            String jsonString = EntityUtils.toString(response.getEntity());
            JSONArray serviceJsonArr = new JSONArray(jsonString);
            List<Service> services = new ArrayList<Service>();
            for(int i=0;i<serviceJsonArr.length();i++){
                services.add(
                        new Service(
                                Integer.valueOf(serviceJsonArr.getJSONObject(i).get("id").toString()),
                                serviceJsonArr.getJSONObject(i).get("libelle").toString()
                        )
                );
            }
            return services;
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

    public List<ServiceUser> getServiceUserById(int userId) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url + "serviceuser.php?id=" + userId);
            HttpResponse response = httpclient.execute(httpget);
            String jsonString = EntityUtils.toString(response.getEntity());
            JSONArray serviceUserJsonArr = new JSONArray(jsonString);
            List<ServiceUser> serviceUserArrayList = new ArrayList<ServiceUser>();
            for(int i=0;i<serviceUserJsonArr.length();i++){
                serviceUserArrayList.add(
                        new ServiceUser(
                                Integer.valueOf(serviceUserJsonArr.getJSONObject(i).get("id").toString()),
                                Integer.valueOf(serviceUserJsonArr.getJSONObject(i).get("id_service").toString()),
                                Integer.valueOf(serviceUserJsonArr.getJSONObject(i).get("id_user").toString()),
                                serviceUserJsonArr.getJSONObject(i).get("date_demande").toString(),
                                serviceUserJsonArr.getJSONObject(i).get("etat_demande").toString()
                        )
                );
            }
            return serviceUserArrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ServiceUser addServiceUser(ServiceUser serviceUser) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url + "serviceuser.php");
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("id_service", serviceUser.getIdService());
            jsonBody.put("id_user", serviceUser.getIdUser());
            jsonBody.put("date_demande", serviceUser.getDateDemande());
            jsonBody.put("etat_demande", serviceUser.getEtatDemande());
            httppost.setHeader("Content-type", "application/json");


            StringEntity se = new StringEntity(jsonBody.toString());
            httppost.setEntity(se);
            HttpResponse response = httpclient.execute(httppost);
            String jsonString = EntityUtils.toString(response.getEntity());
            JSONObject serviceUserObject = new JSONObject(jsonString);
            ServiceUser submittedService = new ServiceUser(
                    Integer.valueOf(serviceUserObject.get("id_service").toString()),
                    Integer.valueOf(serviceUserObject.get("id_user").toString()),
                    serviceUserObject.get("date_demande").toString(),
                    serviceUserObject.get("etat_demande").toString()
            );
            return submittedService;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
