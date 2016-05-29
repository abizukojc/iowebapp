package iowebapp;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * Klasa LoadFromWebWindow dziedziczaca po klasie Window.
 * Wyœwietla okno w którym uzytkownik podaje link.
 * 
 * @author Micha³ Bielecki
 */
public class LoadFromWebWindow extends Window {
	
	/**
	 * Konstruktor tworzy okno w którym u¿ytkownik podaje link.
	 * 
	 * @param eventsContainer
	 */
	public LoadFromWebWindow(final BeanItemContainer<CalendarEvent> eventsContainer)
	{
		super("Load events from web");
	
		// window settings
		setWidth("300px");
		setHeight("180px");
		final Toolkit size = Toolkit.getDefaultToolkit();
		setPosition(2 * size.getScreenSize().width / 5, size.getScreenSize().height / 6);
		setResizable(false);
		
		// buttonsGLay settings
		GridLayout buttonsGLay;
		buttonsGLay = new GridLayout(1, 3);
		buttonsGLay.setSizeFull();
		buttonsGLay.setMargin(false);
		setContent(buttonsGLay);
		
		// urlLabel settings
		Label urlLabel;
		urlLabel = new Label("Enter link");
		urlLabel.setWidth(null);
		buttonsGLay.addComponent(urlLabel, 0, 0);
		buttonsGLay.setComponentAlignment(urlLabel, Alignment.MIDDLE_CENTER);
		
		// urlField settings (pole do wpisania linku)
		TextField urlField;
		urlField = new TextField();
		urlField.setWidth(250, Unit.PIXELS);
		buttonsGLay.addComponent(urlField, 0, 1);
		buttonsGLay.setComponentAlignment(urlField, Alignment.MIDDLE_CENTER);
		
		// loadFromUZButton settings
		Button loadFromUZButton;
		loadFromUZButton = new Button("Load events from PLAN UZ");
		buttonsGLay.addComponent(loadFromUZButton, 0, 2);
		buttonsGLay.setComponentAlignment(loadFromUZButton, Alignment.MIDDLE_CENTER);
		loadFromUZButton.addClickListener(new ClickListener() {

			/**
			 * Klikniecie w przycisk spowoduje dodanie wydarzeñ z planu uz do tabeli
			 */
			@Override
			public void buttonClick(final ClickEvent event) 
			{
				String urlUZ = urlField.getValue();
				
				if(urlUZ.contains("plan.uz.zgora.pl/grupy_plan"))
				{
					try {
						ParsUZ parsUZ = new ParsUZ(urlUZ);
						List<CalendarEvent> events = new ArrayList<CalendarEvent>();
						events = parsUZ.getEvents();
						
						for(int i=0; i<events.size(); i++)
						{
							eventsContainer.addBean(events.get(i));
						}
						
						final Notification add = new Notification("Events were added succesfully!");
						add.setPosition(Position.TOP_CENTER);
						add.setDelayMsec(2000);
						add.show(Page.getCurrent());
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					final Notification add = new Notification("The link you've typed is incorrect!", Notification.Type.ERROR_MESSAGE);
					add.setPosition(Position.TOP_CENTER);
					add.setDelayMsec(2000);
					add.show(Page.getCurrent());
					urlField.clear();
				}
			}
		});
	}
}
