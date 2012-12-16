package com.flabs.reminder.activities;

import android.app.Activity;
import android.os.Bundle;

public abstract class ReminderActivity extends Activity implements IReminderActivity {

	public static final String TAG = "ReminderActivity";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		onReminderActivityCreate();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		onReminderActivityStart();
	}
    
	@Override
    protected void onRestart() {
		super.onRestart();
		onReminderActivityRestart();
    }

	@Override
    protected void onResume() {
		super.onResume();
		onReminderActivityResume();
    }

	@Override
    protected void onPause() {
		super.onPause();
		onReminderActivityPause();
    }

	@Override
    protected void onStop() {
		super.onStop();
		onReminderActivityStop();
    }

	@Override
    protected void onDestroy() {
		super.onDestroy();
		onReminderActivityDestroy();
    }
}
