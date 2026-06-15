<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>My First JSP Page</title>
</head>
<body>
  <h1>Welcome to JSP!</h1>

  <%-- Scriptlet: Java code block --%>
  <%
    String user = "MIHIR";
    int year = 2026;
  %>

  <%-- Expression: outputs value directly --%>
  <p>Hello, <%= user %>! Year: <%= year %></p>

  <p>Server Time: <%= new Date() %></p>

  <%-- Conditional in Scriptlet --%>
  <%
    if (year >= 2026) {
  %>
    <p>✅ Current year!</p>
  <%
    }
  %>

</body>
</html>