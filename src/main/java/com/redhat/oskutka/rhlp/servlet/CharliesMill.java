package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/CharliesMill")
public class CharliesMill extends ParsingRestaurantGetter {

	private static final long serialVersionUID = -8402121313881447170L;

	@Override
	protected String getUrl() {
		return "https://www.charliesmill.cz/menu/";
	}
	
	@Override
	protected boolean includingDayName() {
		return false;
	}

	@Override
	protected String getDayOpeningTag() {
		return "<div";
	}

	@Override
	protected String getDayClosingTag() {
		return "</div>";
	}

	@Override
	protected String[] getDays() {
		return new String[]{"PONDĚLÍ", "ÚTERÝ", "STŘEDA", "ČTVRTEK", "PÁTEK", "Informace o alergenech", "NEDĚLE"};
	}	

}
