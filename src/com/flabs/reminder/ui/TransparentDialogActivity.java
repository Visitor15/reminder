package com.flabs.reminder.ui;


import java.io.IOException;
import java.io.StreamCorruptedException;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.flabs.reminder.activities.ReminderActivity;
import com.flabs.reminder.reminder_object.ReminderObject;

public class TransparentDialogActivity extends ReminderActivity {

	@Override
	public void onReminderActivityCreate() {
		try {
			handleIntent(getIntent());
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
	
	private void handleIntent(final Intent mIntent) throws StreamCorruptedException, IOException, ClassNotFoundException {
		byte[] reminderByteArray = mIntent.getByteArrayExtra(ReminderObject.TAG);
		Log.d(TAG, "NCC - BYTE LENGTH: " + reminderByteArray.length);
		ReminderObject reminder = (ReminderObject) ReminderObject.fromBinary(reminderByteArray);
		
		showReminderDialog(reminder);
	}

	private void showReminderDialog(ReminderObject reminder) {
		TestDialogFragment dialogFrag = new TestDialogFragment(reminder);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		dialogFrag.show(ft, "TAG");
	}
}
