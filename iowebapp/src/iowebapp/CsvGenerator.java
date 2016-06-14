package iowebapp;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.StreamResource.StreamSource;

/**
 * Klasa, która konwertuje dane z tabeli do formatu CSV
 * 
 * @author Micha³ Bielecki
 *
 */
public class CsvGenerator implements StreamSource {
	
	/** Tablica przechowuj¹ca referencje do wszystkich wydarzeñ */
	private final BeanItemContainer<CalendarEvent> eventsContainer;
	
	/**
	 * Konstruktor przyjmuj¹cy eventsContainer.
	 * 
	 * @param eventsContainer
	 */
	public CsvGenerator(final BeanItemContainer<CalendarEvent> eventsContainer)
	{
		this.eventsContainer = eventsContainer;
	}
	
	/**
	 * Metoda która konwertuje dane z tabeli do formatu CSV
	 */
	@Override
	public InputStream getStream()
	{
		final StringBuilder csvFile = new StringBuilder(200);
		
		//header pliku CSV
		csvFile.append("title")
		.append(',')
		.append("dateStart")
		.append(',')
		.append("dateEnd")
		.append(',')
		.append("dateCreated")
		.append(',')
		.append("dateModified")
		.append(',')
		.append("location")
		.append(',')
		.append("description")
		.append(',')
		.append("allDay")
		.append('\n');
		
		//zapisywanie danych z tabeli
		for(int i=0; i<eventsContainer.size(); i++)
		{
			final CalendarEvent event = eventsContainer.getItem(eventsContainer.getIdByIndex(i)).getBean();
			csvFile.append(event.getTitle())
			.append(',')
			.append(event.getDateStart())
			.append(',')
			.append(event.getDateEnd())
			.append(',')
			.append(event.getDateCreated())
			.append(',')
			.append(event.getDateModified())
			.append(',')
			.append(event.getLocation())
			.append(',')
			.append(event.getDescription())
			.append(',')
			.append(event.isAllDay())
			.append('\n');
		}
		
		return new ByteArrayInputStream(csvFile.toString().getBytes(StandardCharsets.UTF_8));
	}
}
