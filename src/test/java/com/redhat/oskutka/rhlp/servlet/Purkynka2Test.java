package com.redhat.oskutka.rhlp.servlet;

public class Purkynka2Test extends ParsingTest {

	@SuppressWarnings("serial")
	public Purkynka2Test() {
		// creates ParsingRestaurantGetter that retrieves the html from file and
		// also takes weekday from here instead of the current date
		parser = new Purkynka() {
			public String getFreshMenuHTML() throws java.io.IOException, java.text.ParseException {
				return parseHTML(readFile(getClass().getResource(getHtmlFileName()).getFile()));
			};

			public int getDayOfWeek() {
				return weekday;
			}
		};
	}
}
