package iowebapp;

import java.util.Date;

/**
 * Klasa, która reprezentuje wydarzenie używane w kalendarzu.
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
	 * Data końca wydarzenia.
	 */
	private Date dateEnd;
	/**
	 * Data utworzenia wydarzenia.
	 */
	private Date dateCreated;
	/**
	 * Data modyfikacji wydarzenia.
	 */
	private Date dateModified;
	/**
	 * Miejsce, w którym zajdzie wydarzenie.
	 */
	private String location;
	/**
	 * Szczegóły, opis wydarzenia.
	 */
	private String description;
	/**
	 * Pole, które daje informacje czy wydarzenie zachodzi przez cały dzień.
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
	public CalendarEvent(final String title, final Date dateStart, final Date dateEnd, final Date dateCreated,
			final Date dateModified, final String location, final String description, final boolean allDay) {
		super();
		this.title = title;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
		this.location = location;
		this.description = description;
		this.allDay = allDay;
	}

	/**
	 * Metoda, która daje dostęp do pola title.
	 * 
	 * @return referencja do title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Metoda, która pozwala zmienić title.
	 * 
	 * @param title
	 *            obiekt klasy String (nazwa wydarzenia).
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Metoda, która daje dostęp do pola dateStart.
	 * 
	 * @return referencja do dateStart.
	 */
	public Date getDateStart() {
		return dateStart;
	}

	/**
	 * Metoda, która pozwala zmienić dateStart.
	 * 
	 * @param dateStart
	 *            obiekt klasy Date (data startu wydarzenia).
	 */
	public void setDateStart(final Date dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * Metoda, która daje dostęp do pola dateEnd.
	 * 
	 * @return referencja do dateEnd.
	 */
	public Date getDateEnd() {
		return dateEnd;
	}

	/**
	 * Metoda, która pozwala zmienić dateEnd.
	 * 
	 * @param dateEnd
	 *            obiekt klasy Date (data ko�ca wydarzenia).
	 */
	public void setDateEnd(final Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	/**
	 * Metoda, która daje dostęp do pola dateCreated.
	 * 
	 * @return referencja do dateCreated.
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * Metoda, która pozwala zmienić dateCreated.
	 * 
	 * @param dateCreated
	 *            obiekt klasy Date (data utworzenia wydarzenia).
	 */
	public void setDateCreated(final Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * Metoda, która daje dostęp do pola dateModified.
	 * 
	 * @return referencja do dateModified.
	 */
	public Date getDateModified() {
		return dateModified;
	}

	/**
	 * Metoda, która pozwala zmienić dateModified.
	 * 
	 * @param dateModified
	 *            obiekt klasy Date (data modyfikacji wydarzenia).
	 */
	public void setDateModified(final Date dateModified) {
		this.dateModified = dateModified;
	}

	/**
	 * Metoda, która daje dostęp do pola location.
	 * 
	 * @return referencja do location.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Metoda, która pozwala zmienić location.
	 * 
	 * @param location
	 *            obiekt klasy String (lokalizacja wydarzenia).
	 */
	public void setLocation(final String location) {
		this.location = location;
	}

	/**
	 * Metoda, która daje dostęp do pola description.
	 * 
	 * @return referencja do description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Metoda, która pozwala zmienić description.
	 * 
	 * @param description
	 *            obiekt klasy String (opis wydarzenia).
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Metoda, która daje dostęp do pola allDay.
	 * 
	 * @return referencja do allDay.
	 */
	public boolean isAllDay() {
		return allDay;
	}

	/**
	 * Metoda, która pozwala zmienić allDay.
	 * 
	 * @param allDay
	 *            obiekt klasy boolean (czy wydarzenia trwa cały dzień).
	 */
	public void setAllDay(final boolean allDay) {
		this.allDay = allDay;
	}

}
