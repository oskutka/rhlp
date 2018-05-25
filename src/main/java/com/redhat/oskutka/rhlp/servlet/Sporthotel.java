package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Sporthotel
 */
@WebServlet("/Sporthotel")
public class Sporthotel extends ParsingRestaurantGetter {
	private static final long serialVersionUID = 8475278392840940079L;

	@Override
	protected String getUrl() {
        return "http://www.a-sporthotel.cz/menu/";
    }

	@Override
	protected String[] getDays() {
		return new String[]{"Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "RIGHT"};
	}

	@Override
	protected String getDayOpeningTag() {
		return "<tr>";
	}

	@Override
	protected String getDayClosingTag() {
		return "</tr>";
	}
	
	
	/* There are several "středa" and "čtvrtek" on the page. We need to get the first ones (not the last ones). */
	@Override
	protected int getTodayIndex(String html) {
		return html.toLowerCase().indexOf(getToday().toLowerCase());
	}

	@Override
	protected int getTomorrowIndex(int beginIndex, String html) {
		return html.toLowerCase().indexOf(getTomorrow().toLowerCase());
	}

}
