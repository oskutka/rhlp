package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Sporthotel
 */
@WebServlet("/Sporthotel")
public class Sporthotel extends ParsingRestaurantGetter {
	private static final long serialVersionUID = 8475278392840940079L;

	protected String getUrl() {
        return "http://www.a-sporthotel.cz/menu/";
    }

	@Override
	protected String[] getDays() {
		return new String[]{"Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "RIGHT"};
	}
	
	@Override
	protected String parseHTML(String freshMenuHTML) {
		String result = super.parseHTML(freshMenuHTML);
		int todayIndex = result.indexOf("<tr>",result.indexOf(getToday()));
		int tomorrowIndex = result.lastIndexOf("</tr>", result.indexOf(getTomorrow())) + "</tr>".length();
		result = result.substring(todayIndex, tomorrowIndex);
		return result;
	}
	
}
