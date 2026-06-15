package com.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse res)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if ("admin".equals(username) && "pass123".equals(password)) {

            HttpSession session = req.getSession(true);
            session.setAttribute("username", username);
            session.setAttribute("role", "ADMIN");

            res.sendRedirect("dashboard");

        } else {
            res.sendRedirect("login.html?error=invalid");
        }
    }
}