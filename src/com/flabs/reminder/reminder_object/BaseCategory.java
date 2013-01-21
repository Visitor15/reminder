package com.flabs.reminder.reminder_object;

import java.io.Serializable;

import android.graphics.Color;

public abstract class BaseCategory implements IBaseCategory, Serializable {

	protected String label = "NULL";
	protected int color = -1;
	
	public BaseCategory() {
		
	}
}
