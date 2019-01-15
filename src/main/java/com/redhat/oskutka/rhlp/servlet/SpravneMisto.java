package com.redhat.oskutka.rhlp.servlet;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class SpravneMisto.
 */
@SuppressWarnings("serial")
@WebServlet("/SpravneMisto")
public class SpravneMisto extends ZomatoRestaurantGetter {
	@Override
	protected String getZomatoId() {
		return "18563357";
	}

}
