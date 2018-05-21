<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="">Roles</a>
            <table class="table table-borderless margin-top-5px">
                <thead class="bg-dark text-white">
                    <tr>
                        <th class="width-5">ID</th>
                        <th>Role</th>
                        <th class="width-25">Actions</th>
                    </tr>
                </thead>
                <tbody class="bg-light">
                    <c:forEach var="row" items="${requestScope.model}">
                        <tr>
                            <td>${row.id}</td>
                            <td>${row.name}</td>
                            <td>
                                <a class="btn btn-actions-divide btn-info" href="roledetails?id=${row.id}">Details</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
