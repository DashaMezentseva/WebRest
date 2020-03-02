
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
   <h1>Login</h1>
    <c:if test="${not empty errorMessge}"><div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMessge}</div></c:if>

   <form name="login_form" action="loginCheck" id="login_form" method="post">
      <table>
         <tr>
            <td>User:</td>
            <td><input type="text" name="username" value=""></td>
         </tr>
         <tr>
            <td>Password:</td>
            <td><input type="password" name="password" /></td>
         </tr>
         <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
            <td><a href="/registration"> Register</a></td>
         </tr>

      </table>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  </form>
</body>
</html>

