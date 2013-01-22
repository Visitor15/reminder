package com.flabs.reminder.ui;


import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.reminder_object.ReminderObject;


public class TestDialogFragment extends DialogFragment {

	private ReminderObject mReminder;
	
	public TestDialogFragment() {

	}
	
	public TestDialogFragment(final ReminderObject reminder) {
		mReminder = reminder;
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.test_dialog_layout, container);

		TextView mTitle = (TextView) v.findViewById(R.id.tv_title);
		
		mTitle.setText(mReminder.getTitle());

		return v;
	}
}
