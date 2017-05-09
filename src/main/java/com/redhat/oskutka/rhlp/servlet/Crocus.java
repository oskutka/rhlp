package com.redhat.oskutka.rhlp.servlet;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Crocus.
 */
@WebServlet("/Crocus")
public class Crocus extends RestaurantGetter {
	private static final long serialVersionUID = 8896145050453227749L;

	protected String getUrl() {
        return "https://www.menicka.cz/3875-crocus.html";
    }

    @Override
    protected String getCharset() {
    	return "windows-1250";
    }
}
