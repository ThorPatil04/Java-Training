package com.demo;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse res)
            throws IOException {

        HttpSession session = req.getSession(false);

        if (session == null) {
            res.sendRedirect("login.html");
            return;
        }

        String user =
            (String) session.getAttribute("username");

        res.setContentType("text/html");
        res.getWriter().println(
            "<h1>Welcome " + user + "</h1>" +
            "<br><a href='logout'>Logout</a>"
        );
    }
}