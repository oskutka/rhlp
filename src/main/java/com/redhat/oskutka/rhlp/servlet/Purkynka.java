package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;


/**
 * Servlet implementation class Purkynka
 */
@WebServlet("/Purkynka")
public class Purkynka extends ParsingRestaurantGetter {
	private static final long serialVersionUID = 1520663750240248873L;

	protected String getUrl() {
    	return "http://www.napurkynce.cz/denni-menu/";
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
		return new String[]{"Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "<hr class=\"no-show\" />", "Neděle"};
	}
	
	/*
	 * The html of the page is sometimes 'broken' around symbols with diacritics. 
	 * This function fixes it at least for the days of the week. 
	 */
	@Override
	protected String preParseHTML(String html) {
		html = html.replaceAll("(?iu)Pond(<[^>]*>)*ě(<[^>]*>)*l(<[^>]*>)*í","Pondělí");
		html = html.replaceAll("(?iu)Ú(<[^>]*>)*ter(<[^>]*>)*ý","Úterý");
		html = html.replaceAll("(?iu)St(<[^>]*>)*ř(<[^>]*>)*eda","Středa");
		html = html.replaceAll("(?iu)Č(<[^>]*>)*tvrtek","Čtvrtek");
		html = html.replaceAll("(?iu)P(<[^>]*>)*á(<[^>]*>)*tek","Pátek");
		return super.preParseHTML(html);
	}
	
	@Override
	protected boolean inCaseOfSeveralTomorrowsOnThePageUseTheLastOne() {
		return false;
	}
}