package iowebapp;

import java.awt.Toolkit;
import java.util.Date;
import java.util.Locale;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.server.UserError;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

/**
 * Klasa NewEventWindow dziedzicząca po klasie Window. Wyświetla okno w którym
 * użytkownik podaje tytuł, datę startu, datę końca, lokalizację oraz opis
 * wydarzenia.
 *
 * @author Krzysztof Perchlicki
 */
public class NewEventWindow extends Window {

	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor tworzy okno, które służy do tworzenia i edycji wydarzeń. Okno
	 * potrafi sygnalizować błędy.
	 * 
	 * @param eventsContainer
	 * @param eventG
	 * @param ref
	 */
	@SuppressWarnings({ "serial", "deprecation" })
	public
	NewEventWindow(final BeanItemContainer<CalendarEvent> eventsContainer, final Grid eventG, final CalendarEvent ref) {
		super("New Event");

		// mainLayout settings
		GridLayout mainGLay;
		mainGLay = new GridLayout(1, 2);
		mainGLay.setSizeFull();
		mainGLay.setRowExpandRatio(0, 9);
		mainGLay.setRowExpandRatio(1, 1);

		// window settings
		setWidth("400px");
		setHeight("500px");
//		final Toolkit size = Toolkit.getDefaultToolkit();
//		setPosition(2 * size.getScreenSize().width / 5, size.getScreenSize().height / 6);
		setPosition(768,180);
		setResizable(false);
		setContent(mainGLay);

		// eventGLay settings
		GridLayout eventGLay;
		eventGLay = new GridLayout(2, 6);
		eventGLay.setSizeFull();
		eventGLay.setMargin(new MarginInfo(false, true, false, true));
		mainGLay.addComponent(eventGLay, 0, 0);
		eventGLay.setColumnExpandRatio(0, 1);
		eventGLay.setColumnExpandRatio(1, 3);
		eventGLay.setRowExpandRatio(0, 5);
		eventGLay.setRowExpandRatio(1, 5);
		eventGLay.setRowExpandRatio(2, 1);
		eventGLay.setRowExpandRatio(3, 5);
		eventGLay.setRowExpandRatio(4, 5);
		eventGLay.setRowExpandRatio(5, 5);

		// titleL settings
		Label titleL;
		titleL = new Label("Title");
		titleL.setWidth(null);
		eventGLay.addComponent(titleL, 0, 0);
		eventGLay.setComponentAlignment(titleL, Alignment.MIDDLE_LEFT);

		// dateStartL settings
		Label dateStartL;
		dateStartL = new Label("Start");
		dateStartL.setWidth(null);
		eventGLay.addComponent(dateStartL, 0, 1);
		eventGLay.setComponentAlignment(dateStartL, Alignment.MIDDLE_LEFT);

		// dateEndL settings
		Label dateEndL;
		dateEndL = new Label("End");
		dateEndL.setWidth(null);
		eventGLay.addComponent(dateEndL, 0, 3);
		eventGLay.setComponentAlignment(dateEndL, Alignment.MIDDLE_LEFT);

		// locationL settings
		Label locationL;
		locationL = new Label("Location");
		locationL.setWidth(null);
		eventGLay.addComponent(locationL, 0, 4);
		eventGLay.setComponentAlignment(locationL, Alignment.MIDDLE_LEFT);

		// descriptionL settings
		Label descriptionL;
		descriptionL = new Label("Description");
		descriptionL.setWidth(null);
		eventGLay.addComponent(descriptionL, 0, 5);
		eventGLay.setComponentAlignment(descriptionL, Alignment.MIDDLE_LEFT);

		// titleTF settings
		TextField titleTF;
		titleTF = new TextField();
		eventGLay.addComponent(titleTF, 1, 0);
		eventGLay.setComponentAlignment(titleTF, Alignment.MIDDLE_CENTER);

		// dateStartDF settings
		DateField dateStartDF;
		dateStartDF = new DateField();
		dateStartDF.setResolution(Resolution.MINUTE);
		dateStartDF.setLocale(new Locale("en", "GB"));
		dateStartDF.setWidth(186, Unit.PIXELS);
		eventGLay.addComponent(dateStartDF, 1, 1);
		eventGLay.setComponentAlignment(dateStartDF, Alignment.MIDDLE_CENTER);
		dateStartDF.setValue(new Date());

		// dateEndDF settings
		DateField dateEndDF;
		dateEndDF = new DateField();
		dateEndDF.setResolution(Resolution.MINUTE);
		dateEndDF.setLocale(new Locale("en", "GB"));
		dateEndDF.setWidth(186, Unit.PIXELS);
		eventGLay.addComponent(dateEndDF, 1, 3);
		eventGLay.setComponentAlignment(dateEndDF, Alignment.MIDDLE_CENTER);
		final Date dateEnd = new Date();
		dateEnd.setHours(dateEnd.getHours() + 1);
		dateEndDF.setValue(dateEnd);

		HorizontalLayout allDayHLay;
		allDayHLay = new HorizontalLayout();
		allDayHLay.setWidthUndefined();
		allDayHLay.setHeight(25, Unit.PIXELS);
		allDayHLay.setStyleName("mymargins");
		Label allDayL;
		allDayL = new Label("All day");
		CheckBox allDayCh;
		allDayCh = new CheckBox();
		allDayL.setWidth(null);
		allDayCh.addValueChangeListener(new ValueChangeListener() {

			/**
			 * W przypadku gdy uzytkownik zaznaczy checkboxa data końca staje
			 * sie taka jak data poczatku, znika informacja o godzinach i
			 * możliwość edycji dateEnd. Gdy natomiast odznaczy checkboxa to
			 * wracaja minuty i możliwość edycji dateEnd.
			 */
			@Override
			public void valueChange(final ValueChangeEvent event) {
				if (allDayCh.getValue()) {
					dateStartDF.setResolution(Resolution.DAY);
					dateEndDF.setResolution(Resolution.DAY);
					dateEndDF.setValue(dateStartDF.getValue());
					dateEndDF.setEnabled(false);
				} else {
					dateStartDF.setResolution(Resolution.MINUTE);
					dateEndDF.setResolution(Resolution.MINUTE);
					dateEndDF.setEnabled(true);
				}

			}
		});

		allDayHLay.addComponent(allDayCh);
		allDayHLay.addComponent(allDayL);
		allDayHLay.setMargin(new MarginInfo(false, false, false, true));
		eventGLay.addComponent(allDayHLay, 1, 2);
		eventGLay.setComponentAlignment(allDayHLay, Alignment.MIDDLE_LEFT);

		// locationTF settings
		TextField locationTF;
		locationTF = new TextField();
		eventGLay.addComponent(locationTF, 1, 4);
		eventGLay.setComponentAlignment(locationTF, Alignment.MIDDLE_CENTER);

		// descriptionTA settings
		TextArea descriptionTA;
		descriptionTA = new TextArea();
		eventGLay.addComponent(descriptionTA, 1, 5);
		eventGLay.setComponentAlignment(descriptionTA, Alignment.MIDDLE_CENTER);

		if (ref != null) {
			titleTF.setValue(ref.getTitle());
			dateStartDF.setValue(ref.getDateStart());
			dateEndDF.setValue(ref.getDateEnd());
			locationTF.setValue(ref.getLocation());
			descriptionTA.setValue(ref.getDescription());
			allDayCh.setValue(ref.isAllDay());
		}

		// addButt settings
		Button addB;
		addB = new Button("Add event");
		addB.addClickListener(new ClickListener() {

			/**
			 * Pole przechowujace nazwe wydarzenia.
			 */
			String titleS;
			/**
			 * Pole przechowujace date startu wydarzenia.
			 */
			Date dateStartD;
			/**
			 * Pole przechowujace date końca wydarzenia.
			 */
			Date dateEndD;

			/**
			 * Kliknięcie na przycisk powoduje utworzenie nowego wydarzenia
			 * (dodane jest ono do tablicy eventsContainer). Obowiązkowe pola są
			 * sprawdzane i wyświetlane są odpowiednie komunikaty. Jeśli
			 * istnieje referencja do wiersza z eventsContainer to nastepuje
			 * edycja wydarzenia.
			 */
			@Override
			public void buttonClick(final ClickEvent event) {
				dateStartD = dateStartDF.getValue();
				dateEndD = dateEndDF.getValue();
				titleS = titleTF.getValue();
				Date dateCreated;
				Date dateModified;
				boolean errorCondition;
				titleTF.setComponentError(null);
				dateStartDF.setComponentError(null);
				dateEndDF.setComponentError(null);
				errorCondition = "".equals(titleS) || dateStartD == null || dateEndD == null
						|| dateStartD.after(dateEndD);

				if ("".equals(titleS)) {
					titleTF.setComponentError(new UserError("That field can't be empty!"));
				}
				if (dateStartD == null && dateStartDF.isValid()) {
					dateStartDF.setComponentError(new UserError("That field can't be empty!"));
				}
				if (dateEndD == null && dateEndDF.isValid()) {
					dateEndDF.setComponentError(new UserError("That field can't be empty!"));
				}
				if (dateStartD != null && dateEndD != null && dateStartD.after(dateEndD)) {
					dateEndDF.setComponentError(new UserError("End date must be later than start date"));
				}
				dateModified=new Date();
				if (!errorCondition) {
					if (ref == null) {					
						dateCreated = new Date(dateModified.getTime());
						eventsContainer.addBean(new CalendarEvent(titleTF.getValue(), dateStartDF.getValue(),
								dateEndDF.getValue(), dateCreated, dateModified, locationTF.getValue(),
								descriptionTA.getValue(), allDayCh.getValue()));
						final Notification addSuccess = new Notification("Successfully added event!", Notification.Type.ASSISTIVE_NOTIFICATION);
						addSuccess.setPosition(Position.TOP_CENTER);
						addSuccess.setDelayMsec(1000);
						addSuccess.show(Page.getCurrent());
					} else {
						ref.setTitle(titleTF.getValue());
						ref.setDateStart(dateStartDF.getValue());
						ref.setDateEnd(dateEndDF.getValue());
						ref.setLocation(locationTF.getValue());
						ref.setDescription(descriptionTA.getValue());
						ref.setAllDay(allDayCh.getValue());
						ref.setDateModified(dateModified);
						eventG.setDetailsVisible(ref, false);
						eventG.setDetailsVisible(ref, true);
						eventG.clearSortOrder();
						final Notification editSuccess = new Notification("Successfully edited event!", Notification.Type.ASSISTIVE_NOTIFICATION);
						editSuccess.setPosition(Position.TOP_CENTER);
						editSuccess.setDelayMsec(1000);
						editSuccess.show(Page.getCurrent());
					}
					return;
				}
				final Notification addError = new Notification("Can't add event!", Notification.Type.ERROR_MESSAGE);
				addError.setPosition(Position.TOP_CENTER);
				addError.setDelayMsec(1000);
				addError.show(Page.getCurrent());
			}

		});
		mainGLay.addComponent(addB, 0, 1);
		mainGLay.setComponentAlignment(addB, Alignment.MIDDLE_CENTER);
	}

}
