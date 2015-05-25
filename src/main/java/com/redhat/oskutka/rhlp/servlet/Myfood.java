package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class My Food Truck
 */
@WebServlet("/Myfood")
public class Myfood extends RestaurantGetter {

	private static final long serialVersionUID = 168925516869097823L;

	@Override
    protected String getUrl() {
        return "https://eshop.myfoodmarket.cz/roznos";
    }

}
