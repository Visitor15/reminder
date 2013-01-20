package com.flabs.reminder.reminder_object;

import com.flabs.reminder.util.EnvironmentVariables.ACTION;

public interface IReminderObject {

	public void setTitle(String title);
	
	public void setIconPath(String iconPath);
	
	public void setMessage(String message);
	
	public void setCategory(Category category);
	
	public void setSubCategory(SubCategory subCategory);
	
	public void setActivatedState(boolean isActivated);
	
	public void setOnRemindAction(ACTION action);
	
	public void setHasDisplayedIn24Hours(boolean hasDisplayed);
	
	public String getTitle();
	
	public String getIconPath();
	
	public String getMessage();
	
	public Category getCategory();
	
	public SubCategory getSubCategory();
	
	public boolean isActivated();
	
	public ACTION getOnRemindAction();
	
	public boolean hasDisplayedIn24Hours();
	
	public byte[] toBinary();
	
}
