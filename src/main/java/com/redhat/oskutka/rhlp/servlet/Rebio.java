package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Rebio
 */
@WebServlet("/Rebio")
public class Rebio extends RestaurantGetter {
	private static final long serialVersionUID = 3901775532572221827L;

	protected String getUrl() {
        return "http://www.rebio.cz/Rebio-Park/Nase-nabidka/gn-ha.folder.aspx";
    }

}
