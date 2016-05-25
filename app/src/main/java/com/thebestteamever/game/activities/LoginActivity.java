package com.thebestteamever.game.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.thebestteamever.game.R;
import com.thebestteamever.game.serviceapi.LoginParams;
import com.thebestteamever.game.serviceapi.RegistrationParams;
import com.thebestteamever.game.serviceapi.ServiceHelper;

public class LoginActivity extends AppCompatActivity implements ServiceHelper.LoginResultListener, View.OnClickListener {

    private ProgressBar progressBar;
    private int requestId;

    private EditText loginText;
    private EditText passwordText;

    private Button loginButton;
    private Button registrationButton;
    private Button withoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginText = (EditText) findViewById(R.id.editText1);
        passwordText = (EditText) findViewById(R.id.editText2);

        loginButton = (Button) findViewById(R.id.button1);
        registrationButton = (Button) findViewById(R.id.button2);
        withoutButton = (Button) findViewById(R.id.button3);

        loginButton.setOnClickListener(this);
        registrationButton.setOnClickListener(this);
        withoutButton.setOnClickListener(this);

    }

    @Override
    public void onLoginResult(boolean success, String result) {
//        progressBar.setVisibility(View.INVISIBLE);

        switch (result) {
            case "Ok":
                Intent intent = new Intent(this, NawActivity.class);
                startActivity(intent);
                finish();
                break;
            case "Login is already used":
                // TODO
                break;
            case "No some fields":
                // TODO
                break;
            default:
                // TODO
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
            case R.id.button2:
                Intent intent1 = new Intent(this, RegistrationActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.button3:
                Intent intent2 = new Intent(this, NawActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }
}
