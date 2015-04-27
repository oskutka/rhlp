package com.redhat.oskutka.rhlp.servlet;

import java.util.Date;

public abstract class ParsingRestaurantGetter extends RestaurantGetter {
	private static final long serialVersionUID = 8693643179400623490L;

	public ParsingRestaurantGetter() {
		super();
	}

	protected String parseHTML(String freshMenuHTML) {
		return freshMenuHTML;
	}
	static protected String[] getDays() {
		return new String[]{"Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "Sobota", "Neděle"};
	}
	static protected String getToday() {
		int dayOfWeek = new Date().getDay() - 1;
		return getDays()[dayOfWeek > 4 ? 5 : dayOfWeek];
	}
	static protected String getTomorrow() {
		int dayOfWeek = new Date().getDay();
		return getDays()[dayOfWeek > 5 ? 6 : dayOfWeek];
	}
	
	@Override
	protected String getFreshMenuHTML() {
		return parseHTML(super.getFreshMenuHTML());
	}
}