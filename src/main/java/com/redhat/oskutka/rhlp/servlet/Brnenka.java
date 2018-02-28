package com.redhat.oskutka.rhlp.servlet;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Brnenka.
 */
@WebServlet("/Brnenka")
public class Brnenka extends ZomatoRestaurantGetter {
	private static final long serialVersionUID = -2638821397207571142L;

	@Override
	protected String getZomatoId() {
		return "16506969";
	}

}
