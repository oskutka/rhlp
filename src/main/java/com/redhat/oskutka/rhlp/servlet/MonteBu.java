package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class MonteBu 
 */
@WebServlet("/MonteBu")
public class MonteBu extends RestaurantGetter {
	private static final long serialVersionUID = 115451115456630158L;

	protected String getUrl() {
        return "http://www.monte-bu.cz/menu.php";
    }

}
