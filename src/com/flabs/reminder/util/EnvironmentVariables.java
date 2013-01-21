package com.flabs.reminder.util;


public class EnvironmentVariables {

	
	
	public static enum ACTION {
		VIEW_REMINDER
	}
	
	public static class DATABASE {
		
		public static final String MASTER_DATABASE_NAME = "reminderdb";
		public static final int MASTER_DATABASE_VERSION = 1;
		
		public static final String MASTER_TABLE_NAME = "reminder_main_table";
		
		public static enum Columns {
			_id,
			TITLE_NAME,
			ICON,
			DATA_BLOB,
			HAS_DISPLAYED_IN_24H
		}
		
		public static final String[] ALL_COLUMNS = { Columns._id.name(), Columns.TITLE_NAME.name(), Columns.ICON.name(),
	        Columns.DATA_BLOB.name(), Columns.HAS_DISPLAYED_IN_24H.name() };
		
		public static final String CREATE_MASTER_DATABASE_TABLE = "create table " + MASTER_TABLE_NAME +
				" (" + Columns._id + "  INTEGER PRIMARY KEY," +
				Columns.TITLE_NAME + " TEXT," +
				Columns.ICON + "  TEXT," +
				Columns.DATA_BLOB + " BLOB," +
				Columns.HAS_DISPLAYED_IN_24H + " INTEGER" + ");";

	}
}
