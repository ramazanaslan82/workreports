package com.measurements.worklogs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkLog {

	private Date day;
	private List<Event> events = new ArrayList<Event>();

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorkLog [day=");
		builder.append(day);
		builder.append(", events=");
		builder.append(events);
		builder.append("]");
		return builder.toString();
	}

}
