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
            <a class="btn btn-info btn-distance" href="productcreate">Add Product</a>
            <div class="row">
                <c:forEach var="row" items="${requestScope.model}">
                    <div class="col-sm-2">
                        <table class="bg-light width-max text-center table-product">
                            <tr>
                                <td class="cell-thumbnail"><img src="${row.thumbnail}" width="100%"/></td>
                            </tr>
                            <tr>
                                <td class="cell-name"><a href='productdetails?id=${row.id}'>${row.name}</a></td>
                            </tr>
                            <tr>
                                <td class="cell-price bg-warning">$${row.price}</td>
                            </tr>
                        </table>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
