package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Kotelna
 */
@SuppressWarnings("serial")
@WebServlet("/Kotelna")
public class Kotelna extends ZomatoRestaurantGetter {

	@Override
	protected String getZomatoId() {
		return "16506016";
	}

}
