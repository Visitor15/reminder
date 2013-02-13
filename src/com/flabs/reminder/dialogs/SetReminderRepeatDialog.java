package com.flabs.reminder.dialogs;

import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.fragments.ReminderDialogCallback;
import com.flabs.reminder.reminder_object.ReminderObject;
import com.flabs.reminder.reminder_object.ReminderRepeatType;
import com.flabs.reminder.reminder_object.ReminderRepeatType.REPEAT_TYPE;
import com.flabs.reminder.util.EnvironmentVariables.DAYS;

public class SetReminderRepeatDialog extends DialogFragment {

	public static final int REPEAT_TYPE_MINUTE = 0;
	public static final int REPEAT_TYPE_HOUR = 1;
	public static final int REPEAT_TYPE_DAY = 2;
	public static final int REPEAT_TYPE_WEEK = 3;
	public static final int REPEAT_TYPE_MONTH = 4;
	public static final int REPEAT_TYPE_YEAR= 5;
	public static final int REPEAT_TYPE_ON_DATE = 6;

	private ReminderObject reminderObj;

	private View rootView;

	private RelativeLayout mContainer;

	private Spinner repeatTypeSpinner;

	private EditText numOfTimesToRepeat;

	private Button btnSet;

	private Button btnCancel;

	private TextView monday;

	private TextView tuesday;

	private TextView wednesday;

	private TextView thursday;

	private TextView friday;

	private TextView saturday;

	private TextView sunday;

	private ReminderDialogCallback mCallback;

	private String[] repeatTypeList;

	private ArrayAdapter<String> spinnerRepeatTypeAdapter;

	private LayoutInflater mInflater;

	private Animation fadeIn;

	private Animation fadeOut;

	public SetReminderRepeatDialog() {

	}

	public SetReminderRepeatDialog(ReminderDialogCallback callback) {
		this.mCallback = callback;
	}

	public SetReminderRepeatDialog(final ReminderObject reminderObj) {
		this.reminderObj = reminderObj;
	}

	private void setBtnSetClickListener(final Button btn) {
		btn.setText("SET");
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}

		});
	}

	private void setBtnCancelClickListener(final Button btn) {
		btn.setText("CANCEL");
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}

		});
	}

	private void initSpinner() {
		repeatTypeList = getActivity().getResources().getStringArray(R.array.repeat_type);
		spinnerRepeatTypeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, repeatTypeList);
		repeatTypeSpinner.setAdapter(spinnerRepeatTypeAdapter);

		repeatTypeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> viewParent, View v, int pos, long id) {
				handleRepeatTypeSelected(pos);
			}

			@Override
			public void onNothingSelected(AdapterView<?> viewParent) {
				// TODO Auto-generated method stub

			}

		});
	}

	private void handleRepeatTypeSelected(final int pos) {
		View v;
		switch(pos) {
		case 0: {
			mContainer.startAnimation(fadeOut);
			mContainer.setVisibility(View.INVISIBLE);

			mContainer.removeAllViews();

			v = mInflater.inflate(R.layout.repeat_type_minute_layout, mContainer);

			mContainer.startAnimation(fadeIn);
			mContainer.setVisibility(View.VISIBLE);

			break;
		}
		case 1: {
			mContainer.startAnimation(fadeOut);
			mContainer.setVisibility(View.INVISIBLE);

			mContainer.removeAllViews();

			v = mInflater.inflate(R.layout.repeat_type_hour_layout, mContainer);

			mContainer.startAnimation(fadeIn);
			mContainer.setVisibility(View.VISIBLE);

			break;
		}
		case 2: {
			mContainer.startAnimation(fadeOut);
			mContainer.setVisibility(View.INVISIBLE);

			mContainer.removeAllViews();

			v = mInflater.inflate(R.layout.repeat_type_day_layout, mContainer);

			initDaysView(v);

			mContainer.startAnimation(fadeIn);
			mContainer.setVisibility(View.VISIBLE);

			break;
		}
		case 3: {
			mContainer.startAnimation(fadeOut);
			mContainer.setVisibility(View.INVISIBLE);

			mContainer.removeAllViews();

			v = mInflater.inflate(R.layout.repeat_type_week_layout, mContainer);

			mContainer.startAnimation(fadeIn);
			mContainer.setVisibility(View.VISIBLE);

			break;
		}
		case 4: {
			mContainer.startAnimation(fadeOut);
			mContainer.setVisibility(View.INVISIBLE);

			v = mInflater.inflate(R.layout.repeat_type_month_layout, mContainer);

			mContainer.startAnimation(fadeIn);
			mContainer.setVisibility(View.VISIBLE);

			break;
		}
		case 5: {
			mContainer.startAnimation(fadeOut);
			mContainer.setVisibility(View.INVISIBLE);

			mContainer.removeAllViews();

			v = mInflater.inflate(R.layout.repeat_type_year_layout, mContainer);

			mContainer.startAnimation(fadeIn);
			mContainer.setVisibility(View.VISIBLE);

			break;
		}
		case 6: {
			mContainer.startAnimation(fadeOut);
			mContainer.setVisibility(View.INVISIBLE);

			mContainer.removeAllViews();

			v = mInflater.inflate(R.layout.repeat_type_specified_date_layout, mContainer);

			mContainer.startAnimation(fadeIn);
			mContainer.setVisibility(View.VISIBLE);

			break;
		}
		}
	}

	private void initDaysView(final View v) {
		LinearLayout row1 = (LinearLayout) v.findViewById(R.id.ll_row1);
		LinearLayout row2 = (LinearLayout) v.findViewById(R.id.ll_row2);

		monday = (TextView) ((LinearLayout)row1.findViewById(R.id.btn1_container)).findViewById(R.id.tv_text);
		tuesday = (TextView) ((LinearLayout)row1.findViewById(R.id.btn2_container)).findViewById(R.id.tv_text);
		wednesday = (TextView) ((LinearLayout)row1.findViewById(R.id.btn3_container)).findViewById(R.id.tv_text);
		thursday = (TextView) ((LinearLayout)row1.findViewById(R.id.btn4_container)).findViewById(R.id.tv_text);
		friday = (TextView) ((LinearLayout)row2.findViewById(R.id.btn1_container)).findViewById(R.id.tv_text);
		saturday = (TextView) ((LinearLayout)row2.findViewById(R.id.btn2_container)).findViewById(R.id.tv_text);
		sunday = (TextView) ((LinearLayout)row2.findViewById(R.id.btn3_container)).findViewById(R.id.tv_text);

		monday.setText("MONDAY");
		tuesday.setText("TUESDAY");
		wednesday.setText("WEDNESDAY");
		thursday.setText("THURSDAY");
		friday.setText("FRIDAY");
		saturday.setText("SATURDAY");
		sunday.setText("SUNDAY");

		setDayButtonClickListener(monday);
		setDayButtonClickListener(tuesday);
		setDayButtonClickListener(wednesday);
		setDayButtonClickListener(thursday);
		setDayButtonClickListener(friday);
		setDayButtonClickListener(saturday);
		setDayButtonClickListener(sunday);
	}

	private void setDayButtonClickListener(final View btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(v.isSelected()) {
					v.setSelected(false);
				}
				else {
					v.setSelected(true);

				}
			}

		});
	}

	private void setDialogPositiveButton(final int typeId) {
		switch(typeId) {
		case REPEAT_TYPE_MINUTE: {
			setBtnSetClickListenerForTypeMinute(btnSet);
			break;
		}
		case REPEAT_TYPE_HOUR: {
			setBtnSetClickListenerForTypeHour(btnSet);
			break;
		}
		case REPEAT_TYPE_DAY: {
			setBtnSetClickListenerForTypeDay(btnSet);
			break;
		}
		case REPEAT_TYPE_WEEK: {
			setBtnSetClickListenerForTypeWeek(btnSet);
			break;
		}
		case REPEAT_TYPE_MONTH: {
			setBtnSetClickListenerForTypeMonth(btnSet);
			break;
		}
		case REPEAT_TYPE_YEAR: {
			setBtnSetClickListenerForTypeYear(btnSet);
			break;
		}
		case REPEAT_TYPE_ON_DATE: {
			setBtnSetClickListenerForTypeSpecifiedDate(btnSet);
			break;
		}
		default: {
//			setBtnSetClickListener(btnSet);
		}
		}
	}

	private void setBtnSetClickListenerForTypeYear(Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mCallback.onSetRepeatType(constructRepeatObject(REPEAT_TYPE.YEAR));
				dismiss();
			}

		});
	}

	private void setBtnSetClickListenerForTypeMonth(Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mCallback.onSetRepeatType(constructRepeatObject(REPEAT_TYPE.MONTH));
				dismiss();
			}

		});
	}

	private void setBtnSetClickListenerForTypeWeek(Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mCallback.onSetRepeatType(constructRepeatObject(REPEAT_TYPE.WEEK));
				dismiss();
			}

		});
	}

	private void setBtnSetClickListenerForTypeDay(Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mCallback.onSetRepeatType(constructRepeatObject(REPEAT_TYPE.DAY));
				dismiss();
			}

		});
	}

	private void setBtnSetClickListenerForTypeHour(Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mCallback.onSetRepeatType(constructRepeatObject(REPEAT_TYPE.HOUR));
				dismiss();
			}

		});
	}

	private void setBtnSetClickListenerForTypeMinute(Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mCallback.onSetRepeatType(constructRepeatObject(REPEAT_TYPE.MINUTE));
				dismiss();
			}

		});
	}
	
	private void setBtnSetClickListenerForTypeSpecifiedDate(Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mCallback.onSetRepeatType(constructRepeatObject(REPEAT_TYPE.ON_DATE));
				dismiss();
			}

		});
	}

	private ReminderRepeatType constructRepeatObject(final REPEAT_TYPE type) {
		ReminderRepeatType repeat = new ReminderRepeatType();

		int numOfTimes;
		if(numOfTimesToRepeat.getText() != null && numOfTimesToRepeat.getText().length() > 0) {
			numOfTimes = Integer.parseInt(numOfTimesToRepeat.getText().toString());
		}
		else {
			numOfTimes = -1;
		}

		repeat.setType(type);
		repeat.setNumOfTimesToRepeat(numOfTimes);

		switch(type) {
		case MINUTE: {
			Spinner spinner = (Spinner) mContainer.findViewById(R.id.spinner);
			EditText input = (EditText) mContainer.findViewById(R.id.et_input);
			if(input.getText() != null && input.getText().length() > 0) {
				repeat.setMinuteTime(Integer.parseInt(input.getText().toString()));
			}
			else {
				repeat.setMinuteTime(Integer.parseInt(spinner.getSelectedItem().toString()));
			}
			break;
		}
		case HOUR: {
			Spinner spinner = (Spinner) mContainer.findViewById(R.id.spinner);
			EditText input = (EditText) mContainer.findViewById(R.id.et_input);
			if(input.getText().length() > 0) {
				repeat.setHourTime(Integer.parseInt(input.getText().toString()));
			}
			else {
				repeat.setHourTime(Integer.parseInt(spinner.getSelectedItem().toString()));
			}
			break;
		}
		case DAY: {
			if(monday.isSelected()) {
				repeat.addDay(DAYS.MONDAY);
			}
			if(tuesday.isSelected()) {
				repeat.addDay(DAYS.TUESDAY);
			}
			if(wednesday.isSelected()) {
				repeat.addDay(DAYS.WEDNESDAY);
			}
			if(thursday.isSelected()) {
				repeat.addDay(DAYS.THURSDAY);
			}
			if(friday.isSelected()) {
				repeat.addDay(DAYS.FRIDAY);
			}
			if(saturday.isSelected()) {
				repeat.addDay(DAYS.SATURDAY);
			}
			if(sunday.isSelected()) {
				repeat.addDay(DAYS.SUNDAY);
			}
			break;
		}
		case WEEK: {
			Spinner spinner = (Spinner) mContainer.findViewById(R.id.spinner);
			EditText input = (EditText) mContainer.findViewById(R.id.et_input);
			if(input.getText().length() > 0) {
				repeat.setWeekTime(Integer.parseInt(input.getText().toString()));
			}
			else {
				repeat.setWeekTime(Integer.parseInt(spinner.getSelectedItem().toString()));
			}
			break;
		}
		case MONTH: {
			Spinner spinner = (Spinner) mContainer.findViewById(R.id.spinner);
			EditText input = (EditText) mContainer.findViewById(R.id.et_input);
			if(input.getText().length() > 0) {
				repeat.setMonthTime(Integer.parseInt(input.getText().toString()));
			}
			else {
				repeat.setMonthTime(Integer.parseInt(spinner.getSelectedItem().toString()));
			}
			break;
		}
		case YEAR: {
			Spinner spinner = (Spinner) mContainer.findViewById(R.id.spinner);
			EditText input = (EditText) mContainer.findViewById(R.id.et_input);
			if(input.getText().length() > 0) {
				repeat.setYearTime(Integer.parseInt(input.getText().toString()));
			}
			else {
				repeat.setYearTime(Integer.parseInt(spinner.getSelectedItem().toString()));
			}
			break;
		}
		case ON_DATE: {
			DatePicker datePicker = (DatePicker) mContainer.findViewById(R.id.date_picker);
			
			Calendar cal = Calendar.getInstance();
			cal.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
			
			repeat.setDate(cal);
			break;
		}
		default: {

		}
		}

		return repeat;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setStyle(STYLE_NO_FRAME, 0);
		setStyle(STYLE_NO_TITLE, 0);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mInflater = inflater;
		rootView = inflater.inflate(R.layout.set_reminder_repeat_layout, container, false);
		mContainer = (RelativeLayout) rootView.findViewById(R.id.rl_container);
		repeatTypeSpinner = (Spinner) rootView.findViewById(R.id.spinner_repeat_type);
		btnSet = (Button) rootView.findViewById(R.id.btn_two);
		btnCancel = (Button) rootView.findViewById(R.id.btn_one);
		numOfTimesToRepeat = (EditText) rootView.findViewById(R.id.et_num_of_time_to_repeat);

		initSpinner();
		setDialogPositiveButton(repeatTypeSpinner.getSelectedItemPosition());
		setBtnCancelClickListener(btnCancel);

		fadeIn = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
		fadeOut = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);

		return rootView;
	}
}
