package com.redhat.oskutka.rhlp.servlet;

import java.text.ParseException;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Liquid Bread
 */
@WebServlet("/LiquidBread")
public class LiquidBread extends ParsingRestaurantGetter {
	private static final long serialVersionUID = -8938753077408484386L;

	protected String getUrl() {
        return "http://www.liquidbread.cz/liquidbread/liquidbread/mainmenu/daily-menu/";
    }

	@Override
	protected boolean includingDayName() {
		return true;
	}
	
	@Override
	protected String getDayOpeningTag() {
		return "";
	}

	@Override
	protected String getDayClosingTag() {
		return "";
	}
	@Override
	protected String[] getDays() {
		return new String[]{"PONDĚLÍ", "ÚTERÝ", "STŘEDA", "ČTVRTEK", "PÁTEK", "<hr class=\"no-show\" />", "NEDĚLE"};
	}	


	protected String getOfferOfTheWeek(String html) {
		int beginIndex = html.toLowerCase().indexOf("CELOTÝDENNÍ NABÍDKA".toLowerCase()); // first <p that comes after CELOTÝDENNÍ NABÍDKA
		int endIndex = html.toLowerCase().indexOf("pondělí", beginIndex); // up to the first </p> that comes after that
		return html.substring(beginIndex, endIndex);
	}
	
	@Override
	protected String parseHTML(String html) throws ParseException {
		return super.parseHTML(html) + "\n" + getOfferOfTheWeek(html);
	}
}
