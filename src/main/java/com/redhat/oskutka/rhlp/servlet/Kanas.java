package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Kanas
 */
@WebServlet("/Kanas")
public class Kanas extends RestaurantGetter {
    private static final long serialVersionUID = 3080185090102093278L;

    protected String getUrl() {
        return "http://kanas.cz/stranka/jidelna";
    }

}
