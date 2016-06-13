package iowebapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Klasa, która zapisuje dane z planu UZ.
 * 
 * @author Micha³ Bielecki
 */
public class ParsUZ {
	
	/** Tablica przechowuj¹ca informacje czy dzien istnieje w planie. Wartosc 1 = istnieje. */
	private int[] dayIndex = new int[7];
	
	/** Indeks dnia w kodzie html */
	private int indexA;
	
	/** Indeks nastepnego dnia w kodzie html */
	private int indexB;
	
	/** Zmienna, ktora przechowuje rozmiar elements */
	private int elementSize;
	
	/** Lista przechowuj¹ca wydarzenia */
	private List<CalendarEvent> events = new ArrayList<CalendarEvent>();
	
	/**
	 * Kontruktor klasy ParsUZ. Generuje liste wydarzen.
	 * 
	 * @param urlUZ
	 * @throws IOException
	 */
	public ParsUZ(String urlUZ) throws IOException
	{
		Document doc = Jsoup.connect(urlUZ).get();
		Elements elements = doc.getElementsByTag("td");
		elementSize = elements.size();
		dayIndex(elements, dayIndex);
		saveAllData(dayIndex, elements);
	}

	/**
	 * Metoda sprawdza, ktore dni istnieja w planie. Jezeli dzien jest w planie wstawia wartosc 1 to tablicy.
	 * 
	 * @param elements
	 * @param dayIndex
	 */
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
	
	/**
	 * Metoda zapisuje dane z 1 konkretnego dnia.
	 * 
	 * @param day
	 * @param dayIndex
	 * @param elements
	 * @param a indeks dnia
	 * @param b indeks nastepnego dnia
	 * @param events
	 */
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
				day[j].setDescription(day[j].getDescription() + "Grupa: " + e.text() + ", ");
				
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
				day[j].setDescription(day[j].getDescription() + "RZ: " + e.text());
			}
			if(counter==6)
			{
				Element e = elements.get(i);
				day[j].setDescription(day[j].getDescription() + ", Wyk³adowca: " + e.text());
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
	
	/**
	 * Metoda znajduje odpowiednie indeksy dni.
	 * 
	 * @param dayIndex
	 * @param index
	 * @param elements
	 */
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
	
	/**
	 * Metoda zapisuje dane z ka¿dego dnia.
	 * 
	 * @param dayIndex
	 * @param elements
	 */
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

	/**
	 * Metoda, ktora daje dostep do pola IndexA
	 * @return
	 */
	public int getIndexA() {
		return indexA;
	}

	/**
	 * Metoda, ktora pozwala zmieniac pole indexA.
	 * @param indexA
	 */
	public void setIndexA(int indexA) {
		this.indexA = indexA;
	}

	/**
	 * Metoda, ktora daje dostep do pola IndexB.
	 * @return
	 */
	public int getIndexB() {
		return indexB;
	}

	/**
	 * Metoda, ktora pozwala na zmiane pola indexB
	 * @param indexB
	 */
	public void setIndexB(int indexB) {
		this.indexB = indexB;
	}

	/**
	 * Metoda, ktora daje dostep do pola elementSize.
	 * @return
	 */
	public int getElementSize() {
		return elementSize;
	}

	/**
	 * Metoda, ktora daje dostep do listy wydarzen.
	 * 
	 * @return
	 */
	public List<CalendarEvent> getEvents() {
		return events;
	}	
}
