package iowebapp;

import static eu.hurion.vaadin.heroku.VaadinForHeroku.forApplication;
import static eu.hurion.vaadin.heroku.VaadinForHeroku.herokuServer;

public class Main {
	
	public static void main(String[] args) {
		 herokuServer(forApplication(IowebappUI.class)).start();
	}

}
