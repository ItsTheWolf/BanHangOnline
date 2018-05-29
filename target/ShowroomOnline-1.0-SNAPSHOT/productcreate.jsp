<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="index">Home</a> >> <a href="">Add Product</a><br>
            <div class="container">
                <form action="" method="POST" enctype="multipart/form-data">
                    <div class="container" style="background-color: #f1f1f1; padding: 1px 5px 20px 5px">
                        <table class="table table-borderless bg-light margin-top-5px">
                            <tr>
                                <td colspan="100%"><h2>Add Product</h2></td>
                            </tr>
                            <tr>
                                <td class="width-10">*Product:</td>
                                <td><input class="btn-block" type="text" name="txtProduct" value=""/></td>
                            </tr>
                            <tr>
                                <td class="width-10">*Price:</td>
                                <td><input class="btn-block" type="number" step="0.01" name="txtPrice" value=""/></td>
                            </tr>
                            <tr>
                                <td class="width-10">*Stocks:</td>
                                <td><input class="btn-block" type="number" name="txtAmount" value=""/></td>
                            </tr>
                            <tr>
                                <td class="width-10">*Category:</td>
                                <td>
                                    <select class="btn-block" name="txtCateId">
                                        <option value="0">--Choose Category--</option>
                                        <c:forEach var="item" items="${requestScope.model}">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="width-10">Description:</td>
                                <td><textarea class="btn-block" name="txtDesc"></textarea></td>
                            </tr>
                            <tr>
                                <td class="width-10">Thumbnail:</td>
                                <td>
                                    <img src="resources/img/thumbnailtmp.png" id="previewimg" width="350px"><br>
                                    <input type="file" id="file" name="file" size="50" onchange="loadImg()">
                                </td>
                            </tr>
                        </table>
                        <div class="container">
                            *: Required<br><br>
                            <button class="btn btn-confirm btn-success" type="submit">Add Product</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>