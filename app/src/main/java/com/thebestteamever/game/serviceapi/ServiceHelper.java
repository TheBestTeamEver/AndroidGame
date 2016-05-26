package com.thebestteamever.game.serviceapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.thebestteamever.game.serviceapi.parcelable.Score;
import com.thebestteamever.game.serviceapi.parcelable.Level;
import com.thebestteamever.game.serviceapi.parcelable.LoginParams;

import java.util.Hashtable;
import java.util.Map;

public class ServiceHelper {
    private static int mIdCounter = 1;
    private static Map<Integer, LevelResultListener> levelListeners = new Hashtable<>();
    private static Map<Integer, TopResultListener> topListeners = new Hashtable<>();
    private static Map<Integer, LoginResultListener> loginListeners = new Hashtable<>();
    private static Map<Integer, ScoreResultListener> scoreListeners = new Hashtable<>();

    public static int makeLevel(final Context context, final String text, final LevelResultListener listener) {
        final IntentFilter filter = new IntentFilter();
        filter.addAction(GameService.ACTION_LEVEL_RESULT_SUCCESS);
        filter.addAction(GameService.ACTION_LEVEL_RESULT_ERROR);

        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                final Level result = intent.getParcelableExtra(GameService.EXTRA_LEVEL_RESULT);
                final boolean success = intent.getAction().equals(GameService.ACTION_LEVEL_RESULT_SUCCESS);
                for (Map.Entry<Integer, LevelResultListener> pair : levelListeners.entrySet()) {
                    pair.getValue().onLevelResult(success, result);
                }
                levelListeners.clear();
            }
        }, filter);

        levelListeners.put(mIdCounter, listener);

        Intent intent = new Intent(context, GameService.class);
        intent.setAction(GameService.ACTION_LEVEL);
        intent.putExtra(GameService.EXTRA_LEVEL_TEXT, text);
        context.startService(intent);

        return mIdCounter++;
    }

    public static int makeTop(final Context context, final String text, final TopResultListener listener) {
        final IntentFilter filter = new IntentFilter();
        filter.addAction(GameService.ACTION_TOP_RESULT_SUCCESS);
        filter.addAction(GameService.ACTION_TOP_RESULT_ERROR);

        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                final String result = intent.getStringExtra(GameService.EXTRA_TOP_RESULT);
                final boolean success = intent.getAction().equals(GameService.ACTION_TOP_RESULT_SUCCESS);
                for (Map.Entry<Integer, TopResultListener> pair : topListeners.entrySet()) {
                    pair.getValue().onTopResult(success, result);
                }
                topListeners.clear();
            }
        }, filter);

        topListeners.put(mIdCounter, listener);

        Intent intent = new Intent(context, GameService.class);
        intent.setAction(GameService.ACTION_TOP);
        intent.putExtra(GameService.EXTRA_TOP_TEXT, text);
        context.startService(intent);

        return mIdCounter++;
    }

    public static int makeLogin (final Context context, final LoginParams params, final LoginResultListener listener) {
        final IntentFilter filter = new IntentFilter();
        filter.addAction(GameService.ACTION_LOGIN_RESULT_SUCCESS);
        filter.addAction(GameService.ACTION_LOGIN_RESULT_ERROR);

        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                final String result = intent.getStringExtra(GameService.EXTRA_LOGIN_RESULT);
                final boolean success = intent.getAction().equals(GameService.ACTION_LOGIN_RESULT_SUCCESS);
                for (Map.Entry<Integer, LoginResultListener> pair : loginListeners.entrySet()) {
                    pair.getValue().onLoginResult(success, result);
                }
                loginListeners.clear();
            }
        }, filter);

        loginListeners.put(mIdCounter, listener);

        Intent intent = new Intent(context, GameService.class);
        intent.setAction(GameService.ACTION_LOGIN);
        intent.putExtra(GameService.EXTRA_LOGIN_TEXT, params);
        context.startService(intent);

        return mIdCounter++;
    }

    public static int makeScore (final Context context, final Score params, final ScoreResultListener listener) {
        final IntentFilter filter = new IntentFilter();
        filter.addAction(GameService.ACTION_SCORE_RESULT_SUCCESS);
        filter.addAction(GameService.ACTION_SCORE_RESULT_ERROR);

        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                final String result = intent.getStringExtra(GameService.EXTRA_SCORE_RESULT);
                final boolean success = intent.getAction().equals(GameService.ACTION_SCORE_RESULT_SUCCESS);
                for (Map.Entry<Integer, ScoreResultListener> pair : scoreListeners.entrySet()) {
                    pair.getValue().onScoreResult(success, result);
                }
                scoreListeners.clear();
            }
        }, filter);

        scoreListeners.put(mIdCounter, listener);

        Intent intent = new Intent(context, GameService.class);
        intent.setAction(GameService.ACTION_SCORE);
        intent.putExtra(GameService.EXTRA_SCORE_TEXT, params);
        context.startService(intent);

        return mIdCounter++;
    }

    public static void removeLevelListener(final int id) {
        levelListeners.remove(id);
    }

    public static void removeTopListener(final int id) {
        topListeners.remove(id);
    }

    public static void removeLoginListener(final int id) {
        levelListeners.remove(id);
    }

    public interface LevelResultListener {
        void onLevelResult(final boolean success, final Level result);
    }

    public interface TopResultListener {
        void onTopResult(final boolean success, final String result);
    }

    public interface LoginResultListener {
        void onLoginResult(final boolean success, final String result);
    }

    public interface ScoreResultListener {
        void onScoreResult(final boolean success, final String result);
    }
}
