package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Welcome
 */
@WebServlet("/Welcome")
public class Welcome extends RestaurantGetter {
	private static final long serialVersionUID = 3574455625348862403L;

	protected String getUrl() {
        return "http://restaurant-welcome.cz/denni-menu/";
    }

}
