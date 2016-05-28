package com.thebestteamever.game.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.thebestteamever.game.R;
import com.thebestteamever.game.serviceapi.ServiceHelper;
import com.thebestteamever.game.serviceapi.parcelable.LoginParams;

public class PreActivity extends AppCompatActivity implements ServiceHelper.LoginResultListener, ServiceHelper.TopResultListener {
    private static boolean isLogin;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre);

        isLogin = false;
        sharedPreferences = getSharedPreferences(LoginActivity.SAVED, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(LoginActivity.SAVED_LOGIN) && sharedPreferences.contains(LoginActivity.SAVED_PASSWORD)) {

            String login = sharedPreferences.getString(LoginActivity.SAVED_LOGIN, "");
            String password = sharedPreferences.getString(LoginActivity.SAVED_PASSWORD, "");

            if (!login.equals("") && !password.equals("")) {
                int requestId = ServiceHelper.makeLogin(this, new LoginParams(login, password), this);
            }
        } else {

            int requestId = ServiceHelper.makeTop(this, "top", this);
        }
    }

    @Override
    public void onTopResult(boolean success, String result) {
        if (isLogin) {
            Intent intent = new Intent(this, NawActivity.class);
            startActivity(intent);
            finish();
        } else {
            startActivity(new Intent(PreActivity.this, LoginActivity.class));
            finish();
        }
    }

    @Override
    public void onLoginResult(boolean success, String result) {

        if (result.equals("Ok")) {
            isLogin = true;
        }
        int requestId = ServiceHelper.makeTop(this, "top", this);

    }
}
