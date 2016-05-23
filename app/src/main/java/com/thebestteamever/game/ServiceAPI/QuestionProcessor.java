package com.thebestteamever.game.ServiceAPI;

import android.graphics.Bitmap;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.thebestteamever.game.QuestionSet;

public class QuestionProcessor {
    private static final int COUNT_LEVELS = 10;
    private static int level = 0;
    private static RandomLevels msg = null;
    public static QuestionSet processQuest(final String text) throws IOException {
        if (level == 0) {
            String data = new QuestionRest().getJSON("http://91.218.230.80/api/get_random_urls/", 15000);
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
            Bitmap firstImg = new QuestionRest().getImage(firstImageUrl, 15000);
            Bitmap secondImg = new QuestionRest().getImage(secondImageUrl, 15000);
            return new QuestionSet(firstImg, secondImg, firstName, secondName);
        }
        return null;
    }


}
