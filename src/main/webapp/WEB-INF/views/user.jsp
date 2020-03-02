<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
		  integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script type='text/javascript' src='https://code.jquery.com/jquery-latest.min.js'></script>
</head>
<body>

<div align="center">
	<br>
	<br>
	<br>
	<h2>
		Hello, ${userName}!
	</h2>
	<br />
	Click here to <a href="/logout">logout</a>
</div>

</body>
</html>