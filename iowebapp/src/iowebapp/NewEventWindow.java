package iowebapp;

import java.awt.Toolkit;
import java.util.Date;
import java.util.Locale;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.UserError;
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
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

/**
 * Klasa NewEventWindow dziedzicz¹ca po klasie Window. Wyœwietla okno w którym
 * u¿ytkownik podaje tytu³, datê startu, datê koñca, lokalizacjê oraz opis
 * wydarzenia.
 *
 * @author Krzysztof Perchlicki
 */
public class NewEventWindow extends Window {

	private static final long serialVersionUID = 1L;

	BeanItemContainer<CalendarEvent> eventContainer;
	/**
	 * Referencja do interfejsu u¿ytkownika (g³ównego servletu).
	 */

	private CalendarEvent ref;
	private IowebappUI userInterface;

	/**
	 * Grid layout na którym znajduj¹ siê pola które nale¿y wype³niæ aby
	 * utworzyæ wydarzenie.
	 */
	private GridLayout eventGLay;

	/**
	 * Przycisk który w przypadku gdy odpowiednie pola s¹ wype³nione wysy³a
	 * informacje do g³ównego servletu (referencja UserInterface).
	 */
	private Button addB;

	/**
	 * Grid layout który zawiera w sobie eventGLay i przycisk addB. S³u¿y do
	 * odpowiedniego pozycjonowania tych komponentów.
	 */
	private GridLayout mainGLay;

	/**
	 * Wyœwietla napis: "Title".
	 */
	private Label titleL;

	/**
	 * Wyœwietla napis: "Date start".
	 */
	private Label dateStartL;

	/**
	 * Wyœwietla napis: "Date end".
	 */
	private Label dateEndL;

	/**
	 * Wyœwietla napis: "Location".
	 */
	private Label locationL;

	/**
	 * Wyœwietla napis: "Description"
	 */
	private Label descriptionL;

	private Label allDayL;
	private CheckBox allDayCh;
	private HorizontalLayout allDayHLay;

	/**
	 * Pola w których podaje siê
	 */
	private TextField titleTF;
	private TextField locationTF;
	private boolean check;

	/**
	 * Pole w którym podajê siê opis wydarzenia.
	 */
	private TextArea descriptionTA;

	/**
	 * Data startu wydarzenia.
	 */
	private DateField dateStartDF;

	/**
	 * Data koñca wydarzenia.
	 */
	private DateField dateEndDF;

	private Grid eventG;

	class AddEventListener implements ClickListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			if (titleTF.getValue().equals("") == true || dateStartDF.getValue() == null || dateEndDF.getValue() == null
					|| dateStartDF.getValue().after(dateEndDF.getValue())) {
				if (titleTF.getValue().equals("") == true) {
					titleTF.setComponentError(new UserError("That field can't be empty!"));
				}
				if (titleTF.getValue().equals("") == false) {
					titleTF.setComponentError(null);
				}
				if (dateStartDF.getValue() == null) {
					dateStartDF.setComponentError(new UserError("That field can't be empty!"));
				}
				if (dateStartDF.getValue() != null) {
					dateStartDF.setComponentError(null);
				}
				if (dateEndDF.getValue() == null) {
					dateEndDF.setComponentError(new UserError("That field can't be empty!"));
				}
				if (dateEndDF.getValue() != null) {
					dateEndDF.setComponentError(null);
				}
				if (dateStartDF.getValue() != null && dateEndDF.getValue() != null) {
					if (dateStartDF.getValue().after(dateEndDF.getValue())) {
						dateEndDF.setComponentError(new UserError("End date must be later than start date"));
					}
				}
			} else {
				titleTF.setComponentError(null);
				dateStartDF.setComponentError(null);
				dateEndDF.setComponentError(null);
				if (ref == null) {
					eventContainer.addBean(new CalendarEvent(titleTF.getValue(), dateStartDF.getValue(),
							dateEndDF.getValue(), locationTF.getValue(), descriptionTA.getValue(), check));
				} else {
					ref.setTitle(titleTF.getValue());
					ref.setDateStart(dateStartDF.getValue());
					ref.setDateEnd(dateEndDF.getValue());
					ref.setLocation(locationTF.getValue());
					ref.setDescription(descriptionTA.getValue());
					ref.setAllDay(check);
					eventG.setDetailsVisible(ref, false);
					eventG.setDetailsVisible(ref, true);
					eventG.clearSortOrder();

				}
			}

		}

	};

	/**
	 * @param eventContainer
	 * @param UI
	 *            Konstruktor klasy NewEventWindow,który przyjmujê referencjê do
	 *            obiektu klasy IowebappUI
	 */

	NewEventWindow(BeanItemContainer<CalendarEvent> eventContainer, Grid eventG, CalendarEvent ref) {
		super("New Event");
		this.check = false;
		this.eventG = eventG;
		this.ref = ref;

		// this.userInterface = userInterface;
		this.eventContainer = eventContainer;

		// mainLayout settings
		mainGLay = new GridLayout(1, 2);
		mainGLay.setSizeFull();
		mainGLay.setRowExpandRatio(0, 9);
		mainGLay.setRowExpandRatio(1, 1);

		// window settings
		setWidth("400px");
		setHeight("500px");
		final Toolkit size = Toolkit.getDefaultToolkit();
		setPosition(2 * size.getScreenSize().width / 5, size.getScreenSize().height / 6);
		setResizable(false);
		setContent(mainGLay);

		// eventGLay settings
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
		eventGLay.addComponent(dateEndL, 0, 3);
		eventGLay.setComponentAlignment(dateEndL, Alignment.MIDDLE_LEFT);

		// locationL settings
		locationL = new Label("Location");
		locationL.setWidth(null);
		eventGLay.addComponent(locationL, 0, 4);
		eventGLay.setComponentAlignment(locationL, Alignment.MIDDLE_LEFT);

		// descriptionL settings
		descriptionL = new Label("Description");
		descriptionL.setWidth(null);
		eventGLay.addComponent(descriptionL, 0, 5);
		eventGLay.setComponentAlignment(descriptionL, Alignment.MIDDLE_LEFT);

		// titleTF settings
		titleTF = new TextField();
		eventGLay.addComponent(titleTF, 1, 0);
		eventGLay.setComponentAlignment(titleTF, Alignment.MIDDLE_CENTER);

		// dateStartDF settings
		dateStartDF = new DateField();
		dateStartDF.setResolution(Resolution.MINUTE);
		dateStartDF.setLocale(new Locale("en", "GB"));
		dateStartDF.setWidth(186, Unit.PIXELS);
		eventGLay.addComponent(dateStartDF, 1, 1);
		eventGLay.setComponentAlignment(dateStartDF, Alignment.MIDDLE_CENTER);
		dateStartDF.setValue(new Date());

		// dateEndDF settings
		dateEndDF = new DateField();
		dateEndDF.setResolution(Resolution.MINUTE);
		dateEndDF.setLocale(new Locale("en", "GB"));
		dateEndDF.setWidth(186, Unit.PIXELS);
		eventGLay.addComponent(dateEndDF, 1, 3);
		eventGLay.setComponentAlignment(dateEndDF, Alignment.MIDDLE_CENTER);
		Date dateEnd = new Date();
		dateEnd.setHours(dateEnd.getHours() + 1);
		dateEndDF.setValue(dateEnd);

		allDayHLay = new HorizontalLayout();
		allDayHLay.setWidthUndefined();
		allDayHLay.setHeight(25, Unit.PIXELS);
		allDayHLay.setStyleName("mymargins");
		allDayL = new Label("All day");
		allDayCh = new CheckBox();
		allDayL.setWidth(null);
		allDayCh.addValueChangeListener(new ValueChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				check = allDayCh.getValue();
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
		locationTF = new TextField();
		eventGLay.addComponent(locationTF, 1, 4);
		eventGLay.setComponentAlignment(locationTF, Alignment.MIDDLE_CENTER);

		// descriptionTA settings
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
			check = ref.isAllDay();
		}

		// addButt settings
		addB = new Button("Add event");
		addB.addClickListener(new AddEventListener());
		mainGLay.addComponent(addB, 0, 1);
		mainGLay.setComponentAlignment(addB, Alignment.MIDDLE_CENTER);
	}

	/**
	 * Metoda, która dajê dostêp do pola userInterface.
	 * 
	 * @return referencja do userInterface
	 */
	public IowebappUI getUserInterface() {
		return userInterface;
	}

	/**
	 * Metoda, która pozwala zmieniæ userInterface.
	 * 
	 * @param userInterface
	 *            referencja do UI (User Interface).
	 * 
	 */
	public void setUserInterface(final IowebappUI userInterface) {
		this.userInterface = userInterface;
	}

	/**
	 * Metoda, która dajê dostêp do pola eventGLay.
	 * 
	 * @return referencja do eventGLay
	 */
	public GridLayout getEventGLay() {
		return eventGLay;
	}

	/**
	 * Metoda, która pozwala zmieniæ eventGLay.
	 * 
	 * @param eventGLay
	 *            obiekt klasy GridLayout (layout zdarzeñ).
	 */
	public void setEventGLay(final GridLayout eventGLay) {
		this.eventGLay = eventGLay;
	}

	/**
	 * Metoda, która dajê dostêp do pola addB.
	 * 
	 * @return referencja do addB
	 */
	public Button getAddB() {
		return addB;
	}

	/**
	 * Metoda, która pozwala zmieniæ addB.
	 * 
	 * @param addB
	 *            obiekt klasy Button (przycisk dodawania zdarzenia).
	 */
	public void setAddB(final Button addB) {
		this.addB = addB;
	}

	/**
	 * Metoda, która dajê dostêp do pola mainGLay.
	 * 
	 * @return referencja do mainGLay
	 */
	public GridLayout getMainGLay() {
		return mainGLay;
	}

	/**
	 * Metoda, która pozwala zmieniæ mainGLay.
	 * 
	 * @param mainGLay
	 *            obiekt klasy GridLayout (g³ówny layout).
	 */
	public void setMainGLay(final GridLayout mainGLay) {
		this.mainGLay = mainGLay;
	}

	/**
	 * Metoda, która dajê dostêp do pola titleL.
	 * 
	 * @return referencja do titleL
	 */
	public Label getTitleL() {
		return titleL;
	}

	/**
	 * Metoda, która pozwala zmieniæ titleL.
	 * 
	 * @param titleL
	 *            obiekt klasy Label (tytu³).
	 */
	public void setTitleL(final Label titleL) {
		this.titleL = titleL;
	}

	/**
	 * Metoda, która dajê dostêp do pola dateStartL.
	 * 
	 * @return referencja do dateStartL
	 */
	public Label getDateStartL() {
		return dateStartL;
	}

	/**
	 * Metoda, która pozwala zmieniæ dateStartL.
	 * 
	 * @param dateStartL
	 *            obiekt klasy Label (data startu).
	 */
	public void setDateStartL(final Label dateStartL) {
		this.dateStartL = dateStartL;
	}

	/**
	 * Metoda, która dajê dostêp do pola dateEndL.
	 * 
	 * @return referencja do dateEndL
	 */
	public Label getDateEndL() {
		return dateEndL;
	}

	/**
	 * Metoda, która pozwala zmieniæ dateEndL.
	 * 
	 * @param dateEndL
	 *            obiekt klasy Label (data koñca).
	 */
	public void setDateEndL(final Label dateEndL) {
		this.dateEndL = dateEndL;
	}

	/**
	 * Metoda, która dajê dostêp do pola locationL.
	 * 
	 * @return referencja do locationL
	 */
	public Label getLocationL() {
		return locationL;
	}

	/**
	 * Metoda, która pozwala zmieniæ locationL.
	 * 
	 * @param locationL
	 *            obiekt klasy Label (lokalizacja zdarzenia).
	 */
	public void setLocationL(final Label locationL) {
		this.locationL = locationL;
	}

	/**
	 * Metoda, która dajê dostêp do pola descriptionL.
	 * 
	 * @return referencja do descriptionL
	 */
	public Label getDescriptionL() {
		return descriptionL;
	}

	/**
	 * Metoda, która pozwala zmieniæ descriptionL.
	 * 
	 * @param descriptionL
	 *            obiekt klasy Label (opis zdarzenia).
	 */
	public void setDescriptionL(final Label descriptionL) {
		this.descriptionL = descriptionL;
	}

	/**
	 * Metoda, która dajê dostêp do pola titleTF.
	 * 
	 * @return referencja do titleTF
	 */
	public TextField getTitleTF() {
		return titleTF;
	}

	/**
	 * Metoda, która pozwala zmieniæ titleTF.
	 * 
	 * @param titleTF
	 *            obiekt klasy TextField (pole tytu³u zdarzenia).
	 */
	public void setTitleTF(final TextField titleTF) {
		this.titleTF = titleTF;
	}

	/**
	 * Metoda, która dajê dostêp do pola locationTF.
	 * 
	 * @return referencja do locationTF
	 */
	public TextField getLocationTF() {
		return locationTF;
	}

	/**
	 * Metoda, która pozwala zmieniæ locationTF.
	 * 
	 * @param locationTF
	 *            obiekt klasy TextField (pole lokalizacji zdarzenia).
	 */
	public void setLocationTF(final TextField locationTF) {
		this.locationTF = locationTF;
	}

	/**
	 * Metoda, która dajê dostêp do pola descriptionTA.
	 * 
	 * @return referencja do descriptionTA
	 */
	public TextArea getDescriptionTA() {
		return descriptionTA;
	}

	/**
	 * Metoda, która pozwala zmieniæ descriptionTA.
	 * 
	 * @param descriptionTA
	 *            obiekt klasy TextArea (pole opisu zdarzenia).
	 */
	public void setDescriptionTA(final TextArea descriptionTA) {
		this.descriptionTA = descriptionTA;
	}

	/**
	 * Metoda, która dajê dostêp do pola dateStartDF.
	 * 
	 * @return referencja do dateStartDF
	 */
	public DateField getDateStartDF() {
		return dateStartDF;
	}

	/**
	 * Metoda, która pozwala zmieniæ dateStartDF.
	 * 
	 * @param dateStartDF
	 *            obiekt klasy DataField (data startu zdarzenia).
	 */
	public void setDateStartDF(final DateField dateStartDF) {
		this.dateStartDF = dateStartDF;
	}

	/**
	 * Metoda, która dajê dostêp do pola dateEndDF.
	 * 
	 * @return referencja do dateEndDF
	 */
	public DateField getDateEndDF() {
		return dateEndDF;
	}

	/**
	 * Metoda, która pozwala zmieniæ dateEndDF.
	 * 
	 * @param dateEndDF
	 *            obiekt klasy DataField (data koñca zdarzenia).
	 */
	public void setDateEndDF(final DateField dateEndDF) {
		this.dateEndDF = dateEndDF;
	}
}
