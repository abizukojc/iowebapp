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

public class DeleteAllWindow extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VerticalLayout mainVLay;
	private GridLayout buttonsGLay;
	private Label warningL;
	private Button yesB;
	private Button noB;

	BeanItemContainer<CalendarEvent> eventContainer;

	public DeleteAllWindow(BeanItemContainer<CalendarEvent> eventContainer) {
		super();
		final Toolkit size = Toolkit.getDefaultToolkit();

		setPosition(2 * size.getScreenSize().width / 5, size.getScreenSize().height / 6);
		this.eventContainer = eventContainer;
		setWidth("330px");
		setHeight("100px");
		setClosable(false);
		setResizable(false);
		// mainVLay settings
		mainVLay = new VerticalLayout();
		mainVLay.setMargin(true);
		mainVLay.setSizeFull();
		setContent(mainVLay);
		// warningL settings
		warningL = new Label("Are you sure you want to delete all events?");
		mainVLay.setWidthUndefined();
		mainVLay.addComponent(warningL);
		mainVLay.setComponentAlignment(warningL, Alignment.TOP_CENTER);
		// buttonsHLay settings
		buttonsGLay = new GridLayout(2, 1);
		buttonsGLay.setWidth(270, Unit.PIXELS);
		mainVLay.addComponent(buttonsGLay);
		mainVLay.setComponentAlignment(buttonsGLay, Alignment.TOP_CENTER);
		// yesB settings
		yesB = new Button("Yes");
		yesB.setIcon(FontAwesome.CHECK);
		yesB.setImmediate(true);
		yesB.setWidthUndefined();
		yesB.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				eventContainer.removeAllItems();
				close();

			}

		});
		buttonsGLay.addComponent(yesB, 0, 0);
		buttonsGLay.setComponentAlignment(yesB, Alignment.MIDDLE_LEFT);

		// noB settings
		noB = new Button("No");
		noB.setIcon(FontAwesome.CLOSE);
		noB.setImmediate(true);
		noB.setWidthUndefined();
		noB.addClickListener(new ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				close();
				
			}
		});
		buttonsGLay.addComponent(noB, 1, 0);
		buttonsGLay.setComponentAlignment(noB, Alignment.MIDDLE_RIGHT);
	}

	public VerticalLayout getMainVLay() {
		return mainVLay;
	}

	public void setMainVLay(VerticalLayout mainVLay) {
		this.mainVLay = mainVLay;
	}

	public GridLayout getButtonsHLay() {
		return buttonsGLay;
	}

	public void setButtonsHLay(GridLayout buttonsHLay) {
		this.buttonsGLay = buttonsHLay;
	}

	public Label getWarningL() {
		return warningL;
	}

	public void setWarningL(Label warningL) {
		this.warningL = warningL;
	}

	public Button getYesB() {
		return yesB;
	}

	public void setYesB(Button yesB) {
		this.yesB = yesB;
	}

	public Button getNoB() {
		return noB;
	}

	public void setNoB(Button noB) {
		this.noB = noB;
	}

}
