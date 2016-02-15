package com.redhat.oskutka.rhlp.servlet;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
 * [beginIndex]<dayOpeningTag>
 *   <p>[todayIndex]pondeli</p>
 *   <ul>
 *     <li>1st meal</li>
 *     <li>2nd meal</li>
 *   </ul>
 * </dayClosingTag>[endIndex]
 * <div>
 *   <p>[tomorrowIndex]pondeli</p>
 *   ...
 */
public abstract class ParsingRestaurantGetter extends RestaurantGetter {
	private static final long serialVersionUID = 8693643179400623490L;

	public ParsingRestaurantGetter() {
		super();
	}
	
	protected String parseHTML(String freshMenuHTML) {
		String result = freshMenuHTML;
		int beginIndex = getBeginIndex(result);
		int endIndex = getEndIndex(result);
		result = result.substring(beginIndex, endIndex);
		return result;
	}

	protected int getEndIndex(String result) {
		return result.lastIndexOf(getDayClosingTag(), getTomorrowIndex(result)) + getDayClosingTag().length();
	}

	protected int getBeginIndex(String result) {
		if (includingDayName()) {
			return result.lastIndexOf(getDayOpeningTag(),getTodayIndex(result));
		} else {
			return result.indexOf(getDayOpeningTag(),getTodayIndex(result));
		}
	}
	
	protected boolean includingDayName() {
		return false;
	}

	protected String getDayOpeningTag() {
		return "<div";
	}
	
	protected String getDayClosingTag() {
		return "</div>";
	}
	
	protected String[] getDays() {
		return new String[]{"Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "Sobota", "Neděle"};
	}
	
	protected int getDayOfWeek() {
		return new Date().getDay() - 1;
	}
	
	protected String getToday() {
		int dayOfWeek = getDayOfWeek();
		return getDays()[dayOfWeek > 4 ? 4 : dayOfWeek];
	}
	
	protected int getTodayIndex(String html) {
		return getIndex(html, getToday());
	}
	
	protected String getTomorrow() {
		int dayOfWeek = getDayOfWeek() + 1;
		return getDays()[dayOfWeek > 5 ? 5 : dayOfWeek];
	}
	
	protected int getTomorrowIndex(String html) {
		return getIndex(html, getTomorrow()); 
	}
	
	private int getIndex(String html, String substring) {
		return html.toLowerCase().lastIndexOf(substring.toLowerCase());
	}

	@Override
	protected String getFreshMenuHTML() {
		return parseHTML(super.getFreshMenuHTML());
	}
}