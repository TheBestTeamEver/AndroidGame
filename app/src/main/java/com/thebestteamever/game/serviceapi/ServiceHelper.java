package com.thebestteamever.game.serviceapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.thebestteamever.game.serviceapi.parcelable.Level;
import com.thebestteamever.game.serviceapi.parcelable.LoginParams;

import java.util.Hashtable;
import java.util.Map;

public class ServiceHelper {
    private static int mIdCounter = 1;
    private static Map<Integer, LevelResultListener> levelListeners = new Hashtable<>();
    private static Map<Integer, TopResultListener> topListeners = new Hashtable<>();
    private static Map<Integer, LoginResultListener> loginListeners = new Hashtable<>();

    public static int makeLevel(final Context context, final String text, final LevelResultListener listener) {
        final IntentFilter filter = new IntentFilter();
        filter.addAction(GameIntentService.ACTION_LEVEL_RESULT_SUCCESS);
        filter.addAction(GameIntentService.ACTION_LEVEL_RESULT_ERROR);

        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                final Level result = intent.getParcelableExtra(GameIntentService.EXTRA_LEVEL_RESULT);
                final boolean success = intent.getAction().equals(GameIntentService.ACTION_LEVEL_RESULT_SUCCESS);
                for (Map.Entry<Integer, LevelResultListener> pair : levelListeners.entrySet()) {
                    pair.getValue().onLevelResult(success, result);
                }
                levelListeners.clear();
            }
        }, filter);

        levelListeners.put(mIdCounter, listener);

        Intent intent = new Intent(context, GameIntentService.class);
        intent.setAction(GameIntentService.ACTION_LEVEL);
        intent.putExtra(GameIntentService.EXTRA_LEVEL_TEXT, text);
        context.startService(intent);

        return mIdCounter++;
    }

    public static int makeTop(final Context context, final String text, final TopResultListener listener) {
        final IntentFilter filter = new IntentFilter();
        filter.addAction(GameIntentService.ACTION_TOP_RESULT_SUCCESS);
        filter.addAction(GameIntentService.ACTION_TOP_RESULT_ERROR);

        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                final String result = intent.getStringExtra(GameIntentService.EXTRA_TOP_RESULT);
                final boolean success = intent.getAction().equals(GameIntentService.ACTION_TOP_RESULT_SUCCESS);
                for (Map.Entry<Integer, TopResultListener> pair : topListeners.entrySet()) {
                    pair.getValue().onTopResult(success, result);
                }
                topListeners.clear();
            }
        }, filter);

        topListeners.put(mIdCounter, listener);

        Intent intent = new Intent(context, GameIntentService.class);
        intent.setAction(GameIntentService.ACTION_TOP);
        intent.putExtra(GameIntentService.EXTRA_TOP_TEXT, text);
        context.startService(intent);

        return mIdCounter++;
    }

    public static int makeLogin (final Context context, final LoginParams params, final LoginResultListener listener) {
        final IntentFilter filter = new IntentFilter();
        filter.addAction(GameIntentService.ACTION_LOGIN_RESULT_SUCCESS);
        filter.addAction(GameIntentService.ACTION_LOGIN_RESULT_ERROR);

        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                final String result = intent.getStringExtra(GameIntentService.EXTRA_LOGIN_RESULT);
                final boolean success = intent.getAction().equals(GameIntentService.ACTION_LOGIN_RESULT_SUCCESS);
                for (Map.Entry<Integer, LoginResultListener> pair : loginListeners.entrySet()) {
                    pair.getValue().onLoginResult(success, result);
                }
                loginListeners.clear();
            }
        }, filter);

        loginListeners.put(mIdCounter, listener);

        Intent intent = new Intent(context, GameIntentService.class);
        intent.setAction(GameIntentService.ACTION_LOGIN);
        intent.putExtra(GameIntentService.EXTRA_LOGIN_TEXT, params);
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
}
