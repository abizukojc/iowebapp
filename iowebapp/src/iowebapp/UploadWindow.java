package iowebapp;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;

public class UploadWindow extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8895860555262151677L;
	public String path = null;
	BeanItemContainer<CalendarEvent> eventsContainer;

	@SuppressWarnings("serial")
	public UploadWindow(final BeanItemContainer<CalendarEvent> eventsContainer) {
		// window settings
		super();
		this.eventsContainer = eventsContainer;
		final Toolkit size = Toolkit.getDefaultToolkit();
		path = getClass().getResource("CalendarEvent.class").getPath();
		path = path.substring(0, path.length() - 19);
		setPosition(2 * size.getScreenSize().width / 5, size.getScreenSize().height / 6);
		setWidth("330px");
		setHeight("150px");
		setClosable(true);
		setResizable(false);

		// mainVLay settings
		VerticalLayout mainVLay;
		mainVLay = new VerticalLayout();
		mainVLay.setMargin(false);
		mainVLay.setSizeFull();
		setContent(mainVLay);
		HorizontalLayout topHLay = new HorizontalLayout();
		topHLay.setMargin(new MarginInfo(true, false, true, false));
		topHLay.setSpacing(true);
		TextField pathTF = new TextField();
		pathTF.setWidth(150, Unit.PIXELS);

		Upload uploadButton = new Upload();
		uploadButton.setButtonCaption("Browse...");
		uploadButton.setReceiver(new Receiver() {

			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {
				FileOutputStream fos = null;
				try {
					if (pathTF.getValue().equals("") == false) {
						new File(path + pathTF.getValue()).delete();
					}
					String extension = filename.substring(filename.length() - 4, filename.length());
					if (!(extension.equals(".ics") == true || extension.equals(".ICS") == true)) {
						final Notification badExtensionError = new Notification("You need to choose .ics file!",
								Notification.Type.ERROR_MESSAGE);
						badExtensionError.setPosition(Position.TOP_CENTER);
						badExtensionError.setDelayMsec(2000);
						badExtensionError.show(Page.getCurrent());
						// When I tried to return null there was an
						// uploadException so I return this.
						return new ByteArrayOutputStream();
					}
					File file = new File(path + filename);
					fos = new FileOutputStream(file);
				} catch (final java.io.FileNotFoundException e) {
					Notification openError = new Notification("Could not open file<br/>", e.getMessage(),
							Notification.Type.ERROR_MESSAGE);
					openError.setDelayMsec(2000);
					openError.setPosition(Position.TOP_CENTER);
					openError.show(Page.getCurrent());
					return null;
				}
				return fos;
			}

		});
		uploadButton.setImmediate(true);
		uploadButton.addSucceededListener(new SucceededListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 8342221784732468501L;

			@Override
			public void uploadSucceeded(SucceededEvent event) {
				String filename = event.getFilename();
				String extension = filename.substring(filename.length() - 4, filename.length());
				if (extension.equals(".ics") == true || extension.equals(".ICS") == true) {
					pathTF.setValue(event.getFilename());
				}

			}

		});
		Button addButton = new Button("Confirm Upload");
		addButton.setImmediate(true);
		addButton.setSizeUndefined();
		addButton.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -1846907558871330146L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					if (!pathTF.getValue().equals("")) {
						File file = new File(path + pathTF.getValue());
						BufferedReader fileReader = new BufferedReader(
								new InputStreamReader(new FileInputStream(file), "UTF-8"));
						String line = null;
						String title = null;
						String location = null;
						String description = null;
						Date dateStart = null;
						Date dateEnd = null;
						Date dateCreated = null;
						Date dateModified = null;
						boolean allDay = false;
						while ((line = fileReader.readLine()) != null) {
							if (line.contains("DTSTART")) {
								dateStart = stringToDateConvert(line.substring("DTSTART".length()));
								if (line.charAt("DTSTART".length()) == ';') {
									allDay = true;
								}
							}
							if (line.contains("DTEND")) {

								dateEnd = stringToDateConvert(line.substring("DTEND".length()));
							}
							if (line.contains("CREATED")) {

								dateCreated = stringToDateConvert(line.substring("CREATED".length()));
							}
							if (line.contains("DESCRIPTION:")) {
								description = line.substring("DESCRIPTION:".length());
							}
							if (line.contains("LAST-MODIFIED")) {

								dateModified = stringToDateConvert(line.substring("LAST-MODIFIED".length()));
								continue;
							}
							if (line.contains("LOCATION:")) {
								location = line.substring("LOCATION:".length());
								continue;
							}
							if (line.contains("SUMMARY:")) {
								title = line.substring("SUMMARY:".length());
								continue;
							}
							if (line.contains("END:VEVENT")) {
								eventsContainer.addBean(new CalendarEvent(title, dateStart, dateEnd, dateCreated,
										dateModified, location, description, allDay));
								continue;
							}
						}
						final Notification addSuccess = new Notification("Successfully imported iCal file!",
								Notification.Type.ASSISTIVE_NOTIFICATION);
						addSuccess.setPosition(Position.TOP_CENTER);
						addSuccess.setDelayMsec(1000);
						addSuccess.show(Page.getCurrent());
						fileReader.close();
						file.delete();
						pathTF.setValue("");

					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

		addCloseListener(new CloseListener() {

			@Override
			public void windowClose(CloseEvent e) {
				if (!pathTF.getValue().equals("")) {
					new File(path + pathTF.getValue()).delete();
				}

			}
		});

		topHLay.addComponent(pathTF);
		topHLay.addComponent(uploadButton);
		mainVLay.addComponent(topHLay);
		mainVLay.setComponentAlignment(topHLay, Alignment.MIDDLE_CENTER);
		mainVLay.addComponent(addButton);
		mainVLay.setComponentAlignment(addButton, Alignment.MIDDLE_CENTER);
	}

	@SuppressWarnings("deprecation")
	public Date stringToDateConvert(String dateString) {
		final int year;
		final int month;
		final int day;
		final int hours;
		final int minutes;
		final int seconds;
		if (dateString.charAt(0) == ':') {
			year = Integer.parseInt(dateString.substring(1, 5)) - 1900;
			month = Integer.parseInt(dateString.substring(5, 7));
			day = Integer.parseInt(dateString.substring(7, 9));
			hours = Integer.parseInt(dateString.substring(10, 12));
			minutes = Integer.parseInt(dateString.substring(12, 14));
			seconds = Integer.parseInt(dateString.substring(14, 16));
			return new Date(year, month, day, hours, minutes, seconds);
		} else {
			year = Integer.parseInt(dateString.substring(12, 16)) - 1900;
			month = Integer.parseInt(dateString.substring(16, 18));
			day = Integer.parseInt(dateString.substring(18, 20));
			return new Date(year, month, day);
		}
	}

}
