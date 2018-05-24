<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="index">Home</a> >> <a href="">Users</a><br>
            <c:if test="${sessionScope.loggedRoleId < 3 && sessionScope.loggedRoleId != null}">
                <a class="btn btn-info btn-distance" href="register">Add User</a>
            </c:if>
            <table class="table table-borderless margin-top-5px">
                <thead class="bg-dark text-white">
                    <tr>
                        <th>Username</th>
                        <th class="width-30">Fullname</th>
                        <th class="width-15">Role</th>
                        <th class="width-25">Actions</th>
                    </tr>
                </thead>
                <tbody class="bg-light">
                    <c:forEach var="row" items="${requestScope.model}">
                        <tr>
                            <td>${row.username}</td>
                            <td>${row.fullname}</td>
                            <td><a href="roledetails?id=${row.role.id}">${row.role.name}</a></td>
                            <td>
                                <a class="btn btn-actions-divide btn-info" href="userdetails?username=${row.username}">Details</a>
                                <c:if test = "${row.username != 'admin'}">
                                    <c:if test = "${sessionScope.loggedRoleId < 3 || sessionScope.loggedName == row.username}">
                                        <a class="btn btn-actions-divide btn-info" href="useredit?username=${row.username}">Edit</a>
                                        <a class="btn btn-actions-divide btn-danger" href="userdelete?username=${row.username}">Delete</a>
                                    </c:if>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
