package com.flabs.reminder.reminder_object;

import com.flabs.reminder.util.EnvironmentVariables.ACTION;

public class ReminderObject implements IReminderObject {

	private String title;
	private String message;
	private String iconUriPath;
	private Category category;
	private SubCategory subCategory;
	private boolean isActivated;
	private ACTION onRemindAction;
	
	public ReminderObject() {
		
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public void setIconPath(String iconPath) {
		this.iconUriPath = iconPath;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	@Override
	public void setActivatedState(boolean isActivated) {
		this.isActivated = isActivated;
	}

	@Override
	public void setOnRemindAction(ACTION action) {
		this.onRemindAction = action;
	}

	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public String getIconPath() {
		return iconUriPath;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public Category getCategory() {
		return category;
	}

	@Override
	public SubCategory getSubCategory() {
		return subCategory;
	}

	@Override
	public boolean isActivated() {
		return isActivated;
	}

	@Override
	public ACTION getOnRemindAction() {
		return onRemindAction;
	}
}
