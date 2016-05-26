package com.thebestteamever.game.serviceapi.parcelable;

/**
 * Created by alexss8 on 10.05.2016.
 */

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Level implements Parcelable {
    private Bitmap firstImage;
    private Bitmap secondImage;
    private String firstName;
    private String secondName;

    public Level(Bitmap firstImage, Bitmap secondImage, String firstName, String secondName) {
        this.firstImage = firstImage;
        this.secondImage = secondImage;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    protected Level(Parcel in) {
        firstImage = in.readParcelable(Bitmap.class.getClassLoader());
        secondImage = in.readParcelable(Bitmap.class.getClassLoader());
        firstName = in.readString();
    }

    public static final Creator<Level> CREATOR = new Creator<Level>() {
        @Override
        public Level createFromParcel(Parcel in) {
            return new Level(in);
        }

        @Override
        public Level[] newArray(int size) {
            return new Level[size];
        }
    };

    public Bitmap getFirstImage() {
        return firstImage;
    }

    public Bitmap getSecondImage() {
        return secondImage;
    }

    public String getFirstName() {
        return firstName;
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeParcelable(firstImage, i);
        parcel.writeParcelable(secondImage, i);
        parcel.writeString(firstName);
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
