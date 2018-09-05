package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Moname
 */
@WebServlet("/Moname")
public class Moname extends RestaurantGetter {
	
	@Override
	protected String getUrl() {
        return "http://www.moname.cz/poledni-nabidka/";
    }

}
