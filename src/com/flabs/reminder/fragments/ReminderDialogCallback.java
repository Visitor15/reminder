package com.flabs.reminder.fragments;

public interface ReminderDialogCallback {

	public void onSetTime(final int hour, final int minute);
	
	public void onSetDate(final int month, final int day, final int year);
	
	public void onDialogPositiveBtnClicked();
	
	public void onDialogNegativeBtnClicked();
}
