package com.flabs.reminder.services;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ReminderListenerService extends Service {


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
	    return Service.START_NOT_STICKY;
	  }

}
