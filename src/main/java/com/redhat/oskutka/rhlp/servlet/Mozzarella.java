package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Menza
 */
@WebServlet("/Mozzarella")
public class Menza extends RestaurantGetter {
	private static final long serialVersionUID = -1438512371900562512L;

	protected String getUrl() {
        return "http://www.kam.vutbr.cz/?p=menu&provoz=18";
    }

}
