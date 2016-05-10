package com.thebestteamever.game;

/**
 * Created by alexss8 on 10.05.2016.
 */
import android.graphics.Bitmap;

public class QuestionSet {
    private Bitmap firstImage;
    private Bitmap secondImage;
    private String personName;
    private Boolean firstPersonTrue;

    QuestionSet(Bitmap imF, Bitmap imF1, String persName, Boolean fTrue) {
        firstImage = imF;
        secondImage = imF1;
        personName = persName;
        firstPersonTrue = fTrue;
    }

    public Bitmap getFirstImage() { return firstImage;}
    public Bitmap getsecondImage() { return  secondImage;}
    public String getersonName() { return  personName;}
    public Boolean getfirstPersonTrue() { return  firstPersonTrue;}
}
