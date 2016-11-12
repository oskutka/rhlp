package com.redhat.oskutka.rhlp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.annotation.WebServlet;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

/**
 * Servlet implementation class Crocus.
 */
@WebServlet("/Crocus")
public class Crocus extends ParsingRestaurantGetter {
    private static final long serialVersionUID = 7226051113762646793L;

    private static final String MAIN_DOMAIN = "http://www.crocus.cz";
    private static final String PAGE_WITH_MENU_LINKS = "/104-zavodni-stravovani.html";

    /**
     * This is actually just URL to page that contains link to particular .doc file that contains the food menu.
     */
    protected String getUrl() {
        return MAIN_DOMAIN + PAGE_WITH_MENU_LINKS;
    }

    @Override
    protected boolean includingDayName() {
        return true;
    }

    @Override
    protected String getFreshMenuHTML() {
        String result = "FAILED TO RETRIEVE...";

        try {
            // First we need to get link to correct .doc file
            String docUrl = getDocUrl(new URL(getUrl()));

            if (docUrl != null) {
                // Now we have to extract text from the .doc file (Apache Tika is awesome as it can
                // consume also URL so we don't have to download it first)
                Tika parserDocToText = new Tika();
                String wholeWeekMenu = parserDocToText.parseToString(new URL(docUrl));
                wholeWeekMenu = wholeWeekMenu.replaceAll("\\<[^>]*>", "").replaceAll("\n", "<br>");

                // Well... now let's format it little bit so ParsingRestaurant class can handle result easily...
                result = formatResult(wholeWeekMenu);
            } else {
                return result;
            }
        } catch (TikaException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parseHTML(result);
    }

    private String formatResult(String wholeWeekMenu) {
        StringBuilder sb = new StringBuilder();

        int monday = wholeWeekMenu.indexOf("Pondělí");
        int tuesday = wholeWeekMenu.indexOf("Úterý");
        int wednesday = wholeWeekMenu.indexOf("Středa");
        int thursday = wholeWeekMenu.indexOf("Čtvrtek");
        int friday = wholeWeekMenu.indexOf("Pátek");
        int eof = wholeWeekMenu.indexOf("Přejeme");
        sb.append("<div>" + wholeWeekMenu.substring(monday, tuesday - 1) + "</div>");
        sb.append("<div>" + wholeWeekMenu.substring(tuesday, wednesday - 1) + "</div>");
        sb.append("<div>" + wholeWeekMenu.substring(wednesday, thursday - 1) + "</div>");
        sb.append("<div>" + wholeWeekMenu.substring(thursday, friday - 1) + "</div>");
        // also append 'Sobota' string so we have last handle
        sb.append("<div>" + wholeWeekMenu.substring(friday, eof - 1) + "</div>Sobota");

        return sb.toString();
    }

    private String getDocUrl(URL url) {
        // From empirical evidence:
        // - correct link (mean latest) to current doc file might be sometimes ordered as a first one and sometimes as a second
        // one in the HTML source code. Thus we need to check both available and take the one with greater number in the name.
        // - BUT also link to menu for upcoming week is usually added on Friday week before already. Thus on Friday we might get
        // wrong .doc file (the one that targets upcoming week) if we settle just with the 'get the latest .doc' rule.

        // Get number of current week
        int week = currentWeekNumber();
        // search for .doc file on the line
        Pattern docPat = Pattern.compile("^.*cz\\.doc.*$");
        // excerpt based on: 'Jídelní lístek 38. týden 19.9. - 23.9. 2016' -> >>tek 38. t<<
        Pattern weekPat = Pattern.compile("^.*stek\\s" + week + "\\.\\st.*$");

        // Save only chosen line from received HTML code that matches both regexps...
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader((InputStream) url.getContent(), getCharset()));
            String line;
            while ((line = is.readLine()) != null) {
                if (docPat.matcher(line).matches() && weekPat.matcher(line).matches()) {
                    sb.append(line);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        SortedSet<String> allMatches = new TreeSet<String>();
//        Matcher m = Pattern.compile("soubory/[0-9]*cz.doc").matcher(sb.toString());
//        while (m.find()) {
//          allMatches.add(m.group());
//        }
//
//        // Get last item from sorted array - we expect that latest file has highest number in filename...
//        String substring = allMatches.last();

        // All right - we should have only one unique line with the .doc link, just retrieve correct link...
        int index = sb.indexOf("cz.doc");
        if (index >= 0) {
            String substring = sb.substring(0, index + 6);
            substring = substring.substring(substring.indexOf("soubory"));
            return MAIN_DOMAIN + "/" + substring;
        } else {
            return null;
        }
    }

    private int currentWeekNumber() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setMinimalDaysInFirstWeek(4); // defined by czech calendar rules, see: https://cs.wikipedia.org/wiki/T%C3%BDden
        cal.setTime(new Date());
        return cal.get(Calendar.WEEK_OF_YEAR);
    }
}
