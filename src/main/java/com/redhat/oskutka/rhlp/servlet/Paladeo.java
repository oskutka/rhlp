package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Paladeo
 */
@WebServlet("/Paladeo")
public class Paladeo extends ParsingRestaurantGetter {
	private static final long serialVersionUID = 2441840709878659316L;

	protected String getUrl() {
        return "http://www.paladeo.cz/menu";
    }

	@Override
	protected String[] getDays() {
		return new String[]{"Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "K obědu"};
	}	
}
