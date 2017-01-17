package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class CookPoint
 */
@WebServlet("/Cookpoint")
public class CookPoint extends RestaurantGetter {
	private static final long serialVersionUID = 5185240436706683195L;

	protected String getUrl() {
        return "http://cookpoint.cz";
    }

}
