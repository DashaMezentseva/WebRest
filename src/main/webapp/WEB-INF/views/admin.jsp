<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="usersListTag" prefix="myTags" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8"/>
	<title>Admin's Page</title>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>

<div style="text-align: right">
	<br>
	Admin ${adminName} <a href="logout"> logout </a>
</div>

<a href="/add"><h3>Add new user</h3></a>
<br>
<myTags:table userList="${users}"/>
</body>
</html>