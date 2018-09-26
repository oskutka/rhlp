package com.redhat.oskutka.rhlp.servlet;

import javax.servlet.annotation.WebServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URLConnection;
import java.text.ParseException;

@WebServlet("/Globus")
public class Globus extends RestaurantGetter {
    private static final long serialVersionUID = 7809185614434754161L;

    @Override
    protected String getUrl() {
        return "https://www.globus.cz/brno/nabidka/restaurace.html";
    }

    @Override
    protected String getFreshMenuHTML() throws IOException, ParseException {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        StringBuffer sb = new StringBuffer();
        URLConnection connection = getConnection();
        BufferedReader is = new BufferedReader(new InputStreamReader((InputStream) connection.getContent(), getCharset()));
        String line;
        while ((line = is.readLine()) != null) {
            sb.append(line);
        }
        return stripImages() ? stripImages(sb.toString()) : sb.toString();
    }
}
