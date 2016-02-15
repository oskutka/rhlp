package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Bavorska
 */
@WebServlet("/Bavorska")
public class Bavorska extends ParsingRestaurantGetter {
	private static final long serialVersionUID = 4064993267050512376L;

	protected String getUrl() {
        return "https://www.zomato.com/cs/bavorska/menu";
    }

}
