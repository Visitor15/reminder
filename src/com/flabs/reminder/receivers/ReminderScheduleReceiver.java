package com.flabs.reminder.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.flabs.reminder.preferences.ReminderPreferences;
import com.flabs.reminder.ui.TransparentDialogActivity;

public class ReminderScheduleReceiver extends BroadcastReceiver {

	public static final String TAG = "ReminderScheduleReceiver";

	@Override
	public void onReceive(Context c, Intent intent) {

		Log.d(TAG, "onReceive");

		if((Boolean) ReminderPreferences.getPreference(c, ReminderPreferences.KEY_REMIND_IS_ACTIVE)) {
			if((Boolean) ReminderPreferences.getPreference(c, ReminderPreferences.KEY_HAS_REMINDERS_TO_SHOW)) {
				startTransparentActivity(c);
			}
		}
	}

	private void startTransparentActivity(final Context c) {
		Intent mIntent = new Intent(c, TransparentDialogActivity.class);
		mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		c.startActivity(mIntent);
	}

}
