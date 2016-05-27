package iowebapp;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.StreamResource.StreamSource;

/**
 * Klasa która konwertuje dane z tabelki do formatu iCal i tworzy strumieñ
 * wejœciowy.
 * 
 * @author Krzysztof Perchlicki
 *
 */
@SuppressWarnings("serial")
class IcalGenerator implements StreamSource {

	/**
	 * Tablica przechowuj¹ca referencje do wszystkich wydarzeñ
	 * (CalendarEvent).
	 */
	private final BeanItemContainer<CalendarEvent> eventsContainer;

	/**
	 * Konstruktor przyjmuj¹cy eventsContainer.
	 * 
	 * @param eventsContainer
	 */
	public IcalGenerator(final BeanItemContainer<CalendarEvent> eventsContainer) {
		this.eventsContainer = eventsContainer;

	}

	/**
	 * Metoda która konwertuje dane z tabeli do formatu iCal i tworzy
	 * strumieñ wejœciowy.
	 */
	@Override
	public InputStream getStream() {
		final StringBuilder icalFile = new StringBuilder(200);
		final Date dateStamp = new Date();
		icalFile.append("BEGIN:VCALENDAR\nPRODID:IOWEBAPP_PROJECT_TEAM");
		for (int i = 0; i < eventsContainer.size(); i++) {
			final CalendarEvent event = eventsContainer.getItem(eventsContainer.getIdByIndex(i)).getBean();
			icalFile.append("\nBEGIN:VEVENT\nDTSTART");
			icalFile.append(dateConvert(event.getDateStart(), event.isAllDay()));
			icalFile.append("\nDTEND");
			icalFile.append(dateConvert(event.getDateEnd(), event.isAllDay()));
			icalFile.append("\nDTSTAMP");
			icalFile.append(dateConvert(dateStamp, false));
			icalFile.append("\nCREATED");
			icalFile.append(dateConvert(event.getDateCreated(), false));
			icalFile.append("\nDESCRIPTION:");
			icalFile.append(event.getDescription());
			icalFile.append("\nLAST-MODIFIED");
			icalFile.append(dateConvert(event.getDateModified(), false));
			icalFile.append("\nLOCATION:");
			icalFile.append(event.getLocation());
			icalFile.append("\nSUMMARY:");
			icalFile.append(event.getTitle());
			icalFile.append("\nEND:VEVENT");

		}
		icalFile.append("\nEND:VCALENDAR");
		return new ByteArrayInputStream(icalFile.toString().getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * Konwertuje daty na zgodne z formatem zapisu w plikach iCal.
	 * 
	 * @param date
	 * @param checked
	 * @return dataIcal
	 */
	@SuppressWarnings("deprecation")
	String dateConvert(final Date date, final boolean checked) {
		final int year = date.getYear() + 1900;
		final int month = date.getMonth() + 1;
		final int day = date.getDate();
		final int hours = date.getHours();
		final int minutes = date.getMinutes();
		final int seconds = date.getSeconds();
		if (!checked) {
			return String.format(":%d%02d%02dT%02d%02d%02d", year, month, day, hours, minutes, seconds);
		}
		return String.format(";VALUE=DATE:%d%02d%02d", year, month, day);
	}

}