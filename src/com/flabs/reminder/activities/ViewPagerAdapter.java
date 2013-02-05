package com.flabs.reminder.activities;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.flabs.reminder.fragments.BaseReminderFragment;
import com.flabs.reminder.reminder_object.ReminderObject;

public class ViewPagerAdapter extends FragmentPagerAdapter {
	
	private ArrayList<Fragment> dataList;
	
	private ReminderObject reminderObj;

	public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> dataList, ReminderObject reminderObj) {
		super(fm);
		this.dataList = dataList;
		this.reminderObj = reminderObj;
		init();
	}
	
	private void init() {
		if(reminderObj == null) {
			reminderObj = new ReminderObject();
		}
	}
	
	public void setReminderObject(final ReminderObject reminder) {
		this.reminderObj = reminder;
	}
	
	public ReminderObject getReminderObject() {
		return this.reminderObj;
	}

	@Override
	public Fragment getItem(int pos) {
		BaseReminderFragment frag = ((BaseReminderFragment) dataList.get(pos));
		frag.setReminderObject(reminderObj);
		return frag;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

}
