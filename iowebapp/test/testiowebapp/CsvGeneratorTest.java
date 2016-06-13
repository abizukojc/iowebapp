package testiowebapp;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.junit.Test;

import com.vaadin.data.util.BeanItemContainer;

import iowebapp.CalendarEvent;
import iowebapp.CsvGenerator;

public class CsvGeneratorTest {

	@Test
	public void testCsvGenerator() {
		BeanItemContainer<CalendarEvent> eventsContainer = new BeanItemContainer<CalendarEvent>(CalendarEvent.class);
		eventsContainer.addBean(new CalendarEvent("Urodziny", new Date(2016, 9, 25, 16, 0, 0), 
				new Date(2016, 9, 25, 20, 0, 0), new Date(2016, 5, 5, 16, 47, 13), 
				new Date(2016, 5, 5, 16, 47, 13), "Zielona Gora", "Impreza urodzinowa Mariana", false));
		CsvGenerator csvGenerator = new CsvGenerator(eventsContainer);
		assertNotNull(csvGenerator);
		
	}
	
	@Test
	public void testGetStream(){
		BeanItemContainer<CalendarEvent> eventsContainer = new BeanItemContainer<CalendarEvent>(CalendarEvent.class);
		eventsContainer.addBean(new CalendarEvent("Urodziny", new Date(2016, 9, 25, 16, 0, 0), 
				new Date(2016, 9, 25, 20, 0, 0), new Date(2016, 5, 5, 16, 47, 13), 
				new Date(2016, 5, 5, 16, 47, 13), "Zielona Gora", "Impreza urodzinowa Mariana", false));
		CsvGenerator csvGenerator = new CsvGenerator(eventsContainer);
		
final StringBuilder csvFile = new StringBuilder(200);
		
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
		
		ByteArrayInputStream stream = new ByteArrayInputStream(csvFile.toString().getBytes(StandardCharsets.UTF_8));
		
final StringBuilder csvFile2 = new StringBuilder(200);
		
		csvFile2.append("title")
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
		
		for(int i=0; i<eventsContainer.size(); i++)
		{
			final CalendarEvent event = eventsContainer.getItem(eventsContainer.getIdByIndex(i)).getBean();
			csvFile2.append(event.getTitle())
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
			.append("rampampam")
			.append('\n');
		}
		
		ByteArrayInputStream stream2 = new ByteArrayInputStream(csvFile2.toString().getBytes(StandardCharsets.UTF_8));
		
final StringBuilder csvFile3 = new StringBuilder(200);
		
		csvFile3.append("title")
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
		.append("opis")
		.append(',')
		.append("allDay")
		.append('\n');
		
		for(int i=0; i<eventsContainer.size(); i++)
		{
			final CalendarEvent event = eventsContainer.getItem(eventsContainer.getIdByIndex(i)).getBean();
			csvFile3.append(event.getTitle())
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
		
		ByteArrayInputStream stream3 = new ByteArrayInputStream(csvFile3.toString().getBytes(StandardCharsets.UTF_8));
		
		try {
			assertEquals(stream.read(new byte[1000]), csvGenerator.getStream().read(new byte[1000]));
			assertNotEquals(stream2.read(new byte[1000]), csvGenerator.getStream().read(new byte[1000]));
			assertNotEquals(stream3.read(new byte[1000]), csvGenerator.getStream().read(new byte[1000]));
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertNotNull(csvGenerator.getStream());
	}

}
