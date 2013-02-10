package com.flabs.reminder.database;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.flabs.reminder.reminder_object.Category;
import com.flabs.reminder.reminder_object.IReminderObject;
import com.flabs.reminder.reminder_object.ReminderObject;
import com.flabs.reminder.reminder_object.SubCategory;
import com.flabs.reminder.util.EnvironmentVariables;

public class DBManager extends SQLiteOpenHelper {

	public static final String TAG = "DBManager";

	private static DBManager instance;
	private static Context mContext;

	public static final DBManager getInstance(Context c) {
		if(instance == null) {
			return new DBManager(c);
		}
		else {
			return instance;
		}
	}

	private DBManager(final Context context) {
		super(context, EnvironmentVariables.DATABASE.MASTER_DATABASE_NAME, null, EnvironmentVariables.DATABASE.MASTER_DATABASE_VERSION);
		mContext = context;
		instance = this;
	}

	@Override
	public void onCreate(final SQLiteDatabase db) {
		db.execSQL(EnvironmentVariables.DATABASE.CREATE_MASTER_DATABASE_TABLE);
		db.execSQL(EnvironmentVariables.DATABASE.CREATE_CATEGORIES_DATABASE_TABLE);

		if(db.isReadOnly()) {
			final SQLiteDatabase wdb = this.getWritableDatabase();
			//Whoever may need a writeable database gets it here.
			wdb.close();
		} else {
			//Initialize with the read only database.
		}
	}

	public long insertCategoryObject(Category category) {
		return insertCategoryObject(category.getLabel(), category);
	}

	private long insertCategoryObject(final String title, final Category category) {
		ContentValues values = new ContentValues();
		values.put(EnvironmentVariables.DATABASE.Columns.TITLE_NAME.name(), title);
		values.put(EnvironmentVariables.DATABASE.Columns.DATA_BLOB.name(), category.toBinary());

		Log.d("TAG", "NCC - INSERTING INTO DB: " + values.toString());

		return getReadableDatabase().insert(EnvironmentVariables.DATABASE.MASTER_TABLE_NAME, null, values);
	}

	public long insertReminderObject(IReminderObject reminderObj) {
		return insertReminderObject(reminderObj.getTitle(), reminderObj.getIconPath(), reminderObj);
	}

	private long insertReminderObject(final String title, final String iconPath, final IReminderObject reminderObj) {
		ContentValues values = new ContentValues();
		values.put(EnvironmentVariables.DATABASE.Columns.TITLE_NAME.name(), title);
		values.put(EnvironmentVariables.DATABASE.Columns.ICON.name(), iconPath);
		values.put(EnvironmentVariables.DATABASE.Columns.DATA_BLOB.name(), reminderObj.toBinary());

		Log.d("TAG", "NCC - INSERTING INTO DB: " + values.toString());

		return getReadableDatabase().insert(EnvironmentVariables.DATABASE.MASTER_TABLE_NAME, null, values);
	}

	public ArrayList<Category> getCategoryByTitle(final String title) throws StreamCorruptedException, IOException, ClassNotFoundException {
		final Cursor all = query(EnvironmentVariables.DATABASE.CATEGORIES_TABLE_NAME, null, null, null);
		ArrayList<Category> categoryList = new ArrayList<Category>();
		Category category;

		Log.d("TAG", "NCC : Cursor count: " + all.getCount());

		while (all.moveToNext()) {

			category = (Category) Category.fromBinary(all.getBlob(all.getColumnIndex(
					EnvironmentVariables.DATABASE.Columns.DATA_BLOB.name())));

			Log.d("TAG", "NCC - REMINDER FROM DB: " + category.getLabel());

			if((title == null) || title.trim().length() == 0) {
				categoryList.add(category);
			}
			else if(category.getLabel().equalsIgnoreCase(title)) {
				categoryList.add(category);
			}
		}

		all.close();

		return categoryList;
	}

	public ArrayList<ReminderObject> getReminderObjectsByTitle(final String title) throws StreamCorruptedException, IOException, ClassNotFoundException {
		final Cursor all = query(EnvironmentVariables.DATABASE.MASTER_DATABASE_NAME, null, null, null);
		ArrayList<ReminderObject> reminderList = new ArrayList<ReminderObject>();
		ReminderObject reminder;

		if(all != null) {
			Log.d("TAG", "NCC : Cursor count: " + all.getCount());

			while (all.moveToNext()) {

				reminder = (ReminderObject) ReminderObject.fromBinary(all.getBlob(all.getColumnIndex(
						EnvironmentVariables.DATABASE.Columns.DATA_BLOB.name())));

				Log.d("TAG", "NCC - REMINDER FROM DB: " + reminder.getTitle());

				if((title == null) || title.trim().length() == 0) {
					reminderList.add(reminder);
				}
				else if(reminder.getTitle().equalsIgnoreCase(title)) {
					reminderList.add(reminder);
				}
			}

			all.close();
		}

		return reminderList;
	}

	public Cursor query(final String tableName, final String selection, final String[] selectionArgs, final String orderBy) {
		Cursor c = null;
		try {
			SQLiteDatabase db = getReadableDatabase();
			Log.d("TAG", "NCC : Query");

			if(tableName.equalsIgnoreCase(EnvironmentVariables.DATABASE.MASTER_TABLE_NAME)) {

				c = db.query(EnvironmentVariables.DATABASE.MASTER_TABLE_NAME, EnvironmentVariables.DATABASE.ALL_COLUMNS_FOR_REMINDER_OBJ,
						selection, selectionArgs, null, null, orderBy);
				return c;
			}
			else if(tableName.equalsIgnoreCase(EnvironmentVariables.DATABASE.CATEGORIES_TABLE_NAME)) {
				c = db.query(EnvironmentVariables.DATABASE.CATEGORIES_TABLE_NAME, EnvironmentVariables.DATABASE.ALL_COLUMNS_FOR_CATEGORY_OBJ,
						selection, selectionArgs, null, null, orderBy);
				return c;
			}
		} catch (final SQLiteException e) {
			Log.e(TAG, "There was an error querying database for table: " + tableName);
		}

		return c;
	}

	private void initDefaultCategories() {
		Category c1 = new Category();
		c1.setLabel("Misc");
		SubCategory s1 = new SubCategory();
		s1.setLabel("General");
		c1.addSubCategory(s1);

		Category c2 = new Category();
		c2.setLabel("School");
		SubCategory s2 = new SubCategory();
		s2.setLabel("Mathematics");
		c2.addSubCategory(s2);
		SubCategory s3 = new SubCategory();
		s3.setLabel("History");
		c2.addSubCategory(s3);
		SubCategory s4 = new SubCategory();
		s4.setLabel("Language Arts");
		c2.addSubCategory(s4);
		SubCategory s5 = new SubCategory();
		s5.setLabel("Sciences");
		c2.addSubCategory(s5);

		Category c3 = new Category();
		c3.setLabel("Parking");
		SubCategory s6 = new SubCategory();
		s6.setLabel("1 Hour Parking");
		c3.addSubCategory(s6);
		SubCategory s7 = new SubCategory();
		s7.setLabel("2 Hour Parking");
		c3.addSubCategory(s7);

		insertCategoryObject(c1);
		insertCategoryObject(c2);
		insertCategoryObject(c3);
	}

	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
	}

	@Override
	protected void finalize() throws Throwable {
		close();
	}

	public void checkAndInitDefaults() throws StreamCorruptedException, IOException, ClassNotFoundException {
		ArrayList<Category> catList = getCategoryByTitle(null);
		
		if(catList == null || catList.size() == 0) {
			initDefaultCategories();
		}
	}
}
