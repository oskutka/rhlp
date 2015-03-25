package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Vista
 */
@WebServlet("/Vista")
public class Vista extends RestaurantGetter {
	private static final long serialVersionUID = -2089022964121446361L;

	protected String getUrl() {
        return "https://www.zomato.com/cs/brno/hotel-vista-medl%C3%A1nky-brno-sever#denni_menu";
    }

}
