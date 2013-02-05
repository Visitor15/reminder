package com.flabs.reminder.activities;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
	
	ArrayList<Fragment> dataList;

	public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> dataList) {
		super(fm);
		this.dataList = dataList;
		
		init();
	}
	
	private void init() {
		
	}

	@Override
	public Fragment getItem(int pos) {
		return dataList.get(pos);
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

}
