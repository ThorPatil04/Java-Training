package com.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import java.util.List;
import java.util.ArrayList;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/studentsadd")
public class StudentServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// CREATE — Add new student
    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse res)
            throws ServletException, IOException {

        String name  = req.getParameter("name");
        String email = req.getParameter("email");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "INSERT INTO students (name, email) VALUES (?, ?)")) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.executeUpdate();
            res.sendRedirect("students");  // Redirect-after-POST

        } catch (SQLException e) {
            throw new ServletException("DB error", e);
        }
    }

    // READ — List all students
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	res.getWriter().append("Served at: ").append(req.getContextPath());
        List<Student> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "SELECT id, name, email FROM students ORDER BY id");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email")));
            }

        } catch (SQLException e) {
            throw new ServletException("DB error", e);
        }

        // Forward list to JSP for rendering
        req.setAttribute("students", list);
        req.getRequestDispatcher("/students.jsp").forward(req, res);
    }
}