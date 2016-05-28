package com.thebestteamever.game.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.thebestteamever.game.R;
import com.thebestteamever.game.serviceapi.parcelable.LoginParams;
import com.thebestteamever.game.serviceapi.ServiceHelper;

import java.util.logging.SocketHandler;

public class LoginActivity extends AppCompatActivity implements ServiceHelper.LoginResultListener, View.OnClickListener {

    private ProgressBar progressBar;
    private int requestId;

    private EditText loginText;
    private EditText passwordText;

    private Button loginButton;
    private Button withoutButton;
    
    
    final public static String SAVED = "saved";
    final public static String SAVED_LOGIN = "saved_login";
    final public static String SAVED_PASSWORD = "saved_password";
    
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences(SAVED, Context.MODE_PRIVATE);

        loginText = (EditText) findViewById(R.id.editText1);
        passwordText = (EditText) findViewById(R.id.editText2);

        loginButton = (Button) findViewById(R.id.button1);
        withoutButton = (Button) findViewById(R.id.button3);

        loginButton.setOnClickListener(this);
        withoutButton.setOnClickListener(this);

    }

    @Override
    public void onLoginResult(boolean success, String result) {
//        progressBar.setVisibility(View.INVISIBLE);

        switch (result) {
            case "Ok":
                // TODO: Сохранение данных
                String login = loginText.getText().toString();
                String password = passwordText.getText().toString();

                SharedPreferences.Editor ed = sharedPreferences.edit();
                ed.putString(SAVED_LOGIN, login);
                ed.putString(SAVED_PASSWORD, password);
                ed.apply();

                Intent intent = new Intent(this, NawActivity.class);
                startActivity(intent);
                finish();
                break;
            case "Wrong login/password":
                // TODO: Отобржать на активити надпись (на русском)
                break;
            case "No some fields":
                // TODO: Отобржать на активити надпись (на русском)
                break;
            default:
                // TODO: Отобржать на активити надпись (на русском)
                break;
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button1:
//                progressBar.setVisibility(View.VISIBLE);
                String login = loginText.getText().toString();
                String password = passwordText.getText().toString();
                requestId = ServiceHelper.makeLogin(this, new LoginParams(login, password), this);
                break;

            case R.id.button3:
                Intent intent = new Intent(this, NawActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
