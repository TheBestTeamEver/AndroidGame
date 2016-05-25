package com.thebestteamever.game.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.thebestteamever.game.R;
import com.thebestteamever.game.serviceapi.RegistrationParams;
import com.thebestteamever.game.serviceapi.ServiceHelper;

public class RegistrationActivity extends AppCompatActivity implements ServiceHelper.RegistrationResultListener, View.OnClickListener {

    private Button signUpButton;
    private Button signInButton;

    private ProgressBar progressBar;

    private TextView textName;
    private TextView textLogin;
    private TextView textPassword;

    private int requestId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        signUpButton = (Button) findViewById(R.id.button6);
        signInButton = (Button) findViewById(R.id.button7);
        progressBar = (ProgressBar) findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.INVISIBLE);
        textName = (TextView) findViewById(R.id.textView9);
        textLogin = (TextView) findViewById(R.id.textView10);
        textPassword = (TextView) findViewById(R.id.textView11);

        signUpButton.setOnClickListener(this);
        signInButton.setOnClickListener(this);
    }

    @Override
    public void onRegistrationResult(boolean success, String result) {
        progressBar.setVisibility(View.INVISIBLE);

        switch (result) {
            case "Ok":
                Intent intent = new Intent(this, NawActivity.class);
                startActivity(intent);
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
            case R.id.button6:
                progressBar.setVisibility(View.VISIBLE);
                String firstName = textName.getText().toString();
                String login = textLogin.getText().toString();
                String password = textPassword.getText().toString();
                requestId = ServiceHelper.makeRegistration(this, new RegistrationParams(firstName, login, password), this);
                break;
            case R.id.button7:
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();
                break;
        }

    }
}
