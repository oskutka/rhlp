package com.redhat.oskutka.rhlp.servlet;

public class PurkynkaTest extends ParsingTest {

	@SuppressWarnings("serial")
	public PurkynkaTest() {
		// creates ParsingRestaurantGetter that retrieves the html from file and
		// also takes weekday from here instead of the current date
		parser = new Purkynka() {
			public String getFreshMenuHTML() throws java.io.IOException, java.text.ParseException {
				return parseHTML(preParseHTML(readFile(getClass().getResource(getHtmlFileName()).getFile())));
			};

			public int getDayOfWeek() {
				return weekday;
			}
		};
	}
}
