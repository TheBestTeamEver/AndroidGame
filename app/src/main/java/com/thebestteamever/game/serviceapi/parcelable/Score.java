package com.thebestteamever.game.serviceapi.parcelable;

import android.os.Parcel;
import android.os.Parcelable;


public class Score implements Parcelable {
    private String login;
    private String score;

    public Score(String login, String score) {
        this.login = login;
        this.score = score;
    }

    protected Score(Parcel in) {
        login = in.readString();
        score = in.readString();
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

    public String getLogin() {
        return login;
    }

    public String getScore() {
        return score;
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(login);
        parcel.writeString(score);
    }

}
