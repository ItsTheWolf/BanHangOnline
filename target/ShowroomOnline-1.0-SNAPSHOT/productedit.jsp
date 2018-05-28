<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="index">Home</a> >> <a href="productdetails?id=${requestScope.model.id}">${requestScope.model.name}'s Details</a> >> <a href="">Edit ${requestScope.model.name}'s Information</a><br>
            <form action="" method="POST" enctype="multipart/form-data">
                <table class="table table-borderless margin-top-5px">
                    <tr>
                        <td class="width-10">ID:</td>
                        <td>${requestScope.model.id}<input class="btn-block" type="hidden" name="txtId" readonly="true" value="${requestScope.model.id}"></td>
                    </tr>
                    <tr>
                        <td class="width-10">*Product:</td>
                        <td><input class="btn-block" type="text" name="txtProduct" value="${requestScope.model.name}"/></td>
                    </tr>
                    <tr>
                        <td class="width-10">*Price:</td>
                        <td><input class="btn-block" type="number" step="0.01" name="txtPrice" value="${requestScope.model.price}"/></td>
                    </tr>
                    <tr>
                        <td class="width-10">*Stocks:</td>
                        <td><input class="btn-block" type="number" name="txtAmount" value="${requestScope.model.amount}"/></td>
                    </tr>
                    <tr>
                        <td class="width-10">*Category:</td>
                        <td>
                            <select class="btn-block" name="txtCateId">
                                <option value="0">--Choose Category--</option>
                                <c:forEach var="item" items="${requestScope.listItem}">
                                    <c:choose>
                                        <c:when test = "${item.id == requestScope.model.category.id}">
                                            <option value="${item.id}" selected="true">
                                                <c:out value="${item.name}" />
                                            </option>
                                        </c:when>    
                                        <c:otherwise>
                                            <option value="${item.id}">
                                                <c:out value="${item.name}" />
                                            </option>
                                        </c:otherwise>
                                    </c:choose>                      
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="width-10">Description:</td>
                        <td><input class="btn-block" type="text" name="txtDesc" value="${requestScope.model.description}"/></td>
                    </tr>
                    <tr>
                        <td class="width-10">Thumbnail:</td>
                        <td>
                            <img src="resources/img/${requestScope.model.thumbnail}" id="previewimg" width="350px"><br>
                            <input type="file" id="file" name="file" size="50" onchange="loadImg()" src="resources/img/${requestScope.model.thumbnail}">
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><button class="btn btn-confirm btn-success" type="submit">Edit Product</button>
                    </tr>
                </table>
            </form>
            *: Required
        </div>
    </body>
</html>