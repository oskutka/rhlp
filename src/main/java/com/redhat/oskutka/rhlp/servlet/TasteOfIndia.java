package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/TasteOfIndia")
public class TasteOfIndia extends ParsingRestaurantGetter {

	private static final long serialVersionUID = -8402121313881447170L;

	@Override
	protected String getUrl() {
		return "https://www.taste-of-india.cz";
	}
	
	@Override
	protected boolean includingDayName() {
		return true;
	}

	@Override
	protected String getDayOpeningTag() {
		return "<li>";
	}

	@Override
	protected String getDayClosingTag() {
		return "</li>";
	}

	@Override
	protected String[] getDays() {
		return new String[]{"Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "</ul>", "Neděle"};
	}	

}
