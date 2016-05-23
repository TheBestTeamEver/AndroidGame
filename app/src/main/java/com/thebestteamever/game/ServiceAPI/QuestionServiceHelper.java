package com.thebestteamever.game.ServiceAPI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.thebestteamever.game.Level;

import java.util.Hashtable;
import java.util.Map;

public class QuestionServiceHelper {
	private static int mIdCounter = 1;
	private static Map<Integer, QUESTIONResultListener> mListeners = new Hashtable<>();

	public static int makeQUESTION(final Context context, final String text, final QUESTIONResultListener listener) {
		final IntentFilter filter = new IntentFilter();
		filter.addAction(QuestionIntentService.ACTION_QUESTION_RESULT_SUCCESS);
		filter.addAction(QuestionIntentService.ACTION_QUESTION_RESULT_ERROR);

		LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(final Context context, final Intent intent) {
				final Level result = intent.getParcelableExtra(QuestionIntentService.EXTRA_QUESTION_RESULT);
				final boolean success = intent.getAction().equals(QuestionIntentService.ACTION_QUESTION_RESULT_SUCCESS);
				for (Map.Entry<Integer, QUESTIONResultListener> pair : mListeners.entrySet()) {
					pair.getValue().onQUESTIONResult(success, result);
				}
				mListeners.clear();
			}
		}, filter);

		mListeners.put(mIdCounter, listener);

		Intent intent = new Intent(context, QuestionIntentService.class);
		intent.setAction(QuestionIntentService.ACTION_QUESTION);
		intent.putExtra(QuestionIntentService.EXTRA_QUESTION_TEXT, text);
		context.startService(intent);

		return mIdCounter++;
	}

	public static void removeListener(final int id) {
		mListeners.remove(id);
	}

	public interface QUESTIONResultListener {
		void onQUESTIONResult(final boolean success, final Level result);
	}
}
