package com.thebestteamever.game.serviceapi;

import android.os.Parcel;
import android.os.Parcelable;


public class LoginParams implements Parcelable {
    private String login;
    private String password;

    public LoginParams(String login, String password) {
        this.login = login;
        this.password = password;
    }

    protected LoginParams(Parcel in) {
        login = in.readString();
        password = in.readString();
    }

    public static final Creator<LoginParams> CREATOR = new Creator<LoginParams>() {
        @Override
        public LoginParams createFromParcel(Parcel in) {
            return new LoginParams(in);
        }

        @Override
        public LoginParams[] newArray(int size) {
            return new LoginParams[size];
        }
    };

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(login);
        parcel.writeString(password);
    }

}
