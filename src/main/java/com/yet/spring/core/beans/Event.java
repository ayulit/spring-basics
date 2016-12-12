package com.yet.spring.core.beans;

import java.text.DateFormat;
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
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", msg=" + msg + ", date=" + df.format(date) + "]";
	}

}
