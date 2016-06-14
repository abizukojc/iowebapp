package testiowebapp;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.vaadin.data.util.BeanItemContainer;

import iowebapp.CalendarEvent;

import iowebapp.UploadWindow;

public class UploadWindowTest {

	@Test
	public void testUploadWindow() {
		BeanItemContainer<CalendarEvent> eventsContainer = new BeanItemContainer<CalendarEvent>(CalendarEvent.class);
		eventsContainer.addBean(new CalendarEvent("Urodziny", new Date(2016, 9, 25, 16, 0, 0), 
				new Date(2016, 9, 25, 20, 0, 0), new Date(2016, 5, 5, 16, 47, 13), 
				new Date(2016, 5, 5, 16, 47, 13), "Zielona Gora", "Impreza urodzinowa Mariana", false));
		
		assertNotNull(eventsContainer);
		
		UploadWindow uw = new UploadWindow(eventsContainer);
		assertNotNull(uw);
		assertEquals(uw, uw);
	}

	@Test
	public void testStringToDateConvert() {
		BeanItemContainer<CalendarEvent> eventsContainer = new BeanItemContainer<CalendarEvent>(CalendarEvent.class);
		eventsContainer.addBean(new CalendarEvent("Urodziny", new Date(2016, 9, 25, 16, 0, 0), 
				new Date(2016, 9, 25, 20, 0, 0), new Date(2016, 5, 5, 16, 47, 13), 
				new Date(2016, 5, 5, 16, 47, 13), "Zielona Gora", "Impreza urodzinowa Mariana", false));
		UploadWindow uw = new UploadWindow(eventsContainer);
		String string = new String(":20161028T160000");
		String krotszyString = new String(";VALUE=DATE:20161028");
		Date data = new Date(116,10,28,16,0,0);
		Date data1 = new Date(116,10,28);
		assertEquals(data, uw.stringToDateConvert(string));
		assertEquals(data1, uw.stringToDateConvert(krotszyString));
		
	}

}
