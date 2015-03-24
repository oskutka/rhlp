package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Kotelna
 */
@WebServlet("/Kotelna")
public class Kotelna extends RestaurantGetter {
	private static final long serialVersionUID = -3544687922716446281L;

	protected String getUrl() {
        return "https://www.zomato.com/cs/brno/u-kotelny-kr%C3%A1lovo-pole-brno-sever#denni_menu";
    }

}
