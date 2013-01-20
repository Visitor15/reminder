package com.flabs.reminder.services;


import java.util.Calendar;

import com.flabs.reminder.receivers.StartScheduleServiceReceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class ReminderListenerService extends Service {

	//30 seconds
	private static final long REPEAT_TIME = 1000 * 30;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized int onStartCommand(final Intent intent, final int flags, final int startId) {
		
		
		
		AlarmManager service = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
		Intent i = new Intent(getApplicationContext(), StartScheduleServiceReceiver.class);
		PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
		Calendar cal = Calendar.getInstance();
		// Start 30 seconds after boot completed
		cal.add(Calendar.SECOND, 30);
		//
		// Fetch every 30 seconds
		// InexactRepeating allows Android to optimize the energy consumption
		service.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), REPEAT_TIME, pending);
		
		
		return Service.START_STICKY;
	}

}
