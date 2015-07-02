package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Semilasso
 */
@WebServlet("/Semilasso")
public class Semilasso extends ParsingRestaurantGetter {
	private static final long serialVersionUID = 8475278392840940079L;

	@Override
	protected String getUrl() {
        return "http://www.restaurace-semilasso.cz/dmenu.php";
    }

	@Override
	protected String[] getDays() {
		return new String[]{"Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "NÁPOJ K MENU ZDARMA"};
	}

	@Override
	protected String getDayOpeningTag() {
		return "<table";
	}

	@Override
	protected String getDayClosingTag() {
		return "</table>";
	}
	
	@Override
    protected String getCharset() {
		return "Windows-1250";
	}
	
	protected boolean includingDayName() {
		return true;
	}

}
