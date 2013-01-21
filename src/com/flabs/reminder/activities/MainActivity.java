package com.flabs.reminder.activities;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Random;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.controllers.ReminderObjectBuilder;
import com.flabs.reminder.database.DBManager;
import com.flabs.reminder.reminder_object.Category;
import com.flabs.reminder.reminder_object.ReminderObject;
import com.flabs.reminder.reminder_object.SubCategory;

public class MainActivity extends ReminderActivity {

	public static final String TAG = "MainActivity";
	
	//UI Elements
	LinearLayout container;
	EditText messageInput;
	EditText titleInput;
	Spinner category;
	Spinner subCategory;
	Button btnAdd;
	Button btnSearch;
	
	
	@Override
	public void onReminderActivityCreate() {
		setContentView(R.layout.temp_main);
		
		initVariables();
	}

	private void initVariables() {
		container = (LinearLayout) findViewById(R.id.ll_container);
		titleInput = (EditText) findViewById(R.id.et_title);
		messageInput = (EditText) findViewById(R.id.et_message);
		category = (Spinner) findViewById(R.id.spinner_category);
		subCategory = (Spinner) findViewById(R.id.spinner_subcategory);
		btnAdd = (Button) findViewById(R.id.btn);
		btnSearch = (Button) findViewById(R.id.btn_search);
		
		initAddBtn(btnAdd);
		initSearchBtn(btnSearch);
	}

	private void initAddBtn(Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBtnAddClicked();
			}
			
		});
	}
	
	private void initSearchBtn(Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBtnSearchClicked();
			}
			
		});
	}
	
	protected void onBtnSearchClicked() {
		try {
			ArrayList<ReminderObject> reminders = DBManager.getInstance(this)
					.getReminderObjectsByTitle(titleInput.getText().toString());
			
			addToUI(reminders);
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

	private void addToUI(ArrayList<ReminderObject> reminders) {
		LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
		
		
		for(ReminderObject obj : reminders) {
		
		View v = inflater.inflate(R.layout.test_list_reminder_item_layout, null);
		
		TextView title = (TextView) v.findViewById(R.id.tv_title);
		TextView category = (TextView) v.findViewById(R.id.tv_category);
		TextView subCategory = (TextView) v.findViewById(R.id.tv_subcategory);
		TextView message = (TextView) v.findViewById(R.id.tv_message);
		
		
		title.setText(obj.getTitle());
		message.setText(obj.getMessage());
		category.setText(obj.getCategory().getLabel());
		subCategory.setText(obj.getSubCategory().getLabel());
		
		if(obj.isActivated()) {
			v.setBackgroundColor(Color.parseColor("#22000088"));
		}
		else {
			v.setBackgroundColor(Color.parseColor("#22880000"));
		}
		
		Log.d(TAG, "NCC - ADDING: " + title);
		
		container.addView(v);
		
		}
	}

	protected void onBtnAddClicked() {
		
		ReminderObject mReminder = ReminderObjectBuilder.getInstance(this).createSimpleReminder();
		
		mReminder.setTitle(titleInput.getText().toString());
		mReminder.setMessage(messageInput.getText().toString());
		
		Category category = new Category();
		category.setLabel("Test category");
		category.setCustomLabel("Test custom label");
		
		SubCategory subCategory = new SubCategory();
		subCategory.setLabel("Test subcategory");
		subCategory.setCustomLabel("Test custom subcategory label");
		
		mReminder.setCategory(category);
		mReminder.setSubCategory(subCategory);
		
		Random ran = new Random();
		mReminder.setActivatedState(ran.nextBoolean());
		
		Log.d(TAG, "NCC - DB IT RETURNED: " + DBManager.getInstance(this).insert(mReminder));
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
