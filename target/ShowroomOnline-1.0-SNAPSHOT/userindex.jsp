<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="">Users</a><br>
            <a class="btn btn-info btn-distance" href="usercreate">Add User</a>
            <table class="table table-borderless margin-top-5px">
                <thead class="bg-dark text-white">
                    <tr>
                        <th>Username</th>
                        <th class="width-15">Role</th>
                        <th class="width-25">Actions</th>
                    </tr>
                </thead>
                <tbody class="bg-light">
                    <c:forEach var="row" items="${requestScope.model}">
                        <tr>
                            <td>${row.username}</td>
                            <td>${row.role.name}</td>
                            <td>
                                <a class="btn btn-actions-divide btn-info" href="userdetails?id=${row.username}">Details</a>
                                <a class="btn btn-actions-divide btn-info" href="useredit?id=${row.username}">Edit</a>
                                <a class="btn btn-actions-divide btn-danger" href="userdelete?id=${row.username}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
