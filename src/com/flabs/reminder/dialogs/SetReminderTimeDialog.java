package com.flabs.reminder.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.reminder_object.ReminderObject;

public class SetReminderTimeDialog extends DialogFragment {

	private ReminderObject reminderObj;
	
	private View rootView;
	
	private Button btnSet;
	
	private Button btnCancel;
	
	public SetReminderTimeDialog() {
		
	}
	
	public SetReminderTimeDialog(final ReminderObject reminderObj) {
		this.reminderObj = reminderObj;
	}
	
	private void setBtnSetClickListener(final Button btn) {
		btn.setText("SET");
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private void setBtnCancelClickListener(final Button btn) {
		btn.setText("CANCEL");
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
			
		});
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setStyle(STYLE_NO_FRAME, 0);
		setStyle(STYLE_NO_TITLE, 0);

	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.new_reminder_set_time_dialog_layout, container, false);
	
		btnSet = (Button) rootView.findViewById(R.id.btn_two);
		btnCancel = (Button) rootView.findViewById(R.id.btn_one);
		
		setBtnSetClickListener(btnSet);
		setBtnCancelClickListener(btnCancel);
		
		return rootView;
	}
}
