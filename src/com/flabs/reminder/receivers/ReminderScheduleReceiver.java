package com.flabs.reminder.receivers;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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
		
		byte[] byteArray = intent.getByteArrayExtra(ReminderObject.TAG);
		
		Log.d(TAG, "NCC - BYTE LENGTH: " + byteArray.length);
		
		ActivityManager activityManager = (ActivityManager)c.getSystemService (Context.ACTIVITY_SERVICE); 
	    List<RunningTaskInfo> services = activityManager.getRunningTasks(Integer.MAX_VALUE); 
	    boolean isServiceFound = false; 
	    for (int i = 0; i < services.size(); i++) { 
	        if (services.get(i).topActivity.getClassName().equalsIgnoreCase("com.flabs.reminder.ui.TransparentDialogActivity")) {
	            isServiceFound = true;
	            
	            Intent mIntent = new Intent();
	            mIntent.setComponent(services.get(i).topActivity);
	    		mIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
	    		mIntent.putExtra(ReminderObject.TAG, byteArray);
	    		c.startActivity(mIntent);
	        }
	    } 
		
	    Intent mIntent = new Intent(c, TransparentDialogActivity.class);
		mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mIntent.putExtra(ReminderObject.TAG, byteArray);
		c.startActivity(mIntent);
	}

}
