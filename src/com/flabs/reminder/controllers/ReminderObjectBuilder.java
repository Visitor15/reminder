package com.flabs.reminder.controllers;

import android.content.Context;

import com.flabs.reminder.reminder_object.ReminderObject;

public class ReminderObjectBuilder {

	public static final String TAG = "ReminderObjectBuilder";
	private static ReminderObjectBuilder instance;
	private Context mContext;
	
	
	
	private ReminderObjectBuilder(Context c) {
		mContext = c;
		instance = this;
	}
	
	public static ReminderObjectBuilder getInstance(Context c) {
		if(instance == null) {
			return new ReminderObjectBuilder(c);
		}
		else {
			return instance;
		}
	}
	
	public ReminderObject createSimpleReminder() {
		ReminderObject reminderObj = new ReminderObject();
		
		return reminderObj;
	}
	
}
