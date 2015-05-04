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
	protected String[] getDays() {
		return new String[]{"Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "Sobota", "Neděle"};
	}
	protected String getToday() {
		int dayOfWeek = new Date().getDay() - 1;
		return getDays()[dayOfWeek > 4 ? 4 : dayOfWeek];
	}
	protected String getTomorrow() {
		int dayOfWeek = new Date().getDay();
		return getDays()[dayOfWeek > 5 ? 5 : dayOfWeek];
	}
	
	@Override
	protected String getFreshMenuHTML() {
		return parseHTML(super.getFreshMenuHTML());
	}
}