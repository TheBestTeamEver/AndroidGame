package com.thebestteamever.game.ServiceAPI;

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

    public GameIntentService() {
        super("GameIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_LEVEL.equals(action)) {
                final String text = intent.getStringExtra(EXTRA_LEVEL_TEXT);
                handleActionQUESTION(text);
            }
        }
    }

    private void handleActionQUESTION(final String text) {
        try {
            final Level myQuestion = QuestionProcessor.processQuest("kek");
            if (myQuestion != null) {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
                        new Intent(ACTION_LEVEL_RESULT_SUCCESS).putExtra(EXTRA_LEVEL_RESULT, myQuestion)
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
}