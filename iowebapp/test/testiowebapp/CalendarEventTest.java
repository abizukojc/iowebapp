package testiowebapp;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import iowebapp.CalendarEvent;

public class CalendarEventTest {
	
	CalendarEvent testCE_false = new CalendarEvent("testCE_False", new Date(2016,1,1,1,1,1), new Date(2016,1,1,1,1,1),
			new Date(2016,1,1,1,1,1), new Date(2016,1,1,1,1,1), "lokacja_False", "opis_False", false);
	
	CalendarEvent testCE_true = new CalendarEvent("testCE_True", new Date(2016,2,1,1,1,1), new Date(2016,2,1,1,1,1),
			new Date(2016,2,1,1,1,1), new Date(2016,2,1,1,1,1), "lokacja_True", "opis_True", true);
	
	Date pomocniczaData = new Date(2016,3,1,1,1,1);

	@Test
	public void testCalendarEvent() {
		assertEquals(testCE_false, testCE_false);
	}

	@Test
	public void testGetTitle() {
		assertNotNull(testCE_false.getTitle());
		assertEquals(testCE_false.getTitle(), testCE_false.getTitle());
	}

	@Test
	public void testSetTitle() {
		testCE_false.setTitle("Test setTitle");
		assertEquals("Test setTitle", testCE_false.getTitle());
		assertNotEquals("test SetTitle", testCE_false.getTitle());
	}

	@Test
	public void testGetDateStart() {
		assertNotNull(testCE_false.getDateStart());
		assertEquals(testCE_false.getDateStart(), testCE_false.getDateStart());
		assertNotEquals(pomocniczaData, testCE_false.getDateStart());
	}

	@Test
	public void testSetDateStart() {
		testCE_false.setDateStart(pomocniczaData);
		assertEquals(new Date(2016,3,1,1,1,1), testCE_false.getDateStart());
		assertNotEquals(new Date(2016,1,1,1,1,1), testCE_false.getDateStart());
	}

	@Test
	public void testGetDateEnd() {
		assertNotNull(testCE_false.getDateEnd());
		assertEquals(testCE_false.getDateEnd(), testCE_false.getDateEnd());
		assertNotEquals(pomocniczaData, testCE_false.getDateEnd());
	}

	@Test
	public void testSetDateEnd() {
		testCE_false.setDateEnd(pomocniczaData);
		assertEquals(testCE_false.getDateEnd(), testCE_false.getDateEnd());
		assertNotEquals(pomocniczaData,testCE_false);
	}

	@Test
	public void testGetDateCreated() {
		assertNotNull(testCE_false.getDateCreated());
		assertEquals(testCE_false.getDateCreated(), testCE_false.getDateCreated());
		assertNotEquals(pomocniczaData,testCE_false);
	}

	@Test
	public void testSetDateCreated() {
		testCE_false.setDateCreated(pomocniczaData);
		assertEquals(testCE_false.getDateCreated(), testCE_false.getDateCreated());
		assertNotEquals(testCE_true.getDateCreated(),testCE_false.getDateCreated());
	}

	@Test
	public void testGetDateModified() {
		assertNotNull(testCE_false.getDateModified());
		assertEquals(testCE_false.getDateModified(), testCE_false.getDateModified());
		assertNotEquals(pomocniczaData,testCE_false);
	}

	@Test
	public void testSetDateModified() {
		testCE_false.setDateModified(pomocniczaData);
		assertEquals(pomocniczaData, testCE_false.getDateModified());
		assertNotEquals(testCE_true.getDateModified(),testCE_false.getDateModified());
	}

	@Test
	public void testGetLocation() {
		assertNotNull(testCE_false.getLocation());
		assertEquals(testCE_false.getLocation(), testCE_false.getLocation());
		assertNotEquals(testCE_true.getLocation(), testCE_false.getLocation());
	}

	@Test
	public void testSetLocation() {
		testCE_false.setLocation("test_setLocation");
		assertNotNull(testCE_false.getLocation());
		assertEquals(testCE_false.getLocation(), testCE_false.getLocation());
		assertNotEquals(testCE_true.getLocation(), testCE_false.getLocation());
	}

	@Test
	public void testGetDescription() {
		assertNotNull(testCE_false.getDescription());
		assertEquals(testCE_false.getDescription(), testCE_false.getDescription());
		assertNotEquals(testCE_true.getDescription(), testCE_false.getDescription());
	}

	@Test
	public void testSetDescription() {
		testCE_false.setDescription("test_setDescription");
		assertNotNull(testCE_false.getDescription());
		assertEquals(testCE_false.getDescription(), testCE_false.getDescription());
		assertNotEquals(testCE_true.getDescription(), testCE_false.getDescription());
	}

	@Test
	public void testIsAllDay() {
		assertEquals(false, testCE_false.isAllDay());
		assertNotEquals(true, testCE_false.isAllDay());
	}

	@Test
	public void testSetAllDay() {
		testCE_false.setAllDay(true);
		assertEquals(true, testCE_false.isAllDay());
		assertNotEquals(false, testCE_false.isAllDay());
	}

}
