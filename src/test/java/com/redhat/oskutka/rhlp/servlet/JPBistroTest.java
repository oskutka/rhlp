package com.redhat.oskutka.rhlp.servlet;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;

public class JPBistroTest extends ParsingTest {

	@SuppressWarnings("serial")
	public JPBistroTest() {
		// creates ParsingRestaurantGetter that retrieves the html from file and
		// also takes weekday from here instead of the current date
		parser = new JPBistro() {
			public String getFreshMenuHTML() throws java.io.IOException, java.text.ParseException {
				return parseHTML(preParseHTML(readFile(getClass().getResource(getHtmlFileName()).getFile())));
			};

			public int getDayOfWeek() {
				return weekday;
			}
		};
	}

	@Override
	public void testMonday() throws IOException, ParseException {
	}
	
	@Override
	public void testTuesday() throws IOException, ParseException {
	}
	
	@Override
	public void testWednesday() throws IOException, ParseException {
	}
}
