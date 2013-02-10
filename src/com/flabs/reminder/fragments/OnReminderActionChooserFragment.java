package com.flabs.reminder.fragments;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.activities.NewReminderActivity;
import com.flabs.reminder.activities.ViewPagerAdapter;
import com.flabs.reminder.reminder_object.ReminderObject;
import com.flabs.reminder.util.EnvironmentVariables.ACTION;
import com.flabs.reminder.util.EnvironmentVariables.REMINDER_TYPE;

public class OnReminderActionChooserFragment extends BaseReminderFragment {

	private Button btnNext;

	private LinearLayout actionsContainer;

	private ToggleButton btnDialog;
	private ToggleButton btnNotification;
	private ToggleButton btnSMS;
	private ToggleButton btnEmail;
	private ToggleButton btnCall;
	private ToggleButton btnVibrate;

	private LinearLayout row1;
	private LinearLayout row2;

	public OnReminderActionChooserFragment() {
		init();
	}

	public OnReminderActionChooserFragment(final ReminderObject reminderObj) {
		this.setReminderObject(reminderObj);
		init();
	}

	private void init() {
		setLayoutId(R.layout.new_reminder_on_reminder_action_layout);
		setBackground(R.drawable.orange_gradient_background);
	}

	private void setNextButtonListener(final Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				handleNextButtonClicked();
			}

		});
	}

	private void initActionsContainer(final LinearLayout container) {
		int dipValue = 325;
		Resources r = getResources();
		int pix = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());

		container.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);

		Log.d("TAG", "NCC - PIX IS: " + pix + " VIEW WIDTH: " + container.getMeasuredWidth());

		if(container.getMeasuredWidth() > pix) {
			container.setLayoutParams(new LinearLayout.LayoutParams(pix, 0, 0.75f));
		}
	}

	private void handleNextButtonClicked() {
		ViewPagerAdapter adapter = (ViewPagerAdapter) ((NewReminderActivity) getActivity()).getAdapter();
		ViewPager pager = ((NewReminderActivity) getActivity()).getViewPager();

		if((adapter.getDataList().size() - 1) == pager.getCurrentItem()) {

			if(getReminderObject().getReminderType().name().equalsIgnoreCase(REMINDER_TYPE.QUICK_REMINDER.name())) {
				// We're going to view the frequency fragment
				//			adapter.getDataList().add(new ReminderFrequencyFragment(getReminderObject()));
				adapter.getDataList().add(new ReminderCategoryChooserFragment(getReminderObject()));
			}
			else if(getReminderObject().getReminderType().name().equalsIgnoreCase(REMINDER_TYPE.REPEAT_REMINDER.name())) {
				// We're going to view the set message fragment
				adapter.getDataList().add(new ReminderCategoryChooserFragment(getReminderObject()));
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

	private void saveActionsToReminder(final ReminderObject reminderObj) {
		if(btnCall.isChecked()) {
			reminderObj.setOnRemindAction(ACTION.CALL);
		}
		if(btnDialog.isChecked()) {
			reminderObj.setOnRemindAction(ACTION.VIEW_REMINDER_DIALOG);
		}
		if(btnEmail.isChecked()) {
			reminderObj.setOnRemindAction(ACTION.SEND_EMAIL);
		}
		if(btnNotification.isChecked()) {
			reminderObj.setOnRemindAction(ACTION.VIEW_REMINDER_NOTIFICATION);
		}
		if(btnSMS.isChecked()) {
			reminderObj.setOnRemindAction(ACTION.SEND_SMS);
		}
		if(btnVibrate.isChecked()) {
			reminderObj.setOnRemindAction(ACTION.VIBRATE);
		}
	}

	private void initButtons(final ReminderObject reminderObj) {
		ArrayList<ACTION> actionList = reminderObj.getOnRemindAction();

		for(ACTION action : actionList) {
			switch(action) {
			case VIEW_REMINDER_DIALOG: {
				btnDialog.setChecked(true);
				break;
			}
			case VIEW_REMINDER_NOTIFICATION: {
				btnNotification.setChecked(true);
				break;
			}
			case SEND_SMS: {
				btnSMS.setChecked(true);
				break;
			}
			case SEND_EMAIL: {
				btnEmail.setChecked(true);
				break;
			}
			case CALL: {
				btnCall.setChecked(true);
				break;
			}
			case VIBRATE: {
				btnVibrate.setChecked(true);
				break;
			}
			default: {
				break;
			}
			}
		}
	}

	@Override
	public void onFragmentCreate(Bundle b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFragmentCreateView(View v) {
		btnNext = (Button) v.findViewById(R.id.btn_next);
		actionsContainer = (LinearLayout) v.findViewById(R.id.ll_actions_container);
		row1 = (LinearLayout) v.findViewById(R.id.ll_row1);
		row2 = (LinearLayout) v.findViewById(R.id.ll_row2);

		btnCall = (ToggleButton) row2.findViewById(R.id.toggleButton3);
		btnDialog = (ToggleButton) row1.findViewById(R.id.toggleButton1);
		btnEmail = (ToggleButton) row2.findViewById(R.id.toggleButton2);
		btnNotification = (ToggleButton) row1.findViewById(R.id.toggleButton2);
		btnSMS = (ToggleButton) row2.findViewById(R.id.toggleButton1);
		btnVibrate = (ToggleButton) row1.findViewById(R.id.toggleButton3);

		setNextButtonListener(btnNext);
		initButtons(getReminderObject());
	}

	@Override
	public void onFragmentCreated(Bundle b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFragmentPause() {
		saveActionsToReminder(getReminderObject());

		ViewPagerAdapter adapter = (ViewPagerAdapter) ((NewReminderActivity) getActivity()).getAdapter();
		adapter.setReminderObject(getReminderObject());
	}

	@Override
	public void onFragmentResume() {
		initActionsContainer(actionsContainer);
	}

	@Override
	public void onFragmentStop() {
		// TODO Auto-generated method stub

	}

}
