package com.flabs.reminder.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.database.DBManager;
import com.flabs.reminder.fragments.ReminderDialogCallback;
import com.flabs.reminder.reminder_object.Category;

public class AddCategoryDialog extends DialogFragment {

	private View rootView;

	private Button btnSet;

	private Button btnCancel;
	
	private EditText userInput;

	private ReminderDialogCallback mCallback;

	public AddCategoryDialog() {

	}

	public AddCategoryDialog(ReminderDialogCallback callback) {
		this.mCallback = callback;
	}

	private void setBtnCancelClickListener(final Button btn) {
		btn.setText("CANCEL");
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}

		});
	}
	
	private void setAddCategoryClickListener(final Button btn) {
		btn.setText("SAVE");
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(userInput.getText() != null) {
					if(userInput.getText().toString().trim().length() > 0) {
						createAndSaveCategory(userInput.getText().toString());
						mCallback.onDialogPositiveBtnClicked();
						dismiss();
					}
				}
			}
			
		});
	}
	
	private void createAndSaveCategory(final String label) {
		Category newCategory = new Category();
		newCategory.addDefaultSubCategory(newCategory.getAllSubCategories());
		newCategory.setLabel(label);
		
		DBManager.getInstance(getActivity()).insertCategoryObject(newCategory);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setStyle(STYLE_NO_FRAME, 0);
		setStyle(STYLE_NO_TITLE, 0);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.dialog_input_layout, container, false);

		btnSet = (Button) rootView.findViewById(R.id.btn_two);
		btnCancel = (Button) rootView.findViewById(R.id.btn_one);
		userInput = (EditText) rootView.findViewById(R.id.et_input);
		
		setAddCategoryClickListener(btnSet);
		setBtnCancelClickListener(btnCancel);

		return rootView;
	}
}
