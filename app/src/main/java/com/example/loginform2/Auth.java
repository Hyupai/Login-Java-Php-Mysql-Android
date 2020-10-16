package com.example.loginform2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.content.Context;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.text.Html;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;


import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.X509EncodedKeySpec;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.net.ssl.TrustManagerFactory;

public class Auth extends AsyncTask<String, Void, String> {
    private WeakReference<MainActivity> weakActivity;
    private ProgressDialog pDialog;
    private String URLSERVER = "https://lurching-hinge.000webhostapp.com/login.php";
    private byte[] puk = {48, -127, -97, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -127, -115, 0, 48, -127, -119, 2, -127, -127, 0, -41, 36, -11, -27, -61, 32, 124, 58, 39, -94, -13, 7, 48, -104, -109, 106, -75, -8, -128, -92, -89, -125, -49, -83, 75, 12, -26, 90, 56, 35, 52, -116, 30, 40, -69, -70, 86, 14, -80, -20, 55, -89, 104, -46, -17, -80, -119, 83, -14, 116, -66, 11, -108, 5, 76, 12, -43, -89, -49, 11, 38, -124, 71, 45, 65, -103, 10, 99, 33, 79, 21, -16, -38, -60, 24, -108, 101, -89, -18, 48, -37, -78, 59, 10, 89, 42, 51, -43, 9, -33, -68, 61, -45, 94, -49, 83, 52, 56, 105, -123, 18, 89, 3, 54, -48, -63, -61, -103, -9, 79, -36, 18, 119, 11, -35, 82, 73, -66, 12, 123, -38, 97, 121, -30, 31, -50, -106, 127, 2, 3, 1, 0, 1};
    private byte[] crt = {45, 45, 45, 45, 45, 66, 69, 71, 73, 78, 32, 67, 69, 82, 84, 73, 70, 73, 67, 65, 84, 69, 45, 45, 45, 45, 45, 13, 10, 77, 73, 73, 70, 54, 122, 67, 67, 66, 78, 79, 103, 65, 119, 73, 66, 65, 103, 73, 82, 65, 73, 68, 53, 88, 53, 112, 118, 85, 72, 71, 112, 43, 81, 49, 84, 54, 88, 103, 117, 67, 78, 81, 119, 68, 81, 89, 74, 75, 111, 90, 73, 104, 118, 99, 78, 65, 81, 69, 76, 66, 81, 65, 119, 13, 10, 99, 106, 69, 76, 77, 65, 107, 71, 65, 49, 85, 69, 66, 104, 77, 67, 86, 86, 77, 120, 67, 122, 65, 74, 66, 103, 78, 86, 66, 65, 103, 84, 65, 108, 82, 89, 77, 82, 65, 119, 68, 103, 89, 68, 86, 81, 81, 72, 69, 119, 100, 73, 98, 51, 86, 122, 100, 71, 57, 117, 77, 82, 85, 119, 13, 10, 69, 119, 89, 68, 86, 81, 81, 75, 69, 119, 120, 106, 85, 71, 70, 117, 90, 87, 119, 115, 73, 69, 108, 117, 89, 121, 52, 120, 76, 84, 65, 114, 66, 103, 78, 86, 66, 65, 77, 84, 74, 71, 78, 81, 89, 87, 53, 108, 98, 67, 119, 103, 83, 87, 53, 106, 76, 105, 66, 68, 90, 88, 74, 48, 13, 10, 97, 87, 90, 112, 89, 50, 70, 48, 97, 87, 57, 117, 73, 69, 70, 49, 100, 71, 104, 118, 99, 109, 108, 48, 101, 84, 65, 101, 70, 119, 48, 120, 79, 84, 65, 51, 77, 68, 77, 119, 77, 68, 65, 119, 77, 68, 66, 97, 70, 119, 48, 120, 79, 84, 69, 119, 77, 68, 69, 121, 77, 122, 85, 53, 13, 10, 78, 84, 108, 97, 77, 66, 77, 120, 69, 84, 65, 80, 66, 103, 78, 86, 66, 65, 77, 84, 67, 71, 116, 116, 98, 50, 82, 122, 76, 109, 49, 115, 77, 73, 73, 66, 73, 106, 65, 78, 66, 103, 107, 113, 104, 107, 105, 71, 57, 119, 48, 66, 65, 81, 69, 70, 65, 65, 79, 67, 65, 81, 56, 65, 13, 10, 77, 73, 73, 66, 67, 103, 75, 67, 65, 81, 69, 65, 113, 68, 77, 48, 52, 103, 116, 121, 106, 80, 76, 114, 117, 97, 113, 103, 70, 84, 56, 71, 99, 49, 112, 103, 68, 48, 77, 49, 71, 78, 49, 86, 43, 49, 78, 56, 89, 102, 76, 50, 89, 65, 98, 48, 105, 100, 118, 48, 100, 87, 108, 116, 13, 10, 66, 106, 43, 116, 49, 100, 117, 82, 111, 80, 52, 55, 89, 43, 98, 81, 65, 69, 104, 79, 67, 43, 47, 110, 108, 97, 88, 113, 115, 115, 85, 65, 101, 116, 72, 68, 67, 69, 89, 100, 79, 54, 117, 110, 109, 88, 71, 113, 48, 82, 75, 119, 53, 114, 79, 80, 77, 103, 65, 113, 99, 81, 79, 48, 13, 10, 50, 86, 81, 85, 80, 86, 56, 120, 53, 119, 85, 111, 121, 85, 52, 76, 115, 121, 54, 98, 79, 57, 86, 48, 68, 83, 72, 120, 89, 77, 114, 71, 43, 97, 107, 83, 65, 56, 121, 73, 82, 87, 104, 77, 81, 84, 115, 51, 105, 109, 118, 78, 111, 73, 78, 83, 90, 111, 111, 70, 72, 110, 75, 100, 13, 10, 115, 83, 76, 68, 79, 89, 81, 78, 51, 118, 80, 112, 121, 53, 75, 121, 69, 116, 76, 110, 70, 84, 116, 88, 83, 89, 49, 74, 120, 79, 67, 71, 52, 52, 88, 114, 106, 119, 83, 82, 97, 122, 79, 66, 117, 84, 121, 102, 86, 98, 56, 100, 116, 88, 79, 83, 118, 78, 85, 101, 69, 88, 104, 52, 13, 10, 118, 107, 72, 54, 103, 109, 90, 98, 67, 83, 53, 98, 71, 99, 117, 88, 118, 89, 49, 71, 77, 114, 97, 68, 114, 117, 116, 50, 89, 83, 121, 72, 48, 71, 80, 102, 65, 73, 119, 75, 83, 112, 120, 90, 47, 52, 82, 74, 75, 97, 85, 67, 75, 49, 114, 56, 48, 103, 79, 57, 67, 121, 76, 50, 13, 10, 57, 78, 100, 117, 100, 101, 77, 100, 103, 71, 85, 72, 88, 122, 80, 80, 87, 70, 122, 56, 100, 73, 117, 72, 49, 119, 104, 109, 108, 56, 68, 48, 88, 119, 73, 68, 65, 81, 65, 66, 111, 52, 73, 67, 50, 84, 67, 67, 65, 116, 85, 119, 72, 119, 89, 68, 86, 82, 48, 106, 66, 66, 103, 119, 13, 10, 70, 111, 65, 85, 102, 103, 78, 97, 90, 85, 70, 114, 112, 51, 52, 75, 52, 98, 105, 100, 67, 79, 111, 100, 106, 104, 49, 113, 120, 50, 85, 119, 72, 81, 89, 68, 86, 82, 48, 79, 66, 66, 89, 69, 70, 77, 113, 103, 116, 109, 77, 102, 100, 97, 84, 87, 76, 108, 97, 107, 72, 107, 99, 116, 13, 10, 83, 112, 72, 77, 70, 122, 69, 48, 77, 65, 52, 71, 65, 49, 85, 100, 68, 119, 69, 66, 47, 119, 81, 69, 65, 119, 73, 70, 111, 68, 65, 77, 66, 103, 78, 86, 72, 82, 77, 66, 65, 102, 56, 69, 65, 106, 65, 65, 77, 66, 48, 71, 65, 49, 85, 100, 74, 81, 81, 87, 77, 66, 81, 71, 13, 10, 67, 67, 115, 71, 65, 81, 85, 70, 66, 119, 77, 66, 66, 103, 103, 114, 66, 103, 69, 70, 66, 81, 99, 68, 65, 106, 66, 80, 66, 103, 78, 86, 72, 83, 65, 69, 83, 68, 66, 71, 77, 68, 111, 71, 67, 121, 115, 71, 65, 81, 81, 66, 115, 106, 69, 66, 65, 103, 73, 48, 77, 67, 115, 119, 13, 10, 75, 81, 89, 73, 75, 119, 89, 66, 66, 81, 85, 72, 65, 103, 69, 87, 72, 87, 104, 48, 100, 72, 66, 122, 79, 105, 56, 118, 99, 50, 86, 106, 100, 88, 74, 108, 76, 109, 78, 118, 98, 87, 57, 107, 98, 121, 53, 106, 98, 50, 48, 118, 81, 49, 66, 84, 77, 65, 103, 71, 66, 109, 101, 66, 13, 10, 68, 65, 69, 67, 65, 84, 66, 77, 66, 103, 78, 86, 72, 82, 56, 69, 82, 84, 66, 68, 77, 69, 71, 103, 80, 54, 65, 57, 104, 106, 116, 111, 100, 72, 82, 119, 79, 105, 56, 118, 89, 51, 74, 115, 76, 109, 78, 118, 98, 87, 57, 107, 98, 50, 78, 104, 76, 109, 78, 118, 98, 83, 57, 106, 13, 10, 85, 71, 70, 117, 90, 87, 120, 74, 98, 109, 78, 68, 90, 88, 74, 48, 97, 87, 90, 112, 89, 50, 70, 48, 97, 87, 57, 117, 81, 88, 86, 48, 97, 71, 57, 121, 97, 88, 82, 53, 76, 109, 78, 121, 98, 68, 66, 57, 66, 103, 103, 114, 66, 103, 69, 70, 66, 81, 99, 66, 65, 81, 82, 120, 13, 10, 77, 71, 56, 119, 82, 119, 89, 73, 75, 119, 89, 66, 66, 81, 85, 72, 77, 65, 75, 71, 79, 50, 104, 48, 100, 72, 65, 54, 76, 121, 57, 106, 99, 110, 81, 117, 89, 50, 57, 116, 98, 50, 82, 118, 89, 50, 69, 117, 89, 50, 57, 116, 76, 50, 78, 81, 89, 87, 53, 108, 98, 69, 108, 117, 13, 10, 89, 48, 78, 108, 99, 110, 82, 112, 90, 109, 108, 106, 89, 88, 82, 112, 98, 50, 53, 66, 100, 88, 82, 111, 98, 51, 74, 112, 100, 72, 107, 117, 89, 51, 74, 48, 77, 67, 81, 71, 67, 67, 115, 71, 65, 81, 85, 70, 66, 122, 65, 66, 104, 104, 104, 111, 100, 72, 82, 119, 79, 105, 56, 118, 13, 10, 98, 50, 78, 122, 99, 67, 53, 106, 98, 50, 49, 118, 90, 71, 57, 106, 89, 83, 53, 106, 98, 50, 48, 119, 77, 65, 89, 68, 86, 82, 48, 82, 66, 67, 107, 119, 74, 52, 73, 73, 97, 50, 49, 118, 90, 72, 77, 117, 98, 87, 121, 67, 68, 87, 49, 104, 97, 87, 119, 117, 97, 50, 49, 118, 13, 10, 90, 72, 77, 117, 98, 87, 121, 67, 68, 72, 100, 51, 100, 121, 53, 114, 98, 87, 57, 107, 99, 121, 53, 116, 98, 68, 67, 67, 65, 81, 81, 71, 67, 105, 115, 71, 65, 81, 81, 66, 49, 110, 107, 67, 66, 65, 73, 69, 103, 102, 85, 69, 103, 102, 73, 65, 56, 65, 66, 50, 65, 76, 118, 90, 13, 10, 51, 55, 119, 102, 105, 110, 71, 49, 107, 53, 81, 106, 108, 54, 113, 83, 101, 48, 99, 52, 86, 53, 85, 75, 113, 49, 76, 111, 71, 112, 67, 87, 90, 68, 97, 79, 72, 116, 71, 70, 65, 65, 65, 66, 97, 55, 108, 90, 120, 106, 81, 65, 65, 65, 81, 68, 65, 69, 99, 119, 82, 81, 73, 104, 13, 10, 65, 79, 87, 50, 80, 65, 114, 105, 114, 77, 84, 54, 57, 116, 75, 52, 112, 107, 121, 50, 108, 108, 74, 84, 51, 112, 117, 79, 77, 122, 76, 114, 106, 73, 87, 82, 52, 119, 71, 54, 119, 72, 104, 49, 65, 105, 65, 116, 117, 107, 121, 114, 78, 111, 71, 86, 90, 65, 87, 115, 43, 50, 74, 101, 13, 10, 65, 107, 57, 107, 87, 76, 104, 57, 76, 72, 121, 104, 98, 66, 80, 43, 49, 66, 83, 68, 121, 50, 74, 71, 100, 119, 66, 50, 65, 72, 82, 43, 50, 111, 77, 120, 114, 84, 77, 81, 107, 83, 71, 99, 122, 105, 86, 80, 81, 110, 68, 67, 118, 47, 49, 101, 81, 105, 65, 73, 120, 106, 99, 49, 13, 10, 101, 101, 89, 81, 101, 56, 120, 87, 65, 65, 65, 66, 97, 55, 108, 90, 120, 108, 77, 65, 65, 65, 81, 68, 65, 69, 99, 119, 82, 81, 73, 103, 81, 102, 103, 118, 90, 66, 85, 80, 100, 102, 122, 119, 100, 67, 83, 67, 101, 118, 77, 80, 75, 52, 79, 57, 105, 80, 111, 101, 101, 75, 98, 74, 13, 10, 67, 120, 56, 85, 89, 69, 109, 74, 105, 109, 111, 67, 73, 81, 68, 121, 120, 107, 43, 113, 98, 104, 85, 70, 54, 49, 89, 53, 114, 79, 68, 117, 47, 52, 122, 72, 118, 116, 116, 105, 53, 66, 56, 85, 114, 120, 65, 73, 109, 82, 109, 86, 52, 115, 65, 85, 117, 68, 65, 78, 66, 103, 107, 113, 13, 10, 104, 107, 105, 71, 57, 119, 48, 66, 65, 81, 115, 70, 65, 65, 79, 67, 65, 81, 69, 65, 70, 72, 116, 51, 47, 102, 87, 115, 80, 111, 110, 47, 55, 74, 81, 72, 84, 50, 66, 76, 82, 111, 50, 112, 55, 103, 111, 73, 73, 51, 52, 112, 115, 121, 103, 67, 50, 55, 72, 106, 87, 120, 110, 109, 13, 10, 57, 50, 121, 102, 107, 74, 68, 106, 113, 87, 99, 90, 56, 110, 79, 48, 113, 73, 114, 116, 100, 79, 85, 84, 103, 114, 115, 108, 82, 83, 89, 67, 111, 71, 97, 108, 111, 49, 119, 90, 81, 54, 107, 73, 71, 89, 117, 71, 115, 97, 89, 121, 110, 71, 78, 68, 67, 56, 109, 47, 86, 76, 52, 75, 13, 10, 116, 72, 65, 110, 120, 108, 49, 79, 106, 49, 105, 68, 77, 117, 43, 48, 89, 115, 113, 114, 69, 80, 72, 107, 78, 111, 81, 109, 118, 99, 108, 47, 75, 86, 88, 74, 47, 121, 74, 122, 68, 56, 118, 114, 69, 74, 47, 107, 110, 103, 55, 106, 51, 85, 51, 101, 115, 122, 98, 114, 69, 71, 117, 90, 13, 10, 79, 54, 77, 122, 105, 90, 47, 54, 121, 105, 56, 56, 108, 65, 109, 65, 81, 71, 79, 103, 105, 84, 108, 76, 117, 113, 69, 68, 97, 110, 57, 112, 107, 116, 76, 72, 78, 55, 84, 115, 82, 53, 85, 50, 119, 84, 103, 68, 112, 50, 111, 83, 114, 116, 50, 52, 110, 49, 111, 90, 116, 47, 115, 117, 13, 10, 49, 53, 66, 47, 82, 119, 55, 97, 116, 74, 78, 43, 49, 66, 110, 83, 43, 47, 102, 81, 108, 51, 98, 118, 57, 80, 97, 108, 56, 48, 65, 80, 66, 88, 107, 121, 100, 97, 90, 109, 50, 90, 120, 86, 68, 103, 49, 65, 83, 105, 47, 118, 119, 120, 55, 68, 49, 106, 104, 101, 71, 81, 111, 100, 13, 10, 56, 66, 115, 88, 48, 53, 110, 80, 73, 83, 51, 68, 111, 97, 79, 83, 121, 85, 76, 83, 106, 66, 76, 120, 109, 111, 51, 111, 86, 81, 106, 69, 55, 116, 48, 106, 99, 115, 66, 47, 87, 65, 61, 61, 13, 10, 45, 45, 45, 45, 45, 69, 78, 68, 32, 67, 69, 82, 84, 73, 70, 73, 67, 65, 84, 69, 45, 45, 45, 45, 45};
// comente ou descomente
    public native void Check();

    Auth(MainActivity activity){
        weakActivity = new WeakReference<>(activity);
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCancelable(false);
        pDialog = dialog;
    }

    @Override
    protected void onPreExecute() {
        MainActivity activity = getActivity();
        if (activity == null) {
            return;
        }

        if (getDialog() != null) {
            getDialog().setMessage("Logging in...");
            getDialog().show();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        if (!isInternetAvailable(getActivity())) {
            return "No internet connection";
        }
        try {
            JSONObject token = new JSONObject();

            JSONObject data = new JSONObject();
            data.put("uname", strings[0]);
            data.put("pass", strings[1]);
            data.put("cs", getUniqueId(getActivity()));

            token.put("Data", encrypt(data.toString(), puk));
            token.put("Hash", Utils.SHA256(data.toString()));

            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate ca = cf.generateCertificate(new ByteArrayInputStream(crt));

            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            HttpURLConnection urlConnection = (HttpURLConnection) new URL(URLSERVER).openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String postParameters = "token=" + Utils.toBase64(token.toString());
            urlConnection.setFixedLengthStreamingMode(postParameters.getBytes().length);
            PrintWriter out = new PrintWriter(urlConnection.getOutputStream());
            out.print(postParameters);
            out.close();

            return Utils.fromBase64String(Utils.readStream(urlConnection.getInputStream()));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }

        if (getDialog() != null) {
            getDialog().dismiss();
        }

        if(s == null || s.isEmpty()){
            Toast.makeText(activity,(Html.fromHtml("<font face='monospace'> <font color='#ff0000'>Erro no Servidor!</font></font>")), Toast.LENGTH_LONG).show();
            return;
        }

        if(s.equals("No internet connection")){
            Toast.makeText(activity,s, Toast.LENGTH_LONG).show();
            return;
        }

        try {
            JSONObject ack = new JSONObject(s);
            String decData = Utils.profileDecrypt(ack.get("Data").toString(), ack.get("Hash").toString());
            if (!verify(decData, ack.get("Sign").toString(), puk)) {
                Toast.makeText(activity, (Html.fromHtml("<font face='monospace'> <font color='#ff0000'>Dados Incorretos!</font></font>")), Toast.LENGTH_LONG).show();
                return;
            }
            JSONObject data = new JSONObject(decData);
            if(data.get("Status").toString().equals("Success")) {
                //custom toast
                Toast.makeText(activity, (Html.fromHtml("<font face='monospace'> <font color='#00ff00'>Logado com Sucesso!</font></font>")), Toast.LENGTH_SHORT).show();
                getActivity().startActivity(new Intent(activity, Class.forName(getActivity().sGameActivity)));
                Check();
                if (data.get("Status").toString().equals("Success")) {

                    getActivity().startActivity(new Intent(activity, Class.forName(getActivity().sGameActivity)));
                    //Start service


                } else {
                    if (!isServiceRunning()) {
                        Toast.makeText(activity, "AAAAAAAAAA!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Service Already Running!", Toast.LENGTH_SHORT).show();
                    }
                }
                getActivity().finish();
            } else {
                //customize essa mensagem no server
                Toast.makeText(activity,data.get("MessageString").toString(), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    private static boolean isInternetAvailable(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private boolean isServiceRunning() {
        String s = "";
        if (s != null) {
            return true;
        }
        return false;
    }


    private MainActivity getActivity() {
        return weakActivity.get();
    }

    private ProgressDialog getDialog() {
        return pDialog;
    }

    private PublicKey getPublicKey(byte[] keyBytes) throws Exception {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    private String encrypt(String plainText, byte[] keyBytes) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, getPublicKey(keyBytes));
        return Utils.toBase64(encryptCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8)));
    }

    private boolean verify(String plainText, String signature, byte[] keyBytes) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(getPublicKey(keyBytes));
        publicSignature.update(plainText.getBytes(StandardCharsets.UTF_8));
        return publicSignature.verify(Utils.fromBase64(signature));
    }

    private String getUniqueId(Context ctx) {
        String key = (getDeviceName() + Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID) + Build.HARDWARE).replace(" ", "");
        UUID uniqueKey = UUID.nameUUIDFromBytes(key.getBytes());
        return uniqueKey.toString().replace("-", "");
    }

    private String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        } else {
            return manufacturer + " " + model;
        }
    }
}
