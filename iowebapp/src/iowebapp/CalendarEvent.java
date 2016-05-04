package iowebapp;

import java.util.Date;

/**
 * Klasa, która reprezentuje wydarzenie u¿ywane w kalendarzu.
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
	 * Data koñca wydarzenia.
	 */
	private Date dateEnd;
	/**
	 * Miejsce, w którym zajdzie wydarzenie.
	 */
	private String location;
	/**
	 * Szczegó³y, opis wydarzenia.
	 */
	private String description;
	/**
	 * Pole, które daje informacje czy wydarzenie zachodzi przez ca³y dzieñ.
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
	 * Metoda, która dajê dostêp do pola title.
	 * 
	 * @return referencja do title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Metoda, która pozwala zmieniæ title.
	 * 
	 * @param title
	 *            obiekt klasy String (nazwa wydarzenia).
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Metoda, która dajê dostêp do pola dateStart.
	 * 
	 * @return referencja do dateStart.
	 */
	public Date getDateStart() {
		return dateStart;
	}

	/**
	 * Metoda, która pozwala zmieniæ dateStart.
	 * 
	 * @param dateStart
	 *            obiekt klasy Date (data startu wydarzenia).
	 */
	public void setDateStart(final Date dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * Metoda, która dajê dostêp do pola dateEnd.
	 * 
	 * @return referencja do dateEnd.
	 */
	public Date getDateEnd() {
		return dateEnd;
	}

	/**
	 * Metoda, która pozwala zmieniæ dateEnd.
	 * 
	 * @param dateEnd
	 *            obiekt klasy Date (data koñca wydarzenia).
	 */
	public void setDateEnd(final Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	/**
	 * Metoda, która dajê dostêp do pola location.
	 * 
	 * @return referencja do location.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Metoda, która pozwala zmieniæ location.
	 * 
	 * @param location
	 *            obiekt klasy String (lokalizacja wydarzenia).
	 */
	public void setLocation(final String location) {
		this.location = location;
	}

	/**
	 * Metoda, która dajê dostêp do pola description.
	 * 
	 * @return referencja do description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Metoda, która pozwala zmieniæ description.
	 * 
	 * @param description
	 *            obiekt klasy String (opis wydarzenia).
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Metoda, która dajê dostêp do pola allDay.
	 * 
	 * @return referencja do allDay.
	 */
	public boolean isAllDay() {
		return allDay;
	}

	/**
	 * Metoda, która pozwala zmieniæ allDay.
	 * 
	 * @param allDay
	 *            obiekt klasy boolean (czy wydarzenia trwa ca³y dzieñ).
	 */
	public void setAllDay(final boolean allDay) {
		this.allDay = allDay;
	}

}
