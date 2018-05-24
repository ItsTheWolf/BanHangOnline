<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="index">Home</a> >> <a href="categoryindex">Categories</a> >> <a href="">'${requestScope.model.name}'s Details</a><br>
            <table class="table table-borderless margin-top-5px">
                <tr>
                    <td class="width-10 bg-dark text-white">ID:</td>
                    <td class="bg-light">${requestScope.model.id}</td>
                </tr>
                <tr>
                    <td class="bg-dark text-white">Category:</td>
                    <td class="bg-light">${requestScope.model.name}</td>
                </tr>
            </table>
            <div class="row">
                <c:forEach var="row" items="${requestScope.products}">
                    <div class="col-sm-2">
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
        </div>
    </body>
</html>
