<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="productindex">Home</a> >> <a href="userindex">Users</a> >> <a href="">${requestScope.model.username}'s Details</a><br>
            <table class="table table-borderless margin-top-5px">
                <tr>
                    <td class="width-10 bg-dark text-white">Username:</td>
                    <td class="bg-light">${requestScope.model.username}</td>
                </tr>
                <tr>
                    <td class="width-10 bg-dark text-white">Fullname:</td>
                    <td class="bg-light">${requestScope.model.fullname}</td>
                </tr>
                <tr>
                    <td class="width-10 bg-dark text-white">Email:</td>
                    <td class="bg-light">${requestScope.model.email}</td>
                </tr>
                <tr>
                    <td class="width-10 bg-dark text-white">Address:</td>
                    <td class="bg-light">${requestScope.model.address}</td>
                </tr>
                <tr>
                    <td class="bg-dark text-white">Role:</td>
                    <td class="bg-light">${requestScope.model.role.name}</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <c:if test = "${requestScope.model.username != 'admin'}">
                            <a class="btn btn-actions-divide btn-info" href="useredit?username=${requestScope.model.username}">Edit</a>
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
