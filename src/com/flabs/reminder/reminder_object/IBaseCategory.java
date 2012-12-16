package com.flabs.reminder.reminder_object;

import android.graphics.Color;

public interface IBaseCategory {

	void setLabel(String label);
	
	void setColor(Color color);
	
	void setColor(int color);
	
	Color getColor();
	
	int getColorAsInt();
	
}
