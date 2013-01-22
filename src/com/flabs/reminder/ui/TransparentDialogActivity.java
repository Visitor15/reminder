package com.flabs.reminder.ui;


import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.app.FragmentTransaction;

import com.flabs.reminder.activities.ReminderActivity;
import com.flabs.reminder.database.DBManager;
import com.flabs.reminder.reminder_object.ReminderObject;

public class TransparentDialogActivity extends ReminderActivity {

	@Override
	public void onReminderActivityCreate() {
		try {
			ArrayList<ReminderObject> reminders = DBManager.getInstance(this)
					.getReminderObjectsByTitle("");
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
