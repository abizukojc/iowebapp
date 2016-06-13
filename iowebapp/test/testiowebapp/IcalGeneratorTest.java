package testiowebapp;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.junit.Test;

import com.vaadin.data.util.BeanItemContainer;

import iowebapp.CalendarEvent;
import iowebapp.IcalGenerator;

public class IcalGeneratorTest {

	IcalGenerator iCalGenerator;
	@Test
	public void testIcalGenerator() {
		BeanItemContainer<CalendarEvent> eventsContainer = new BeanItemContainer<CalendarEvent>(CalendarEvent.class);
		eventsContainer.addBean(new CalendarEvent("Urodziny", new Date(2016, 9, 25, 16, 0, 0), 
				new Date(2016, 9, 25, 20, 0, 0), new Date(2016, 5, 5, 16, 47, 13), 
				new Date(2016, 5, 5, 16, 47, 13), "Zielona Gora", "Impreza urodzinowa Mariana", false));
		iCalGenerator = new IcalGenerator(eventsContainer);
		assertNotNull(iCalGenerator);
	}
	
	@Test
	public void getStreamTest(){
		BeanItemContainer<CalendarEvent> eventsContainer = new BeanItemContainer<CalendarEvent>(CalendarEvent.class);
		eventsContainer.addBean(new CalendarEvent("Urodziny", new Date(2016, 9, 25, 16, 0, 0), 
				new Date(2016, 9, 25, 20, 0, 0), new Date(2016, 5, 5, 16, 47, 13), 
				new Date(2016, 5, 5, 16, 47, 13), "Zielona Gora", "Impreza urodzinowa Mariana", false));
		iCalGenerator = new IcalGenerator(eventsContainer);
		
		final StringBuilder icalFile = new StringBuilder(200);
		final Date dateStamp = new Date();
		icalFile.append("BEGIN:VCALENDAR\nPRODID:IOWEBAPP_PROJECT_TEAM");
		for (int i = 0; i < eventsContainer.size(); i++) {
			final CalendarEvent event = eventsContainer.getIdByIndex(i);
			icalFile.append("\nBEGIN:VEVENT\nDTSTART");
			icalFile.append(iCalGenerator.dateConvert(event.getDateStart(), event.isAllDay()));
			icalFile.append("\nDTEND");
			icalFile.append(iCalGenerator.dateConvert(event.getDateEnd(), event.isAllDay()));
			icalFile.append("\nDTSTAMP");
			icalFile.append(iCalGenerator.dateConvert(dateStamp, false));
			icalFile.append("\nCREATED");
			icalFile.append(iCalGenerator.dateConvert(event.getDateCreated(), false));
			icalFile.append("\nDESCRIPTION:");
			icalFile.append(event.getDescription());
			icalFile.append("\nLAST-MODIFIED");
			icalFile.append(iCalGenerator.dateConvert(event.getDateModified(), false));
			icalFile.append("\nLOCATION:");
			icalFile.append(event.getLocation());
			icalFile.append("\nSUMMARY:");
			icalFile.append(event.getTitle());
			icalFile.append("\nEND:VEVENT");

		}
		icalFile.append("\nEND:VCALENDAR");
		
		assertNotNull(iCalGenerator.getStream());
		ByteArrayInputStream stream = new ByteArrayInputStream(icalFile.toString().getBytes(StandardCharsets.UTF_8));
		
		final StringBuilder icalFile2 = new StringBuilder(200);
		final Date dateStamp2 = new Date();
		icalFile2.append("BEGIN:VCALENDAR\nPRODID:IOWEBAPP_PROJECT_TEAM");
		for (int i = 0; i < eventsContainer.size(); i++) {
			final CalendarEvent event = eventsContainer.getIdByIndex(i);
			icalFile2.append("\nBEGIN:VEVENT\nDTSTART");
			icalFile2.append(iCalGenerator.dateConvert(event.getDateStart(), event.isAllDay()));
			icalFile2.append("\nDTEND");
			icalFile2.append(iCalGenerator.dateConvert(event.getDateEnd(), event.isAllDay()));
			icalFile2.append("\nDTSTAMP");
			icalFile2.append(iCalGenerator.dateConvert(dateStamp2, false));
			icalFile2.append("\nCREATED");
			icalFile2.append(iCalGenerator.dateConvert(event.getDateCreated(), false));
			icalFile2.append("\nDESCRIPTION:");
			icalFile2.append(event.getDescription());
			icalFile2.append("\nLAST-MODIFIED");
			icalFile2.append(iCalGenerator.dateConvert(event.getDateModified(), false));
			icalFile2.append("\nLOCATION:");
			icalFile2.append(event.getLocation());
			icalFile2.append("\nSUMMARY:");
			icalFile2.append(event.getTitle());
			icalFile2.append("\nEND:VEVENT");

		}
		icalFile2.append("\nEND:VKalendarz");
		
		ByteArrayInputStream stream2 = new ByteArrayInputStream(icalFile2.toString().getBytes(StandardCharsets.UTF_8));
				
		final StringBuilder icalFile3 = new StringBuilder(200);
		final Date dateStamp3 = new Date();
		icalFile3.append("BEGIN:VCALENDAR\nPRODID:IOWEBAPP_PROJECT_TEAM");
		for (int i = 0; i < eventsContainer.size(); i++) {
			final CalendarEvent event = eventsContainer.getIdByIndex(i);
			icalFile3.append("\nBEGIN:VEVENT\nDTSTART");
			icalFile3.append(iCalGenerator.dateConvert(event.getDateStart(), event.isAllDay()));
			icalFile3.append("\nDTEND");
			icalFile3.append(iCalGenerator.dateConvert(event.getDateEnd(), event.isAllDay()));
			icalFile3.append("\nDTSTAMP");
			icalFile3.append(iCalGenerator.dateConvert(dateStamp3, false));
			icalFile3.append("\nCREATED");
			icalFile3.append(iCalGenerator.dateConvert(event.getDateCreated(), false));
			icalFile3.append("\nDESCRIPTION:");
			icalFile3.append(event.getDescription());
			icalFile3.append("\nLAST-MODIFIED");
			icalFile3.append(iCalGenerator.dateConvert(event.getDateModified(), false));
			icalFile3.append("\nLOCATION:");
			icalFile3.append(event.getLocation());
			icalFile3.append("\nSUMMARY:");
			icalFile3.append("Ala ma kota");
			icalFile3.append("\nEND:VEVENT");

		}
		icalFile3.append("\nEND:VCALENDAR");
		
		ByteArrayInputStream stream3 = new ByteArrayInputStream(icalFile3.toString().getBytes(StandardCharsets.UTF_8));		
		
		try {
			assertEquals(stream.read(new byte[1000]), iCalGenerator.getStream().read(new byte[1000]));
			assertNotEquals(stream2.read(new byte[1000]), iCalGenerator.getStream().read(new byte[1000]));
			assertNotEquals(stream3.read(new byte[1000]), iCalGenerator.getStream().read(new byte[1000]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void dateConvertTest(){
		BeanItemContainer<CalendarEvent> eventsContainer = new BeanItemContainer<CalendarEvent>(CalendarEvent.class);
		eventsContainer.addBean(new CalendarEvent("Urodziny", new Date(2016, 9, 25, 16, 0, 0), 
				new Date(2016, 9, 25, 20, 0, 0), new Date(2016, 5, 5, 16, 47, 13), 
				new Date(2016, 5, 5, 16, 47, 13), "Zielona Gora", "Impreza urodzinowa Mariana", false));
		iCalGenerator = new IcalGenerator(eventsContainer);
		
		Date date = new Date(116, 8, 13, 16, 0, 0);
		assertEquals(":20160913T160000", iCalGenerator.dateConvert(date, false));
		assertEquals(";VALUE=DATE:20160913", iCalGenerator.dateConvert(date, true));
	}
}
