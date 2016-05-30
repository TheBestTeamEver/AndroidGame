package com.thebestteamever.game.serviceapi;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.thebestteamever.game.item.ListItem;
import com.thebestteamever.game.serviceapi.parcelable.Level;
import com.thebestteamever.game.serviceapi.parcelable.LoginParams;
import com.thebestteamever.game.serviceapi.parcelable.Score;

public class Processor {
    static final String LOG_TAG = "myLogs";
    static final Uri RATING_URI = Uri
            .parse("content://com.thebestteamever.game.Rating/rating");

    static final String USER_NAME = "name";
    static final String USER_RATING = "rating";

    private static final int COUNT_LEVELS = 10;
    private static int level = 0;
    private static DataResponse msg = null;

    public static Level processLevel(final String text) throws IOException {
        if (level == 0) {
            String data = new Rest().getJSON("http://91.218.230.80/api/get_random_urls/", 15000);
            msg = new Gson().fromJson(data, DataResponse.class);
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

    public static String processTop(final String text, Context context) throws IOException {
        String data = new Rest().getJSON("http://91.218.230.80/api/get_top/", 15000);
        DataResponse msg = new Gson().fromJson(data, DataResponse.class);

        if (msg != null) {
            ArrayList<HashMap<String, String>> topList = msg.getData();
            ContentValues cv = new ContentValues();
            ContentResolver cr = context.getContentResolver();
//            Cursor cursor = cr.query(RATING_URI, null, null,
//                    null, null);
//            if (cursor.moveToFirst()) {
//                do {
//                    Uri uri = ContentUris.withAppendedId(RATING_URI, cursor.getColumnIndex("id"));
 //                   int del = cr.delete(uri, null, null);
 //                   Log.d(LOG_TAG, "delete, count = " + del);
 //               } while (cursor.moveToNext() && cursor.getColumnIndex("id")!= -1);
 //           }
  //          cursor.close();
//
            int k = topList.size();
            for(int i = 0; i < k; i++) {
                cv.put(USER_NAME, topList.get(i).get("login"));
                cv.put(USER_RATING, topList.get(i).get("rating"));
                Uri uri = ContentUris.withAppendedId(RATING_URI, i);
                int cnt = cr.update(uri, cv, null, null);
                Log.d(LOG_TAG, "update, count = " + cnt);
            }

            return "Ok";
        }


        return null;
    }

    public static String processLogin(LoginParams params) throws IOException {

        String postParams = "login=" + params.getLogin() + "&password=" + params.getPassword();

        String data = new Rest().postJSON("http://91.218.230.80/api/authentication/", 15000, postParams);
        LoginRequest msg = new Gson().fromJson(data, LoginRequest.class);

        if (msg != null) {
            return msg.getData();
        }

        return null;
    }

    public static String processScore(Score params) throws IOException {

        String postParams = "login=" + params.getLogin() + "&score=" + params.getScore();

        String data = new Rest().postJSON("http://91.218.230.80/api/put_score/", 15000, postParams);
        LoginRequest msg = new Gson().fromJson(data, LoginRequest.class);

        if (msg != null) {
            return msg.getData();
        }

        return null;
    }


    public class DataResponse {

        private ArrayList<HashMap<String,String>> data;

        public ArrayList<HashMap<String, String>> getData() {
            return data;
        }
    }

    class LoginRequest {
        private String data;

        public String getData() {
            return data;
        }
    }
}
