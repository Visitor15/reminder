package com.flabs.reminder.fragments;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.flabs.mobile.reminder.R;
import com.flabs.reminder.activities.NewReminderActivity;
import com.flabs.reminder.activities.ViewPagerAdapter;
import com.flabs.reminder.database.DBManager;
import com.flabs.reminder.dialogs.AddCategoryDialog;
import com.flabs.reminder.dialogs.AddSubCategoryDialog;
import com.flabs.reminder.reminder_object.Category;
import com.flabs.reminder.reminder_object.ReminderObject;
import com.flabs.reminder.reminder_object.SubCategory;
import com.flabs.reminder.util.EnvironmentVariables.REMINDER_TYPE;

public class ReminderCategoryChooserFragment extends BaseReminderFragment {
	
	private Button btnNext;
	
	private ImageView btnAddCategory;
	
	private ImageView btnAddSubCategory;
	
	private Spinner categorySpinner;
	
	private Spinner subCategorySpinner;
	
	private ArrayAdapter<SubCategory> spinnerSubCategoryAdapter;
	
	private ArrayAdapter<Category> spinnerCategoryAdapter ;
	
	public ReminderCategoryChooserFragment() {
		init();
	}
	
//	public ReminderCategoryChooserFragment(final ReminderObject reminderObj) {
//		this.setReminderObject(reminderObj);
//		init();
//	}
	
	private void init() {
//		this.setReminderObject(((ViewPagerAdapter) ((NewReminderActivity) getActivity()).getAdapter()).getReminderObject());
//		Log.d("TAG", "REMINDER OBJECT: " + getReminderObject().toString());
		
		setLayoutId(R.layout.new_reminder_category_chooser_layout);
		setBackground(R.drawable.green_gradient_background);
	}
	
	private void setNextButtonListener(final Button btn) {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				handleNextButtonClicked();
			}
			
		});
	}
	
	private void handleNextButtonClicked() {
		saveCategoriesToReminder(getReminderObject());
		ViewPagerAdapter adapter = (ViewPagerAdapter) ((NewReminderActivity) getActivity()).getAdapter();
		ViewPager pager = ((NewReminderActivity) getActivity()).getViewPager();
		
		adapter.setReminderObject(getReminderObject());
		
		if((adapter.getDataList().size() - 1) == pager.getCurrentItem()) {
		
		if(getReminderObject().getReminderType().name().equalsIgnoreCase(REMINDER_TYPE.QUICK_REMINDER.name())) {
			// We're going to view the frequency fragment
			adapter.getDataList().add(new ReminderFrequencyFragment());
		}
		else if(getReminderObject().getReminderType().name().equalsIgnoreCase(REMINDER_TYPE.REPEAT_REMINDER.name())) {
			// We're going to view the set message fragment
			adapter.getDataList().add(new ReminderFrequencyFragment());
		}
		
		switchToNewFragment(pager, (adapter.getDataList().size() - 1));
		} else {
			pager.setCurrentItem((pager.getCurrentItem() + 1), true);
		}
	}
	
	private void setAddCategoryButton(final View v) {
		v.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showAddCategoryDialog();
			}
			
		});
	}
	
	private void setAddSubCategoryButton(final View v) {
		v.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showAddSubCategoryDialog();
			}
			
		});
	}
	
	private void showAddSubCategoryDialog() {
		AddSubCategoryDialog dialog = new AddSubCategoryDialog(this, (Category) categorySpinner.getSelectedItem());
		dialog.show(getActivity().getSupportFragmentManager(), "DIALOG");
	}
	
	private void showAddCategoryDialog() {
		AddCategoryDialog dialog = new AddCategoryDialog(this);
		dialog.show(getActivity().getSupportFragmentManager(), "DIALOG");
	}
	
	private void switchToNewFragment(final ViewPager pager, final int pos) {
		pager.setCurrentItem(pos, true);
	}

	private void initSpinners() throws StreamCorruptedException, IOException, ClassNotFoundException {
		ArrayList<Category> categoryList = DBManager.getInstance(getActivity()).getCategoryByTitle(null);
		ArrayList<SubCategory> subCategoryList = categoryList.get(1).getAllSubCategories();
		
		String[] categoryTitleList = new String[categoryList.size()];
		for(Category cat : categoryList) {
			categoryTitleList[categoryList.indexOf(cat)] = cat.getLabel();
		}
		
		spinnerCategoryAdapter = new ArrayAdapter<Category>(getActivity(), android.R.layout.simple_spinner_dropdown_item, categoryList);
		categorySpinner.setAdapter(spinnerCategoryAdapter);
		
		spinnerSubCategoryAdapter = new ArrayAdapter<SubCategory>(getActivity(), android.R.layout.simple_spinner_dropdown_item, subCategoryList);
		subCategorySpinner.setAdapter(spinnerSubCategoryAdapter);
		
		categorySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				spinnerSubCategoryAdapter = new ArrayAdapter<SubCategory>(getActivity(), android.R.layout.simple_spinner_dropdown_item, ((Category) categorySpinner.getItemAtPosition(position)).getAllSubCategories());
				subCategorySpinner.setAdapter(spinnerSubCategoryAdapter);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		subCategorySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private void saveCategoriesToReminder(ReminderObject reminderObject) {
		reminderObject.setCategory((Category) categorySpinner.getSelectedItem());
		reminderObject.setSubCategory((SubCategory) subCategorySpinner.getSelectedItem());
	}
	
	private void refreshCategorySpinner() throws StreamCorruptedException, IOException, ClassNotFoundException {
		ArrayList<Category> categoryList = DBManager.getInstance(getActivity()).getCategoryByTitle(null);
		spinnerCategoryAdapter = new ArrayAdapter<Category>(getActivity(), android.R.layout.simple_spinner_dropdown_item, categoryList);
		categorySpinner.setAdapter(spinnerCategoryAdapter);
	}
	
	private void refreshSubCategorySpinner() {
		ArrayList<SubCategory> subCategoryList = ((Category) categorySpinner.getSelectedItem()).getAllSubCategories();
		spinnerSubCategoryAdapter = new ArrayAdapter<SubCategory>(getActivity(), android.R.layout.simple_spinner_dropdown_item, subCategoryList);
		subCategorySpinner.setAdapter(spinnerSubCategoryAdapter);
	}

	@Override
	public void onFragmentCreate(Bundle b) {
	}

	@Override
	public void onFragmentCreateView(View v) {
		btnNext = (Button) v.findViewById(R.id.btn_next);
		categorySpinner = (Spinner) v.findViewById(R.id.spinner_category);
		subCategorySpinner = (Spinner) v.findViewById(R.id.spinner_subcategory);
		btnAddCategory = (ImageView) v.findViewById(R.id.btn_add_category);
		btnAddSubCategory = (ImageView) v.findViewById(R.id.btn_add_subcategory);
		
		try {
			initSpinners();
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
		
		setNextButtonListener(btnNext);
		setAddCategoryButton(btnAddCategory);
		setAddSubCategoryButton(btnAddSubCategory);
	}

	@Override
	public void onFragmentCreated(Bundle b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFragmentPause() {
		saveCategoriesToReminder(getReminderObject());
		
		ViewPagerAdapter adapter = (ViewPagerAdapter) ((NewReminderActivity) getActivity()).getAdapter();
		adapter.setReminderObject(getReminderObject());
	}

	@Override
	public void onFragmentResume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFragmentStop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDialogPositiveBtnClicked() {
		try {
			refreshCategorySpinner();
			refreshSubCategorySpinner();
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
	public void onDialogNegativeBtnClicked() {
		
	}



}
