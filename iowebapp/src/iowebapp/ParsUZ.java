package iowebapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gwt.user.client.Event;

/**
 * Klasa, która parsuje dane z planu UZ.
 * 
 * @author Micha³ Bielecki
 */
public class ParsUZ {
	
	private int[] dayIndex = new int[7];
	private int indexA, indexB, elementSize;
	private List<CalendarEvent> events = new ArrayList<CalendarEvent>();
	
	public ParsUZ(String urlUZ) throws IOException
	{
		Document doc = Jsoup.connect(urlUZ).get();
		Elements elements = doc.getElementsByTag("td");
		elementSize = elements.size();
		dayIndex(elements, dayIndex);
		saveAllData(dayIndex, elements);
	}

	public void dayIndex(Elements elements, int dayIndex[])
	{
		for(int i=0; i<dayIndex.length; i++)
		{
			dayIndex[i]=-1;
		}
		
		for(int i=0; i<elements.size(); i++)
		{
			Element e = elements.get(i);
			if(e.text().equals("Poniedzia³ek"))
			{
				dayIndex[0]=i;
			}
			if(e.text().equals("Wtorek"))
			{
				dayIndex[1]=i;
			}
			if(e.text().equals("Œroda"))
			{
				dayIndex[2]=i;
			}
			if(e.text().equals("Czwartek"))
			{
				dayIndex[3]=i;
			}
			if(e.text().equals("Pi¹tek"))
			{
				dayIndex[4]=i;
			}
			if(e.text().equals("Sobota"))
			{
				dayIndex[5]=i;
			}
			if(e.text().equals("Niedziela"))
			{
				dayIndex[6]=i;
			}
		}
	}
	
	public void getDataFromUZ(CalendarEvent day[], int dayIndex[], Elements elements, int a, int b, List<CalendarEvent> events)
	{
		int counter=1;
		int j=0;
		
		for(int i = a+1; i<b; i++)
		{
			if(counter==1)
			{
				day[j] = new CalendarEvent(null, null, null, null, null, "", "", false);
				events.add(day[j]);
				day[j].setDateCreated(new Date());
				day[j].setDateModified(new Date());
				Element e = elements.get(i);
				day[j].setDescription(day[j].getDescription() + "Grupa: " + e.text() + " ");
				
			}
			if(counter==2)
			{
				Element e = elements.get(i);
				day[j].setDateStart(new Date());
			}
			if(counter==3)
			{
				Element e = elements.get(i);
				day[j].setDateEnd(new Date());
			}
			if(counter==4)
			{
				Element e = elements.get(i);
				day[j].setTitle(e.text());
			}
			if(counter==5)
			{
				Element e = elements.get(i);
				day[j].setDescription(day[j].getDescription() + "RZ: " + e.text()  + " ");
			}
			if(counter==6)
			{
				Element e = elements.get(i);
				day[j].setDescription(day[j].getDescription() + "Wyk³adowca: " + e.text());
			}
			if(counter==7)
			{
				Element e = elements.get(i);
				day[j].setLocation("Sala: " + e.text());
			}
			if(counter==8)
			{
				Element e = elements.get(i);
				//day[j].set //Data
				counter=1;
				j++;
				continue;
			}
			counter++;
		}
	}
	
	public void findIndex(int dayIndex[], int index, Elements elements)
	{
		int guard=0;
		
		for(int i=index+1; i<7; i++)
		{
			if(dayIndex[i]!=-1)
			{
				setIndexA(dayIndex[index]);
				setIndexB(dayIndex[i]);
				guard=1;
				break;
			}
		}
		if(guard==0)
		{
			setIndexA(dayIndex[index]);
			setIndexB(getElementSize());
		}
	}
	
	public void showEvent(CalendarEvent event)
	{
			System.out.println(event.getTitle());
			System.out.println(event.getLocation());
			System.out.println(event.getDescription());
			System.out.println("Data rozpoczecia: " + event.getDateStart());
			System.out.println("Data zakoñczenia: " + event.getDateEnd());
			System.out.println("Data utworzenia: " + event.getDateCreated());
			System.out.println("Data modyfikacji: " + event.getDateModified());
			System.out.println();
	}
	
	public void saveAllData(int dayIndex[], Elements elements)
	{
		for(int i=0; i<7; i++)
		{
			if(dayIndex[i]!=-1)
			{
				findIndex(dayIndex, i, elements);
				CalendarEvent day[] = new CalendarEvent[(indexB-(indexA + 1))/8];
				getDataFromUZ(day, dayIndex, elements, getIndexA(), getIndexB(), events);
			}
		}
	}

	public int getIndexA() {
		return indexA;
	}

	public void setIndexA(int indexA) {
		this.indexA = indexA;
	}

	public int getIndexB() {
		return indexB;
	}

	public void setIndexB(int indexB) {
		this.indexB = indexB;
	}

	public int getElementSize() {
		return elementSize;
	}

	public List<CalendarEvent> getEvents() {
		return events;
	}	
}
