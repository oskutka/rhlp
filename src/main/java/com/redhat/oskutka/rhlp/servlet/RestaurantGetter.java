package com.redhat.oskutka.rhlp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RestaurantGetter extends HttpServlet {
    private static final long serialVersionUID = -35835583774554291L;
	private static final long CACHE_TIMEOUT = 5*60*1000; // in milis
    protected SoftReference<String> menuHtml;
    protected Date timeOfRetrieval;

    protected abstract String getUrl();

    private void doGetAndPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        try {
            writer.println(getMenuHTML());
        } finally {
        	writer.close();
        }
    }

    protected String getMenuHTML() {
    	try {
	    	if (isOldCache() || menuHtml == null || menuHtml.get() == null) {
	    		if (menuHtml != null) {
	    			menuHtml.clear();
	    		}
	    		menuHtml = new SoftReference<String>(getFreshMenuHTML());
	    		timeOfRetrieval = new Date();
	    	}
			return menuHtml.get();
		} catch (IOException e) {
			log("Not able to get FreshMenuHTML for " + getUrl() + ": " + e.getMessage());
			return "Cannot connect to the server";
		} catch (ParseException e) {
			log("Not able to parse FreshMenuHTML for " + getUrl() + ": " + e.getMessage());
			return "Cannot parse the menu";
    	}
	}

	protected boolean isOldCache() {
		if (timeOfRetrieval == null) {
			return true;
		}
		Date now = new Date();
		return (now.getTime() - timeOfRetrieval.getTime() >= CACHE_TIMEOUT);
	}
	
	protected boolean stripImages() {
		return true;
	}

	private String stripImages(String html) {
		return html.replaceAll("<img[^>]*>", " ");
	}
	
	protected String getFreshMenuHTML() throws IOException, ParseException {
        StringBuffer sb = new StringBuffer();
            URLConnection connection = getConnection();
            BufferedReader is = new BufferedReader(new InputStreamReader((InputStream) connection.getContent(), getCharset()));
            String line;
            while ((line = is.readLine()) != null) {
            	sb.append(line);
            }
        return stripImages() ? stripImages(sb.toString()) : sb.toString();
    }

	protected URLConnection getConnection() throws IOException {
		URL url = new URL(getUrl());
		URLConnection connection = url.openConnection();
		connection.setReadTimeout(20000);
		connection.setConnectTimeout(20000);
		return connection;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGetAndPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGetAndPost(request, response);
    }

    protected String getCharset() {
		return "UTF-8";
	}

}
