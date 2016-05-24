package com.thebestteamever.game.serviceapi;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by subzero on 24.05.16.
 */
public class RegistrationParams implements Parcelable {
    private String firstName;
    private String login;
    private String password;

    public RegistrationParams(String firstName, String login, String password) {
        this.firstName = firstName;
        this.login = login;
        this.password = password;
    }

    protected RegistrationParams(Parcel in) {
        firstName = in.readString();
        login = in.readString();
        password = in.readString();
    }

    public static final Creator<RegistrationParams> CREATOR = new Creator<RegistrationParams>() {
        @Override
        public RegistrationParams createFromParcel(Parcel in) {
            return new RegistrationParams(in);
        }

        @Override
        public RegistrationParams[] newArray(int size) {
            return new RegistrationParams[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

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
        parcel.writeString(firstName);
        parcel.writeString(login);
        parcel.writeString(password);
    }

}
