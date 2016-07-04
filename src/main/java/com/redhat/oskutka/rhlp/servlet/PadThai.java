package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class PadThai
 */
@WebServlet("/PadThai")
public class PadThai extends ParsingRestaurantGetter {
	private static final long serialVersionUID = 4L;

	protected String getUrl() {
        return "https://www.zomato.com/brno/pad-thai-kr%C3%A1lovo-pole-brno-sever/menu";
    }

}
