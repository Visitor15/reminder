package com.flabs.reminder.reminder_object;

import android.graphics.Color;
import android.os.Bundle;

public class Category extends BaseCategory implements ICategory {

	private String customLabel;
	private int customColor;
	
	public Category() {
		super();
	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public void setColor(int color) {
		this.color = color;
	}
	
	@Override
	public void setColor(String color) {
		this.color = Color.parseColor(color);
	}

	@Override
	public int getColor() {
		return color;
	}

	@Override
	public int getColorAsInt() {
		return color;
	}

	@Override
	public void setCustomLabel(String label) {
		this.customLabel = label;
	}

	@Override
	public void setCustomColor(int color) {
		this.customColor = color;
	}

	@Override
	public void setCusomColor(String color) {
		this.customColor = Color.parseColor(color);
	}

	@Override
	public String getCustomLabel() {
		return customLabel;
	}

	@Override
	public int getCustomColor() {
		return customColor;
	}

	@Override
	public void toBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Category fromBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		return null;
	}
}
