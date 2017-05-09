package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Bavorska
 */
@WebServlet("/Bavorska")
public class Bavorska extends RestaurantGetter {
	private static final long serialVersionUID = 4064993267050512376L;

	@Override
	protected String getUrl() {
		return "https://www.menicka.cz/4849-bar-a-restaurant-bavorska.html";
	}

    @Override
    protected String getCharset() {
    	return "windows-1250";
    }
}
