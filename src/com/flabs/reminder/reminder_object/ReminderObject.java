package com.flabs.reminder.reminder_object;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import com.flabs.reminder.util.EnvironmentVariables;
import com.flabs.reminder.util.EnvironmentVariables.ACTION;

public class ReminderObject implements IReminderObject {

	private String title;
	private String message;
	private String iconUriPath;
	private static Category category;
	private SubCategory subCategory;
	private boolean isActivated;
	private boolean hasDisplayedIn24Hours;
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

			out.writeUTF(getTitle());
			out.writeUTF(getMessage());
			out.writeUTF(getIconPath());
			out.writeBoolean(isActivated());
			out.writeUTF(getOnRemindAction().name());
			out.write(getCategory().toBinary());
			out.write(getSubCategory().toBinary());
			out.writeBoolean(hasDisplayedIn24Hours());

			out.flush();
		} catch (final IOException e) {
		}

		final byte[] res = os.toByteArray();

		return res;
	}

	public static IReminderObject fromBinary(byte[] byteArray) throws StreamCorruptedException, IOException {
		final ByteArrayInputStream is = new ByteArrayInputStream(byteArray);
		final ObjectInputStream in = new ObjectInputStream(is);
		final ReminderObject reminderObj = new ReminderObject();
		
		reminderObj.setTitle(in.readUTF());
		reminderObj.setMessage(in.readUTF());
		reminderObj.setIconPath(in.readUTF());
		reminderObj.setActivatedState(in.readBoolean());
		reminderObj.setOnRemindAction(EnvironmentVariables.ACTION.valueOf(in.readUTF()));
		
		final int categoryByteLength = in.readInt();
	    final byte[] categoryBytes = new byte[categoryByteLength];
		reminderObj.setCategory(Category.fromBinary(categoryBytes));
		
		final int subCategoryByteLength = in.readInt();
	    final byte[] subCategoryBytes = new byte[subCategoryByteLength];
		reminderObj.setSubCategory(SubCategory.fromBinary(subCategoryBytes));
		
		reminderObj.setHasDisplayedIn24Hours(in.readBoolean());
		
		return reminderObj;
	}
}
