package com.thebestteamever.game.serviceapi;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.thebestteamever.game.serviceapi.parcelable.Level;
import com.thebestteamever.game.serviceapi.parcelable.LoginParams;

import java.io.IOException;

public class GameIntentService extends IntentService {
    public final static String ACTION_LEVEL = "action.LEVEL";
    public final static String EXTRA_LEVEL_TEXT = "extra.LEVEL_TEXT";
    public final static String ACTION_LEVEL_RESULT_SUCCESS = "action.ACTION_LEVEL_RESULT_SUCCESS";
    public final static String ACTION_LEVEL_RESULT_ERROR = "action.ACTION_LEVEL_RESULT_ERROR";
    public final static String EXTRA_LEVEL_RESULT = "extra.LEVEL_RESULT";

    public final static String ACTION_TOP = "action.TOP";
    public final static String EXTRA_TOP_TEXT = "extra.TOP_TEXT";
    public final static String ACTION_TOP_RESULT_SUCCESS = "action.ACTION_TOP_RESULT_SUCCESS";
    public final static String ACTION_TOP_RESULT_ERROR = "action.ACTION_TOP_RESULT_ERROR";
    public final static String EXTRA_TOP_RESULT = "extra.TOP_RESULT";

    public final static String ACTION_LOGIN = "action.LOGIN";
    public final static String EXTRA_LOGIN_TEXT = "extra.LOGIN_TEXT";
    public static final String ACTION_LOGIN_RESULT_SUCCESS = "action.ACTION_LOGIN_RESULT_SUCCESS";
    public final static String ACTION_LOGIN_RESULT_ERROR = "action.ACTION_LOGIN_RESULT_ERROR";
    public final static String EXTRA_LOGIN_RESULT = "extra.LOGIN_RESULT";

    public GameIntentService() {
        super("GameIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_LEVEL.equals(action)) {
                final String text = intent.getStringExtra(EXTRA_LEVEL_TEXT);
                handleActionLevel(text);
            } else if (ACTION_TOP.equals(action)) {
                final String text = intent.getStringExtra(EXTRA_TOP_TEXT);
                handleActionTop(text);
            } else if (ACTION_LOGIN.equals(action)) {
                final LoginParams params = intent.getParcelableExtra(EXTRA_LOGIN_TEXT);
                handleActionLogin(params);
            }
        }
    }

    private void handleActionLevel(final String text) {
        try {
            final Level level = Processor.processLevel("kek");
            if (level != null) {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
                        new Intent(ACTION_LEVEL_RESULT_SUCCESS).putExtra(EXTRA_LEVEL_RESULT, level)
                );
            } else {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
                        new Intent(ACTION_LEVEL_RESULT_ERROR).putExtra(EXTRA_LEVEL_RESULT, "result is null")
                );
            }
        } catch (IOException ex) {
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
                    new Intent(ACTION_LEVEL_RESULT_ERROR).putExtra(EXTRA_LEVEL_RESULT, ex.getMessage())
            );
        }
    }

    private void handleActionTop(final String text) {
        try {
            final String top = Processor.processTop("kek");
            if (top != null && !top.isEmpty()) {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
                        new Intent(ACTION_TOP_RESULT_SUCCESS).putExtra(EXTRA_TOP_RESULT, top)
                );
            } else {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
                        new Intent(ACTION_TOP_RESULT_ERROR).putExtra(EXTRA_TOP_RESULT, "result is null")
                );
            }
        } catch (IOException ex) {
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
                    new Intent(ACTION_TOP_RESULT_ERROR).putExtra(EXTRA_TOP_RESULT, ex.getMessage())
            );
        }
    }

    private void handleActionLogin(final LoginParams params) {
        try {
            final String login = Processor.processLogin(params);
            if (login != null && !login.isEmpty()) {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
                        new Intent(ACTION_LOGIN_RESULT_SUCCESS).putExtra(EXTRA_LOGIN_RESULT, login)
                );
            } else {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
                        new Intent(ACTION_LOGIN_RESULT_ERROR).putExtra(EXTRA_LOGIN_RESULT, "result is null")
                );
            }
        } catch (IOException ex) {
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
                    new Intent(ACTION_LOGIN_RESULT_ERROR).putExtra(EXTRA_LOGIN_RESULT, ex.getMessage())
            );
        }
    }
}