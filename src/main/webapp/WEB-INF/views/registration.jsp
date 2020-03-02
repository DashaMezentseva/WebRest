<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script type='text/javascript' src='https://code.jquery.com/jquery-latest.min.js'></script>
</head>
<body>
<div align="center">
    <br>
    <h1> Sign up </h1>
    <br>
    <br>
    <form:form modelAttribute="userDto" action="registration">
        <table>
            <tr>
                <td>Login</td>
                <td><form:input path="login"/></td>
                <td><form:errors path="login"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><form:password path="password"/></td>
                <td><form:errors path="password"/></td>
            </tr>
            <tr>
                <td>Password Again</td>
                <td><form:password path="passwordAgain"/></td>
                <td><form:errors path="passwordAgain"/></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><form:input path="email"/></td>
                <td><form:errors path="email"/></td>
            </tr>
            <tr>
                <td>First name</td>
                <td><form:input path="firstName"/></td>
                <td><form:errors path="firstName"/></td>
            </tr>
            <tr>
                <td>Last name</td>
                <td><form:input path="lastName"/></td>
                <td><form:errors path="lastName"/></td>
            </tr>
            <tr>
                <td>Birth date</td>
                <td><form:input path="birthday"/></td>
                <td><form:errors path="birthday"/></td>
            </tr>

            <tr>
                <td><img src="<c:url value="/captcha-image.htm"/>"/></td>
                <td><form:input path="captcha"/></td>
                <td><form:errors path="captcha"/></td>
            </tr>
            <tr>
                <td><br><br><br></td>
                <td align="left"><input type="submit" style="width: 100px" value="Submit"> <a href="/login" role="button" aria-pressed="true">Cancel</a></td>
            </tr>
        </table>
    </form:form>
</div>
</body>