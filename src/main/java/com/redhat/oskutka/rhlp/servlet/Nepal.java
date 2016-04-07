package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;


/**
 * Servlet implementation class Nepal
 */
@WebServlet("/Nepal")
public class Nepal extends ParsingRestaurantGetter {
	private static final long serialVersionUID = 7504818210814030684L;

	@Override
	protected String getUrl() {
		return "http://nepalbrno.cz/weekly-menu/";
	}

	@Override
	protected String getDayOpeningTag() {
		return "<tr>";
	}
	
	@Override
	protected String getDayClosingTag() {
		return "</tr>";
	}
	
	@Override
	protected String[] getDays() {
		return new String[]{"Monday--", "Tuesday--", "Wednesday--", "Thursday--", "Friday--", "nbsp", "Sunday"};
	}

}
