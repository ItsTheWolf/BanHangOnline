<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="index">Home</a> >> <a href="">Roles</a><br>
            <table class="table table-borderless margin-top-5px">
                <thead class="bg-dark text-white">
                    <tr>
                        <th class="width-5">ID</th>
                        <th class="width-10">Role</th>
                        <th>Role's privilege</th>
                        <th class="width-25">Actions</th>
                    </tr>
                </thead>
                <tbody class="bg-light">
                    <c:forEach var="row" items="${requestScope.model}">
                        <tr>
                            <td>${row.id}</td>
                            <td>${row.name}</td>
                            <td>${row.description}</td>
                            <td>
                                <a class="btn btn-info btn-block" href="roledetails?id=${row.id}">View User list</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>N/A</td>
                        <td>Non-User</td>
                        <td>- Can view Products and Categories details</td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
