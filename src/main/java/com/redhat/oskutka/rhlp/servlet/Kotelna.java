package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Kotelna
 */
@WebServlet("/Kotelna")
public class Kotelna extends ZomatoRestaurantGetter {
	private static final long serialVersionUID = -3544687922716446281L;

	@Override
	protected String getZomatoId() {
		return "brno/u-kotelny-kr%C3%A1lovo-pole-brno-sever";
	}

}
