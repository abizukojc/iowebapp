package iowebapp;

import java.util.Date;

/**
 * Klasa, kt�ra reprezentuje wydarzenie u�ywane w kalendarzu.
 * 
 * @author Krzysztof Perchlicki
 */
public class CalendarEvent {
	/**
	 * Nazwa wydarzenia.
	 */
	private String title;
	/**
	 * Data startu wydarzenia.
	 */
	private Date dateStart;
	/**
	 * Data ko�ca wydarzenia.
	 */
	private Date dateEnd;
	/**
	 * Miejsce, w kt�rym zajdzie wydarzenie.
	 */
	private String location;
	/**
	 * Szczeg�y, opis wydarzenia.
	 */
	private String description;
	/**
	 * Pole, kt�re daje informacje czy wydarzenie zachodzi przez ca�y dzie�.
	 */
	private boolean allDay;

	/**
	 * @param title
	 * @param dateStart
	 * @param dateEnd
	 * @param location
	 * @param description
	 * @param allDay
	 */
	public CalendarEvent(final String title, final Date dateStart, final Date dateEnd, final String location,
			final String description, final boolean allDay) {
		super();
		this.title = title;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.location = location;
		this.description = description;
		this.allDay = allDay;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola title.
	 * 
	 * @return referencja do title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� title.
	 * 
	 * @param title
	 *            obiekt klasy String (nazwa wydarzenia).
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola dateStart.
	 * 
	 * @return referencja do dateStart.
	 */
	public Date getDateStart() {
		return dateStart;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� dateStart.
	 * 
	 * @param dateStart
	 *            obiekt klasy Date (data startu wydarzenia).
	 */
	public void setDateStart(final Date dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola dateEnd.
	 * 
	 * @return referencja do dateEnd.
	 */
	public Date getDateEnd() {
		return dateEnd;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� dateEnd.
	 * 
	 * @param dateEnd
	 *            obiekt klasy Date (data ko�ca wydarzenia).
	 */
	public void setDateEnd(final Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola location.
	 * 
	 * @return referencja do location.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� location.
	 * 
	 * @param location
	 *            obiekt klasy String (lokalizacja wydarzenia).
	 */
	public void setLocation(final String location) {
		this.location = location;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola description.
	 * 
	 * @return referencja do description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� description.
	 * 
	 * @param description
	 *            obiekt klasy String (opis wydarzenia).
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola allDay.
	 * 
	 * @return referencja do allDay.
	 */
	public boolean isAllDay() {
		return allDay;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� allDay.
	 * 
	 * @param allDay
	 *            obiekt klasy boolean (czy wydarzenia trwa ca�y dzie�).
	 */
	public void setAllDay(final boolean allDay) {
		this.allDay = allDay;
	}

}
