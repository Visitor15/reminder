package com.flabs.reminder.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.activities.NewReminderActivity;
import com.flabs.reminder.activities.ViewPagerAdapter;
import com.flabs.reminder.reminder_object.ReminderObject;
import com.flabs.reminder.util.EnvironmentVariables.REMINDER_TYPE;

public class ReminderSetTitleFragment extends BaseReminderFragment {

	private EditText titleField;

	private Button btnPreview;

	public ReminderSetTitleFragment() {
		init();
	}

	public ReminderSetTitleFragment(final ReminderObject reminderObj) {
		this.setReminderObject(reminderObj);
		init();
	}

	private void init() {
		setLayoutId(R.layout.new_reminder_title_layout);
		setBackground(R.drawable.navy_gradient_background);
	}

	private void handleNextButtonClicked() {
		if(titleField.getText().length() > 0) {
			getReminderObject().setTitle(titleField.getText().toString());
		}
		ViewPagerAdapter adapter = (ViewPagerAdapter) ((NewReminderActivity) getActivity()).getAdapter();
		ViewPager pager = ((NewReminderActivity) getActivity()).getViewPager();

		if((adapter.getDataList().size() - 1) == pager.getCurrentItem()) {
			if(getReminderObject().getReminderType().name().equalsIgnoreCase(REMINDER_TYPE.QUICK_REMINDER.name())) {
				// We're going to view the frequency fragment
				//			adapter.getDataList().add(new ReminderFrequencyFragment(getReminderObject()));
				adapter.getDataList().add(new ReminderPreviewFragment(getReminderObject()));
			}
			else if(getReminderObject().getReminderType().name().equalsIgnoreCase(REMINDER_TYPE.REPEAT_REMINDER.name())) {
				// We're going to view the set message fragment
				adapter.getDataList().add(new ReminderPreviewFragment(getReminderObject()));
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

	private void setPreviewButtonListener(Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				handleNextButtonClicked();
			}

		});
	}

	@Override
	public void onFragmentCreate(Bundle b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFragmentCreateView(View v) {
		titleField = (EditText) v.findViewById(R.id.et_title);
		btnPreview = (Button) v.findViewById(R.id.btn_next);

		setPreviewButtonListener(btnPreview);
	}

	@Override
	public void onFragmentCreated(Bundle b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFragmentPause() {
		if(titleField.getText().length() > 0) {
			getReminderObject().setTitle(titleField.getText().toString());
		}

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


}
