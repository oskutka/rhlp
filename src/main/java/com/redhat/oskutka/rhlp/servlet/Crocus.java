package com.redhat.oskutka.rhlp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

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

    /**
     * This is actually just URL to page that contains link to particular .doc file that contains the food menu.
     */
    protected String getUrl() {
        return MAIN_DOMAIN + "/104-zavodni-stravovani.html";
    }

    @Override
    protected boolean includingDayName() {
        return true;
    }

    @Override
    protected String getFreshMenuHTML() {
        String result = "NOTHING-PARSED-YET";

        try {
            // First we need to get link to correct .doc file
            String docUrl = getDocUrl(new URL(getUrl()));

            // Now we have to extract text from the .doc file (Apache Tika is awesome as it can
            // consume also URL so we don't have to download it first)
            Tika parserDocToText = new Tika();
            String wholeWeekMenu = parserDocToText.parseToString(new URL(docUrl));
            wholeWeekMenu = wholeWeekMenu.replaceAll("\\<[^>]*>", "").replaceAll("\n", "<br>");

            // Well... now let's format it little bit so ParsingRestaurant class can handle result easily...
            result = formatResult(wholeWeekMenu);
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
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader((InputStream) url.getContent(), getCharset()));
            String line;
            while ((line = is.readLine()) != null) {
                sb.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int index = sb.indexOf("cz.doc");
        String substring = sb.substring(0, index + 6);
        substring = substring.substring(substring.indexOf("soubory"));

        return MAIN_DOMAIN + "/" + substring;
    }
}