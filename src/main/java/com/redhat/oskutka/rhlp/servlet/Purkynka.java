package com.redhat.oskutka.rhlp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Purkynka
 */
@WebServlet("/Purkynka")
public class Purkynka extends RestaurantGetter {
    private static final long serialVersionUID = 7226051113762646793L;

    protected String getUrl() {
        return "http://www.napurkynce.cz/denni-menu/";
    }}
