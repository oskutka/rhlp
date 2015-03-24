package com.redhat.oskutka.rhlp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RestaurantGetter extends HttpServlet {
    private static final long serialVersionUID = -35835583774554291L;
	private static final long CACHE_TIMEOUT = 60*60*1000; // in milis
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
    	if (isOldCache()) {
    		if (menuHtml != null) {
    			menuHtml.clear();
    		}
    		menuHtml = new SoftReference<String>(getFreshMenuHTML());
    		timeOfRetrieval = new Date();
    	}
		return menuHtml.get();
	}

	protected boolean isOldCache() {
		if (timeOfRetrieval == null) {
			return true;
		}
		Date now = new Date();
		return (now.getTime() - timeOfRetrieval.getTime() >= CACHE_TIMEOUT);
	}

	protected String getFreshMenuHTML() {
        StringBuffer sb = new StringBuffer();
        try
        {
            URL url = new URL(getUrl());
            BufferedReader is = new BufferedReader(new InputStreamReader((InputStream) url.getContent()));
            String line;
            while ((line = is.readLine()) != null) {
            	sb.append(line);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGetAndPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGetAndPost(request, response);
    }

}
