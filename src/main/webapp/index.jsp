<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="">Home</a><br>
            <c:if test="${sessionScope.loggedRoleId < 3 && sessionScope.loggedRoleId != null}">
                <a class="btn btn-info btn-distance" href="productcreate">Add Product</a>
            </c:if>
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
                            <c:forEach var="row" items="${requestScope.listItem}">
                                <a href="categorydetails?id=${row.id}" class="btn btn-light width-max text-left">${row.name}</a><br>
                            </c:forEach>
                        </td>
                        <td class="bg-lightgray">
                            <div class="row">
                                <c:forEach var="row" items="${requestScope.model}">
                                    <div class="col-sm-3">
                                        <a href='productdetails?id=${row.id}' class="product-card">
                                            <table class="bg-light width-max text-center table-product product-card-distance">
                                                <tr>
                                                    <td class="cell-thumbnail"><img src="resources/img/${row.thumbnail}" width="100%"/></a></td>
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
