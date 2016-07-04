package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Opice
 */
@WebServlet("/Opice")
public class Opice extends ParsingRestaurantGetter {
    private static final long serialVersionUID = 7226051113762646793L;

    protected String getUrl() {
        return "http://www.u3opic.cz/denni-menu/";
    }

    @Override
    protected String getCharset() {
    	return "windows-1250";
    }
}