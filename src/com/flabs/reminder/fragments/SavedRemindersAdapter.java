package com.flabs.reminder.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.reminder_object.ReminderObject;

public class SavedRemindersAdapter extends ArrayAdapter<ReminderObject> {

	private Context mContext;
	private LayoutInflater inflater;
	private ArrayList<ReminderObject> dataList;

	public SavedRemindersAdapter(final Context c, int resource, ArrayList<ReminderObject> items) {
		super(c, resource, items);
		this.mContext = c;
		this.inflater = (LayoutInflater) mContext.getSystemService
				(Context.LAYOUT_INFLATER_SERVICE);
		dataList = items;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public ReminderObject getItem(int pos) {
		return dataList.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {

		if(convertView == null) {
			convertView = inflater.inflate(R.layout.saved_reminder_item_layout, null);
		}

		setupItemView(convertView, dataList.get(pos));

		return convertView;
	}

	private void setupItemView(final View v, final ReminderObject reminderObj) {
		Log.d("TAG", "NCC - Writing Label: " + reminderObj.getTitle());
		TextView title = (TextView) v.findViewById(R.id.tv_title);
		title.setText(reminderObj.getTitle());
	}

}
