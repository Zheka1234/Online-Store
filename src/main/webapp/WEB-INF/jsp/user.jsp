<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Personal User Page</title>
</head>

<style>
    <%--    Add css for table, rows, column here--%>
</style>
<body>

<div>
    <b>
        Hello, ${nameUser}
    </b>
</div>

<div>
    <h1>System Users</h1>
</div>
<div>
    <table>
        <tr>
            <td>User Id</td>
            <td>User Name</td>
            <td>User Surname</td>
            <td>Is Deleted</td>
            <td>Buys</td>
            <td>Creation Date</td>
            <td>Modification Date</td>
            <td>Login Users</td>
            <td>Password Users Users</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        <tr>
            <td>${user.idUser}</td>
            <td>${user.nameUsers}</td>
            <td>${user.surnameUsers}</td>
            <td>${user.buys}</td>
            <td>${user.isDeleted}</td>
            <td>${user.loginUsers}</td>
            <td>${user.passwordUsers}</td>
            <td><fmt:formatDate value="${user.creationDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><fmt:formatDate value="${user.modificationDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

            <td>
                <button>Edit Profile Info</button>
            </td>
            <td>
                <button>Delete Account</button>
            </td>
        </tr>
    </table>
</div>

</body>
</html>