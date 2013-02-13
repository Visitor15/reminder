package com.flabs.reminder.fragments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.activities.NewReminderActivity;
import com.flabs.reminder.activities.ViewPagerAdapter;
import com.flabs.reminder.dialogs.SetReminderDateDialog;
import com.flabs.reminder.dialogs.SetReminderRepeatDialog;
import com.flabs.reminder.dialogs.SetReminderTimeDialog;
import com.flabs.reminder.reminder_object.ReminderObject;
import com.flabs.reminder.reminder_object.ReminderRepeatType;
import com.flabs.reminder.util.EnvironmentVariables.REMINDER_TYPE;

public class ReminderFrequencyFragment extends BaseReminderFragment implements ReminderDialogCallback {

	public static final String TAG = "ReminderFrequencyFragment";

	private Button btnNext;
	private Button btnSetTime;
	private Button btnSetDate;
	private Button btnRepeat;

	private TextView timeView;
	private TextView dateView;

	private Calendar calendar = Calendar.getInstance();

	public ReminderFrequencyFragment() {
		init();
	}

	public ReminderFrequencyFragment(ReminderObject reminderObj) {
		this.setReminderObject(reminderObj);
		init();
	}

	private void init() {
		setLayoutId(R.layout.new_reminder_frequency_layout);
		setBackground(R.drawable.teal_gradient_background);
	}

	private void setNextButtonListener(final Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				handleNextButtonClicked();
			}

		});
	}

	private void setSetTimeButtonListener(final Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new SetReminderTimeDialog(ReminderFrequencyFragment.this).show(getActivity().getSupportFragmentManager(), "TAG");
			}

		});
	}

	private void setSetDateButtonListener(final Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new SetReminderDateDialog(ReminderFrequencyFragment.this).show(getActivity().getSupportFragmentManager(), "TAG");
			}

		});
	}
	
	private void setSetRepeatTypeButtonListener(final Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new SetReminderRepeatDialog(ReminderFrequencyFragment.this).show(getActivity().getSupportFragmentManager(), "TAG");
			}

		});
	}

	private void handleNextButtonClicked() {
		saveDateTimeToReminder(getReminderObject());
		ViewPagerAdapter adapter = (ViewPagerAdapter) ((NewReminderActivity) getActivity()).getAdapter();
		ViewPager pager = ((NewReminderActivity) getActivity()).getViewPager();

		if((adapter.getDataList().size() - 1) == pager.getCurrentItem()) {
			if(getReminderObject().getReminderType().name().equalsIgnoreCase(REMINDER_TYPE.QUICK_REMINDER.name())) {
				// We're going to view the frequency fragment
				adapter.getDataList().add(new ReminderSetMessageFragment(getReminderObject()));
			}
			else if(getReminderObject().getReminderType().name().equalsIgnoreCase(REMINDER_TYPE.REPEAT_REMINDER.name())) {
				// We're going to view the set message fragment
				adapter.getDataList().add(new ReminderSetMessageFragment(getReminderObject()));
			}

			switchToNewFragment(pager, (adapter.getDataList().size() - 1));
		}
		else {
			pager.setCurrentItem((pager.getCurrentItem() + 1), true);
		}
	}

	private void switchToNewFragment(final ViewPager pager, final int pos) {
		pager.setCurrentItem(pos, true);
	}

	private void saveDateTimeToReminder(ReminderObject reminderObject) {
		reminderObject.setReminderTime(calendar);
	}

	private void updateDateView(final Calendar calendar) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM d, ''yy");
		timeView.setText(formatter.format(calendar.getTime()));
	}

	private void updateTimeView(final Calendar calendar) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
		dateView.setText(formatter.format(calendar.getTime()));
	}
	
	private void updateRepeatView(final ReminderRepeatType repeat) {
		
	}

	@Override
	public void onFragmentCreate(Bundle b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFragmentCreateView(View v) {
		btnNext = (Button) v.findViewById(R.id.btn_next);
		btnSetTime = (Button) v.findViewById(R.id.btn_set_time);
		btnSetDate = (Button) v.findViewById(R.id.btn_set_date);
		btnRepeat = (Button) v.findViewById(R.id.btn_set_repeat);
		timeView = (TextView) v.findViewById(R.id.tv_time);
		dateView = (TextView) v.findViewById(R.id.tv_date);
		
		if(getReminderObject().getReminderType().name().equalsIgnoreCase(REMINDER_TYPE.REPEAT_REMINDER.name())) {
			btnRepeat.setVisibility(View.VISIBLE);
			setSetRepeatTypeButtonListener(btnRepeat);
		}
		else {
			btnRepeat.setVisibility(View.GONE);
		}

		try {
			updateDateView(getReminderObject().getReminderTime());
			updateTimeView(getReminderObject().getReminderTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setSetTimeButtonListener(btnSetTime);
		setSetDateButtonListener(btnSetDate);
		
		setNextButtonListener(btnNext);
	}

	@Override
	public void onFragmentCreated(Bundle b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFragmentPause() {
		saveDateTimeToReminder(getReminderObject());

		ViewPagerAdapter adapter = (ViewPagerAdapter) ((NewReminderActivity) getActivity()).getAdapter();
		adapter.setReminderObject(getReminderObject());
	}

	@Override
	public void onFragmentResume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFragmentStop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSetTime(final int hour, final int minute) {
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);

		try {
			updateTimeView(calendar);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onSetDate(final int month, final int day, final int year) {
		calendar.set(year, month, day);

		try {
			updateDateView(calendar);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onSetRepeatType(final ReminderRepeatType repeatType) {
		getReminderObject().setRepeat(repeatType);
		updateRepeatView(repeatType);
	}
}
