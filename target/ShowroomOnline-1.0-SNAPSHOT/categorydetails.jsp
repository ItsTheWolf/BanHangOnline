<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="index">Home</a>
            <c:if test="${sessionScope.loggedRoleId < 3 && sessionScope.loggedRoleId != null}">
                >> <a href="categoryindex">Categories</a>
            </c:if>
            >> <a href="">${requestScope.model.name}'s Details</a><br>
            <table class="table table-borderless margin-top-5px">
                <c:if test="${sessionScope.loggedRoleId < 3 && sessionScope.loggedRoleId != null}">
                    <tr>
                        <td class="bg-dark text-white">ID:</td>
                        <td class="bg-light">${requestScope.model.id}</td>
                    </tr>
                </c:if>
                <tr>
                    <td class="width-10 bg-dark text-white">Category:</td>
                    <td class="bg-light">${requestScope.model.name}</td>
                </tr>
                <c:if test="${sessionScope.loggedRoleId < 3 && sessionScope.loggedRoleId != null}">
                    <tr>
                        <td></td>
                        <td>
                            <a class="btn btn-info margin-top-5px btn-product-actions" href="categoryedit?id=${requestScope.model.id}">Edit</a>
                            <a class="btn btn-danger margin-top-5px btn-product-actions" href="categorydelete?id=${requestScope.model.id}">Delete</a>
                        </td>
                    </tr>
                </c:if>
            </table>
            <table class="table table-borderless">
                <thead class="bg-dark text-white">
                    <tr>
                        <th class="width-15">Categories</th>
                        <th>Products</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="bg-light">
                            <table class="table">
                                <tbody class="bg-light">
                                    <c:forEach var="row" items="${requestScope.listItem}">
                                        <tr>
                                            <td><a href="categorydetails?id=${row.id}" class="btn btn-block btn-info text-left">${row.name}</a></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </td>
                        <td class="bg-lightgray">
                            <div class="row">
                                <c:forEach var="row" items="${requestScope.products}">
                                    <div class="col-sm-3">
                                        <a href='productdetails?id=${row.id}' class="product-card">
                                            <table class="bg-light width-max text-center table-product product-card-distance">
                                                <tr>
                                                    <td class="cell-thumbnail"><img src="${row.thumbnail}" width="100%"/></a></td>
                                                </tr>
                                                <tr>
                                                    <td class="cell-name">${row.name}</td>
                                                </tr>
                                                <tr>
                                                    <td class="cell-price bg-warning text-black">$${row.price}</td>
                                                </tr>
                                            </table>
                                        </a>
                                    </div>
                                </c:forEach>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
