package com.flabs.reminder.fragments;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.database.DBManager;
import com.flabs.reminder.reminder_object.ReminderObject;

public class SavedRemindersFragment extends BaseReminderFragment {
	
	private ListView listView;
	
	private SavedRemindersAdapter mAdapter;
	
	private ArrayList<ReminderObject> allReminders;
	
	public SavedRemindersFragment() {
	}
	
	private void init() {
		try {
			allReminders = DBManager.getInstance(getActivity()).getReminderObjectsByTitle(null);
			Log.d("TAG", "NCC - GOT REMINDER LIST SIZE: " + allReminders.size());
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
	public void onFragmentCreate(Bundle b) {
		setLayoutId(R.layout.saved_reminders_fragment_layout);
		init();
	}

	@Override
	public void onFragmentCreateView(View v) {
		listView = (ListView) v.findViewById(R.id.list_view);
		
		mAdapter = new SavedRemindersAdapter(getActivity(), R.layout.saved_reminder_item_layout, allReminders);
		listView.setAdapter(mAdapter);
	}

	@Override
	public void onFragmentCreated(Bundle b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFragmentPause() {
		// TODO Auto-generated method stub
		
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
