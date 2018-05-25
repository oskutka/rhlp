package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Portoriko
 */
@WebServlet("/Portoriko")
public class Portoriko extends ZomatoRestaurantGetter {
	private static final long serialVersionUID = -687991492884005033L;

	@Override
	protected String getZomatoId() {
		return "16506862";
	}

}
