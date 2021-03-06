package com.flabs.reminder.activities;

import java.io.IOException;
import java.io.StreamCorruptedException;

import com.flabs.reminder.database.DBManager;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public abstract class ReminderActivity extends FragmentActivity implements IReminderActivity {

	public static final String TAG = "ReminderActivity";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		onReminderActivityCreate();
		
		try {
			DBManager.getInstance(this).checkAndInitDefaults();
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

	/*
	 * This is here to attempt to have each background gradient to be drawn
	 * with the best quality as possible on older Android devices.
	 */
	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Window window = getWindow();
		window.setFormat(PixelFormat.RGBA_8888);
	}
}
