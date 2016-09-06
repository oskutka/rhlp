package com.redhat.oskutka.rhlp.servlet;

import java.util.Date;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Molino
 */
@WebServlet("/Molino")
public class Molino extends RestaurantGetter {
	private static final long serialVersionUID = 7609185514424754160L;
	
	@SuppressWarnings("deprecation")
	protected String getUrl() {
		String days[] = {"nedele", "pondeli", "utery", "streda", "ctvrtek", "patek", "sobota"};
		Date dateHolder = new Date();
		int day = dateHolder.getDay(),
			date = dateHolder.getDate(),
			month = dateHolder.getMonth() + 1;
		return "http://www.molinorestaurant.cz/poledni-menu/" + days[day] + "-" + date + "-" + month + "/";
    
    }

}
