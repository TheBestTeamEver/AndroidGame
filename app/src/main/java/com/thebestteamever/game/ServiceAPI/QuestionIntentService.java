package com.thebestteamever.game.ServiceAPI;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.thebestteamever.game.QuestionSet;

import java.io.IOException;

public class QuestionIntentService extends IntentService {
    public final static String ACTION_QUESTION = "action.QUESTION";
    public final static String EXTRA_QUESTION_TEXT = "extra.QUESTION_TEXT";

    public final static String ACTION_QUESTION_RESULT_SUCCESS = "action.ACTION_QUESTION_RESULT_SUCCESS";
    public final static String ACTION_QUESTION_RESULT_ERROR = "action.ACTION_QUESTION_RESULT_ERROR";
    public final static String EXTRA_QUESTION_RESULT = "extra.QUESTION_RESULT";

    public QuestionIntentService() {
        super("QuestionIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_QUESTION.equals(action)) {
                final String text = intent.getStringExtra(EXTRA_QUESTION_TEXT);
                handleActionQUESTION(text);
            }
        }
    }

    private void handleActionQUESTION(final String text) {
        try {
            final QuestionSet myQuestion = QuestionProcessor.processQuest("kek");
            if (myQuestion != null) {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
                        new Intent(ACTION_QUESTION_RESULT_SUCCESS).putExtra(EXTRA_QUESTION_RESULT, myQuestion)
                );
            } else {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
                        new Intent(ACTION_QUESTION_RESULT_ERROR).putExtra(EXTRA_QUESTION_RESULT, "result is null")
                );
            }
        } catch (IOException ex) {
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(
                    new Intent(ACTION_QUESTION_RESULT_ERROR).putExtra(EXTRA_QUESTION_RESULT, ex.getMessage())
            );
        }
    }
}