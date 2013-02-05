package com.flabs.reminder.activities;

import java.util.ArrayList;

import android.graphics.drawable.TransitionDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.fragments.OnReminderActionChooserFragment;
import com.flabs.reminder.fragments.ReminderCategoryChooserFragment;
import com.flabs.reminder.fragments.ReminderFrequencyFragment;
import com.flabs.reminder.fragments.ReminderSetMessageFragment;
import com.flabs.reminder.fragments.ReminderSetTitleFragment;
import com.flabs.reminder.fragments.ReminderSubCategoryChooserFragment;
import com.flabs.reminder.fragments.ReminderTypeChooserFragment;
import com.flabs.reminder.reminder_object.ReminderObject;

public class NewReminderActivity extends ReminderActivity implements ViewPagerAdapter.ViewPagerCallback {
	
	public static final String TAG = "NewReminderActivity";
	
	public static final int VIEW_PAGER_ID = 0x01;
	
	private ViewPager mViewPager;
	
	private RelativeLayout mViewPagerContainer;
	
	private FrameLayout rootView1;
	
	private FrameLayout rootView2;
	
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
		
		rootView1 = (FrameLayout) findViewById(R.id.background_view1);
		rootView2 = (FrameLayout) findViewById(R.id.background_view2);
		
		mViewPagerContainer = (RelativeLayout) findViewById(R.id.view_pager_container);
		mViewPager = new ViewPager(this);
		mViewPager.setId(NewReminderActivity.VIEW_PAGER_ID);
		
		pageAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, newReminderObj, this);
		
		mViewPager.setAdapter(pageAdapter);
		
		mViewPagerContainer.addView(mViewPager);
	}

	private ArrayList<Fragment> initFragmentList() {
		fragmentList = new ArrayList<Fragment>();
		
		fragmentList.add(new ReminderTypeChooserFragment());
		fragmentList.add(new ReminderSetMessageFragment());
		fragmentList.add(new ReminderFrequencyFragment());
		fragmentList.add(new ReminderCategoryChooserFragment());
		fragmentList.add(new ReminderSubCategoryChooserFragment());
		fragmentList.add(new OnReminderActionChooserFragment());
		fragmentList.add(new ReminderSetTitleFragment());
		
		return fragmentList;
	}

	@Override
	public void onFragmentChanged(final int backgroundId) {
		final Animation fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
		final Animation fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
		if(rootView1.isShown()) {
		rootView1.postDelayed(new Runnable() {

			@Override
			public void run() {
				if(rootView1.isShown()) {
					rootView2.setBackgroundResource(backgroundId);
					rootView1.startAnimation(fadeOut);
					rootView1.setVisibility(View.GONE);
					rootView2.startAnimation(fadeIn);
					rootView2.setVisibility(View.VISIBLE);
				}
				else if(rootView2.isShown()) {
					rootView1.setBackgroundResource(backgroundId);
					rootView2.startAnimation(fadeOut);
					rootView2.setVisibility(View.GONE);
					rootView1.startAnimation(fadeIn);
					rootView1.setVisibility(View.VISIBLE);
				}
			}
			
		}, 100);
		}
		else if(rootView2.isShown()) {
			rootView2.postDelayed(new Runnable() {

				@Override
				public void run() {
					if(rootView1.isShown()) {
						rootView2.setBackgroundResource(backgroundId);
						rootView1.startAnimation(fadeOut);
						rootView1.setVisibility(View.GONE);
						rootView2.startAnimation(fadeIn);
						rootView2.setVisibility(View.VISIBLE);
					}
					else if(rootView2.isShown()) {
						rootView1.setBackgroundResource(backgroundId);
						rootView2.startAnimation(fadeOut);
						rootView2.setVisibility(View.GONE);
						rootView1.startAnimation(fadeIn);
						rootView1.setVisibility(View.VISIBLE);
					}
				}
				
			}, 100);
		}
	}
}
