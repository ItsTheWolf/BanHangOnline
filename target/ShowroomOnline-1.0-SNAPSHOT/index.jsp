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
                        <table class="width-max">
                            <tr>
                                <td>${row.thumbnail}</td>
                            </tr>
                            <tr>
                                <td>${row.name}</td>
                            </tr>
                            <tr>
                                <td>${row.price}</td>
                            </tr>
                        </table>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
