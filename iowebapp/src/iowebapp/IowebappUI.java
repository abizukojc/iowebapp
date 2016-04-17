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

	@Override
	protected void init(VaadinRequest request) {

		Panel panel = new Panel("EVENT GENERATOR");
		panel.setWidth("80%");
		panel.setHeight("43%");
		setContent(panel);

		HorizontalLayout main = new HorizontalLayout();
		main.setSizeFull();
		panel.setContent(main);

		// Left panel settings
		GridLayout leftPanel = new GridLayout(2, 3);
		main.addComponent(leftPanel);
		main.setComponentAlignment(leftPanel, Alignment.TOP_LEFT);
		leftPanel.setSizeFull();

		// Right panel settings
		Grid rightPanel = new Grid();
		rightPanel.addColumn("", String.class);
		rightPanel.addColumn("Date", String.class);
		rightPanel.addColumn("Date end", String.class);
		rightPanel.addColumn("Title", String.class);
		rightPanel.addColumn("Options", String.class);
		rightPanel.setSizeFull();
		main.addComponent(rightPanel);
		main.setExpandRatio(rightPanel, 2);
		main.setExpandRatio(leftPanel, 1);
		main.setComponentAlignment(rightPanel, Alignment.TOP_RIGHT);

		// New event click listener class
		class NewEventListener implements ClickListener {

			IowebappUI UI;

			NewEventListener(IowebappUI UI) {
				this.UI = UI;
			}

			@Override
			public void buttonClick(ClickEvent event) {
				addWindow(new NewEventWindow(UI));

			}

		}

		// Button1 settings
		Button button1 = new Button("NEW EVENT");
		button1.setStyleName(ValoTheme.BUTTON_SMALL, true);
		button1.setImmediate(true);
		button1.setHeight(70, Unit.PERCENTAGE);
		button1.setDescription("Click to create new event!");
		button1.addClickListener(new NewEventListener(this));

		// Button2 settings
		Button button2 = new Button("DELETE ALL");
		button2.setStyleName(ValoTheme.BUTTON_SMALL, true);
		button2.setImmediate(true);
		button2.setDescription("Click to delete all your events!");
		button2.setHeight(70, Unit.PERCENTAGE);
		// Button3 settings
		Button button3 = new Button("LOAD FROM PC");
		button3.setStyleName(ValoTheme.BUTTON_SMALL, true);
		button3.setDescription("Click to load your events from your computer!");
		button3.setImmediate(true);
		button3.setHeight(70, Unit.PERCENTAGE);
		// Button4 settings
		Button button4 = new Button("LOAD FROM WEB");
		button4.setStyleName(ValoTheme.BUTTON_SMALL, true);
		button4.setDescription("Click to load your events from a website!");
		button4.setImmediate(true);
		button4.setHeight(70, Unit.PERCENTAGE);
		// Button5 settings
		Button button5 = new Button("GENERATE ICAL");
		button5.setStyleName(ValoTheme.BUTTON_SMALL, true);
		button5.setDescription("Click to export your events to iCal format.");
		button5.setImmediate(true);
		button5.setHeight(70, Unit.PERCENTAGE);
		// Button6 settings
		Button button6 = new Button("GENERATE CSV");
		button6.setStyleName(ValoTheme.BUTTON_SMALL, true);
		button6.setDescription("Click to export your events to CSV format.");
		button6.setImmediate(true);
		button6.setHeight(70, Unit.PERCENTAGE);

		leftPanel.addComponent(button1, 0, 0);
		button1.setWidth(80, Unit.PERCENTAGE);
		leftPanel.setComponentAlignment(button1, Alignment.MIDDLE_CENTER);
		leftPanel.addComponent(button2, 1, 0);
		button2.setWidth(80, Unit.PERCENTAGE);
		leftPanel.setComponentAlignment(button2, Alignment.MIDDLE_CENTER);
		leftPanel.addComponent(button3, 0, 1);
		button3.setWidth(80, Unit.PERCENTAGE);
		leftPanel.setComponentAlignment(button3, Alignment.MIDDLE_CENTER);
		leftPanel.addComponent(button4, 1, 1);
		button4.setWidth(80, Unit.PERCENTAGE);
		leftPanel.setComponentAlignment(button4, Alignment.MIDDLE_CENTER);
		leftPanel.addComponent(button5, 0, 2);
		button5.setWidth(80, Unit.PERCENTAGE);
		leftPanel.setComponentAlignment(button5, Alignment.MIDDLE_CENTER);
		leftPanel.addComponent(button6, 1, 2);
		button6.setWidth(80, Unit.PERCENTAGE);
		leftPanel.setComponentAlignment(button6, Alignment.MIDDLE_CENTER);

		main.setComponentAlignment(leftPanel, Alignment.TOP_LEFT);
		main.setExpandRatio(leftPanel, 1);
		main.setExpandRatio(rightPanel, 2);

	}
}
