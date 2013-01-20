package com.flabs.reminder.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.flabs.reminder.activities.MainActivity;

public class ReminderScheduleReceiver extends BroadcastReceiver {

	public static final String TAG = "ReminderScheduleReceiver";
	
	

	@Override
	public void onReceive(Context c, Intent intent) {

		Log.d(TAG, "onReceive");

		Intent mIntent = new Intent(c, MainActivity.class);
		mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		c.startActivity(mIntent);
	}

}
