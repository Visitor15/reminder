package com.flabs.reminder.fragments;

import com.flabs.reminder.reminder_object.ReminderRepeatType;

public interface ReminderDialogCallback {

	public void onSetTime(final int hour, final int minute);
	
	public void onSetDate(final int month, final int day, final int year);
	
	public void onSetRepeatType(final ReminderRepeatType repeatType);
	
	public void onDialogPositiveBtnClicked();
	
	public void onDialogNegativeBtnClicked();
}
