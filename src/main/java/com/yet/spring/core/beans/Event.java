package com.yet.spring.core.beans;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Event {

	private static int next_id = 0;
	
	private int id;
	private String msg;
	private Date date;
	private DateFormat df;
	
	public Event(Date date, DateFormat df) {	
		this.id = ++Event.next_id;
		this.date = date;
		this.df = df;
	}

	public int getId() {
		return id;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", msg=" + msg + ", date=" + df.format(date) + "]";
	}
	
	public static boolean isDay() {
				
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.HOUR_OF_DAY,8);
		cal1.set(Calendar.MINUTE,0);
		cal1.set(Calendar.SECOND,0);
		cal1.set(Calendar.MILLISECOND,0);

		final Date s = cal1.getTime();

		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.HOUR_OF_DAY,17);
		cal2.set(Calendar.MINUTE,0);
		cal2.set(Calendar.SECOND,0);
		cal2.set(Calendar.MILLISECOND,0);

		final Date e = cal2.getTime();

		final Date now = new Date();
				
		return now.after(s) && now.before(e);
				
	}

}
