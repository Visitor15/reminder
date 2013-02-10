package com.flabs.reminder.util;


public class EnvironmentVariables {

	
	
	public static enum ACTION {
		VIEW_REMINDER_DIALOG,
		VIEW_REMINDER_NOTIFICATION,
		VIEW_REMINDER_NOTIF_AND_DIALOG,
		SEND_SMS,
		SEND_EMAIL,
		CALL
	}
	
	public static enum REMINDER_TYPE {
		QUICK_REMINDER,
		REPEAT_REMINDER
	}
	
	public static enum DAYS {
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THURSDAY,
		FRIDAY,
		SATURDAY,
		SUNDAY,
		ALL
	}
	
	public static enum MONTHS {
		JANUARY,
		FEBURARY,
		MARCH,
		APRIL,
		MAY,
		JUNE,
		JULY,
		AUGUST,
		SEPTEMBER,
		OCTOBER,
		NOVEMBER,
		DECEMBER,
		ALL
	}
	
	public static class DATABASE {
		
		public static final String MASTER_DATABASE_NAME = "reminderdb";
		public static final int MASTER_DATABASE_VERSION = 1;
		
		public static final String MASTER_TABLE_NAME = "reminder_main_table";
		public static final String CATEGORIES_TABLE_NAME = "reminder_categories_table";
		
		public static enum Columns {
			_id,
			TITLE_NAME,
			ICON,
			DATA_BLOB
		}
		
		public static final String[] ALL_COLUMNS_FOR_REMINDER_OBJ = { Columns._id.name(), Columns.TITLE_NAME.name(), Columns.ICON.name(),
	        Columns.DATA_BLOB.name() };
		
		public static final String[] ALL_COLUMNS_FOR_CATEGORY_OBJ = { Columns._id.name(), Columns.TITLE_NAME.name(), Columns.DATA_BLOB.name() };
		
		public static final String CREATE_MASTER_DATABASE_TABLE = "create table " + MASTER_TABLE_NAME +
				" (" + Columns._id + "  INTEGER PRIMARY KEY," +
				Columns.TITLE_NAME + " TEXT," +
				Columns.ICON + "  TEXT," +
				Columns.DATA_BLOB + " BLOB" + ");";
		
		public static final String CREATE_CATEGORIES_DATABASE_TABLE = "create table " + CATEGORIES_TABLE_NAME +
				" (" + Columns._id + "  INTEGER PRIMARY KEY," +
				Columns.TITLE_NAME + " TEXT," +
				Columns.ICON + "  TEXT," +
				Columns.DATA_BLOB + " BLOB" + ");";

	}
}
