package com.flabs.reminder.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.activities.NewReminderActivity;
import com.flabs.reminder.activities.ViewPagerAdapter;
import com.flabs.reminder.reminder_object.ReminderObject;
import com.flabs.reminder.util.EnvironmentVariables.REMINDER_TYPE;

public class ReminderSetMessageFragment extends BaseReminderFragment {
	
	public static final String TAG = "ReminderSetMessageFragment";
	
	private Button btnNext;
	
	public ReminderSetMessageFragment() {
		init();
	}
	
	public ReminderSetMessageFragment(final ReminderObject reminderObj) {
		this.setReminderObject(reminderObj);
		init();
	}
	
	private void init() {
		setLayoutId(R.layout.new_reminder_message_layout);
		setBackground(R.drawable.blue_gradient_background);
	}
	
	private void setNextButtonListener(final Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				handleNextButtonClicked();
			}
			
		});
	}
	
	private void handleNextButtonClicked() {
		ViewPagerAdapter adapter = (ViewPagerAdapter) ((NewReminderActivity) getActivity()).getAdapter();
		ViewPager pager = ((NewReminderActivity) getActivity()).getViewPager();
		
		if(getReminderObject().getReminderType().name().equalsIgnoreCase(REMINDER_TYPE.QUICK_REMINDER.name())) {
			// We're going to view the frequency fragment
//			adapter.getDataList().add(new ReminderFrequencyFragment(getReminderObject()));
			adapter.getDataList().add(new ReminderSetTitleFragment(getReminderObject()));
		}
		else if(getReminderObject().getReminderType().name().equalsIgnoreCase(REMINDER_TYPE.REPEAT_REMINDER.name())) {
			// We're going to view the set message fragment
			adapter.getDataList().add(new ReminderSetTitleFragment(getReminderObject()));
		}
		
		switchToNewFragment(pager, (adapter.getDataList().size() - 1));
	}
	
	private void switchToNewFragment(final ViewPager pager, final int pos) {
		pager.setCurrentItem(pos, true);
	}

	@Override
	public void onFragmentCreate(Bundle b) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onFragmentCreateView(View v) {
		btnNext = (Button) v.findViewById(R.id.btn_next);
		setNextButtonListener(btnNext);
	}

	@Override
	public void onFragmentCreated(Bundle b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFragmentPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFragmentResume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFragmentStop() {
		// TODO Auto-generated method stub
		
	}

	
}
