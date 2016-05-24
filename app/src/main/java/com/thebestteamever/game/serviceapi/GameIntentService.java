package com.thebestteamever.game.serviceapi;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.thebestteamever.game.Level;

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

    public final static String ACTION_REGISTRATION = "action.REGISTRATION";
    public final static String EXTRA_REGISTRATION_TEXT = "extra.REGISTRATION_TEXT";

    public static final String ACTION_REGISTRATION_RESULT_SUCCESS = "action.ACTION_REGISTRATION_RESULT_SUCCESS";
    public final static String ACTION_REGISTRATION_RESULT_ERROR = "action.ACTION_REGISTRATION_RESULT_ERROR";
    public final static String EXTRA_REGISTRATION_RESULT = "extra.REGISTRATION_RESULT";

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
            } else if (ACTION_REGISTRATION.equals(action)) {
                final RegistrationParams params = intent.getParcelableExtra(EXTRA_REGISTRATION_TEXT);
                handleActionRegistration(params);
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
            final String  top = Processor.processTop("kek");
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

    private void handleActionRegistration(final RegistrationParams params) {
        try {
            final String registration = Processor.processRegistration(params);
            if (registration != null && !registration.isEmpty()) {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
                        new Intent(ACTION_REGISTRATION_RESULT_SUCCESS).putExtra(EXTRA_REGISTRATION_RESULT, registration)
                );
            } else {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
                        new Intent(ACTION_REGISTRATION_RESULT_ERROR).putExtra(EXTRA_REGISTRATION_RESULT, "result is null")
                );
            }
        } catch (IOException ex) {
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
                    new Intent(ACTION_REGISTRATION_RESULT_ERROR).putExtra(EXTRA_REGISTRATION_RESULT, ex.getMessage())
            );
        }
    }
//    private void handleActionAuth(final RegistrationParams params) {
//        try {
//            final String registration = Processor.processRegistration(params);
//            if (registration != null && !registration.isEmpty()) {
//                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
//                        new Intent(ACTION_REGISTRATION_RESULT_SUCCESS).putExtra(EXTRA_REGISTRATION_RESULT, registration)
//                );
//            } else {
//                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
//                        new Intent(ACTION_REGISTRATION_RESULT_ERROR).putExtra(EXTRA_REGISTRATION_RESULT, "result is null")
//                );
//            }
//        } catch (IOException ex) {
//            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
//                    new Intent(ACTION_REGISTRATION_RESULT_ERROR).putExtra(EXTRA_REGISTRATION_RESULT, ex.getMessage())
//            );
//        }
//    }
}