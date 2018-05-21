package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Opice
 */
@WebServlet("/LiquidBread")
public class  extends ZomatoRestaurantGettr {
    private static final long serialVersionUID = 1L;

    @Override
    protected String getZomatoId() {
    	return "18599936";
    }
}
