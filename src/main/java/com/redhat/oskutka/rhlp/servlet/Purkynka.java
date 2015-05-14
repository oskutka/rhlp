package com.redhat.oskutka.rhlp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Purkynka
 */
@WebServlet("/Purkynka")
public class Purkynka extends ParsingRestaurantGetter {
	private static final long serialVersionUID = 1520663750240248873L;

	protected String getUrl() {
    	return "http://www.napurkynce.cz/denni-menu/";
	}

	@Override
	protected String[] getDays() {
		return new String[]{"PONDĚLÍ", "ÚTERÝ", "STŘEDA", "ČTVRTEK", "PÁTEK", "NEDĚLE"};
	}
	
	@Override
	protected String parseHTML(String freshMenuHTML) {
		String result = super.parseHTML(freshMenuHTML);
		int todayIndex = result.lastIndexOf("<div>",result.indexOf(getToday()));
		int tomorrowIndex = result.lastIndexOf("</div>", result.indexOf(getTomorrow())) + "</div>".length();
		result = result.substring(todayIndex, tomorrowIndex);
		return result;
	}

}

