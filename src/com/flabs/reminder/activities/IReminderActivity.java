package com.flabs.reminder.activities;

public interface IReminderActivity {

	void onReminderActivityCreate();
	
	void onReminderActivityStart();
	
	void onReminderActivityRestart();
	
	void onReminderActivityResume();
	
	void onReminderActivityPause();
	
	void onReminderActivityStop();
	
	void onReminderActivityDestroy();
	
}
