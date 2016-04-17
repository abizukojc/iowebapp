package iowebapp;

import java.util.Locale;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class NewEventWindow extends Window {

	private static final long serialVersionUID = 1L;

	IowebappUI UI;
	GridLayout main;
	Label napis1, napis2, napis3, napis4, napis5;
	TextField pole1, pole4;
	TextArea pole5;
	DateField pole2;
	DateField pole3;
	GridLayout vLayout;
	Button addButt;

	NewEventWindow(IowebappUI UI) {
		super("New Event");
		this.UI = UI;
		// vLayout settings
		vLayout = new GridLayout(1,2);
		vLayout.setSizeFull();
		// window settings
		setWidth("400px");
		setHeight("500px");
		center();
		setResizable(false);
		setContent(vLayout);
		// main layout settings
		main = new GridLayout(2, 5);
		main.setSizeFull();
		main.setMargin(new MarginInfo(false, true, false, true));
		// napis1 settings
		napis1 = new Label("Title");
		napis1.setWidth(null);
		main.addComponent(napis1, 0, 0);
		main.setComponentAlignment(napis1, Alignment.MIDDLE_LEFT);
		// napis2 settings
		napis2 = new Label("Start");
		napis2.setWidth(null);
		main.addComponent(napis2, 0, 1);
		main.setComponentAlignment(napis2, Alignment.MIDDLE_LEFT);
		// napis3 settings
		napis3 = new Label("End");
		napis3.setWidth(null);
		main.addComponent(napis3, 0, 2);
		main.setComponentAlignment(napis3, Alignment.MIDDLE_LEFT);
		// napis4 settings
		napis4 = new Label("Location");
		napis4.setWidth(null);
		main.addComponent(napis4, 0, 3);
		main.setComponentAlignment(napis4, Alignment.MIDDLE_LEFT);
		// napis7 settings
		napis5 = new Label("Description");
		napis5.setWidth(null);
		main.addComponent(napis5, 0, 4);
		main.setComponentAlignment(napis5, Alignment.MIDDLE_LEFT);
		// pole1 settings
		pole1 = new TextField();
		main.addComponent(pole1, 1, 0);
		main.setComponentAlignment(pole1, Alignment.MIDDLE_CENTER);
		// pole2 settings
		pole2 = new DateField();
		pole2.setResolution(Resolution.MINUTE);
		pole2.setLocale(Locale.ENGLISH);
		pole2.setWidth("186px");
		main.addComponent(pole2, 1, 1);
		main.setComponentAlignment(pole2, Alignment.MIDDLE_CENTER);
		// pole3 settings
		pole3 = new DateField();
		pole3.setResolution(Resolution.MINUTE);
		pole3.setLocale(Locale.ENGLISH);
		pole3.setWidth("186px");
		main.addComponent(pole3, 1, 2);
		main.setComponentAlignment(pole3, Alignment.MIDDLE_CENTER);
		// pole4 settings
		pole4 = new TextField();
		main.addComponent(pole4, 1, 3);
		main.setComponentAlignment(pole4, Alignment.MIDDLE_CENTER);
		// pole5 settings
		pole5 = new TextArea();
		main.addComponent(pole5, 1, 4);
		main.setComponentAlignment(pole5, Alignment.MIDDLE_CENTER);
		vLayout.addComponent(main,0,0);
		main.setColumnExpandRatio(0, 1);
		main.setColumnExpandRatio(1, 3);
		// addButt settings
		addButt= new Button("Add event");
		vLayout.addComponent(addButt,0,1);
		vLayout.setComponentAlignment(addButt, Alignment.MIDDLE_CENTER);
		vLayout.setRowExpandRatio(0,4);
		vLayout.setRowExpandRatio(1,1);
	}

}
