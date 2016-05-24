package com.thebestteamever.game.serviceapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.thebestteamever.game.Level;

import java.util.Hashtable;
import java.util.Map;

public class ServiceHelper {
	private static int mIdCounter = 1;
	private static Map<Integer, LevelResultListener> mListeners = new Hashtable<>();

	public static int makeLevel(final Context context, final String text, final LevelResultListener listener) {
		final IntentFilter filter = new IntentFilter();
		filter.addAction(GameIntentService.ACTION_LEVEL_RESULT_SUCCESS);
		filter.addAction(GameIntentService.ACTION_LEVEL_RESULT_ERROR);

		LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(final Context context, final Intent intent) {
				final Level result = intent.getParcelableExtra(GameIntentService.EXTRA_LEVEL_RESULT);
				final boolean success = intent.getAction().equals(GameIntentService.ACTION_LEVEL_RESULT_SUCCESS);
				for (Map.Entry<Integer, LevelResultListener> pair : mListeners.entrySet()) {
					pair.getValue().onLevelResult(success, result);
				}
				mListeners.clear();
			}
		}, filter);

		mListeners.put(mIdCounter, listener);

		Intent intent = new Intent(context, GameIntentService.class);
		intent.setAction(GameIntentService.ACTION_LEVEL);
		intent.putExtra(GameIntentService.EXTRA_LEVEL_TEXT, text);
		context.startService(intent);

		return mIdCounter++;
	}

	public static int makeRegistration(final Context context, final String text, final LevelResultListener listener) {
		final IntentFilter filter = new IntentFilter();
		filter.addAction(GameIntentService.ACTION_REGISTRATION_RESULT_SUCCESS);
		filter.addAction(GameIntentService.ACTION_REGISTRATIONL_RESULT_ERROR);

		LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(final Context context, final Intent intent) {
				final Registration result = intent.getParcelableExtra(GameIntentService.EXTRA_REGISTRATION_RESULT);
				final boolean success = intent.getAction().equals(GameIntentService.ACTION_REGISTRATION_RESULT_SUCCESS);
				for (Map.Entry<Integer, RegistrationResultListener> pair : mListeners.entrySet()) {
					pair.getValue().onRegistrationResult(success, result);
				}
				mListeners.clear();
			}
		}, filter);

		mListeners.put(mIdCounter, listener);

		Intent intent = new Intent(context, GameIntentService.class);
		intent.setAction(GameIntentService.ACTION_REGISTRATION);
		intent.putExtra(GameIntentService.EXTRA_REGISTRATION_TEXT, text);
		context.startService(intent);

		return mIdCounter++;
	}

	public static void removeListener(final int id) {
		mListeners.remove(id);
	}

	public interface LevelResultListener {
		void onLevelResult(final boolean success, final Level result);
	}

	public interface RegistrationResultListener {
		void onRegistrationResult(final boolean success, final Registration result);
	}
}
