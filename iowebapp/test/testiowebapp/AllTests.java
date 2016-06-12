package testiowebapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BooleanToFontIconConverterTest.class, CsvGeneratorTest.class, IcalGeneratorTest.class,
		LoadFromWebWindowTest.class, NewEventWindowTest.class })
public class AllTests {

}
