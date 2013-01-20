package com.flabs.reminder.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.flabs.reminder.services.ReminderListenerService;

public class StartScheduleServiceReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent service = new Intent(context, ReminderListenerService.class);
	    context.startService(service);
	}

}
