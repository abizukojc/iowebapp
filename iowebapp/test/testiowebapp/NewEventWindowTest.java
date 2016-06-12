package testiowebapp;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;

import iowebapp.CalendarEvent;
import iowebapp.NewEventWindow;

public class NewEventWindowTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testNewEventWindow() {
		BeanItemContainer<CalendarEvent> eventsContainer = new BeanItemContainer<CalendarEvent>(CalendarEvent.class);
		CalendarEvent calendarEvent = new CalendarEvent("Urodziny", new Date(2016, 9, 25, 16, 0, 0), 
				new Date(2016, 9, 25, 20, 0, 0), new Date(2016, 5, 5, 16, 47, 13), 
				new Date(2016, 5, 5, 16, 47, 13), "Zielona Gora", "Impreza urodzinowa Mariana", false);
		eventsContainer.addBean(calendarEvent);
		assertNotNull(new NewEventWindow(eventsContainer, new Grid(), calendarEvent));
	}

}
