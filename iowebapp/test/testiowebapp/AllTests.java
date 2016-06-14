package testiowebapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BooleanToFontIconConverterTest.class, CalendarEventTest.class, CsvGeneratorTest.class,
		DeleteAllWindowTest.class, IcalGeneratorTest.class, IowebappUITest.class, LoadFromWebWindowTest.class,
		NewEventWindowTest.class, ParsUZTest.class, UploadWindowTest.class })
public class AllTests {

}
