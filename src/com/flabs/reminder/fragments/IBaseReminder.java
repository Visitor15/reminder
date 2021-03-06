package com.flabs.reminder.fragments;

import android.os.Bundle;
import android.view.View;

import com.flabs.reminder.activities.ViewPagerAdapter.OnFragmentCreatedCallback;
import com.flabs.reminder.reminder_object.ReminderObject;

public interface IBaseReminder {
	
	public void setLayoutId(final int layoutId);
	
	public int getLayoutId();
	
	public void setReminderObject(final ReminderObject reminderObj);
	
	public ReminderObject getReminderObject();
	
	public void setBackground(final int backgroundId);
	
	public int getBackgroundId();
	
	public View getBackgroundView();
	
	public void setAdapterCallback(final OnFragmentCreatedCallback callback);
	
	public OnFragmentCreatedCallback getAdapterCallback();
	
	public void onFragmentCreate(final Bundle b);
	
	public void onFragmentCreateView(final View v);
	
	public void onFragmentCreated(final Bundle b);
	
	public void onFragmentPause();
	
	public void onFragmentResume();
	
	public void onFragmentStop();
}
