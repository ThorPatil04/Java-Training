package com.demo;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse res)
            throws ServletException, IOException {

        // Get form data
        String query = req.getParameter("query");

        // Simulate search result
        String result = "Results for: " + query;

        // Store result in request attribute
        req.setAttribute("searchResult", result);

        // Forward request to JSP
        RequestDispatcher rd =
                req.getRequestDispatcher("/results.jsp");

        rd.forward(req, res);
    }
}