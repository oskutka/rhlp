package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Prometheus
 */
@WebServlet("/Prometheus")
public class Prometheus extends RestaurantGetter {
	private static final long serialVersionUID = 7609185514424754160L;

	protected String getUrl() {
        return "http://www.hotel-prometheus.cz/restaurace/denni-menu/";
    }

}
