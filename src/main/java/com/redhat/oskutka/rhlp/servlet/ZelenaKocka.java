package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class ZelenaKocka
 */
@WebServlet("/ZelenaKocka")
public class ZelenaKocka extends RestaurantGetter {
	private static final long serialVersionUID = 7809185614424754161L;

	protected String getUrl() {
        return "http://www.zelenakocka.cz/";
    }

}
