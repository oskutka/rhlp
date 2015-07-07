package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;


/**
 * Servlet implementation class Purkynka
 */
@WebServlet("/Purkynka")
public class Purkynka extends ParsingRestaurantGetter {
	private static final long serialVersionUID = 1520663750240248873L;

	protected String getUrl() {
    	return "http://www.napurkynce.cz/denni-menu/";
	}

	protected String[] getDays() {
		return new String[]{"PONDĚLÍ", "ÚTERÝ", "STŘEDA", "ČTVRTEK", "PÁTEK", "Akce"};
	}
	
	@Override
	protected boolean includingDayName() {
		return true;
	}

	protected String getDayOpeningTag() {
		return "<p>";
	}
	
	protected String getDayClosingTag() {
		return "</p>";
	}
}