package com.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LifecycleServlet
 */
@WebServlet("/LifecycleServlet")
public class LifecycleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private int requestCount = 0;

    @Override
    public void init() throws ServletException {
        // Called ONCE when servlet is first loaded
        System.out.println("[INIT] Servlet initialized");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse res)
            throws ServletException, IOException {
        // Called for EVERY GET request
        requestCount++;
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h2>Request #" + requestCount + "</h2>");
        System.out.println("[SERVICE] Handling request #" + requestCount);
    }

    @Override
    public void destroy() {
        // Called ONCE when server shuts down
        System.out.println("[DESTROY] Servlet destroyed. Total: " + requestCount);
        super.destroy();
    }
}