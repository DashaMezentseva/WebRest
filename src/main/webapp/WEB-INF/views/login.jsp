<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
         integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
   <script type='text/javascript' src='https://code.jquery.com/jquery-latest.min.js'></script>

   <title>Login</title>
</head>
<body>
<div align="center" class="centerLayer">

   <br>
   <h1>Login</h1>
   <br>
   <br>
   <c:if test="${not empty errorMessge}">
      <div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMessge}</div>
   </c:if>

   <form name="form_login" action="loginCheck" method="post">
      <table align="center">
         <tr>
            <td class="td_nowrap">User:</td>
            <td><input type="text" name="username" value=""></td>
         </tr>
         <tr>
            <td>Password:</td>
            <td><input type="password" name="password"/></td>
         </tr>
         <tr>
            <td colspan="2" align="right" style="text-align: right">
               <input name="submit" type="submit" value="Sign In"/>
            </td>
         </tr>

      </table>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
   </form>
   <br>
   Not registered? <a href="/registration">Sign Up</a>
</div>
</body>
</html>

