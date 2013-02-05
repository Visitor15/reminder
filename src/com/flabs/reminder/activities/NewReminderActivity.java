package com.flabs.reminder.activities;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.fragments.OnReminderActionChooserFragment;
import com.flabs.reminder.fragments.ReminderCategoryChooserFragment;
import com.flabs.reminder.fragments.ReminderFrequencyFragment;
import com.flabs.reminder.fragments.ReminderSetMessageFragment;
import com.flabs.reminder.fragments.ReminderSetTitleFragment;
import com.flabs.reminder.fragments.ReminderSubCategoryChooserFragment;
import com.flabs.reminder.reminder_object.ReminderObject;



public class NewReminderActivity extends ReminderActivity {
	
	public static final String TAG = "NewReminderActivity";
	
	public static final int VIEW_PAGER_ID = 0x01;
	
	private ViewPager mViewPager;
	
	private RelativeLayout mViewPagerContainer;
	
	private ViewPagerAdapter pageAdapter;
	
	private ArrayList<Fragment> fragmentList;
	
	private ReminderObject newReminderObj;

	@Override
	public void onReminderActivityCreate() {
		setContentView(R.layout.new_reminder_layout);
		
		init();
	}

	@Override
	public void onReminderActivityStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReminderActivityRestart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReminderActivityResume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReminderActivityPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReminderActivityStop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReminderActivityDestroy() {
		// TODO Auto-generated method stub
		
	}
	
	private void init() {
		newReminderObj = new ReminderObject();
		initFragmentList();
		
		mViewPagerContainer = (RelativeLayout) findViewById(R.id.view_pager_container);
		mViewPager = new ViewPager(this);
		mViewPager.setId(NewReminderActivity.VIEW_PAGER_ID);
		
		pageAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
		
		mViewPagerContainer.addView(mViewPager);
	}

	private ArrayList<Fragment> initFragmentList() {
		fragmentList = new ArrayList<Fragment>();
		
		fragmentList.add(new ReminderSetMessageFragment(newReminderObj));
		fragmentList.add(new ReminderFrequencyFragment(newReminderObj));
		fragmentList.add(new ReminderCategoryChooserFragment(newReminderObj));
		fragmentList.add(new ReminderSubCategoryChooserFragment(newReminderObj));
		fragmentList.add(new OnReminderActionChooserFragment(newReminderObj));
		fragmentList.add(new ReminderSetTitleFragment(newReminderObj));
		
		return fragmentList;
	}
}
