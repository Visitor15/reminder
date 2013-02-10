package com.flabs.reminder.reminder_object;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Calendar;

import android.util.Log;

import com.flabs.reminder.util.EnvironmentVariables;
import com.flabs.reminder.util.EnvironmentVariables.ACTION;
import com.flabs.reminder.util.EnvironmentVariables.REMINDER_TYPE;

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
	private ArrayList<ACTION> onRemindActionList;
	private REMINDER_TYPE reminderType;
	private float reminderPriority;
	private int month;
	private int day;
	private int year;
	private int hour;
	private int minute;
	private long id;

	public ReminderObject() {
		category = new Category();
		subCategory = new SubCategory();
		onRemindActionList = new ArrayList<ACTION>();
		onRemindActionList.add(ACTION.VIBRATE);
		onRemindActionList.add(ACTION.VIEW_REMINDER_DIALOG);
		onRemindActionList.add(ACTION.VIEW_REMINDER_NOTIFICATION);
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
		this.onRemindActionList.add(action);
	}

	@Override
	public void setReminderType(REMINDER_TYPE reminderType) {
		this.reminderType = reminderType;
	}

	@Override
	public void setPriority(final float priority) {
		this.reminderPriority = priority;
	}

	@Override
	public void setId(long Id) {
		this.id = Id;
	}

	@Override
	public void setReminderTime(Calendar calObj) {
		this.month = calObj.get(Calendar.MONTH);
		this.day = calObj.get(Calendar.DAY_OF_MONTH);
		this.year = calObj.get(Calendar.YEAR);
		this.hour = calObj.get(Calendar.HOUR);
		this.minute = calObj.get(Calendar.MINUTE);
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
	public ArrayList<ACTION> getOnRemindAction() {
		return onRemindActionList;
	}

	@Override
	public float getPriority() {
		return reminderPriority;
	}

	@Override
	public REMINDER_TYPE getReminderType() {
		return reminderType;
	}

	@Override
	public boolean hasDisplayedIn24Hours() {
		return hasDisplayedIn24Hours;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public Calendar getReminderTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, hour, minute);

		return calendar;
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

			out.writeInt(onRemindActionList.size());

			for(ACTION action : onRemindActionList) {
				out.writeUTF(action.name());
			}


			final byte[] categoryBytes = getCategory().toBinary();
			out.writeInt(categoryBytes.length);
			out.write(categoryBytes);

			final byte[] subCategoryBytes = getSubCategory().toBinary();
			out.writeInt(subCategoryBytes.length);
			out.write(subCategoryBytes);

			out.writeBoolean(hasDisplayedIn24Hours());
			out.writeFloat(getPriority());
			out.writeLong(getId());

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

		int numOfActions = in.readInt();

		for(int i = 0; i < numOfActions; i++) {
			reminderObj.setOnRemindAction(EnvironmentVariables.ACTION.valueOf(in.readUTF()));
		}

		final int categoryBytesLength = in.readInt();

		final byte[] categoryBytes = new byte[categoryBytesLength];
		in.read(categoryBytes);

		reminderObj.setCategory(Category.fromBinary(categoryBytes));

		final int subCategoryBytesLength = in.readInt();
		final byte[] subCategoryBytes = new byte[subCategoryBytesLength];
		in.read(subCategoryBytes);
		reminderObj.setSubCategory(SubCategory.fromBinary(subCategoryBytes));

		reminderObj.setHasDisplayedIn24Hours(in.readBoolean());
		reminderObj.setPriority(in.readFloat());
		reminderObj.setId(in.readLong());

		return reminderObj;
	}
}
