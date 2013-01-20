package com.flabs.reminder.ui;


import com.flabs.reminder.activities.ReminderActivity;

public class TransparentDialogActivity extends ReminderActivity {

	@Override
	public void onReminderActivityCreate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReminderActivityStart() {
		TestDialogFragment dialogFrag = new TestDialogFragment();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		dialogFrag.show(ft, "TAG");
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

}
