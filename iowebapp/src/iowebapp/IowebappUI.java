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

@SuppressWarnings("serial")
@Theme("iowebapp")
public class IowebappUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = IowebappUI.class)
	public static class Servlet extends VaadinServlet {
	}

	/**
	 * Tworzy GUI u¿ytkownika po otrzymaniu od niego requestu.
	 *  (non-Javadoc)
	 * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
	 */
	@Override
	protected void init(VaadinRequest request) {

		// mainPanel settings
		Panel mainPanel = new Panel("EVENT GENERATOR");
		mainPanel.setWidth("80%");
		mainPanel.setHeight("43%");
		setContent(mainPanel);

		// mainHLay settings
		HorizontalLayout mainHLay = new HorizontalLayout();
		mainHLay.setSizeFull();
		mainPanel.setContent(mainHLay);

		// buttonsGLay settings
		GridLayout buttonsGLay = new GridLayout(2, 3);
		mainHLay.addComponent(buttonsGLay);
		mainHLay.setComponentAlignment(buttonsGLay, Alignment.TOP_LEFT);
		buttonsGLay.setSizeFull();

		// eventG settings
		Grid eventG = new Grid();
		eventG.addColumn("", String.class);
		eventG.addColumn("Date start", String.class);
		eventG.addColumn("Date end", String.class);
		eventG.addColumn("Title", String.class);
		eventG.addColumn("Options", String.class);
		eventG.setSizeFull();
		mainHLay.addComponent(eventG);

		// expand and alignment settings of eventG and buttonsGLay
		mainHLay.setExpandRatio(buttonsGLay, 1);
		mainHLay.setExpandRatio(eventG, 2);
		mainHLay.setComponentAlignment(eventG, Alignment.TOP_RIGHT);
		mainHLay.setComponentAlignment(buttonsGLay, Alignment.TOP_LEFT);

		/**
		 * Klasa wewnêtrzna implementuj¹ca interfejs ClickListener dla przycisku newEventB.
		 * Tworzy obiekt klasy NewEventWindow.
		 * 
		 * @author Krzysztof Perchlicki
		 */
		// newEventB click listener class
		class NewEventListener implements ClickListener {

			IowebappUI UI;

			NewEventListener(IowebappUI UI) {
				this.UI = UI;
			}

			/**
			 * Nadpisana metoda buttonClick przyjmuj¹ca jako parametr obiekt klasy ClickEvent po kliknieciu 
			 * tworzy obiekt klasy NevEventWindow i przekazuje do konstruktora obiekt klasy IowebappUI.
			 * (non-Javadoc)
			 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
			 */
			@Override
			public void buttonClick(ClickEvent event) {
				addWindow(new NewEventWindow(UI));

			}

		}

		// newEventB settings
		Button newEventB = new Button("NEW EVENT");
		newEventB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		newEventB.setImmediate(true);
		newEventB.setHeight(70, Unit.PERCENTAGE);
		newEventB.setDescription("Click to create new event!");
		newEventB.addClickListener(new NewEventListener(this));

		// deleteAllB settings
		Button deleteAllB = new Button("DELETE ALL");
		deleteAllB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		deleteAllB.setImmediate(true);
		deleteAllB.setDescription("Click to delete all your events!");
		deleteAllB.setHeight(70, Unit.PERCENTAGE);

		// loadPcB settings
		Button loadPcB = new Button("LOAD FROM PC");
		loadPcB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		loadPcB.setDescription("Click to load your events from your computer!");
		loadPcB.setImmediate(true);
		loadPcB.setHeight(70, Unit.PERCENTAGE);

		// loadWebB settings
		Button loadWebB = new Button("LOAD FROM WEB");
		loadWebB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		loadWebB.setDescription("Click to load your events from a website!");
		loadWebB.setImmediate(true);
		loadWebB.setHeight(70, Unit.PERCENTAGE);

		// genIcalB settings
		Button genIcalB = new Button("GENERATE ICAL");
		genIcalB.setStyleName(ValoTheme.BUTTON_SMALL, true);
		genIcalB.setDescription("Click to export your events to iCal format.");
		genIcalB.setImmediate(true);
		genIcalB.setHeight(70, Unit.PERCENTAGE);

		// genCsvB settings
		Button genCsvB = new Button("GENERATE CSV");
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
