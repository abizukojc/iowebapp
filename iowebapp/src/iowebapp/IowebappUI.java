package iowebapp;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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
	
	/* Wyjaœnienie nazwewnictwa:
	 * Przyjêta konwencja jest taka, ¿e 1 cz³on oznacza zastosowanie/znaczenie
	 * zmiennej a 2 cz³on typ obiektu.
	 * GLay - Grid Layout | DF - DateField
	 * HLay - Horizontal Layout | VLay - Vertical Layout
	 * G - Grid | L - Label | TA - TextArea | TF - TextField 
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
			addWindow(new NewEventWindow(userInterface));

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
		final Panel mainPanel = new Panel("EVENT GENERATOR");
		mainPanel.setWidth(80, Unit.PERCENTAGE);
		mainPanel.setHeight(43, Unit.PERCENTAGE);
		setContent(mainPanel);

		// mainHLay settings
		final HorizontalLayout mainHLay = new HorizontalLayout();
		mainHLay.setSizeFull();
		mainPanel.setContent(mainHLay);

		// eventG settings
		final Grid eventG = new Grid();
		eventG.addColumn("", String.class);
		eventG.addColumn("Date start", String.class);
		eventG.addColumn("Date end", String.class);
		eventG.addColumn("Title", String.class);
		eventG.addColumn("Options", String.class);
		eventG.setSizeFull();
		mainHLay.addComponent(eventG);

		// buttonsGLay settings
		final GridLayout buttonsGLay = new GridLayout(2, 3);
		mainHLay.addComponent(buttonsGLay);
		buttonsGLay.setSizeFull();

		// expand and alignment settings of eventG and buttonsGLay
		mainHLay.setExpandRatio(buttonsGLay, 1);
		mainHLay.setExpandRatio(eventG, 2);
		mainHLay.setComponentAlignment(eventG, Alignment.TOP_RIGHT);
		mainHLay.setComponentAlignment(buttonsGLay, Alignment.TOP_LEFT);

		// newEventB settings
		final Button newEventB = new Button("NEW EVENT");
		newEventB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		newEventB.setImmediate(true);
		newEventB.setHeight(70, Unit.PERCENTAGE);
		newEventB.setDescription("Click to create new event!");
		newEventB.addClickListener(new NewEventListener(this));

		// deleteAllB settings
		final Button deleteAllB = new Button("DELETE ALL");
		deleteAllB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		deleteAllB.setImmediate(true);
		deleteAllB.setDescription("Click to delete all your events!");
		deleteAllB.setHeight(70, Unit.PERCENTAGE);

		// loadPcB settings
		final Button loadPcB = new Button("LOAD FROM PC");
		loadPcB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		loadPcB.setDescription("Click to load your events from your computer!");
		loadPcB.setImmediate(true);
		loadPcB.setHeight(70, Unit.PERCENTAGE);

		// loadWebB settings
		final Button loadWebB = new Button("LOAD FROM WEB");
		loadWebB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		loadWebB.setDescription("Click to load your events from a website!");
		loadWebB.setImmediate(true);
		loadWebB.setHeight(70, Unit.PERCENTAGE);

		// genIcalB settings
		final Button genIcalB = new Button("GENERATE ICAL");
		genIcalB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		genIcalB.setDescription("Click to export your events to iCal format.");
		genIcalB.setImmediate(true);
		genIcalB.setHeight(70, Unit.PERCENTAGE);

		// genCsvB settings
		final Button genCsvB = new Button("GENERATE CSV");
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
}
