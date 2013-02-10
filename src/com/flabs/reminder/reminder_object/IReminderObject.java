package com.flabs.reminder.reminder_object;

import java.util.ArrayList;
import java.util.Calendar;

import com.flabs.reminder.util.EnvironmentVariables.ACTION;
import com.flabs.reminder.util.EnvironmentVariables.REMINDER_TYPE;

public interface IReminderObject {

	public void setTitle(String title);
	
	public void setIconPath(String iconPath);
	
	public void setMessage(String message);
	
	public void setCategory(Category category);
	
	public void setSubCategory(SubCategory subCategory);
	
	public void setActivatedState(boolean isActivated);
	
	public void setOnRemindAction(ACTION action);
	
	public void setReminderType(REMINDER_TYPE reminderType);
	
	public void setHasDisplayedIn24Hours(boolean hasDisplayed);
	
	public void setPriority(final float priority);
	
	public void setId(long Id);
	
	public void setReminderTime(final Calendar calObj);
	
	public Calendar getReminderTime();
	
	public String getTitle();
	
	public String getIconPath();
	
	public String getMessage();
	
	public Category getCategory();
	
	public SubCategory getSubCategory();
	
	public float getPriority();
	
	public long getId();
	
	public boolean isActivated();
	
	public ArrayList<ACTION> getOnRemindAction();
	
	public REMINDER_TYPE getReminderType();
	
	public boolean hasDisplayedIn24Hours();
	
	public byte[] toBinary();
	
}
