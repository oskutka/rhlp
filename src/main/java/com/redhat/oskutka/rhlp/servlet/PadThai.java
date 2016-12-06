package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class PadThai
 */
@WebServlet("/PadThai")
public class PadThai extends ZomatoRestaurantGetter {
	private static final long serialVersionUID = 4L;

	@Override
	protected String getZomatoId() {
		return "brno/pad-thai-kr%C3%A1lovo-pole-brno-sever";
	}

}
