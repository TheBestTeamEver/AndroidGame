package com.thebestteamever.game.ServiceAPI;

import android.graphics.Bitmap;
import java.io.IOException;
import com.thebestteamever.game.QuestionSet;

public class QuestionProcessor {
    private final static String LOG_TAG = QuestionProcessor.class.getSimpleName();

    public static QuestionSet processQuest(final String text) throws IOException {
        Bitmap firstImg = new QuestionRest().processImage1("kek");
        Bitmap secondImg = new QuestionRest().processImage2("kek");
        String  persName = "kek";
//        Boolean isTrue = true;
        return new QuestionSet(firstImg, secondImg, persName, true);
    }


}
