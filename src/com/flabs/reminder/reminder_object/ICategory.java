package com.flabs.reminder.reminder_object;

import android.os.Bundle;

public interface ICategory {

	void setCustomLabel(String label);
	
	void setCustomColor(int color);
	
	void setCusomColor(String color);
	
	String getCustomLabel();
	
	int getCustomColor();
	
	void toBundle(Bundle bundle);
	
	ICategory fromBundle(Bundle bundle);
	
	
}
