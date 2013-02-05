package com.flabs.reminder.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseReminderFragment extends Fragment implements IBaseReminder {

	private int layoutId;
	
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
		final View v = inflater.inflate(layoutId, null);

		onFragmentCreateView(v);
		
		return v;
	}

	@Override
	public void setLayoutId(int layoutId) {
		this.layoutId = layoutId;
	}

	@Override
	public int getLayoutId() {
		return layoutId;
	}

}
