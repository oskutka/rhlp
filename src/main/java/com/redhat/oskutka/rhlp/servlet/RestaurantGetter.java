package com.redhat.oskutka.rhlp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RestaurantGetter extends HttpServlet
{
    private static final long serialVersionUID = -35835583774554291L;

    public RestaurantGetter()
    {
        super();
    }

    private void doGetAndPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        try {
            writer.println(getMenuHTML());
        } finally {
        	writer.close();
        }
    }

    protected abstract String getUrl();

    private String getMenuHTML()
    {
        StringBuffer sb = new StringBuffer();
        try
        {
            URL url = new URL(getUrl());
            URLConnection con = url.openConnection();
            try {
                con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.74 Safari/537.36 OPR/28.0.1750.36 (Edition beta)");
            	BufferedReader is = new BufferedReader(new InputStreamReader((InputStream) con.getContent()));
            	String line;
            	while ((line = is.readLine()) != null) {
            		sb.append(line);
            	}
            } finally {
            	// TODO close connection?
            }
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	doGetAndPost(request, response);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGetAndPost(request, response);
    }

}
