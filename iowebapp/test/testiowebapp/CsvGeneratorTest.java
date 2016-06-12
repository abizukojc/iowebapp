package testiowebapp;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
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

}
