package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Bavorska
 */
@WebServlet("/Bavorska")
public class Bavorska extends ZomatoRestaurantGetter {
	private static final long serialVersionUID = 4064993267050512376L;

	@Override
	protected String getZomatoId() {
		return "16505905";
	}

}
