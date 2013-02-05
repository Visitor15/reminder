package com.flabs.reminder.fragments;

import android.os.Bundle;
import android.view.View;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.reminder_object.ReminderObject;

public class ReminderFrequencyFragment extends BaseReminderFragment {
	
	public ReminderFrequencyFragment() {
		init();
	}

	public ReminderFrequencyFragment(ReminderObject reminderObj) {
		this.setReminderObject(reminderObj);
		init();
	}
	
	private void init() {
		setLayoutId(R.layout.new_reminder_frequency_layout);
		setBackground(R.drawable.orange_gradient_background);
	}

	@Override
	public void onFragmentCreate(Bundle b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFragmentCreateView(View v) {
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
