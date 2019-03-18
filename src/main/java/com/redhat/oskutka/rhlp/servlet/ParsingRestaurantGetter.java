package com.redhat.oskutka.rhlp.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;


/*
 * [menuSectionBegin]
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
 *   utery
 *   ...
 *   patek
 * [menuSectionEnd]
 */
public abstract class ParsingRestaurantGetter extends RestaurantGetter {
	private static final long serialVersionUID = 8693643179400623490L;

	public ParsingRestaurantGetter() {
		super();
	}
	
	protected String parseHTML(String freshMenuHTML) throws ParseException {
		try {
			String html = freshMenuHTML;
			if (restrictToMenuSection()) {
				html = html.substring(getMenuSectionBeginIndex(html), getMenuSectionEndIndex(html));
			}
			int beginIndex = getBeginIndex(html);
			int endIndex = getEndIndex(beginIndex, html);
			html = html.substring(beginIndex, endIndex);
			return html;
		} catch (IndexOutOfBoundsException e) {
			throw new ParseException(e.getMessage(), 0);
		}
	}

	protected int getEndIndex(int beginIndex, String html) {
		int tomorrowIndex = getTomorrowIndex(beginIndex, html);
		return html.lastIndexOf(getDayClosingTag(), tomorrowIndex) + getDayClosingTag().length();
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
		return getLastIndex(html, getToday());
	}
	
	protected String getTomorrow() {
		int dayOfWeek = getDayOfWeek() + 1;
		return getDays()[dayOfWeek > 5 ? 5 : dayOfWeek];
	}
	
	protected int getTomorrowIndex(int beginIndex, String html) {
		return getFirstIndex(html.substring(beginIndex), getTomorrow()) + beginIndex; 
	}
	
	private int getFirstIndex(String html, String substring) {
		return html.toLowerCase().indexOf(substring.toLowerCase());
	}

	private int getLastIndex(String html, String substring) {
		return html.toLowerCase().lastIndexOf(substring.toLowerCase());
	}

	@Override
	protected String getFreshMenuHTML() throws IOException, ParseException {
		return parseHTML(super.getFreshMenuHTML());
	}
	
	
	/**
	 * If true, the html is not parsed for dayOfWeek strings as a whole, but only the section [menuSectionBegin]...[menuSectionEnd]
	 * In that case you need to override getMenuSectionBeginString and getMenuSectionEndString 
	 */
	protected boolean restrictToMenuSection() {
		return false;
	}
	
	protected String getMenuSectionBeginString() {
		return "";
	}
	
	private int getMenuSectionBeginIndex(String html) {
		return getLastIndex(html, getMenuSectionBeginString());
	}
	
	protected String getMenuSectionEndString() {
		return "";
	}
	
	private int getMenuSectionEndIndex(String html) {
		return getFirstIndex(html, getMenuSectionEndString());
	}
	
}