package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Sporthotel
 */
@WebServlet("/Sporthotel")
public class Sporthotel extends RestaurantGetter {
	private static final long serialVersionUID = 8475278392840940079L;

	protected String getUrl() {
        return "http://www.a-sporthotel.cz/menu/";
    }

}
