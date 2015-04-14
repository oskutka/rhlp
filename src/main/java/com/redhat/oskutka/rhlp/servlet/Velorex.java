package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Kanas
 */
@WebServlet("/Velorex")
public class Velorex extends RestaurantGetter {
    private static final long serialVersionUID = 5654154721254868465L;

	@Override
    protected String getUrl() {
        return "http://www.restauracevelorex.cz";
    }

}
