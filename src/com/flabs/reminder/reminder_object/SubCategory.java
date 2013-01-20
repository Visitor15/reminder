package com.flabs.reminder.reminder_object;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

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
	public String getLabel() {
		return label;
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

	public byte[] toBinary() {
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			final ObjectOutputStream out = new ObjectOutputStream(os);
			out.writeChars(getLabel());
			out.writeChars(getCustomLabel());
			out.writeInt(getColor());
			out.writeInt(getCustomColor());
			out.flush();
		} catch (final IOException e) {
		}

		final byte[] res = os.toByteArray();

		return res;
	}
	
	public static SubCategory fromBinary(final byte[] byteArray) throws StreamCorruptedException, IOException {
		final ByteArrayInputStream is = new ByteArrayInputStream(byteArray);
		final ObjectInputStream in = new ObjectInputStream(is);
		
		SubCategory subCategory = new SubCategory();
		subCategory.setLabel(in.readUTF());
		subCategory.setCustomLabel(in.readUTF());
		subCategory.setColor(in.readInt());
		subCategory.setCustomColor(in.readInt());
		
		return subCategory;
	}
}
