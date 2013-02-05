package com.flabs.reminder.fragments;

import android.os.Bundle;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.reminder_object.ReminderObject;

public class ReminderSetTitleFragment extends BaseReminderFragment {
	
	private ReminderObject reminderObj;

	public ReminderSetTitleFragment() {
		
	}
	
	public ReminderSetTitleFragment(final ReminderObject reminder) {
		this.reminderObj = reminder;
	}
	
	private void init() {
		setLayoutId(R.layout.new_reminder_title_layout);
	}

	@Override
	public void onFragmentCreate(Bundle b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFragmentCreated(Bundle b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFragmentPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFragmentResume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFragmentStop() {
		// TODO Auto-generated method stub
		
	}
}
