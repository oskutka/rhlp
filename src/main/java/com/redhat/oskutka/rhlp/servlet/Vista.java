package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Vista
 */
@SuppressWarnings("serial")
@WebServlet("/Vista")
public class Vista extends ZomatoRestaurantGetter {

	@Override
	protected String getZomatoId() {
		return "brno/hotel-vista-medl%C3%A1nky-brno-sever";
	}

}
