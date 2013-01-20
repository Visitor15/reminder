package com.flabs.reminder.ui;


import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flabs.mobile.reminder.R;


public class TestDialogFragment extends DialogFragment {

	public TestDialogFragment() {

	}

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.test_dialog_layout, container);


		return v;
	}
}
