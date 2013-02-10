package com.flabs.reminder.reminder_object;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;

public class Category extends BaseCategory implements ICategory {

	private String customLabel = "NULL";
	private int customColor = -1;
	
	private ArrayList<SubCategory> subCategories;

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
	public void addSubCategory(SubCategory subCategory) {
		if(subCategories == null) {
			subCategories = new ArrayList<SubCategory>();
		}
		subCategories.add(subCategory);
	}

	@Override
	public int getColor() {
		return color;
	}

	@Override
	public String getLabel() {
		return label;
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
	public SubCategory getSubCategory(int pos) {
		if(subCategories == null) {
			subCategories = new ArrayList<SubCategory>();
		}
		
		return subCategories.get(pos);
	}

	@Override
	public ArrayList<SubCategory> getAllSubCategories() {
		if(subCategories == null) {
			subCategories = new ArrayList<SubCategory>();
		}
		
		return subCategories;
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

	public byte[] toBinary() {
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			final ObjectOutputStream out = new ObjectOutputStream(os);
			out.writeUTF(getLabel());
			out.writeUTF(getCustomLabel());
			out.writeInt(getColor());
			out.writeInt(getCustomColor());
			out.writeInt(subCategories.size());
			
			for(SubCategory subCat : subCategories) {
				final byte[] subCategoryBytes = subCat.toBinary();
				out.writeInt(subCategoryBytes.length);
				out.write(subCategoryBytes);
			}
			
			out.flush();
		} catch (final IOException e) {
		}

		final byte[] res = os.toByteArray();

		return res;
	}
	
	public static Category fromBinary(final byte[] byteArray) throws StreamCorruptedException, IOException {
		final ByteArrayInputStream is = new ByteArrayInputStream(byteArray);
		final ObjectInputStream in = new ObjectInputStream(is);
		
		Category category = new Category();
		category.setLabel(in.readUTF());
		category.setCustomLabel(in.readUTF());
		category.setColor(in.readInt());
		category.setCustomColor(in.readInt());
		
		int numOfSubCategories = in.readInt();
		
		for(int i = 0; i < numOfSubCategories; i++) {
			final int subCategoryBytesLength = in.readInt();
			
			final byte[] subCategoryBytes = new byte[subCategoryBytesLength];
			in.read(subCategoryBytes);
			
			category.addSubCategory(SubCategory.fromBinary(subCategoryBytes));
		}
		
		return category;
	}
}
