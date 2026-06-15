<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>Search Result</h2>

<p>${searchResult}</p>
<form action="search" method="post">
    <input type="text" name="query" placeholder="Search here">
    <button type="submit">Search</button>
</form>
</body>
</html>