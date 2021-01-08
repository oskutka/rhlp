package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Jean Paul's Bistro
 */
@WebServlet("/JPBistro")
public class JPBistro extends ParsingRestaurantGetter {

	@Override
	protected String getUrl() {
        return "https://www.jpbistro.cz/menu-technopark/index.php";
    }

	@Override
	protected String[] getDays() {
		return new String[]{"Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "</table>"};
	}

	@Override
	protected boolean includingDayName() {
		return false;
	}
	
	@Override
	protected String getDayOpeningTag() {
		return "<table";
	}

	@Override
	protected String getDayClosingTag() {
		return "</table>";
	}

	/* There are several "Pondělí" and "Pátek" on the page. We need to get the first ones (not the last ones). */
	@Override
	protected boolean inCaseOfSeveralTodaysOnThePageUseTheLastOne() {
		return false;
	}
}
