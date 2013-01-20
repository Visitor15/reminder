package com.flabs.reminder.receivers;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReminderScheduleReceiver extends BroadcastReceiver {

	// Restart service every 30 seconds
	  private static final long REPEAT_TIME = 1000 * 30;
	
	@Override
	public void onReceive(Context c, Intent intent) {
		AlarmManager service = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
		    Intent i = new Intent(c, StartScheduleServiceReceiver.class);
		    PendingIntent pending = PendingIntent.getBroadcast(c, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
		    Calendar cal = Calendar.getInstance();
		    // Start 30 seconds after boot completed
		    cal.add(Calendar.SECOND, 30);
		    //
		    // Fetch every 30 seconds
		    // InexactRepeating allows Android to optimize the energy consumption
		    service.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), REPEAT_TIME, pending);
		
	}

}
