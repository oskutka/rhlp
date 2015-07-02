package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Vaclav
 */
@WebServlet("/Vaclav")
public class Vaclav extends RestaurantGetter {
	private static final long serialVersionUID = 2403906328393537944L;

	@Override
    protected String getUrl() {
        return "http://www.krcmausvatehovaclava.cz/denni-menu";
    }

}
