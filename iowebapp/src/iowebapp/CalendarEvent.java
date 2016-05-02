package iowebapp;

import java.util.Date;

public class CalendarEvent {
	private String title;
	private Date dateStart;
	private Date dateEnd;
	private String location;
	private String description;
	private boolean allDay;
	//private String options;
	
	public CalendarEvent(String title, Date dateStart, Date dateEnd, String location, String description, boolean allDay) {
		super();
		this.title = title;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.location = location;
		this.description = description;
		this.allDay = allDay;
		//this.options = options;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

//	public String getOptions() {
//		return options;
//	}
//
//	public void setOptions(String options) {
//		this.options = options;
//	}
	
	
}
