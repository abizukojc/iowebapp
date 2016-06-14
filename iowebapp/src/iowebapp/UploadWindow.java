package iowebapp;

import com.vaadin.data.util.*;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.*;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Upload.*;
import java.awt.Toolkit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.Date;
import java.util.Locale;

/**
 * Okno zawiera w sobie pole tekstowe, w którym umieszczana jest nazwa wybranego
 * pliku ics. Ma w sobie także przycisk otwierający okno w którym użytkownik
 * wybiera plik ics, a także przycisk zatwierdzający import.
 * 
 * @author Krzysztof Perchlicki
 */
@SuppressWarnings("serial")
public class UploadWindow extends Window {

	/**
	 * Referencja do obiektu typu BeanItemContainer<CalendarEvent> (tablica
	 * zdarzeń).
	 */
	public BeanItemContainer<CalendarEvent> eventsContainer;
	/**
	 * Ścieżka zapisu plików na serwer.
	 */
	public String path;
	/**
	 * Logger używany do wyświetlania komunikatów.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadWindow.class);

	/**
	 * Konstruktor przyjmujący referencję do obiektu eventsContainer (tablica
	 * wydarzeń). Tworzy okno służące do importu wydarzeń.
	 * 
	 * @param eventsContainer
	 */
	public UploadWindow(final BeanItemContainer<CalendarEvent> eventsContainer) {
		// window settings
		super();
		this.eventsContainer = eventsContainer;
		final Toolkit size = Toolkit.getDefaultToolkit();
		final String tempPath = getClass().getResource("CalendarEvent.class").getPath();
		setPosition(2 * size.getScreenSize().width / 5, size.getScreenSize().height / 6);
		setWidth("330px");
		setHeight("150px");
		setClosable(true);
		setResizable(false);

		// mainVLay settings
		VerticalLayout mainVLay;
		mainVLay = new VerticalLayout();
		mainVLay.setMargin(false);
		mainVLay.setSizeFull();
		setContent(mainVLay);
		final HorizontalLayout topHLay = new HorizontalLayout();
		topHLay.setMargin(new MarginInfo(true, false, true, false));
		topHLay.setSpacing(true);
		final TextField pathTF = new TextField();
		pathTF.setWidth(150, Unit.PIXELS);

		final Upload uploadButton = new Upload();
		uploadButton.setButtonCaption("Browse...");
		uploadButton.setReceiver(new Receiver() {

			/**
			 * Tworzy na serwerze kopie pliku pobranego od użytkownika, pozbywa
			 * się niepotrzebnych plików z serwera.
			 */
			@Override
			public OutputStream receiveUpload(final String filename, final String mimeType) {

				try {
					path = tempPath.substring(0, tempPath.length() - "CalendarEvent.class".length());
					final String textFieldValue = pathTF.getValue();
					if (!("".equals(textFieldValue))) {
						new File(path + pathTF.getValue()).delete();
					}
					final String extension = filename.substring(filename.length() - 4, filename.length());
					if (!".ICS".equals(extension.toUpperCase(Locale.ENGLISH))) {
						final Notification badExtensionError = new Notification("You need to choose .ics file!",
								Notification.Type.ERROR_MESSAGE);
						badExtensionError.setPosition(Position.TOP_CENTER);
						badExtensionError.setDelayMsec(2000);
						badExtensionError.show(Page.getCurrent());
						// When I tried to return null there was an
						// uploadException so I return this.
						return new ByteArrayOutputStream();
					}
					final File file = new File(path + filename);
					return new FileOutputStream(file);
				} catch (final FileNotFoundException e) {
					final Notification openError = new Notification("Could not open file",
							Notification.Type.ERROR_MESSAGE);
					openError.setDelayMsec(2000);
					openError.setPosition(Position.TOP_CENTER);
					openError.show(Page.getCurrent());
				}
				return null;
			}

		});
		uploadButton.setImmediate(true);
		uploadButton.addSucceededListener(new SucceededListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 8342221784732468501L;
			/**
			 * Nazwa pliku.
			 */
			String filename;
			/**
			 * Rozszerzenie pliku.
			 */
			String extension;

			/**
			 * W przypadku sukcesu importu, metoda zmienia tekst wyświetlany w
			 * TextField na nazwę wybranego przez użytkownika pliku.
			 */
			@Override
			public void uploadSucceeded(final SucceededEvent event) {
				filename = event.getFilename();
				extension = filename.substring(filename.length() - 4, filename.length());
				if (".ICS".equals(extension.toUpperCase(Locale.ENGLISH))) {
					pathTF.setValue(event.getFilename());
				}

			}

		});
		final Button addButton = new Button("Confirm Upload");
		addButton.setImmediate(true);
		addButton.setSizeUndefined();
		addButton.addClickListener(
				/**
				* W przypadku kliknięcia na przycisk Confirm, zostają
				* zaimportowane wydarzenia z wybranego pliku. 
				* 
				* @author Krzysztof Perchlicki
				*/
				new ClickListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = -1846907558871330146L;
					/**
					 * BufferedReader służący do odczytu kolejnych linii.
					 */

					BufferedReader fileReader;
					/**
					 * Zmienna przechowująca tymczasowo kolejną linie pliku.
					 */
					String line;
					/**
					 * Plik będący kopią wybranego przez użytkownika pliku i
					 * mieszczący się na serwerze.
					 */
					File file;
					/**
					 * Nazwa wydarzenia.
					 */
					String titleS;
					/**
					 * Lokalizacja wydarzenia.
					 */
					String locationS;
					/**
					 * Opis wydarzenia.
					 */
					String descriptionS;
					/**
					 * Data początku wydarzenia.
					 */
					Date dateStartD;
					/**
					 * Data końca wydarzenia.
					 */
					Date dateEndD;
					/**
					 * Data stworzenia.
					 */
					Date dateCreatedD;
					/**
					 * Data modyfikacji.
					 */
					Date dateModifiedD;

					/**
					 * Kliknięcie powoduje rozpoczęcie parsowania wybranego
					 * pliku iCal do wydarzeń (CalendarEvent).
					 */
					@Override
					public void buttonClick(final ClickEvent event) {
						int length = 0;
						try {
							file = new File(path + pathTF.getValue());
							if (file.exists()) {
								fileReader = new BufferedReader(
										new InputStreamReader(new FileInputStream(file), "UTF-8"));
								boolean allDay = false;
								line = fileReader.readLine();
								while (line != null) {
									length = "DTEND".length();
									if (line.contains("DTSTART")) {
										length = "DTSTART".length();
										dateStartD = stringToDateConvert(line.substring(length));
									}
									if (";".equals(Character.toString(line.charAt(length)))) {
										allDay = true;
									}
									if (line.contains("DTEND")) {
										dateEndD = stringToDateConvert(line.substring(length));
									}
									if (line.contains("CREATED")) {
										length = "CREATED".length();
										dateCreatedD = stringToDateConvert(line.substring(length));
									}
									if (line.contains("DESCRIPTION:")) {
										length = "DESCRIPTION:".length();
										descriptionS = line.substring(length);
									}
									if (line.contains("LAST-MODIFIED")) {
										length = "LAST-MODIFIED".length();
										dateModifiedD = stringToDateConvert(line.substring(length));
									}
									if (line.contains("LOCATION:")) {
										length = "LOCATION:".length();
										locationS = line.substring(length);
									}
									if (line.contains("SUMMARY:")) {
										length = "SUMMARY:".length();
										titleS = line.substring(length);
									}
									if (line.contains("END:VEVENT")) {
										eventsContainer.addBean(new CalendarEvent(titleS, dateStartD, dateEndD,
												dateCreatedD, dateModifiedD, locationS, descriptionS, allDay));
										allDay = false;
									}
									line = fileReader.readLine();
								}
								final Notification addSuccess = new Notification("Successfully imported iCal file!",
										Notification.Type.ASSISTIVE_NOTIFICATION);
								addSuccess.setPosition(Position.TOP_CENTER);
								addSuccess.setDelayMsec(1000);
								addSuccess.show(Page.getCurrent());
								fileReader.close();
								file.delete();
								pathTF.setValue("");

							}
						} catch (FileNotFoundException e) {
							LOGGER.error("Can't open file. Check if exist.", e);
						} catch (IOException e) {
							LOGGER.error("An IOException error occured!", e);
						}

					}
				});

		addCloseListener(new CloseListener() {

			/**
			 * W przypadku zamknięciu okna, zaimportowane na serwer pliki są
			 * usuwane.
			 */
			@Override
			public void windowClose(final CloseEvent event) {
				final String textFieldValue = pathTF.getValue();
				if (!"".equals(textFieldValue)) {
					new File(path + pathTF.getValue()).delete();
				}

			}
		});

		topHLay.addComponent(pathTF);
		topHLay.addComponent(uploadButton);
		mainVLay.addComponent(topHLay);
		mainVLay.setComponentAlignment(topHLay, Alignment.MIDDLE_CENTER);
		mainVLay.addComponent(addButton);
		mainVLay.setComponentAlignment(addButton, Alignment.MIDDLE_CENTER);
	}

	/**
	 * Konwertuje datę zapisaną w formacie ics na obiekt typu Date.
	 * 
	 * @param dateString
	 * @return obiekt typu Data.
	 */
	@SuppressWarnings("deprecation")
	public Date stringToDateConvert(final String dateString) {
		int year;
		int month;
		int day;
		int hours;
		int minutes;
		int seconds;
		if (":".equals(Character.toString(dateString.charAt(0)))) {
			year = Integer.parseInt(dateString.substring(1, 5)) - 1900;
			month = Integer.parseInt(dateString.substring(5, 7));
			day = Integer.parseInt(dateString.substring(7, 9));
			hours = Integer.parseInt(dateString.substring(10, 12));
			minutes = Integer.parseInt(dateString.substring(12, 14));
			seconds = Integer.parseInt(dateString.substring(14, 16));
			return new Date(year, month, day, hours, minutes, seconds);
		} else {
			year = Integer.parseInt(dateString.substring(12, 16)) - 1900;
			month = Integer.parseInt(dateString.substring(16, 18));
			day = Integer.parseInt(dateString.substring(18, 20));
			return new Date(year, month, day);
		}
	}

}