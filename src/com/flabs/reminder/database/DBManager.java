package com.flabs.reminder.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.flabs.reminder.reminder_object.IReminderObject;
import com.flabs.reminder.util.EnvironmentVariables;

public class DBManager extends SQLiteOpenHelper {

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


		if(db.isReadOnly()) {
			final SQLiteDatabase wdb = this.getWritableDatabase();
			//Whoever may need a writeable database gets it here.
			wdb.close();
		} else {
			//Initialize with the read only database.
		}

	}
	
	public long insert(IReminderObject reminderObj) {
		return insert(reminderObj.getTitle(), reminderObj.getIconPath(), reminderObj);
	}
	
	private long insert(final String title, final String iconPath, final IReminderObject reminderObj) {
		ContentValues values = new ContentValues();
		values.put(EnvironmentVariables.DATABASE.Columns.TITLE_NAME.name(), title);
		values.put(EnvironmentVariables.DATABASE.Columns.ICON.name(), iconPath);
		values.put(EnvironmentVariables.DATABASE.Columns.DATA_BLOB.name(), reminderObj.toString());
		return getReadableDatabase().insert(EnvironmentVariables.DATABASE.MASTER_TABLE_NAME, null, values);
	}

	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
	}

	@Override
	protected void finalize() throws Throwable {
		close();
	}
}
