package com.redhat.oskutka.rhlp.servlet;

public class SporthotelTest extends ParsingTest {

	@SuppressWarnings("serial")
	public SporthotelTest() {
		// creates ParsingRestaurantGetter that retrieves the html from file and
		// also takes weekday from here instead of the current date
		parser = new Sporthotel() {
			public String getFreshMenuHTML() throws java.io.IOException, java.text.ParseException {
				return parseHTML(readFile(getClass().getResource(getHtmlFileName()).getFile()));
			};

			public int getDayOfWeek() {
				return weekday;
			}
		};
	}

}
