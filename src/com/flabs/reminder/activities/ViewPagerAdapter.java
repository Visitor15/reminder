package com.flabs.reminder.activities;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.flabs.reminder.fragments.BaseReminderFragment;
import com.flabs.reminder.reminder_object.ReminderObject;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	public static final String TAG = "ViewPagerAdapter";

	private ArrayList<Fragment> dataList;

	private ReminderObject reminderObj;

	private ViewPagerCallback callback;

	public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> dataList, ReminderObject reminderObj, ViewPagerCallback callback) {
		super(fm);
		this.dataList = dataList;
		this.reminderObj = reminderObj;
		this.callback = callback;
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
		frag.setAdapterCallback(getAdapterCallback());
		
		return frag;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}
	
	public ArrayList<Fragment> getDataList() {
		return this.dataList;
	}

	private OnFragmentCreatedCallback getAdapterCallback() {
		return new OnFragmentCreatedCallback() {

			@Override
			public void onFragmentCreated(int backgroundId) {
				callback.onFragmentChanged(backgroundId);
			}

		};
	}

	public interface ViewPagerCallback {
		void onFragmentChanged(final int backgroundId);
	}

	public interface OnFragmentCreatedCallback {
		void onFragmentCreated(final int backgroundId);
	}
}
