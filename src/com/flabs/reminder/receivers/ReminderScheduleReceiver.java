package com.flabs.reminder.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.flabs.reminder.preferences.ReminderPreferences;
import com.flabs.reminder.reminder_object.ReminderObject;
import com.flabs.reminder.ui.TransparentDialogActivity;

public class ReminderScheduleReceiver extends BroadcastReceiver {

	public static final String TAG = "ReminderScheduleReceiver";

	@Override
	public void onReceive(Context c, Intent intent) {

		Log.d(TAG, "onReceive");

		if((Boolean) ReminderPreferences.getPreference(c, ReminderPreferences.KEY_REMIND_IS_ACTIVE)) {
			startTransparentActivity(c, intent);
		}
	}

	private void startTransparentActivity(final Context c, final Intent intent) {
		Intent mIntent = new Intent(c, TransparentDialogActivity.class);
		mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		byte[] byteArray = intent.getByteArrayExtra(ReminderObject.TAG);
		
		Log.d(TAG, "NCC - BYTE LENGTH: " + byteArray.length);
		
		mIntent.putExtra(ReminderObject.TAG, byteArray);
		c.startActivity(mIntent);
	}

}
