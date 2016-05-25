package com.thebestteamever.game.serviceapi;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleCursorAdapter;
import android.content.Context;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.thebestteamever.game.Level;

public class Processor {

    static final String LOG_TAG = "myLogs";

    static final Uri RATING_URI = Uri
            .parse("content://com.thebestteamever.game.Rating/rating");

    static final String USER_NAME = "name";
    static final String USER_RATING = "rating";

    private static final int COUNT_LEVELS = 10;
    private static int level = 0;
    private static RandomLevels msg = null;

    public static Level processLevel(final String text) throws IOException {
        if (level == 0) {
            String data = new Rest().getJSON("http://91.218.230.80/api/get_random_urls/", 15000);
            msg = new Gson().fromJson(data, RandomLevels.class);
        }
        if (msg != null) {
            String firstImageUrl = "http://91.218.230.80" + msg.getData().get(level).get("true_photo");
            String secondImageUrl = "http://91.218.230.80" + msg.getData().get(level).get("fake_photo");
            String firstName = msg.getData().get(level).get("true_name");
            String secondName = msg.getData().get(level).get("fake_name");
            level++;
            if (level >= COUNT_LEVELS - 1 || level >= msg.getData().size()) {
                level = 0;
            }
            Bitmap firstImg = new Rest().getImage(firstImageUrl, 15000);
            Bitmap secondImg = new Rest().getImage(secondImageUrl, 15000);
            return new Level(firstImg, secondImg, firstName, secondName);
        }
        return null;
    }

    public static String processTop(final String text) throws IOException {
        String data = new Rest().getJSON("http://91.218.230.80/api/get_top/", 15000);
        TopResponse msg = new Gson().fromJson(data, TopResponse.class);

        if (msg != null) {
            List topList = msg.getTopList();
            TopListMock topListMock = new TopListMock();
            ContentValues cv = new ContentValues();
            ContentResolver cr = CoreLib.ContentResolver();

            for(int i = 0; i < 3; i++) {
                cv.put(USER_NAME, topListMock.testMap().get(i).get("login"));
                cv.put(USER_RATING, topListMock.testMap().get(i).get("rating"));
                Uri newUri = cr.insert(RATING_URI, cv);
                Log.d(LOG_TAG, "insert, result Uri : " + newUri.toString());
            }

            return "Ok";
        }

        return null;
    }


    public static String processRegistration(RegistrationParams params) throws IOException {

        String postParams = "name=" + params.getFirstName() + "&login=" + params.getLogin() + "&password=" + params.getPassword();

        String data = new Rest().postJSON("http://91.218.230.80/api/registration/", 15000, postParams);
        RegistrationRequest msg = new Gson().fromJson(data, RegistrationRequest.class);

        if (msg != null) {
            return msg.getData();
        }

        return null;
    }

    class TopResponse {
        private List<Map<String, String>> topList;

        public List getTopList() {
            return topList;
        }
    }

    class RegistrationRequest {
        private String data;

        public String getData() {
            return data;
        }
    }
}
