package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Prometheus
 */
@WebServlet("/Prometheus")
public class Prometheus extends RestaurantGetter {
	private static final long serialVersionUID = 7609185514424754160L;

	protected String getUrl() {
        return "https://hotel-prometheus.cz/cs/restaurace/denni-menu";
    }

}
