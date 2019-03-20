package com.redhat.oskutka.rhlp.servlet;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class ParsingTest {
	protected ParsingRestaurantGetter parser;
	protected int weekday;

	protected String readFile(String file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");
	
	    try {
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            if (reader.ready()) {
	            	stringBuilder.append(ls);
	            }
	        }
	
	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}

	public void testWeekDay(int weekday, String expectedResultFileName) throws IOException, ParseException {
		this.weekday = weekday;
		String expectedResult = readFile(getClass().getResource(expectedResultFileName).getFile());
		assertEquals(expectedResult, parser.getFreshMenuHTML());
	}
	
	@Test
	public void testMonday() throws IOException, ParseException {
		testWeekDay(0, getMondayFileName());
	}
	
	@Test
	public void testTuesday() throws IOException, ParseException {
		testWeekDay(1, getTuesdayFileName());
	}
	
	@Test
	public void testWednesday() throws IOException, ParseException {
		testWeekDay(2, getWednesdayFileName());
	}
	
	@Test
	public void testThursday() throws IOException, ParseException {
		testWeekDay(3, getThursdayFileName());
	}
	
	@Test
	public void testFriday() throws IOException, ParseException {
		testWeekDay(4, getFridayFileName());
	}

	private String getClassBaseName() {
		String fullName = this.getClass().getName();
		String baseName = fullName.substring(fullName.lastIndexOf('.')+1, fullName.length() - "Test".length());
		return baseName;
	}
	
	protected String getHtmlFileName() {
		return "/" + getClassBaseName() + ".html";
	}

	protected String getMondayFileName() {
		return "/" + getClassBaseName() + "Monday.html";
	}

	protected String getTuesdayFileName() {
		return "/" + getClassBaseName() + "Tuesday.html";
	}

	protected String getWednesdayFileName() {
		return "/" + getClassBaseName() + "Wednesday.html";
	}

	protected String getThursdayFileName() {
		return "/" + getClassBaseName() + "Thursday.html";
	}
	
	protected String getFridayFileName() {
		return "/" + getClassBaseName() + "Friday.html";
	}
}