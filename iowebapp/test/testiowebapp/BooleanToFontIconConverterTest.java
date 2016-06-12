package testiowebapp;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.Test;

import com.vaadin.server.FontAwesome;

import iowebapp.BooleanToFontIconConverter;

public class BooleanToFontIconConverterTest {

	FontAwesome trueIcon = FontAwesome.CLOSE, falseIcon = FontAwesome.CLONE;
	BooleanToFontIconConverter test = new BooleanToFontIconConverter(trueIcon, falseIcon);
	
	@Test
	public void testBooleanToFontIconConverter() {
		assertNotNull(test);
	}

	@Test
	public void testConvertToModel() {
		try{
			test.convertToModel("Marcin", Boolean.class, new Locale("English"));
			fail("Powinno wyrzucic b³ad, metoda niewspierana");
		} catch(Exception e){
			assertEquals("Not supported", e.getMessage());
		}
	}

	@Test
	public void testConvertToPresentation() {
		assertEquals(trueIcon.getHtml(), test.convertToPresentation(true, String.class, new Locale("English")));
		assertEquals(falseIcon.getHtml(), test.convertToPresentation(false, String.class, new Locale("English")));
		assertNotEquals(falseIcon.getHtml(), test.convertToPresentation(true, String.class, new Locale("English")));
		assertNotEquals(trueIcon.getHtml(), test.convertToPresentation(false, String.class, new Locale("English")));
		assertNotEquals("www.google.pl", test.convertToPresentation(true, String.class, new Locale("English")));
		assertNotEquals("www.google.pl", test.convertToPresentation(false, String.class, new Locale("English")));
	}

	@Test
	public void testGetModelType() {
		assertEquals(Boolean.class, test.getModelType());
	}

	@Test
	public void testGetPresentationType() {
		assertEquals(String.class, test.getPresentationType());
	}

	@Test
	public void testGetTrueIcon() {
		assertEquals(FontAwesome.CLOSE, test.getTrueIcon());
		assertNotEquals(FontAwesome.ARROW_LEFT, test.getTrueIcon());
	}

	@Test
	public void testSetTrueIcon() {
		test.setTrueIcon(FontAwesome.APPLE);
		assertEquals(FontAwesome.APPLE, test.getTrueIcon());
		assertNotEquals(FontAwesome.CLOSE, test.getTrueIcon());
	}

	@Test
	public void testGetFalseIcon() {
		assertEquals(FontAwesome.CLONE, test.getFalseIcon());
		assertNotEquals(FontAwesome.WARNING, test.getFalseIcon());
	}

	@Test
	public void testSetFalseIcon() {
		test.setFalseIcon(FontAwesome.ADJUST);
		assertEquals(FontAwesome.ADJUST, test.getFalseIcon());
		assertNotEquals(FontAwesome.CLONE, test.getFalseIcon());
	}

}
