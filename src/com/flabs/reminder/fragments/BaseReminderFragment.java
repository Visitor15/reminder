package com.flabs.reminder.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.activities.ViewPagerAdapter.OnFragmentCreatedCallback;
import com.flabs.reminder.reminder_object.ReminderObject;

public abstract class BaseReminderFragment extends Fragment implements IBaseReminder, ReminderDialogCallback {

	private View rootView;
	private int layoutId;
	private int backgroundId;
	private ReminderObject reminderObj;
	protected OnFragmentCreatedCallback adapterCallback;

	@Override
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		onFragmentCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated (Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		onFragmentCreated(savedInstanceState);

	}

	@Override
	public void onPause() {
		super.onPause();
		onFragmentPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		onFragmentResume();
	}

	@Override
	public void onStop() {
		super.onStop();
		onFragmentStop();
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		rootView = inflater.inflate(layoutId, null);

		onFragmentCreateView(rootView);

		return rootView;
	}

	@Override
	public void setLayoutId(int layoutId) {
		this.layoutId = layoutId;
	}

	@Override
	public int getLayoutId() {
		return layoutId;
	}

	@Override
	public void setReminderObject(final ReminderObject reminderObj) {
		this.reminderObj = reminderObj;
	}

	@Override
	public ReminderObject getReminderObject() {
		return this.reminderObj;
	}

	@Override
	public void setBackground(final int backgroundId) {
		this.backgroundId = backgroundId;
	}

	@Override
	public int getBackgroundId() {
		return this.backgroundId;
	}

	@Override
	public View getBackgroundView() {
		return rootView.findViewById(R.id.background);
	}

	@Override
	public void setAdapterCallback(final OnFragmentCreatedCallback callback) {
		this.adapterCallback = callback;
	}
	
	@Override
	public OnFragmentCreatedCallback getAdapterCallback() {
		return this.adapterCallback;
	}
	
	@Override
	public void onSetTime(int hour, int minute) {
		//Do nothing
	}

	@Override
	public void onSetDate(int month, int day, int year) {
		//Do nothing
	}
	
	@Override
	public void onDialogPositiveBtnClicked() {
		//Do nothing
	}

	@Override
	public void onDialogNegativeBtnClicked() {
		//Do nothing
	}
}
