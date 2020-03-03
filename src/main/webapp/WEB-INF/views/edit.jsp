<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script type='text/javascript' src='https://code.jquery.com/jquery-latest.min.js'></script>

</head>
<body>

<div style="text-align: right">
    <br>
    Admin ${adminName} <a href="logout"> logout </a>
</div>

<div align="center">
    <h1>Edit User</h1>
    <br>
    <br>
    <div>
        <form:form modelAttribute="userDto" action="edit">
            <table>
                <tr>
                    <form:hidden path="userId"/>
                    <td><form:label path="login">
                        Login
                    </form:label>
                    </td>
                    <td><form:input path="login" readonly="true"/></td>
                    <td><form:errors path="login"/></td>
                </tr>
                <tr>
                    <td><form:label path="password">
                        Password
                    </form:label>
                    </td>
                    <td><form:password path="password"/></td>
                    <td><form:errors path="password"/></td>
                </tr>
                <tr>
                    <td><form:label path="passwordAgain">
                        Password Again
                    </form:label>
                    </td>
                    <td><form:password path="passwordAgain"/></td>
                    <td><form:errors path="passwordAgain"/></td>
                </tr>
                <tr>
                    <td><form:label path="email">
                        Email
                    </form:label>
                    </td>
                    <td><form:input path="email"/></td>
                    <td><form:errors path="email"/></td>
                </tr>
                <tr>
                    <td><form:label path="firstName">
                        First name
                    </form:label>
                    </td>
                    <td><form:input path="firstName"/></td>
                    <td><form:errors path="firstName"/></td>
                </tr>
                <tr>
                    <td><form:label path="lastName">
                        Last name
                    </form:label>
                    </td>
                    <td><form:input path="lastName"/></td>
                    <td><form:errors path="lastName"/></td>
                </tr>
                <tr>
                    <td><form:label path="birthday">
                        Birthday
                    </form:label>
                    </td>
                    <td><form:input path="birthday"/></td>
                    <td><form:errors path="birthday"/></td>
                </tr>
                <tr>
                    <td><form:label path="role">
                        Role
                    </form:label>
                    </td>
                    <td><form:select path="role" items="${roles}"></form:select></td>
                    <td></td>
                </tr>
                <tr>
                    <td><br><br></td>
                    <td align="left">
                        <input type="submit" style="width: 100px" value="Submit"><a href="/admin" role="button"
                                                                                    aria-pressed="true"> Cancel</a></td>
                </tr>
            </table>
        </form:form>
    </div>
</div>
</body>
</html>