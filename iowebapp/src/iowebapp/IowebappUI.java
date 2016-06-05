package iowebapp;

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
import com.vaadin.server.Page;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.Position;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.DetailsGenerator;
import com.vaadin.ui.Grid.RowReference;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Główny servlet. Po otrzymaniu od użytkownika requestu tworzy UI (User
 * Interface).
 * 
 * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
 * @author Krzysztof Perchlicki
 */
@SuppressWarnings("serial")
@Theme("iowebapp")
public class IowebappUI extends UI {

	/*
	 * Wyjaśnienie nazwewnictwa: Przyjęta konwencja jest taka, że 1 człon
	 * oznacza zastosowanie/znaczenie zmiennej a 2 człon typ obiektu. GLay -
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
	 * Konstruktor z nadklasy (klasa UI) przyjmujący jako parametr obiekt klasy
	 * Component.
	 * 
	 * @param content
	 *            referencja do obiektu klasy Component (używany jako kontent
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
	 * Tworzy UI (User Interface) użytkownika po otrzymaniu od niego requestu.
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
		buttonsGLay.setRowExpandRatio(0, 1);
		buttonsGLay.setRowExpandRatio(1, 1);
		buttonsGLay.setRowExpandRatio(2, 1);
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
			 * Metoda wyświetla opis wydarzenia, a także przyciski służące do
			 * usunięcia, edycji i duplikowania wydarzenia.
			 */
			@Override
			public Component getDetails(final RowReference rowReference) {
				final CalendarEvent eventItem = (CalendarEvent) rowReference.getItemId();
				Label descriptionL;
				descriptionL = new Label(eventItem.getDescription());
				descriptionL.setWidth(600, Unit.PIXELS);
				final Button deleteB = new Button("Delete");
				deleteB.setIcon(FontAwesome.CLOSE);
				deleteB.addClickListener(new ClickListener() {

					/**
					 * Usuwa wydarzenie, pod którym klikamy przycisk "Delete".
					 */
					@Override
					public void buttonClick(final ClickEvent event) {
						final Notification editSuccess = new Notification("Successfully deleted event!",
								Notification.Type.ASSISTIVE_NOTIFICATION);
						editSuccess.setPosition(Position.TOP_CENTER);
						editSuccess.setDelayMsec(1000);
						editSuccess.show(Page.getCurrent());
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
						final Notification editSuccess = new Notification("Successfully copied event!",
								Notification.Type.ASSISTIVE_NOTIFICATION);
						editSuccess.setPosition(Position.TOP_CENTER);
						editSuccess.setDelayMsec(1000);
						editSuccess.show(Page.getCurrent());
						eventsContainer.addBean(new CalendarEvent(eventItem.getTitle(), eventItem.getDateStart(),
								new Date(), new Date(), eventItem.getDateEnd(), eventItem.getLocation(),
								eventItem.getDescription(), eventItem.isAllDay()));

					}
				});
				final Button editB = new Button("Edit");
				editB.setIcon(FontAwesome.EDIT);
				editB.addClickListener(new ClickListener() {

					/**
					 * Daje możliwość edycji wydarzenia, pod którym klikamy
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
			 * W przypadku gdy użytkownik w wydarzenie kliknie dwa razy metoda
			 * powoduje zmianę widoczności szczegułów.
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
			 * Metoda tworzy okno w którym użytkownik wypełnia pola aby utworzyć
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
			 * Metoda tworzy okno w którym u`zytknownik podejmuje decyzję czy na
			 * pewno chce usunąć wszystkie wydarzenia (obiekt klasy
			 * DeleteAllWindow).
			 */
			@Override
			public void buttonClick(final ClickEvent event) {
				final Notification deleteError = new Notification("There're no events!",
						Notification.Type.ERROR_MESSAGE);
				deleteError.setPosition(Position.TOP_CENTER);
				deleteError.setDelayMsec(1000);
				if (eventsContainer.size() >= Integer.valueOf(1)) {
					addWindow(new DeleteAllWindow(eventsContainer));
					return;
				}
				deleteError.show(Page.getCurrent());
			}
		});


		final Button loadPcB = new Button("LOAD FROM PC");
		loadPcB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		loadPcB.setDescription("Click to load your events from your computer!");
		loadPcB.setHeight(70, Unit.PERCENTAGE);
		loadPcB.setImmediate(true);
		loadPcB.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				addWindow(new UploadWindow(eventsContainer));

			}

		});

		// loadWebB settings
		Button loadWebB;
		loadWebB = new Button("LOAD FROM WEB");
		loadWebB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		loadWebB.setDescription("Click to load your events from a website!");
		loadWebB.setImmediate(true);
		loadWebB.setHeight(70, Unit.PERCENTAGE);
		loadWebB.addClickListener(new ClickListener() {

			/**
			 * Metoda tworzy okno w którym użytkownik podaje link url.
			 */
			@Override
			public void buttonClick(final ClickEvent event) {
				addWindow(new LoadFromWebWindow(eventsContainer));
			}
		});

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

	private StreamResource createResource(final BeanItemContainer<CalendarEvent> eventsContainer) {
		return new StreamResource(new IcalGenerator(eventsContainer), "plik.ICS");
	}

}
