package com.flabs.reminder.reminder_object;

import android.graphics.Color;
import android.os.Bundle;

public class SubCategory extends BaseCategory implements ICategory {

	private String customLabel;
	private int customColor;
	
	public SubCategory() {
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
		return this.color;
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
		return this.customLabel;
	}

	@Override
	public int getCustomColor() {
		return this.customColor;
	}

	@Override
	public void toBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ICategory fromBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		return null;
	}

}
