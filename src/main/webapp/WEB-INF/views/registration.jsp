<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
 <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
<body>
<div><h2> Sign up </h2>
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

             <%-- <tr>
                <td><img src="<c:url value="/captcha-image.htm"/>"/></td>
                <td><form:input path="captcha"/></td>
                <td><form:errors path="captcha"/></td>
            </tr>--%>
            <tr>
                <td></td>
                <td align="right"><input type="submit" style="width: 100px" value="Submit"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>