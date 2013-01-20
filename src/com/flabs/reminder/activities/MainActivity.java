package com.flabs.reminder.activities;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.controllers.ReminderObjectBuilder;
import com.flabs.reminder.database.DBManager;
import com.flabs.reminder.reminder_object.ReminderObject;

public class MainActivity extends ReminderActivity {

	public static final String TAG = "MainActivity";
	
	//UI Elements
	EditText messageInput;
	EditText titleInput;
	Spinner category;
	Spinner subCategory;
	Button btnAdd;
	
	
	@Override
	public void onReminderActivityCreate() {
		setContentView(R.layout.temp_main);
		
		initVariables();
	}

	private void initVariables() {
		titleInput = (EditText) findViewById(R.id.et_title);
		messageInput = (EditText) findViewById(R.id.et_message);
		category = (Spinner) findViewById(R.id.spinner_category);
		subCategory = (Spinner) findViewById(R.id.spinner_subcategory);
		btnAdd = (Button) findViewById(R.id.btn);
		
		initAddBtn(btnAdd);
	}

	private void initAddBtn(Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBtnAddClicked();
			}
			
		});
	}

	protected void onBtnAddClicked() {
		
		ReminderObject mReminder = ReminderObjectBuilder.getInstance(this).createSimpleReminder();
		
		mReminder.setTitle(titleInput.getText().toString());
		mReminder.setMessage(messageInput.getText().toString());
		
		DBManager.getInstance(this).insert(mReminder);
	}

	@Override
	public void onReminderActivityStart() {
		// TODO Auto-generated method stub
		
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
