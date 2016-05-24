package com.thebestteamever.game.serviceapi;

import android.graphics.Bitmap;
import java.io.IOException;

import com.google.gson.Gson;
import com.thebestteamever.game.Level;

public class Processor {
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


    public static String processRegistration(RegistrationParams params) throws IOException {

        String postParams = "name="+params.getFirstName()+"&login="+params.getLogin()+"&password="+params.getPassword();

        String data = new Rest().postJSON("http://91.218.230.80/api/registration/", 15000, postParams);
        RegistrationRequest msg = new Gson().fromJson(data, RegistrationRequest.class);

        if (msg != null) {
            return msg.getData();
        }

        return null;
    }

    class RegistrationRequest {
        private String data;

        public String getData() {
            return data;
        }
    }
}
