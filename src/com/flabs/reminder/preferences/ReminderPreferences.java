package com.flabs.reminder.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.flabs.reminder.util.EnvironmentVariables;
import com.flabs.reminder.util.EnvironmentVariables.DAYS;
import com.flabs.reminder.util.EnvironmentVariables.MONTHS;

public class ReminderPreferences {

	public static final String TAG = "ReminderPreferences";
	
	public static final String KEY_FIRST_BOOT = "first_boot_key";
	public static final String KEY_MINUTE_REPEAT = "minute_repeat_key";
	public static final String KEY_HOUR_REPEAT = "hour_repeat_key";
	public static final String KEY_DAY_REPEAT = "day_repeat_key";
	public static final String KEY_WEEK_REPEAT = "week_repeat_key";
	public static final String KEY_MONTH_REPEAT = "month_repeat_key";
	public static final String KEY_REMIND_IS_ACTIVE = "remind_is_active_key";
	public static final String KEY_IS_PRO_VERSION = "is_pro_version_key";
	public static final String KEY_HAS_REMINDERS_TO_SHOW = "has_reminders_to_show";
	
	public static final boolean DEFAULT_HAS_REMINDERS_TO_SHOW = false;
	public static final boolean DEFAULT_FIRST_BOOT = true;
	public static final boolean DEFAULT_IS_PRO_VERSION = false;
	public static final boolean DEFAULT_REMIND_IS_ACTIVE = true;
	public static final int DEFAULT_MINUTE_REPEAT = 0;
	public static final int DEFAULT_HOUR_REPEAT = 12;
	public static final DAYS DEFAULT_DAY_REPEAT = EnvironmentVariables.DAYS.ALL;
	public static final int DEFAULT_WEEK_REPEAT = -1;
	public static final MONTHS DEFAULT_MONTH_REPEAT = EnvironmentVariables.MONTHS.ALL;
	
	private static void setDefaultPreferences(Context c) {
		editPreference(c, KEY_FIRST_BOOT, DEFAULT_FIRST_BOOT);
		editPreference(c, KEY_REMIND_IS_ACTIVE, DEFAULT_REMIND_IS_ACTIVE);
		editPreference(c, KEY_MINUTE_REPEAT, DEFAULT_MINUTE_REPEAT);
		editPreference(c, KEY_HOUR_REPEAT, DEFAULT_HOUR_REPEAT);
		editPreference(c, KEY_DAY_REPEAT, DEFAULT_DAY_REPEAT);
		editPreference(c, KEY_WEEK_REPEAT, DEFAULT_WEEK_REPEAT);
		editPreference(c, KEY_MONTH_REPEAT, DEFAULT_MONTH_REPEAT);
		editPreference(c, KEY_HAS_REMINDERS_TO_SHOW, DEFAULT_HAS_REMINDERS_TO_SHOW);
	}
	
	public static void initDefaultPreferences(Context c) {
		setDefaultPreferences(c);
	}
	
	
	public static boolean editPreference(Context c, String key, Object obj) {
		SharedPreferences mPrefs = c.getSharedPreferences(TAG, 0);

		if(obj instanceof Integer) {
			mPrefs.edit().putInt(key, (Integer) obj).commit();
			return true;
		}
		else if(obj instanceof Long) {
			mPrefs.edit().putLong(key, (Long) obj).commit();
			return true;
		}
		else if(obj instanceof Boolean) {
			mPrefs.edit().putBoolean(key, (Boolean) obj).commit();
			return true;
		}
		else if(obj instanceof String) {
			mPrefs.edit().putString(key, (String) obj).commit();
			return true;
		}
		throw new IllegalArgumentException();	
	}
	
	public static Object getPreference(Context c, String key) {
			return c.getSharedPreferences(TAG, 0).getAll().get(key);
	}
}
