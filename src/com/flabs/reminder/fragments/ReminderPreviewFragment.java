package com.flabs.reminder.fragments;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.activities.MainActivity;
import com.flabs.reminder.activities.NewReminderActivity;
import com.flabs.reminder.activities.ViewPagerAdapter;
import com.flabs.reminder.database.DBManager;
import com.flabs.reminder.reminder_object.ReminderObject;

public class ReminderPreviewFragment extends BaseReminderFragment {

	public static final String TAG = "ReminderFrequencyFragment";

	private Button btnSaveReminder;

	private TextView timeView;
	private TextView dateView;
	
	private TextView reminderTextView;

	private Calendar calendar = Calendar.getInstance();

	public ReminderPreviewFragment() {
//		init();
	}

//	public ReminderPreviewFragment(ReminderObject reminderObj) {
//		this.setReminderObject(reminderObj);
//		init();
//	}

	private void init() {
		this.setReminderObject(((ViewPagerAdapter) ((NewReminderActivity) getActivity()).getAdapter()).getReminderObject());
		setLayoutId(R.layout.new_reminder_preview_layout);
		setBackground(R.drawable.blue_gradient_background);
	}

	private void setSaveBtnListener(final Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("TAG", "NCC - SUCCESS INSERTING? : " + DBManager.getInstance(getActivity()).insertReminderObject(getReminderObject()));
				
				sendToMainActivity();
			}
			
		});
	}
	
	private void sendToMainActivity() {
		Intent intent = new Intent(getActivity(), MainActivity.class);
		getActivity().startActivity(intent);
	}

	@Override
	public void onFragmentCreate(Bundle b) {
		init();
	}

	@Override
	public void onFragmentCreateView(View v) {
		btnSaveReminder = (Button) v.findViewById(R.id.btn_save);
		reminderTextView = (TextView) v.findViewById(R.id.textView1);
		
		setSaveBtnListener(btnSaveReminder);
	}

	@Override
	public void onFragmentCreated(Bundle b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFragmentPause() {
		
	}

	@Override
	public void onFragmentResume() {
		reminderTextView.setText(getReminderObject().toString());
	}

	@Override
	public void onFragmentStop() {
		// TODO Auto-generated method stub

	}
}
