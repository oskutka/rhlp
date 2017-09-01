package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Moname
 */
@WebServlet("/Moname")
public class Moname extends ParsingRestaurantGetter {
	
	@Override
	protected String getUrl() {
        return "http://www.moname.cz/poledni-nabidka/";
    }

	@Override
	protected String[] getDays() {
		return new String[]{"Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "Týdenní"};
	}

	@Override
	protected String getDayOpeningTag() {
		return "<p";
	}

	@Override
	protected String getDayClosingTag() {
		return "</p>";
	}
	
	@Override
	protected boolean includingDayName() {
		return true;
	}

}
