package com.flabs.reminder.reminder_object;

import android.graphics.Color;

public interface IBaseCategory {

	void setLabel(String label);
	
	void setColor(int color);
	
	void setColor(String color);
	
	int getColor();
	
	String getLabel();
	
}
