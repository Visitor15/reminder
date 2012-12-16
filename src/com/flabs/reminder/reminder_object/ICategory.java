package com.flabs.reminder.reminder_object;

import android.graphics.Color;
import android.os.Bundle;

public interface ICategory {

	void setCustomLabel(String label);
	
	void setCustomColor(Color color);
	
	void setCusomColor(int color);
	
	String getCustomLabel();
	
	Color getCustomColor();
	
	int getCustomColorAsInt();
	
	void toBundle(Bundle bundle);
	
	ICategory fromBundle(Bundle bundle);
	
}
