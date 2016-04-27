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
 * Klasa NewEventWindow dziedzicz¹ca po klasie Window. Wyœwietla okno w którym
 * u¿ytkownik podaje tytu³, datê startu, datê koñca, lokalizacjê oraz opis
 * wydarzenia.
 *
 * @author Krzysztof Perchlicki
 */
public class NewEventWindow extends Window {

	private static final long serialVersionUID = 1L;
	/**
	 * Referencja do interfejsu u¿ytkownika (g³ównego servletu).
	 */
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
	 * Grid Layout który zawiera w sobie eventGLay i przycisk addB. S³u¿y do
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
	/**
	 * Pola w których podaje siê
	 */
	private TextField titleTF, locationTF;
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

	/**
	 * @param UI
	 *            Konstruktor klasy NewEventWindow,który przyjmujê referencjê do
	 *            obiektu klasy IowebappUI
	 */
	NewEventWindow(final IowebappUI userInterface) {
		super("New Event");
		this.userInterface = userInterface;

		// mainLayout settings
		mainGLay = new GridLayout(1, 2);
		mainGLay.setSizeFull();
		mainGLay.setRowExpandRatio(0, 9);
		mainGLay.setRowExpandRatio(1, 1);

		// window settings
		setWidth("400px");
		setHeight("500px");
		center();
		setResizable(false);
		setContent(mainGLay);

		// eventGLay settings
		eventGLay = new GridLayout(2, 5);
		eventGLay.setSizeFull();
		eventGLay.setMargin(new MarginInfo(false, true, false, true));
		mainGLay.addComponent(eventGLay, 0, 0);
		eventGLay.setColumnExpandRatio(0, 1);
		eventGLay.setColumnExpandRatio(1, 3);

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
		dateStartDF.setWidth(186, Unit.PIXELS);
		eventGLay.addComponent(dateStartDF, 1, 1);
		eventGLay.setComponentAlignment(dateStartDF, Alignment.MIDDLE_CENTER);

		// dateEndDF settings
		dateEndDF = new DateField();
		dateEndDF.setResolution(Resolution.MINUTE);
		dateEndDF.setLocale(new Locale("en", "GB"));
		dateEndDF.setWidth(186, Unit.PIXELS);
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

		// addButt settings
		addB = new Button("Add event");
		mainGLay.addComponent(addB, 0, 1);
		mainGLay.setComponentAlignment(addB, Alignment.MIDDLE_CENTER);
	}

	/**
	 * Metoda, która dajê dostêp do pola userInterface
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
	 * Metoda, która dajê dostêp do pola eventGLay
	 * 
	 * @return referencja do eventGLay
	 */
	public GridLayout getEventGLay() {
		return eventGLay;
	}

	/**
	 * Metoda, która pozwala zmieniæ eventGLay
	 * 
	 * @param eventGLay
	 *            referencja do obiektu klasy Grid Layout (layout zdarzeñ).
	 */

	public void setEventGLay(final GridLayout eventGLay) {
		this.eventGLay = eventGLay;
	}

	/**
	 * Metoda, która dajê dostêp do pola addB
	 * 
	 * @return referencja do addB
	 */

	public Button getAddB() {
		return addB;
	}

	/**
	 * Metoda, która pozwala zmieniæ addB
	 * 
	 * @param addB
	 *            referencja do obiektu klasy Button (przycisk dodawania
	 *            zdarzenia).
	 */

	public void setAddB(final Button addB) {
		this.addB = addB;
	}

	/**
	 * Metoda, która dajê dostêp do pola mainGLay
	 * 
	 * @return referencja do mainGLay
	 */

	public GridLayout getMainGLay() {
		return mainGLay;
	}

	/**
	 * Metoda, która pozwala zmieniæ mainGLay
	 * 
	 * @param mainGLay
	 *            referencja do obiektu klasy Grid Layout (g³ówny layout).
	 */

	public void setMainGLay(final GridLayout mainGLay) {
		this.mainGLay = mainGLay;
	}

	/**
	 * Metoda, która dajê dostêp do pola titleL
	 * 
	 * @return referencja do titleL
	 */
	public Label getTitleL() {
		return titleL;
	}

	/**
	 * Metoda, która pozwala zmieniæ titleL
	 * 
	 * @param titleL
	 *            referencja do obiektu klasy Label (tytu³).
	 */

	public void setTitleL(final Label titleL) {
		this.titleL = titleL;
	}

	/**
	 * Metoda, która dajê dostêp do pola dateStartL
	 * 
	 * @return referencja do dateStartL
	 */

	public Label getDateStartL() {
		return dateStartL;
	}

	/**
	 * Metoda, która pozwala zmieniæ dateStartL
	 * 
	 * @param dateStartL
	 *            referencja do obiektu klasy Label (data startu).
	 */

	public void setDateStartL(final Label dateStartL) {
		this.dateStartL = dateStartL;
	}

	/**
	 * Metoda, która dajê dostêp do pola dateEndL
	 * 
	 * @return referencja do dateEndL
	 */

	public Label getDateEndL() {
		return dateEndL;
	}

	/**
	 * Metoda, która pozwala zmieniæ dateEndL
	 * 
	 * @param dateEndL
	 *            referencja do obiektu klasy Label (data koñca).
	 */

	public void setDateEndL(final Label dateEndL) {
		this.dateEndL = dateEndL;
	}

	/**
	 * Metoda, która dajê dostêp do pola locationL
	 * 
	 * @return referencja do locationL
	 */

	public Label getLocationL() {
		return locationL;
	}

	/**
	 * Metoda, która pozwala zmieniæ locationL
	 * 
	 * @param locationL
	 *            referencja do obiektu klasy Label (lokalizacja zdarzenia).
	 */

	public void setLocationL(final Label locationL) {
		this.locationL = locationL;
	}

	/**
	 * Metoda, która dajê dostêp do pola descriptionL
	 * 
	 * @return referencja do descriptionL
	 */

	public Label getDescriptionL() {
		return descriptionL;
	}

	/**
	 * Metoda, która pozwala zmieniæ descriptionL
	 * 
	 * @param descriptionL
	 *            referencja do obiektu klasy Label (opis zdarzenia).
	 */

	public void setDescriptionL(final Label descriptionL) {
		this.descriptionL = descriptionL;
	}

	/**
	 * Metoda, która dajê dostêp do pola titleTF
	 * 
	 * @return referencja do titleTF
	 */

	public TextField getTitleTF() {
		return titleTF;
	}

	/**
	 * Metoda, która pozwala zmieniæ titleTF
	 * 
	 * @param titleTF
	 *            referencja do obiektu klasy TextField (pole tytu³u zdarzenia).
	 */

	public void setTitleTF(final TextField titleTF) {
		this.titleTF = titleTF;
	}

	/**
	 * Metoda, która dajê dostêp do pola locationTF
	 * 
	 * @return referencja do locationTF
	 */

	public TextField getLocationTF() {
		return locationTF;
	}

	/**
	 * Metoda, która pozwala zmieniæ locationTF
	 * 
	 * @param locationTF
	 *            referencja do obiektu klasy TextField (pole lokalizacji
	 *            zdarzenia).
	 */

	public void setLocationTF(final TextField locationTF) {
		this.locationTF = locationTF;
	}

	/**
	 * Metoda, która dajê dostêp do pola descriptionTA
	 * 
	 * @return referencja do descriptionTA
	 */

	public TextArea getDescriptionTA() {
		return descriptionTA;
	}

	/**
	 * Metoda, która pozwala zmieniæ descriptionTA
	 * 
	 * @param descriptionTA
	 *            referencja do obiektu klasy TextArea (pole opisu zdarzenia).
	 */

	public void setDescriptionTA(final TextArea descriptionTA) {
		this.descriptionTA = descriptionTA;
	}

	/**
	 * Metoda, która dajê dostêp do pola dateStartDF
	 * 
	 * @return referencja do dateStartDF
	 */

	public DateField getDateStartDF() {
		return dateStartDF;
	}

	/**
	 * Metoda, która pozwala zmieniæ dateStartDF
	 * 
	 * @param dateStartDF
	 *            referencja do obiektu klasy DataField (data startu zdarzenia).
	 */

	public void setDateStartDF(final DateField dateStartDF) {
		this.dateStartDF = dateStartDF;
	}

	/**
	 * Metoda, która dajê dostêp do pola dateEndDF
	 * 
	 * @return referencja do dateEndDF
	 */

	public DateField getDateEndDF() {
		return dateEndDF;
	}

	/**
	 * Metoda, która pozwala zmieniæ dateEndDF
	 * 
	 * @param dateEndDF
	 *            referencja do obiektu klasy DataField (data koñca zdarzenia).
	 */

	public void setDateEndDF(final DateField dateEndDF) {
		this.dateEndDF = dateEndDF;
	}
}
