package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Liquid Bread
 */
@WebServlet("/LiquidBread")
public class LiquidBread extends ZomatoRestaurantGetter {
	private static final long serialVersionUID = -7476963319576983227L;

    @Override
    protected String getZomatoId() {
    	return "18599936";
    }
}
