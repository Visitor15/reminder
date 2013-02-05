package com.flabs.reminder.fragments;

import android.os.Bundle;

public interface IBaseReminder {
	
	public void setLayoutId(final int layoutId);
	
	public int getLayoutId();
	
	public void onFragmentCreate(final Bundle b);
	
	public void onFragmentCreated(final Bundle b);
	
	public void onFragmentPause();
	
	public void onFragmentResume();
	
	public void onFragmentStop();
}
