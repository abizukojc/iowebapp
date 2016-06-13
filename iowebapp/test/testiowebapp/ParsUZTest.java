package testiowebapp;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import iowebapp.CalendarEvent;
import iowebapp.ParsUZ;

public class ParsUZTest {
	
	
	@Test
	public void testParsUZ() {
		try {
			ParsUZ parsUZ = new ParsUZ("http://plan.uz.zgora.pl/grupy_plan.php?pId_Obiekt=16669");
			ParsUZ parsUZ2 = new ParsUZ("http://plan.uz.zgora.pl/grupy_plan.php?pId_Obiekt=16669");
			assertNotNull(parsUZ);
			assertEquals(parsUZ, parsUZ);
			assertSame(parsUZ2, parsUZ);
			assertNotSame(parsUZ, parsUZ);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}



//	@Test
//	public void testShowEvent() {
//		ParsUZ parsUZ = new ParsUZ("http://plan.uz.zgora.pl/grupy_plan.php?pId_Obiekt=16669");
//		CalendarEvent t = new CalendarEvent("testShowEvent", new Date(2016,1,1,1,1,1), new Date(2016,1,1,1,1,1),
//				new Date(2016,1,1,1,1,1), new Date(2016,1,1,1,1,1), "lokacja", "opis", false);
//		assertEquals("testCE_False\nlokacja\nopis\nData rozpoczecia: "+ t.getDateStart() +"\n"
//					+ "Data zakoñczenia: " + t.getDateEnd() + "\nData Utworzenia: " + t.getDateCreated() + "\n"
//					+ "Data modyfikacji: " + t.getDateModified() + "\n" , parsUZ.showEvent(t));
//		
//		
//		assertSame(parsUZ.showEvent(t),parsUZ.showEvent(t));
//	}

	@Test
	public void testGetEvents() {
		List<CalendarEvent> events = new ArrayList<CalendarEvent>();
		
		assertNotNull(events);
		assertSame(events, events);
	}

}
