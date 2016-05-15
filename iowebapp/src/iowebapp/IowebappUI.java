package iowebapp;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Locale;
import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.DetailsGenerator;
import com.vaadin.ui.Grid.RowReference;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.themes.ValoTheme;

/**
 * G³ówny servlet. Po otrzymaniu od u¿ytkownika requestu tworzy UI (User
 * Interface).
 * 
 * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
 * @author Krzysztof Perchlicki
 */
@SuppressWarnings("serial")
@Theme("iowebapp")
public class IowebappUI extends UI {

	/*
	 * Wyjaœnienie nazwewnictwa: Przyjêta konwencja jest taka, ¿e 1 cz³on
	 * oznacza zastosowanie/znaczenie zmiennej a 2 cz³on typ obiektu. GLay -
	 * GridLayout | DF - DateField HLay - HorizontalLayout | VLay -
	 * VerticalLayout G - Grid | L - Label | TA - TextArea | TF - TextField
	 */

	/**
	 * Bezparametrowy konstruktor z nadklasy (klasa UI).
	 */
	public IowebappUI() {
		super();
	}

	/**
	 * Konstruktor z nadklasy (klasa UI) przyjmuj¹cy jako parametr obiekt klasy
	 * Component.
	 * 
	 * @param content
	 *            referencja do obiektu klasy Component (u¿ywany jako kontent
	 *            UI).
	 */
	public IowebappUI(final Component content) {
		super(content);
	}

	/**
	 * @author Krzysztof Perchlicki
	 *
	 */
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = IowebappUI.class)
	public static class Servlet extends VaadinServlet {
	}

	/**
	 * Tworzy UI (User Interface) u¿ytkownika po otrzymaniu od niego requestu.
	 * 
	 * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
	 */
	@Override
	protected void init(final VaadinRequest request) {

		// mainPanel settings
		Panel mainPanel;
		mainPanel = new Panel("EVENT GENERATOR");
		mainPanel.setWidth(80, Unit.PERCENTAGE);
		mainPanel.setHeight(43, Unit.PERCENTAGE);
		setContent(mainPanel);

		// mainHLay settings
		HorizontalLayout mainHLay;
		mainHLay = new HorizontalLayout();
		mainHLay.setSizeFull();
		mainPanel.setContent(mainHLay);

		// buttonsGLay settings
		GridLayout buttonsGLay;
		buttonsGLay = new GridLayout(2, 3);
		mainHLay.addComponent(buttonsGLay);
		buttonsGLay.setSizeFull();

		// eventsContainer settings
		BeanItemContainer<CalendarEvent> eventsContainer;
		eventsContainer = new BeanItemContainer<>(CalendarEvent.class);
		// eventG settings
		Grid eventG;
		eventG = new Grid();
		eventG.setContainerDataSource(eventsContainer);
		eventG.setLocale(Locale.GERMAN);
		eventG.setDetailsGenerator(new DetailsGenerator() {

			/**
			 * Metoda wyœwietla opis wydarzenia, a tak¿e przyciski s³u¿¹ce do
			 * usiniêcia, edycji i duplikowania wydarzenia.
			 */
			@Override
			public Component getDetails(final RowReference rowReference) {
				final CalendarEvent eventItem = (CalendarEvent) rowReference.getItemId();
				Label descriptionL;
				descriptionL = new Label(eventItem.getDescription());
				descriptionL.setWidth(100, Unit.PIXELS);
				final Button deleteB = new Button("Delete");
				deleteB.setIcon(FontAwesome.CLOSE);
				deleteB.addClickListener(new ClickListener() {

					/**
					 * Usuwa wydarzenie, pod którym klikamy przycisk "Delete".
					 */
					@Override
					public void buttonClick(final ClickEvent event) {
						eventsContainer.removeItem(rowReference.getItemId());

					}
				});
				final Button copyB = new Button("Copy");
				copyB.setIcon(FontAwesome.CLONE);
				copyB.addClickListener(new ClickListener() {

					/**
					 * Duplikuje wydarzenie, pod którym klikamy przycisk "Copy".
					 */
					@Override
					public void buttonClick(final ClickEvent event) {
						eventsContainer.addBean(new CalendarEvent(eventItem.getTitle(), eventItem.getDateStart(),
								new Date(), new Date(), eventItem.getDateEnd(), eventItem.getLocation(),
								eventItem.getDescription(), eventItem.isAllDay()));

					}
				});
				final Button editB = new Button("Edit");
				editB.setIcon(FontAwesome.EDIT);
				editB.addClickListener(new ClickListener() {

					/**
					 * Daje mo¿liwoœæ edycji wydarzenia, pod którym klikamy
					 * przycisk "Edit".
					 */
					@Override
					public void buttonClick(final ClickEvent event) {
						addWindow(new NewEventWindow(eventsContainer, eventG, eventItem));

					}
				});
				final HorizontalLayout horizontalLay = new HorizontalLayout(deleteB, copyB, editB);
				horizontalLay.setWidth(350, Unit.PIXELS);
				final VerticalLayout verticalLay = new VerticalLayout(descriptionL, horizontalLay);
				verticalLay.setStyleName("descMargins");
				verticalLay.setMargin(true);
				return verticalLay;
			}

		});

		eventG.addItemClickListener(new ItemClickListener() {

			/**
			 * W przypadku gdy u¿ytkownik w wydarzenie kliknie dwa razy metoda
			 * powoduje zmianê widocznoœci szczegó³ów.
			 */
			@Override
			public void itemClick(final ItemClickEvent event) {
				if (event.isDoubleClick()) {
					final Object itemId = event.getItemId();
					eventG.setDetailsVisible(itemId, !eventG.isDetailsVisible(itemId));
				}
			}

		});

		final Column allDayCol = eventG.getColumn("allDay");
		final Column descriptonCol = eventG.getColumn("description");
		final Column dateCreatedCol = eventG.getColumn("dateCreated");
		final Column dateModifiedCol = eventG.getColumn("dateModified");
		descriptonCol.setHidden(true);
		dateCreatedCol.setHidden(true);
		dateModifiedCol.setHidden(true);
		eventG.setEditorEnabled(false);
		allDayCol.setConverter(new BooleanToFontIconConverter(FontAwesome.CHECK_SQUARE_O, FontAwesome.SQUARE_O));
		allDayCol.setRenderer(new HtmlRenderer());
		allDayCol.setMaximumWidth(65);
		allDayCol.setResizable(false);
		eventG.setColumnOrder("title", "allDay", "dateStart", "dateEnd", "location");
		eventG.setSizeFull();
		mainHLay.addComponent(eventG);

		// expand and alignment settings of eventG and buttonsGLay
		mainHLay.setExpandRatio(buttonsGLay, 1);
		mainHLay.setExpandRatio(eventG, 2);
		mainHLay.setComponentAlignment(eventG, Alignment.TOP_RIGHT);
		mainHLay.setComponentAlignment(buttonsGLay, Alignment.TOP_LEFT);

		// newEventB settings
		Button newEventB;
		newEventB = new Button("NEW EVENT");
		newEventB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		newEventB.setImmediate(true);
		newEventB.setHeight(70, Unit.PERCENTAGE);
		newEventB.setDescription("Click to create new event!");
		newEventB.addClickListener(new ClickListener() {

			/**
			 * Metoda tworzy okno w którym u¿ytkownik wype³nia pola aby utworzyæ
			 * nowe wydarzenie (obiekt klasy NewEventWindow).
			 */
			@Override
			public void buttonClick(final ClickEvent event) {
				addWindow(new NewEventWindow(eventsContainer, eventG, null));

			}
		});

		// deleteAllB settings
		Button deleteAllB;
		deleteAllB = new Button("DELETE ALL");
		deleteAllB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		deleteAllB.setImmediate(true);
		deleteAllB.setDescription("Click to delete all your events!");
		deleteAllB.setHeight(70, Unit.PERCENTAGE);
		deleteAllB.addClickListener(new ClickListener() {

			/**
			 * Metoda tworzy okno w którym u¿ytknownik podejmuje decyzjê czy na
			 * pewno chce usun¹æ wszystkie wydarzenia (obiekt klasy
			 * DeleteAllWindow).
			 */
			@Override
			public void buttonClick(final ClickEvent event) {
				addWindow(new DeleteAllWindow(eventsContainer));

			}
		});

		// loadPcB settings
		Button loadPcB;
		loadPcB = new Button("LOAD FROM PC");
		loadPcB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		loadPcB.setDescription("Click to load your events from your computer!");
		loadPcB.setImmediate(true);
		loadPcB.setHeight(70, Unit.PERCENTAGE);

		// loadWebB settings
		Button loadWebB;
		loadWebB = new Button("LOAD FROM WEB");
		loadWebB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		loadWebB.setDescription("Click to load your events from a website!");
		loadWebB.setImmediate(true);
		loadWebB.setHeight(70, Unit.PERCENTAGE);

		// genIcalB settings
		Button genIcalB;
		genIcalB = new Button("GENERATE ICAL");
		genIcalB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		genIcalB.setDescription("Click to export your events to iCal format.");
		genIcalB.setImmediate(true);
		genIcalB.setHeight(70, Unit.PERCENTAGE);
		final StreamResource resource = createResource(eventsContainer);
		final FileDownloader fileDownloader = new FileDownloader(resource);
		fileDownloader.extend(genIcalB);

		// genCsvB settings
		Button genCsvB;
		genCsvB = new Button("GENERATE CSV");
		genCsvB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		genCsvB.setDescription("Click to export your events to CSV format.");
		genCsvB.setImmediate(true);
		genCsvB.setHeight(70, Unit.PERCENTAGE);

		// adding buttons to buttonsGLay
		buttonsGLay.addComponent(newEventB, 0, 0);
		newEventB.setWidth(80, Unit.PERCENTAGE);
		buttonsGLay.setComponentAlignment(newEventB, Alignment.MIDDLE_CENTER);
		buttonsGLay.addComponent(deleteAllB, 1, 0);
		deleteAllB.setWidth(80, Unit.PERCENTAGE);
		buttonsGLay.setComponentAlignment(deleteAllB, Alignment.MIDDLE_CENTER);
		buttonsGLay.addComponent(loadPcB, 0, 1);
		loadPcB.setWidth(80, Unit.PERCENTAGE);
		buttonsGLay.setComponentAlignment(loadPcB, Alignment.MIDDLE_CENTER);
		buttonsGLay.addComponent(loadWebB, 1, 1);
		loadWebB.setWidth(80, Unit.PERCENTAGE);
		buttonsGLay.setComponentAlignment(loadWebB, Alignment.MIDDLE_CENTER);
		buttonsGLay.addComponent(genIcalB, 0, 2);
		genIcalB.setWidth(80, Unit.PERCENTAGE);
		buttonsGLay.setComponentAlignment(genIcalB, Alignment.MIDDLE_CENTER);
		buttonsGLay.addComponent(genCsvB, 1, 2);
		genCsvB.setWidth(80, Unit.PERCENTAGE);
		buttonsGLay.setComponentAlignment(genCsvB, Alignment.MIDDLE_CENTER);

	}

	/**
	 * Klasa która konwertuje dane z tabelki do formatu iCal i tworzy strumieñ
	 * wejœciowy.
	 * 
	 * @author Krzysztof Perchlicki
	 *
	 */
	class IcalGenerator implements StreamSource {

		/**
		 * Tablica przechowuj¹ca referencje do wszystkich wydarzeñ (CalendarEvent).
		 */
		private final BeanItemContainer<CalendarEvent> eventsContainer;

		/**
		 * Konstruktor przyjmuj¹cy eventsContainer.
		 * 
		 * @param eventsContainer
		 */
		public IcalGenerator(final BeanItemContainer<CalendarEvent> eventsContainer) {
			this.eventsContainer = eventsContainer;

		}

		/**
		 * Metoda która konwertuje dane z tabeli do formatu iCal i tworzy
		 * strumieñ wejœciowy.
		 */
		@Override
		public InputStream getStream() {
			final StringBuilder icalFile = new StringBuilder(200);
			final Date dateStamp = new Date();
			icalFile.append("BEGIN:VCALENDAR\nPRODID:IOWEBAPP_PROJECT_TEAM");
			for (int i = 0; i < eventsContainer.size(); i++) {
				final CalendarEvent event = eventsContainer.getItem(eventsContainer.getIdByIndex(i)).getBean();
				icalFile.append("\nBEGIN:VEVENT\nDTSTART");
				icalFile.append(dateConvert(event.getDateStart(), event.isAllDay()));
				icalFile.append("\nDTEND");
				icalFile.append(dateConvert(event.getDateEnd(), event.isAllDay()));
				icalFile.append("\nDTSTAMP");
				icalFile.append(dateConvert(dateStamp, false));
				icalFile.append("\nCREATED");
				icalFile.append(dateConvert(event.getDateCreated(), false));
				icalFile.append("\nDESCRIPTION:");
				icalFile.append(event.getDescription());
				icalFile.append("\nLAST-MODIFIED");
				icalFile.append(dateConvert(event.getDateModified(), false));
				icalFile.append("\nLOCATION:");
				icalFile.append(event.getLocation());
				icalFile.append("\nSUMMARY:");
				icalFile.append(event.getTitle());
				icalFile.append("\nEND:VEVENT");

			}
			icalFile.append("\nEND:VCALENDAR");
			return new ByteArrayInputStream(icalFile.toString().getBytes(StandardCharsets.UTF_8));
		}

		/**
		 * Konwertuje daty na zgodne z formatem zapisu w plikach iCal.
		 * 
		 * @param date
		 * @param checked
		 * @return dataIcal
		 */
		@SuppressWarnings("deprecation")
		String dateConvert(final Date date, final boolean checked) {
			String dataIcal;
			final int year = date.getYear() + 1900;
			final int month = date.getMonth() + 1;
			final int day = date.getDate();
			final int hours = date.getHours();
			final int minutes = date.getMinutes();
			final int seconds = date.getSeconds();
			if (!checked) {
				dataIcal = String.format(":%d%02d%02dT%02d%02d%02d", year, month, day, hours, minutes, seconds);
			}
			dataIcal = String.format(";VALUE=DATE:%d%02d%02d", year, month, day);
			return dataIcal;
		}

	}

	StreamResource createResource(final BeanItemContainer<CalendarEvent> eventsContainer) {
		return new StreamResource(new IcalGenerator(eventsContainer), "plik.ICS");
	}

}
