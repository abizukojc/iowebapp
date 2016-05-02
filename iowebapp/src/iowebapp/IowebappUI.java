package iowebapp;

import java.util.Locale;
import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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

	BeanItemContainer<CalendarEvent> eventContainer;
	/**
	 * G³ówny panel w którym znajduj¹ siê inne komponenty servletu. Wyœwietla
	 * nag³ówek aplikacji "EVENT GENERATOR" i w przypadku gdy komponenty siê nie
	 * mieszcz¹ tworzy paski scrollowania.
	 */
	private Panel mainPanel;
	/**
	 * Horizontal Layout na którym mieœci siê buttonsGLay (siatka przycisków) a
	 * tak¿e eventG (tabela z wydarzeniami). S³u¿y do odpowiedniego
	 * pozycjonowania tych komponentów.
	 */
	private HorizontalLayout mainHLay;
	/**
	 * Grid czyli tablica w której znajduj¹ siê nasze dodane wydarzenia.
	 * Umo¿liwia tak¿e duplikacjê, edycjê i usuniêcie zdarzenia.
	 */
	private Grid eventG;
	/**
	 * Grid layout na którym znajduj¹ siê wszystkie przyciski. Layout s³u¿y do
	 * ich pozycjonowania.
	 */
	private GridLayout buttonsGLay;
	/**
	 * Przycisk dziêki któremu mo¿na utworzyæ wydarzenie.
	 */
	private Button newEventB;
	/**
	 * Przycisk dziêki któremu mo¿na usun¹æ wszystkie wydarzenia.
	 */
	private Button deleteAllB;
	/**
	 * Przycisk dziêki któremu mo¿na wczytaæ wydarzenia z komputera.
	 */
	private Button loadPcB;
	/**
	 * Przycisk dziêki któremu mo¿na wczytaæ wydarzenia z internetu.
	 */
	private Button loadWebB;
	/**
	 * Przycisk dziêki któremu mo¿na eksportowaæ wydarzenia do pliku CSV.
	 */
	private Button genCsvB;
	/**
	 * Przycisk dziêki któremu mo¿na eksportowaæ wydarzenia do pliku iCAL.
	 */
	private Button genIcalB;

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
	 * Klasa wewnêtrzna implementuj¹ca interfejs ClickListener dla przycisku
	 * newEventB. Tworzy obiekt klasy NewEventWindow.
	 * 
	 * @author Krzysztof Perchlicki
	 */
	// newEventB click listener class
	class NewEventListener implements ClickListener {

		/**
		 * Referencja do interfejsu u¿ytkownika (g³ównego servletu). Zostaje
		 * u¿yta do utworzenia obiektu klasy NewEventWindow
		 */
		private final IowebappUI userInterface;

		NewEventListener(final IowebappUI userInterface) {
			this.userInterface = userInterface;
		}

		/**
		 * Nadpisana metoda buttonClick przyjmuj¹ca jako parametr obiekt klasy
		 * ClickEvent po kliknieciu tworzy obiekt klasy NevEventWindow i
		 * przekazuje do konstruktora obiekt klasy IowebappUI.
		 */
		@Override
		public void buttonClick(final ClickEvent event) {
			addWindow(new NewEventWindow(eventContainer, eventG, null));

		}

	}

	/**
	 * Tworzy UI (User Interface) u¿ytkownika po otrzymaniu od niego requestu.
	 * 
	 * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
	 */
	@Override
	protected void init(final VaadinRequest request) {

		// mainPanel settings
		mainPanel = new Panel("EVENT GENERATOR");
		mainPanel.setWidth(80, Unit.PERCENTAGE);
		mainPanel.setHeight(43, Unit.PERCENTAGE);
		setContent(mainPanel);

		// mainHLay settings
		mainHLay = new HorizontalLayout();
		mainHLay.setSizeFull();
		mainPanel.setContent(mainHLay);

		// buttonsGLay settings
		buttonsGLay = new GridLayout(2, 3);
		mainHLay.addComponent(buttonsGLay);
		buttonsGLay.setSizeFull();

		eventContainer = new BeanItemContainer<>(CalendarEvent.class);
		eventG = new Grid();
		eventG.setContainerDataSource(eventContainer);
		eventG.setLocale(Locale.GERMAN);
		DetailsGenerator detailsGenerator = new DetailsGenerator() {

			@Override
			public Component getDetails(RowReference rowReference) {
				CalendarEvent eventItem = (CalendarEvent) rowReference.getItemId();
				Panel panel = new Panel();
				Label descriptionL = new Label(eventItem.getDescription());
				descriptionL.setWidth(100, Unit.PIXELS);
				eventG.setHeightByRows(eventContainer.getItemIds().size() + 4);
				Button deleteB = new Button("Delete");
				deleteB.setIcon(FontAwesome.CLOSE);
				deleteB.addClickListener(new ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						eventContainer.removeItem(rowReference.getItemId());

					}
				});
				Button copyB = new Button("Copy");
				copyB.setIcon(FontAwesome.CLONE);
				copyB.addClickListener(new ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						eventContainer.addBean(new CalendarEvent(eventItem.getTitle(), eventItem.getDateStart(),
								eventItem.getDateEnd(), eventItem.getLocation(), eventItem.getDescription(),
								eventItem.isAllDay()));

					}
				});
				Button editB = new Button("Edit");
				editB.setIcon(FontAwesome.EDIT);
				editB.addClickListener(new ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						addWindow(new NewEventWindow(eventContainer, eventG, eventItem));

					}
				});
				HorizontalLayout horizontalLay = new HorizontalLayout(deleteB, copyB, editB);
				horizontalLay.setWidth(350, Unit.PIXELS);
				VerticalLayout verticalLay = new VerticalLayout(descriptionL, horizontalLay);
				verticalLay.setStyleName("descMargins");
				verticalLay.setMargin(true);
				// gridLay.setSizeFull();
				// gridLay.addComponent(descriptionL, 0, 0);
				// gridLay.addComponent(horizontalLay, 1, 0);
				// gridLay.setColumnExpandRatio(0, 1);
				// gridLay.setColumnExpandRatio(1, 5);
				// gridLay.setMargin(true);

				// rowDetailsVLay.setSpacing(true);
				// rowDetailsVLay.addComponent(descriptionL);
				// rowDetailsVLay.addComponent(horizontalLay);
				// rowDetailsVLay.setMargin(true);
				// rowDetailsVLay.setSpacing(true);
				return verticalLay;
			}

		};

		eventG.setDetailsGenerator(detailsGenerator);
		eventG.addItemClickListener(new ItemClickListener() {

			@Override
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick()) {
					Object itemId = event.getItemId();
					eventG.setDetailsVisible(itemId, !eventG.isDetailsVisible(itemId));
				}
			}

		});

		eventG.setEditorEnabled(false);
		eventG.getColumn("description").setHidden(true);
		eventG.getColumn("allDay").setConverter(new BooleanToFontIconConverter());
		eventG.getColumn("allDay").setRenderer(new HtmlRenderer());
		eventG.getColumn("allDay").setMaximumWidth(65);
		eventG.getColumn("allDay").setResizable(false);
		eventG.setColumnOrder("title", "allDay", "dateStart", "dateEnd", "location");
		eventG.setSizeFull();
		mainHLay.addComponent(eventG);

		// expand and alignment settings of eventG and buttonsGLay
		mainHLay.setExpandRatio(buttonsGLay, 1);
		mainHLay.setExpandRatio(eventG, 2);
		mainHLay.setComponentAlignment(eventG, Alignment.TOP_RIGHT);
		mainHLay.setComponentAlignment(buttonsGLay, Alignment.TOP_LEFT);

		// newEventB settings
		newEventB = new Button("NEW EVENT");
		newEventB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		newEventB.setImmediate(true);
		newEventB.setHeight(70, Unit.PERCENTAGE);
		newEventB.setDescription("Click to create new event!");
		newEventB.addClickListener(new NewEventListener(this));

		// deleteAllB settings
		deleteAllB = new Button("DELETE ALL");
		deleteAllB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		deleteAllB.setImmediate(true);
		deleteAllB.setDescription("Click to delete all your events!");
		deleteAllB.setHeight(70, Unit.PERCENTAGE);
		deleteAllB.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				addWindow(new DeleteAllWindow(eventContainer));

			}
		});

		// loadPcB settings
		loadPcB = new Button("LOAD FROM PC");
		loadPcB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		loadPcB.setDescription("Click to load your events from your computer!");
		loadPcB.setImmediate(true);
		loadPcB.setHeight(70, Unit.PERCENTAGE);

		// loadWebB settings
		loadWebB = new Button("LOAD FROM WEB");
		loadWebB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		loadWebB.setDescription("Click to load your events from a website!");
		loadWebB.setImmediate(true);
		loadWebB.setHeight(70, Unit.PERCENTAGE);

		// genIcalB settings
		genIcalB = new Button("GENERATE ICAL");
		genIcalB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		genIcalB.setDescription("Click to export your events to iCal format.");
		genIcalB.setImmediate(true);
		genIcalB.setHeight(70, Unit.PERCENTAGE);

		// genCsvB settings
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
	 * Metoda, która dajê dostêp do pola mainPanel.
	 * 
	 * @return referencja do mainPanel
	 */
	public Panel getMainPanel() {
		return mainPanel;
	}

	/**
	 * Metoda, która pozwala zmieniæ mainPanel.
	 * 
	 * @param mainPanel
	 *            obiekt klasy Panel (g³ówny panel).
	 */
	public void setMainPanel(final Panel mainPanel) {
		this.mainPanel = mainPanel;
	}

	/**
	 * Metoda, która dajê dostêp do pola mainHLay.
	 * 
	 * @return referencja do mainHLay
	 */
	public HorizontalLayout getMainHLay() {
		return mainHLay;
	}

	/**
	 * Metoda, która pozwala zmieniæ mainHLay.
	 * 
	 * @param mainHLay
	 *            obiekt klasy HorizontalLayout (layout dla przycisków i
	 *            tabeli).
	 */
	public void setMainHLay(final HorizontalLayout mainHLay) {
		this.mainHLay = mainHLay;
	}

	/**
	 * Metoda, która dajê dostêp do pola eventG.
	 * 
	 * @return referencja do eventG
	 */
	public Grid getEventG() {
		return eventG;
	}

	/**
	 * Metoda, która pozwala zmieniæ eventG.
	 * 
	 * @param eventG
	 *            obiekt klasy Grid (tabela dla zdarzeñ).
	 */
	public void setEventG(final Grid eventG) {
		this.eventG = eventG;
	}

	/**
	 * Metoda, która dajê dostêp do pola buttonsGLay.
	 * 
	 * @return referencja do buttonsGLay
	 */
	public GridLayout getButtonsGLay() {
		return buttonsGLay;
	}

	/**
	 * Metoda, która pozwala zmieniæ buttonsGLay.
	 * 
	 * @param buttonsGLay
	 *            obiekt klasy GridLayout (layout dla przycisków).
	 */
	public void setButtonsGLay(final GridLayout buttonsGLay) {
		this.buttonsGLay = buttonsGLay;
	}

	/**
	 * Metoda, która dajê dostêp do pola newEventB.
	 * 
	 * @return referencja do newEventB
	 */
	public Button getNewEventB() {
		return newEventB;
	}

	/**
	 * Metoda, która pozwala zmieniæ newEventB.
	 * 
	 * @param newEventB
	 *            obiekt klasy Button (przycisk nowego zdarzenia).
	 */
	public void setNewEventB(final Button newEventB) {
		this.newEventB = newEventB;
	}

	/**
	 * Metoda, która dajê dostêp do pola deleteAllB.
	 * 
	 * @return referencja do deleteAllB
	 */
	public Button getDeleteAllB() {
		return deleteAllB;
	}

	/**
	 * Metoda, która pozwala zmieniæ deleteAllB.
	 * 
	 * @param deleteAllB
	 *            obiekt klasy Button (przycisk usuwania zdarzeñ).
	 */
	public void setDeleteAllB(final Button deleteAllB) {
		this.deleteAllB = deleteAllB;
	}

	/**
	 * Metoda, która dajê dostêp do pola loadPcB.
	 * 
	 * @return referencja do loadPcB
	 */
	public Button getLoadPcB() {
		return loadPcB;
	}

	/**
	 * Metoda, która pozwala zmieniæ loadPcB
	 * 
	 * @param loadPcB
	 *            obiekt klasy Button (przycisk wczytywania z PC).
	 */
	public void setLoadPcB(final Button loadPcB) {
		this.loadPcB = loadPcB;
	}

	/**
	 * Metoda, która dajê dostêp do pola loadWebB.
	 * 
	 * @return referencja do loadWebB
	 */
	public Button getLoadWebB() {
		return loadWebB;
	}

	/**
	 * Metoda, która pozwala zmieniæ loadWebB
	 * 
	 * @param loadWebB
	 *            obiekt klasy Button (przycisk wczytywania ze stron).
	 */
	public void setLoadWebB(final Button loadWebB) {
		this.loadWebB = loadWebB;
	}

	/**
	 * Metoda, która dajê dostêp do pola genCsvB.
	 * 
	 * @return referencja do genCsvB
	 */
	public Button getGenCsvB() {
		return genCsvB;
	}

	/**
	 * Metoda, która pozwala zmieniæ genCsvB
	 * 
	 * @param genCsvB
	 *            obiekt klasy Button (przycisk eksportu do CSV).
	 */
	public void setGenCsvB(final Button genCsvB) {
		this.genCsvB = genCsvB;
	}

	/**
	 * Metoda, która dajê dostêp do pola genIcalB.
	 * 
	 * @return referencja do genIcalB
	 */
	public Button getGenIcalB() {
		return genIcalB;
	}

	/**
	 * Metoda, która pozwala zmieniæ genIcalB
	 * 
	 * @param genIcalB
	 *            obiekt klasy Button (przycisk eksportu do ICAL).
	 */
	public void setGenIcalB(final Button genIcalB) {
		this.genIcalB = genIcalB;
	}
}
