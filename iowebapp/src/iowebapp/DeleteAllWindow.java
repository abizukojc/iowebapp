package iowebapp;

import java.awt.Toolkit;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * Okno kt�re tworzy si� po klikni�ciu na przycisk Delete all. S�u�y do podj�cia
 * decyzji czy usun�� wszystkie zdarzenia.
 * 
 * @author Krzysztof Perchlicki
 */
public class DeleteAllWindow extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor przyjmuj�cy referencj� do obiektu eventsContainer (tablica
	 * wydarze�) i tworz�cy ca�e okno do podejmowania decyzji.
	 * 
	 * @param eventsContainer
	 */
	@SuppressWarnings("serial")
	public DeleteAllWindow(final BeanItemContainer<CalendarEvent> eventsContainer) {
		// window settings
		super();
		final Toolkit size = Toolkit.getDefaultToolkit();
		setPosition(2 * size.getScreenSize().width / 5, size.getScreenSize().height / 6);
		setWidth("330px");
		setHeight("100px");
		setClosable(false);
		setResizable(false);
		// mainVLay settings
		VerticalLayout mainVLay;
		mainVLay = new VerticalLayout();
		mainVLay.setMargin(true);
		mainVLay.setSizeFull();
		setContent(mainVLay);
		// warningL settings
		Label warningL;
		warningL = new Label("Are you sure you want to delete all events?");
		mainVLay.setWidthUndefined();
		mainVLay.addComponent(warningL);
		mainVLay.setComponentAlignment(warningL, Alignment.TOP_CENTER);
		// buttonsHLay settings
		GridLayout buttonsGLay;
		buttonsGLay = new GridLayout(2, 1);
		buttonsGLay.setWidth(270, Unit.PIXELS);
		mainVLay.addComponent(buttonsGLay);
		mainVLay.setComponentAlignment(buttonsGLay, Alignment.TOP_CENTER);
		// yesB settings
		Button yesB;
		yesB = new Button("Yes");
		yesB.setIcon(FontAwesome.CHECK);
		yesB.setImmediate(true);
		yesB.setWidthUndefined();
		yesB.addClickListener(new ClickListener() {

			/**
			 * Klikni�cie powoduje, �e wszystkie wydarzenia zostaj� usuni�te a
			 * okno si� zamyka.
			 */
			@Override
			public void buttonClick(final ClickEvent event) {
				eventsContainer.removeAllItems();
				close();

			}

		});
		buttonsGLay.addComponent(yesB, 0, 0);
		buttonsGLay.setComponentAlignment(yesB, Alignment.MIDDLE_LEFT);

		// noB settings
		Button noB;
		noB = new Button("No");
		noB.setIcon(FontAwesome.CLOSE);
		noB.setImmediate(true);
		noB.setWidthUndefined();
		noB.addClickListener(new ClickListener() {

			/**
			 * Klikni�cie powoduje, �e okno si� zamyka.
			 */
			@Override
			public void buttonClick(final ClickEvent event) {
				close();

			}
		});
		buttonsGLay.addComponent(noB, 1, 0);
		buttonsGLay.setComponentAlignment(noB, Alignment.MIDDLE_RIGHT);
	}
}
