package com.thebestteamever.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alexss8 on 10.05.2016.
 */
public class QuestionSetRest{

    private final static String API_KEY = "2ndV4pZBnSmsh0AmmnTmk3R9GmBgp1HhTUrjsncQwIJ2Il9tcG";
    private final static String METHOD_URL = "https://yoda.p.mashape.com/yoda";
    private final static String FIRST_IMAGE_URL = "http://lorempixel.com/500/500/";
    private final static String SECOND_IMAGE_URL = "http://lorempixel.com/500/500/";
    private final static String personName = "John Travolta";
    private final static Boolean firstPersTrue = true;

    QuestionSetRest() {

    }

    public String processText(final String text) throws IOException {
        InputStream is = null;
        try {
            final String uri = Uri.parse(METHOD_URL)
                    .buildUpon()
                    .appendQueryParameter("sentence", text)
                    .build()
                    .toString();
            HttpURLConnection conn = (HttpURLConnection) new URL(uri).openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setRequestProperty("X-Mashape-Key", API_KEY);
            conn.setRequestProperty("Accept", "text/plain");
            conn.connect();
            int responseCode = conn.getResponseCode();
            is = conn.getInputStream();
            if (responseCode == 200) {
                return inputStreamToString(is);
            }
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return null;
    }

    public Bitmap processImage1(final String text) throws IOException {
        InputStream is = null;

        try {
            final String uri = Uri.parse(FIRST_IMAGE_URL)
                    .buildUpon()
                    .build()
                    .toString();
            HttpURLConnection conn = (HttpURLConnection) new URL(uri).openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int responseCode = conn.getResponseCode();
            is = conn.getInputStream();
            Bitmap firstImage = BitmapFactory.decodeStream(is);
            if (responseCode == 200) {
                return firstImage;
            }
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return null;
    }

    public Bitmap processImage2(final String text) throws IOException {
        InputStream is = null;

        try {
            final String uri = Uri.parse(SECOND_IMAGE_URL)
                    .buildUpon()
                    .build()
                    .toString();
            HttpURLConnection conn = (HttpURLConnection) new URL(uri).openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int responseCode = conn.getResponseCode();
            is = conn.getInputStream();
            Bitmap secondImage = BitmapFactory.decodeStream(is);
            if (responseCode == 200) {
                return secondImage;
            }
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return null;
    }

    //TODO: name parser
    public String processName(final String text){
        return personName;

    }

    //TODO: true of person parser
    public Boolean processFirstPersTrue(final String text){
        return firstPersTrue;
    }

    private static String inputStreamToString(final InputStream is) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}

