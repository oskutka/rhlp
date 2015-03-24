package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Opice
 */
@WebServlet("/Opice")
public class Opice extends RestaurantGetter {
    private static final long serialVersionUID = 7226051113762646793L;

    protected String getUrl() {
        return "http://www.u3opic.cz/";
    }

}
