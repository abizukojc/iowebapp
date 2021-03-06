package testiowebapp;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.vaadin.data.util.BeanItemContainer;

import iowebapp.CalendarEvent;
import iowebapp.LoadFromWebWindow;

public class LoadFromWebWindowTest {

	@Test
	public void testLoadFromWebWindow() {
		final BeanItemContainer<CalendarEvent> eventsContainer = new BeanItemContainer<CalendarEvent>(CalendarEvent.class);
		eventsContainer.addBean(new CalendarEvent("Urodziny", new Date(2016, 9, 25, 16, 0, 0), 
				new Date(2016, 9, 25, 20, 0, 0), new Date(2016, 5, 5, 16, 47, 13), 
				new Date(2016, 5, 5, 16, 47, 13), "Zielona Gora", "Impreza urodzinowa Mariana", false));
		LoadFromWebWindow loadFromWebWindow = new LoadFromWebWindow(eventsContainer);
		assertNotNull(loadFromWebWindow);
	}

}
