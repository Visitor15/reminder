package com.flabs.reminder.ui;


import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.reminder_object.ReminderObject;


public class TestDialogFragment extends DialogFragment {

	private ReminderObject mReminder;
	
	private LinearLayout mContainer;
	
	public TestDialogFragment() {

	}
	
	public TestDialogFragment(final ReminderObject reminder) {
		mReminder = reminder;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		setStyle(STYLE_NO_FRAME, 0);
	    setStyle(STYLE_NO_TITLE, R.style.Theme_Dialog);
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.test_dialog_layout, container, false);

		TextView mTitle = (TextView) v.findViewById(R.id.tv_title);
		TextView mMessage = (TextView) v.findViewById(R.id.tv_message);
		mContainer = (LinearLayout) v.findViewById(R.id.ll_second_container);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.setMargins(8, 8, 8, 8);
		
		mContainer.setLayoutParams(params);
		
		mTitle.setText(mReminder.getTitle());
		mMessage.setText(mReminder.getMessage());
		
		return v;
	}
}
