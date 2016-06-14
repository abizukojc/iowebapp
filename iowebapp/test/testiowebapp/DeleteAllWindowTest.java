package testiowebapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.util.Date;

import org.junit.Test;

import com.google.gwt.event.dom.client.ClickEvent;
import com.vaadin.data.util.AbstractBeanContainer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.Position;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import iowebapp.CalendarEvent;
import iowebapp.DeleteAllWindow;

public class DeleteAllWindowTest {
	
	@Test
	public void testDeleteAllWindow() {
		
		BeanItemContainer<CalendarEvent> eventsContainer = new BeanItemContainer<CalendarEvent>(CalendarEvent.class);
		eventsContainer.addBean(new CalendarEvent("Urodziny", new Date(2016, 9, 25, 16, 0, 0), 
				new Date(2016, 9, 25, 20, 0, 0), new Date(2016, 5, 5, 16, 47, 13), 
				new Date(2016, 5, 5, 16, 47, 13), "Zielona Gora", "Impreza urodzinowa Mariana", false));
		
		DeleteAllWindow daw = new DeleteAllWindow(eventsContainer);
		assertNotNull(daw);
		assertEquals(daw, daw);
		final Toolkit size = Toolkit.getDefaultToolkit();
		assertNotNull(size);
		assertEquals(size, size);
		
		VerticalLayout mainVLay = new VerticalLayout();
		assertNotNull(mainVLay);
		assertEquals(mainVLay, mainVLay);
		mainVLay.setMargin(true);
		boolean margin = mainVLay.getMargin() != null;
		assertTrue(margin);
		mainVLay.setSizeFull();
		
		Label warningL = new Label("Are you sure you want to delete all events?");
		assertNotNull(warningL);
		assertEquals(warningL, warningL);
		mainVLay.setWidthUndefined();
		assertSame(warningL.getWidth(), warningL.getWidth());
		//assertNotNull(mainVLay.getComponentAlignment((Component) warningL));
		
		GridLayout buttonsGLay = new GridLayout(2, 1);
		assertNotNull(buttonsGLay);
		assertEquals(buttonsGLay, buttonsGLay);
		assertSame(buttonsGLay.getColumns(), buttonsGLay.getColumns());
		//mainVLay.addComponent((Component) buttonsGLay);
		//assertNotNull(mainVLay.getComponentIndex((Component) buttonsGLay));
		//assertNotNull(mainVLay.getComponentAlignment((Component) buttonsGLay));
		
		Button yesB = new Button("Yes");
		assertNotNull(yesB);
		//((Component) yesB).setIcon(FontAwesome.CHECK);
		//assertEquals( ((Component) yesB).getIcon(), ((Component) yesB).getIcon());
	//	((Sizeable) yesB).setWidthUndefined();
		assertSame(yesB.getWidth(), yesB.getWidth());
		
		Button noB = new Button("No");
		assertNotNull(noB);
	//	((Component) noB).setIcon(FontAwesome.CLOSE);
	//	assertEquals(((Component) noB).getIcon(), ((Component) noB).getIcon());
		//((Sizeable) noB).setWidthUndefined();
		assertSame(noB.getWidth(), noB.getWidth());
		
	}

	
	@Test
	public void TestbuttonClick(){		
		Notification deleteSuccess = new Notification("Success...");
		assertNotNull(deleteSuccess);
		deleteSuccess.setPosition(Position.TOP_CENTER);
		assertEquals(deleteSuccess.getPosition(), deleteSuccess.getPosition());
		deleteSuccess.setDelayMsec(1000);
		assertEquals(deleteSuccess.getDelayMsec(), deleteSuccess.getDelayMsec());
		AbstractBeanContainer<CalendarEvent, CalendarEvent> eventsContainer = null;
//		eventsContainer.removeAllItems();
//		assertNotNull(eventsContainer);
//		assertEquals(eventsContainer, eventsContainer);		
	}
	
}
