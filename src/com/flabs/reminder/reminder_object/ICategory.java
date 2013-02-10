package com.flabs.reminder.reminder_object;

import java.util.ArrayList;

import android.os.Bundle;

public interface ICategory {
	
	void addSubCategory(final SubCategory subCategory);

	void setCustomLabel(String label);
	
	void setCustomColor(int color);
	
	void setCusomColor(String color);
	
	SubCategory getSubCategory(final int pos);
	
	ArrayList<SubCategory> getAllSubCategories();
	
	String getCustomLabel();
	
	int getCustomColor();
	
	void toBundle(Bundle bundle);
	
	ICategory fromBundle(Bundle bundle);
	
	
}
