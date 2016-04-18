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

/**
 *Klasa NewEventWindow dziedzicz¹ca po klasie Window. Wyœwietla okno w którym u¿ytkownik podaje
 *tytu³, datê startu, datê koñca, lokalizacjê oraz opis wydarzenia.
 *
 *@author Krzysztof Perchlicki
 */
public class NewEventWindow extends Window {

	private static final long serialVersionUID = 1L;

	IowebappUI UI;
	GridLayout eventGLay;
	Label titleL, dateStartL, dateEndL, locationL, descriptionL;
	TextField titleTF, locationTF;
	TextArea descriptionTA;
	DateField dateStartDF;
	DateField dateEndDF;
	GridLayout mainGLay;
	Button addB;

	/**
	 * @param UI
	 * Konstruktor klasy NewEventWindow, który przyjmujê referencjê do klasy IowebappUI.
	 */
	NewEventWindow(IowebappUI UI) {
		super("New Event");
		this.UI = UI;
		
		// mainLayout settings
		mainGLay = new GridLayout(1, 2);
		mainGLay.setSizeFull();
		
		// window settings
		setWidth("400px");
		setHeight("500px");
		center();
		setResizable(false);
		setContent(mainGLay);
		
		// main layout settings
		eventGLay = new GridLayout(2, 5);
		eventGLay.setSizeFull();
		eventGLay.setMargin(new MarginInfo(false, true, false, true));
		
		// titleL settings
		titleL = new Label("Title");
		titleL.setWidth(null);
		eventGLay.addComponent(titleL, 0, 0);
		eventGLay.setComponentAlignment(titleL, Alignment.MIDDLE_LEFT);
		
		// dateStartL settings
		dateStartL = new Label("Start");
		dateStartL.setWidth(null);
		eventGLay.addComponent(dateStartL, 0, 1);
		eventGLay.setComponentAlignment(dateStartL, Alignment.MIDDLE_LEFT);
		
		// dateEndL settings
		dateEndL = new Label("End");
		dateEndL.setWidth(null);
		eventGLay.addComponent(dateEndL, 0, 2);
		eventGLay.setComponentAlignment(dateEndL, Alignment.MIDDLE_LEFT);
		
		// locationL settings
		locationL = new Label("Location");
		locationL.setWidth(null);
		eventGLay.addComponent(locationL, 0, 3);
		eventGLay.setComponentAlignment(locationL, Alignment.MIDDLE_LEFT);
		
		// descriptionL settings
		descriptionL = new Label("Description");
		descriptionL.setWidth(null);
		eventGLay.addComponent(descriptionL, 0, 4);
		eventGLay.setComponentAlignment(descriptionL, Alignment.MIDDLE_LEFT);
		
		// titleTF settings
		titleTF = new TextField();
		eventGLay.addComponent(titleTF, 1, 0);
		eventGLay.setComponentAlignment(titleTF, Alignment.MIDDLE_CENTER);
		
		// dateStartDF settings
		dateStartDF = new DateField();
		dateStartDF.setResolution(Resolution.MINUTE);
		dateStartDF.setLocale(new Locale("en", "GB"));
		dateStartDF.setWidth("186px");
		eventGLay.addComponent(dateStartDF, 1, 1);
		eventGLay.setComponentAlignment(dateStartDF, Alignment.MIDDLE_CENTER);
		
		// dateEndDF settings
		dateEndDF = new DateField();
		dateEndDF.setResolution(Resolution.MINUTE);
		dateEndDF.setLocale(new Locale("en", "GB"));
		dateEndDF.setWidth("186px");
		eventGLay.addComponent(dateEndDF, 1, 2);
		eventGLay.setComponentAlignment(dateEndDF, Alignment.MIDDLE_CENTER);
		
		// locationTF settings
		locationTF = new TextField();
		eventGLay.addComponent(locationTF, 1, 3);
		eventGLay.setComponentAlignment(locationTF, Alignment.MIDDLE_CENTER);
		
		// descriptionTA settings
		descriptionTA = new TextArea();
		eventGLay.addComponent(descriptionTA, 1, 4);
		eventGLay.setComponentAlignment(descriptionTA, Alignment.MIDDLE_CENTER);
		mainGLay.addComponent(eventGLay, 0, 0);
		eventGLay.setColumnExpandRatio(0, 1);
		eventGLay.setColumnExpandRatio(1, 3);
		
		// addButt settings
		addB = new Button("Add event");
		mainGLay.addComponent(addB, 0, 1);
		mainGLay.setComponentAlignment(addB, Alignment.MIDDLE_CENTER);
		
		// expand of rows settings
		mainGLay.setRowExpandRatio(0, 9);
		mainGLay.setRowExpandRatio(1, 1);
	}

}
