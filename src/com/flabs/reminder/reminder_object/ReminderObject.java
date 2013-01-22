package com.flabs.reminder.reminder_object;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.util.Log;

import com.flabs.reminder.util.EnvironmentVariables;
import com.flabs.reminder.util.EnvironmentVariables.ACTION;

public class ReminderObject implements IReminderObject {

	public static final String TAG = "ReminderObject";
	
	public static final String BUNDLE_KEY = "bundle_reminder_key";
	
	private String title = "NULL";
	private String message = "NULL";
	private String iconUriPath = "NULL";
	private static Category category;
	private SubCategory subCategory;
	private boolean isActivated = false;
	private boolean hasDisplayedIn24Hours = false;
	private ACTION onRemindAction;

	public ReminderObject() {
		category = new Category();
		subCategory = new SubCategory();
		onRemindAction = ACTION.VIEW_REMINDER;
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
	public void setHasDisplayedIn24Hours(boolean hasDisplayed) {
		this.hasDisplayedIn24Hours = hasDisplayed;
	}

	@Override
	public boolean isActivated() {
		return isActivated;
	}

	@Override
	public ACTION getOnRemindAction() {
		return onRemindAction;
	}

	@Override
	public boolean hasDisplayedIn24Hours() {
		return hasDisplayedIn24Hours;
	}

	@Override
	public byte[] toBinary() {
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			final ObjectOutputStream out = new ObjectOutputStream(os);

			Log.d("TAG", "NCC - Writing title: " + getTitle());

			out.writeUTF(getTitle());
			out.writeUTF(getMessage());
			out.writeUTF(getIconPath());
			out.writeBoolean(isActivated());
			out.writeUTF(getOnRemindAction().name());

			final byte[] categoryBytes = getCategory().toBinary();
			out.writeInt(categoryBytes.length);
			out.write(categoryBytes);

			final byte[] subCategoryBytes = getSubCategory().toBinary();
			out.writeInt(subCategoryBytes.length);
			out.write(subCategoryBytes);
			
			out.writeBoolean(hasDisplayedIn24Hours());

			out.flush();
		} catch (final IOException e) {
		}

		final byte[] res = os.toByteArray();

		return res;
	}

	public static IReminderObject fromBinary(byte[] byteArray) throws StreamCorruptedException, IOException, ClassNotFoundException {
		final ByteArrayInputStream is = new ByteArrayInputStream(byteArray);
		final ObjectInputStream in = new ObjectInputStream(is);
		final ReminderObject reminderObj = new ReminderObject();

		reminderObj.setTitle(in.readUTF());
		reminderObj.setMessage(in.readUTF());
		reminderObj.setIconPath(in.readUTF());
		reminderObj.setActivatedState(in.readBoolean());
		reminderObj.setOnRemindAction(EnvironmentVariables.ACTION.valueOf(in.readUTF()));
		
		final int categoryBytesLength = in.readInt();
		
		final byte[] categoryBytes = new byte[categoryBytesLength];
		in.read(categoryBytes);
		
		reminderObj.setCategory(Category.fromBinary(categoryBytes));
		
		final int subCategoryBytesLength = in.readInt();
		final byte[] subCategoryBytes = new byte[subCategoryBytesLength];
		in.read(subCategoryBytes);
		reminderObj.setSubCategory(SubCategory.fromBinary(subCategoryBytes));

		reminderObj.setHasDisplayedIn24Hours(in.readBoolean());

		return reminderObj;
	}
}
