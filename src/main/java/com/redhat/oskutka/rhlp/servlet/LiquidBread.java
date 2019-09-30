package com.redhat.oskutka.rhlp.servlet;

import java.text.ParseException;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Liquid Bread
 * It's a child of Purkynka, since they have the same owner and similar web pages which share the same quirks 
 */
@WebServlet("/LiquidBread")
public class LiquidBread extends Purkynka {
	private static final long serialVersionUID = -8938753077408484386L;

	protected String getUrl() {
        return "http://www.liquidbread.cz/liquidbread/liquidbread/mainmenu/daily-menu/";
    }

	protected String getOfferOfTheWeek(String html) {
		int beginIndex = html.toLowerCase().indexOf("CELOTÝDENNÍ NABÍDKA".toLowerCase());
		if (beginIndex == -1) {
			beginIndex = html.toLowerCase().indexOf("CELOTÝDENNÍ&nbsp;NABÍDKA".toLowerCase());
		}
		int endIndex = html.toLowerCase().indexOf("pondělí", beginIndex); // up to the first </p> that comes after that
		return html.substring(beginIndex, endIndex);
	}
	
	@Override
	protected String preParseHTML(String html) {
		html = html.replaceAll("(?iu)Celot(<[^>]*>)*ý(<[^>]*>)*denn(<[^>]*>)*í(<[^>]*>)* nab(<[^>]*>)*í(<[^>]*>)*dka","Celotýdenní nabídka");
		html = html.replaceAll("(?iu)Celot(<[^>]*>)*ý(<[^>]*>)*denn(<[^>]*>)*í(<[^>]*>)*&nbsp;(<[^>]*>)*nab(<[^>]*>)*í(<[^>]*>)*dka","Celotýdenní&nbsp;nabídka");
		return super.preParseHTML(html);
	}
	
	@Override
	protected String parseHTML(String html) throws ParseException {
		html = preParseHTML(html);
		return super.parseHTML(html) + "\n" + getOfferOfTheWeek(html);
	}
}
