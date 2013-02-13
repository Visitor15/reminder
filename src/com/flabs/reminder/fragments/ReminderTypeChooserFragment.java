package com.flabs.reminder.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RatingBar;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.activities.NewReminderActivity;
import com.flabs.reminder.activities.ViewPagerAdapter;
import com.flabs.reminder.reminder_object.ReminderObject;
import com.flabs.reminder.util.EnvironmentVariables.REMINDER_TYPE;

public class ReminderTypeChooserFragment extends BaseReminderFragment {
	
	public static final String TAG = "ReminderTypeChooserFragment";
	
	public static final int QUICK_REMINDER = 0x01;
	
	public static final int REPEAT_REMINDER = 0x02;
	
	private Button btnQuickReminder;
	private Button btnRepeatReminder;
	private RatingBar priorityBar;
	
	public ReminderTypeChooserFragment() {
		init();
	}
	
	public ReminderTypeChooserFragment(final ReminderObject reminderObj) {
		this.setReminderObject(reminderObj);
		init();
	}
	
	private void init() {
		setLayoutId(R.layout.new_reminder_type_layout);
		setBackground(R.drawable.red_gradient_background);
	}
	
	private void setQuickReminderButton(final Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ViewPagerAdapter adapter = (ViewPagerAdapter) ((NewReminderActivity) getActivity()).getAdapter();
				adapter.getDataList().clear();
				adapter.getDataList().add(ReminderTypeChooserFragment.this);
				adapter.getReminderObject().setReminderType(REMINDER_TYPE.QUICK_REMINDER);
				ViewPager pager = ((NewReminderActivity) getActivity()).getViewPager();
				manipulateFragmentsInAdapter(adapter, ReminderTypeChooserFragment.QUICK_REMINDER);
				switchToNewFragment(pager, (adapter.getDataList().size() - 1));
			}
			
		});
	}
	
	private void setRepeatReminderButton(final Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ViewPagerAdapter adapter = (ViewPagerAdapter) ((NewReminderActivity) getActivity()).getAdapter();
				adapter.getDataList().clear();
				adapter.getDataList().add(ReminderTypeChooserFragment.this);
				adapter.getReminderObject().setReminderType(REMINDER_TYPE.REPEAT_REMINDER);
				ViewPager pager = ((NewReminderActivity) getActivity()).getViewPager();
				manipulateFragmentsInAdapter(adapter, ReminderTypeChooserFragment.REPEAT_REMINDER);
				switchToNewFragment(pager, (adapter.getDataList().size() - 1));
			}
			
		});
	}
	
	private void manipulateFragmentsInAdapter(final ViewPagerAdapter adapter, final int reminderType) {
		adapter.getReminderObject().setPriority(priorityBar.getRating());
		
		switch(reminderType) {
		case ReminderTypeChooserFragment.QUICK_REMINDER: {
			adapter.getDataList().add(new OnReminderActionChooserFragment(adapter.getReminderObject()));
			break;
		}
		case ReminderTypeChooserFragment.REPEAT_REMINDER: {
			adapter.getDataList().add(new OnReminderActionChooserFragment(adapter.getReminderObject()));
			break;
		}
		default: {
			//Do nothing
			break;
		}
		}
		
	}
	
	private void switchToNewFragment(final ViewPager pager, final int pos) {
		pager.setCurrentItem(pos, true);
	}
	
	private void setPriorityBarListener(final RatingBar bar) {
		bar.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.equals(MotionEvent.ACTION_UP)) {
					transitionBackgroundForRating(priorityBar.getRating());
				}
				return false;
			}
			
		});
	}
	
	private void transitionBackgroundForRating(final float rating) {
		if(rating < 1) {
			
		}
		else if(rating < 2 && rating > 1) {
			
		}
		else if(rating < 3 && rating > 2) {
			
		}
		else if(rating < 4 && rating > 3) {
			
		}
		else if(rating < 5 && rating > 4) {
			
		}
		else if(rating == 5) {
			
		}
	}
	
	@Override
	public void onFragmentCreate(Bundle b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFragmentCreateView(View v) {
		btnQuickReminder = (Button) v.findViewById(R.id.btn_quick_reminder);
		btnRepeatReminder = (Button) v.findViewById(R.id.btn_repeat_reminder);
		priorityBar = (RatingBar) v.findViewById(R.id.priority_bar);
		
		setQuickReminderButton(btnQuickReminder);
		setRepeatReminderButton(btnRepeatReminder);
		
		priorityBar.setRating(getReminderObject().getPriority());
		
		setPriorityBarListener(priorityBar);
	}

	@Override
	public void onFragmentCreated(Bundle b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFragmentPause() {
		ViewPagerAdapter adapter = (ViewPagerAdapter) ((NewReminderActivity) getActivity()).getAdapter();
		getReminderObject().setPriority(priorityBar.getRating());
		adapter.setReminderObject(getReminderObject());
	}

	@Override
	public void onFragmentResume() {
		
	}

	@Override
	public void onFragmentStop() {
		
	}
}
