package com.flabs.reminder.reminder_object;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Calendar;

import com.flabs.reminder.util.EnvironmentVariables.DAYS;

public class ReminderRepeatType {

	public static enum REPEAT_TYPE {
		MINUTE,
		HOUR,
		DAY,
		WEEK,
		MONTH,
		YEAR,
		ON_DATE
	}

	private REPEAT_TYPE type;
	private ArrayList<DAYS> daysList;
	private int minute;
	private int hour;
	private int day;
	private int week;
	private int month;
	private int year;
	private int numOfTimesToRepeat;

	public ReminderRepeatType() {
		type = REPEAT_TYPE.DAY;
		daysList = new ArrayList<DAYS>();
		minute = -1;
		hour = -1;
		day = -1;
		week = -1;
		month = -1;
		year = -1;
		numOfTimesToRepeat = -1;
	}
	
	public void addDay(final DAYS day) {
		daysList.add(day);
	}

	public void setType(final REPEAT_TYPE type) {
		this.type = type;
	}

	public void setMinuteTime(final int minute) {
		this.minute = minute;
	}

	public void setHourTime(final int hour) {
		this.hour = hour;
	}

	public void setDayTime(final int day) {
		this.day = day;
	}

	public void setWeekTime(final int week) {
		this.week = week;
	}

	public void setMonthTime(final int month) {
		this.month = month;
	}

	public void setYearTime(final int year) {
		this.year = year;
	}
	
	public void setNumOfTimesToRepeat(final int numOfTimes) {
		this.numOfTimesToRepeat = numOfTimes;
	}
	
	public void setDate(final Calendar calObj) {
		this.month = calObj.get(Calendar.MONTH);
		this.day = calObj.get(Calendar.DAY_OF_MONTH);
		this.year = calObj.get(Calendar.YEAR);
	}
	
	public Calendar getDate() {
		Calendar cal = Calendar.getInstance();
		
		cal.set(getYearTime(), getMonthTime(), getDayTime());
		
		return cal;
	}
	
	public int getNumOfTimesToRepeat() {
		return numOfTimesToRepeat;
	}

	public REPEAT_TYPE getType() {
		return type;
	}
	
	public ArrayList<DAYS> getDaysList() {
		return daysList;
	}

	public int getMinuteTime() {
		return minute;
	}

	public int getHourTime() {
		return hour;
	}

	public int getDayTime() {
		return day;
	}

	public int getWeekTime() {
		return week;
	}

	public int getMonthTime() {
		return month;
	}

	public int getYearTime() {
		return year;
	}

	public byte[] toBinary() {
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			final ObjectOutputStream out = new ObjectOutputStream(os);

			out.writeUTF(getType().name());
			out.writeInt(getMinuteTime());
			out.writeInt(getHourTime());
			
			out.writeInt(getDaysList().size());
			
			for(DAYS day : getDaysList()) {
				out.writeUTF(day.name());
			}
			
			out.writeInt(getDayTime());
			out.writeInt(getWeekTime());
			out.writeInt(getMonthTime());
			out.writeInt(getYearTime());
			out.writeInt(getNumOfTimesToRepeat());

			out.flush();
		} catch (final IOException e) {
		}

		final byte[] res = os.toByteArray();

		return res;
	}

	public static ReminderRepeatType fromBinary(final byte[] byteArray) {
		ReminderRepeatType repeatType = new ReminderRepeatType();
		try {
			final ByteArrayInputStream is = new ByteArrayInputStream(byteArray);
			ObjectInputStream in = new ObjectInputStream(is);

			repeatType.setType(REPEAT_TYPE.valueOf(in.readUTF()));
			repeatType.setMinuteTime(in.readInt());
			repeatType.setHourTime(in.readInt());
			
			int numOfDays = in.readInt();
			for(int i = 0; i < numOfDays; i++) {
				repeatType.addDay(DAYS.valueOf(in.readUTF()));
			}
			
			repeatType.setDayTime(in.readInt());
			repeatType.setWeekTime(in.readInt());
			repeatType.setMonthTime(in.readInt());
			repeatType.setYearTime(in.readInt());
			repeatType.setNumOfTimesToRepeat(in.readInt());

		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return repeatType;
	}
}
