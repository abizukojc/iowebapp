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
 * Klasa NewEventWindow dziedzicz�ca po klasie Window. Wy�wietla okno w kt�rym
 * u�ytkownik podaje tytu�, dat� startu, dat� ko�ca, lokalizacj� oraz opis
 * wydarzenia.
 *
 * @author Krzysztof Perchlicki
 */
public class NewEventWindow extends Window {

	private static final long serialVersionUID = 1L;
	/**
	 * Referencja do interfejsu u�ytkownika (g��wnego servletu).
	 */
	private IowebappUI userInterface;
	/**
	 * Grid layout na kt�rym znajduj� si� pola kt�re nale�y wype�ni� aby
	 * utworzy� wydarzenie.
	 */
	private GridLayout eventGLay;
	/**
	 * Przycisk kt�ry w przypadku gdy odpowiednie pola s� wype�nione wysy�a
	 * informacje do g��wnego servletu (referencja UserInterface).
	 */
	private Button addB;
	/**
	 * Grid Layout kt�ry zawiera w sobie eventGLay i przycisk addB. S�u�y do
	 * odpowiedniego pozycjonowania tych komponent�w.
	 */
	private GridLayout mainGLay;
	/**
	 * Wy�wietla napis: "Title".
	 */
	private Label titleL;
	/**
	 * Wy�wietla napis: "Date start".
	 */
	private Label dateStartL;
	/**
	 * Wy�wietla napis: "Date end".
	 */
	private Label dateEndL;
	/**
	 * Wy�wietla napis: "Location".
	 */
	private Label locationL;
	/**
	 * Wy�wietla napis: "Description"
	 */
	private Label descriptionL;
	/**
	 * Pola w kt�rych podaje si�
	 */
	private TextField titleTF, locationTF;
	/**
	 * Pole w kt�rym podaj� si� opis wydarzenia.
	 */
	private TextArea descriptionTA;
	/**
	 * Data startu wydarzenia.
	 */
	private DateField dateStartDF;
	/**
	 * Data ko�ca wydarzenia.
	 */
	private DateField dateEndDF;

	/**
	 * @param UI
	 *            Konstruktor klasy NewEventWindow,kt�ry przyjmuj� referencj� do
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
	 * Metoda, kt�ra daj� dost�p do pola userInterface
	 * 
	 * @return referencja do userInterface
	 */
	public IowebappUI getUserInterface() {
		return userInterface;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� userInterface.
	 * 
	 * @param userInterface
	 *            referencja do UI (User Interface).
	 * 
	 */
	public void setUserInterface(final IowebappUI userInterface) {
		this.userInterface = userInterface;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola eventGLay
	 * 
	 * @return referencja do eventGLay
	 */
	public GridLayout getEventGLay() {
		return eventGLay;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� eventGLay
	 * 
	 * @param eventGLay
	 *            referencja do obiektu klasy Grid Layout (layout zdarze�).
	 */

	public void setEventGLay(final GridLayout eventGLay) {
		this.eventGLay = eventGLay;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola addB
	 * 
	 * @return referencja do addB
	 */

	public Button getAddB() {
		return addB;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� addB
	 * 
	 * @param addB
	 *            referencja do obiektu klasy Button (przycisk dodawania
	 *            zdarzenia).
	 */

	public void setAddB(final Button addB) {
		this.addB = addB;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola mainGLay
	 * 
	 * @return referencja do mainGLay
	 */

	public GridLayout getMainGLay() {
		return mainGLay;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� mainGLay
	 * 
	 * @param mainGLay
	 *            referencja do obiektu klasy Grid Layout (g��wny layout).
	 */

	public void setMainGLay(final GridLayout mainGLay) {
		this.mainGLay = mainGLay;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola titleL
	 * 
	 * @return referencja do titleL
	 */
	public Label getTitleL() {
		return titleL;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� titleL
	 * 
	 * @param titleL
	 *            referencja do obiektu klasy Label (tytu�).
	 */

	public void setTitleL(final Label titleL) {
		this.titleL = titleL;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola dateStartL
	 * 
	 * @return referencja do dateStartL
	 */

	public Label getDateStartL() {
		return dateStartL;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� dateStartL
	 * 
	 * @param dateStartL
	 *            referencja do obiektu klasy Label (data startu).
	 */

	public void setDateStartL(final Label dateStartL) {
		this.dateStartL = dateStartL;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola dateEndL
	 * 
	 * @return referencja do dateEndL
	 */

	public Label getDateEndL() {
		return dateEndL;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� dateEndL
	 * 
	 * @param dateEndL
	 *            referencja do obiektu klasy Label (data ko�ca).
	 */

	public void setDateEndL(final Label dateEndL) {
		this.dateEndL = dateEndL;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola locationL
	 * 
	 * @return referencja do locationL
	 */

	public Label getLocationL() {
		return locationL;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� locationL
	 * 
	 * @param locationL
	 *            referencja do obiektu klasy Label (lokalizacja zdarzenia).
	 */

	public void setLocationL(final Label locationL) {
		this.locationL = locationL;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola descriptionL
	 * 
	 * @return referencja do descriptionL
	 */

	public Label getDescriptionL() {
		return descriptionL;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� descriptionL
	 * 
	 * @param descriptionL
	 *            referencja do obiektu klasy Label (opis zdarzenia).
	 */

	public void setDescriptionL(final Label descriptionL) {
		this.descriptionL = descriptionL;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola titleTF
	 * 
	 * @return referencja do titleTF
	 */

	public TextField getTitleTF() {
		return titleTF;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� titleTF
	 * 
	 * @param titleTF
	 *            referencja do obiektu klasy TextField (pole tytu�u zdarzenia).
	 */

	public void setTitleTF(final TextField titleTF) {
		this.titleTF = titleTF;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola locationTF
	 * 
	 * @return referencja do locationTF
	 */

	public TextField getLocationTF() {
		return locationTF;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� locationTF
	 * 
	 * @param locationTF
	 *            referencja do obiektu klasy TextField (pole lokalizacji
	 *            zdarzenia).
	 */

	public void setLocationTF(final TextField locationTF) {
		this.locationTF = locationTF;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola descriptionTA
	 * 
	 * @return referencja do descriptionTA
	 */

	public TextArea getDescriptionTA() {
		return descriptionTA;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� descriptionTA
	 * 
	 * @param descriptionTA
	 *            referencja do obiektu klasy TextArea (pole opisu zdarzenia).
	 */

	public void setDescriptionTA(final TextArea descriptionTA) {
		this.descriptionTA = descriptionTA;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola dateStartDF
	 * 
	 * @return referencja do dateStartDF
	 */

	public DateField getDateStartDF() {
		return dateStartDF;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� dateStartDF
	 * 
	 * @param dateStartDF
	 *            referencja do obiektu klasy DataField (data startu zdarzenia).
	 */

	public void setDateStartDF(final DateField dateStartDF) {
		this.dateStartDF = dateStartDF;
	}

	/**
	 * Metoda, kt�ra daj� dost�p do pola dateEndDF
	 * 
	 * @return referencja do dateEndDF
	 */

	public DateField getDateEndDF() {
		return dateEndDF;
	}

	/**
	 * Metoda, kt�ra pozwala zmieni� dateEndDF
	 * 
	 * @param dateEndDF
	 *            referencja do obiektu klasy DataField (data ko�ca zdarzenia).
	 */

	public void setDateEndDF(final DateField dateEndDF) {
		this.dateEndDF = dateEndDF;
	}
}
