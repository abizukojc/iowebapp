package iowebapp;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Klasa, która parsuje dane z planu UZ.
 * 
 * @author Micha³ Bielecki
 */
public class ParsUZ {
	
	private int[] dayIndex = new int[7];
	private int indexA, indexB;
	
	public ParsUZ(String urlUZ) throws IOException
	{
		Document doc = Jsoup.connect(urlUZ).get();
		Elements elements = doc.getElementsByTag("td");
		
		dayIndex(elements, dayIndex);
		
		if(dayIndex[0]!=-1)
		{
			findIndex(dayIndex, 3);
			CalendarEvent Monday[] = new CalendarEvent[(indexB-(indexA + 1))/8];
			getDataFromUZ(Monday, dayIndex, elements, getIndexA(), getIndexB());
			
			System.out.println(Monday[1].getTitle());
			System.out.println(Monday[1].getLocation());
			System.out.println(Monday[1].getDescription());
		}
		
		
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
	
	public void getDataFromUZ(CalendarEvent day[], int dayIndex[], Elements elements, int a, int b)
	{
		int counter=1;
		int j=0;
		
		for(int i = a+1; i<b; i++)
		{
			if(counter==1)
			{
				day[j] = new CalendarEvent(null, null, null, null, null, "", "", false);
				Element e = elements.get(i);
				//day[j].setDescription("Grupa: " + e.text() + ", ");
				day[j].setDescription(day[j].getDescription() + "Grupa: " + e.text() + ", ");
				
			}
			if(counter==2)
			{
				Element e = elements.get(i);
				//Date dataRoz = new Date
				//day[j].setDateStart(dataRoz);
			}
			if(counter==3)
			{
				Element e = elements.get(i);
				//day[j].setGodzZak(e.text());
			}
			if(counter==4)
			{
				Element e = elements.get(i);
				day[j].setTitle(e.text());
			}
			if(counter==5)
			{
				Element e = elements.get(i);
				//day[j].setDescription("RZ: " + e.text());
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
	
	public void findIndex(int dayIndex[], int index)
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
			setIndexB(dayIndex[6]);
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
}
