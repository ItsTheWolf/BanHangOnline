<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="index">Home</a> >> <a href="">Add Product</a><br>
            <form action="" method="POST">
                <table class="table table-borderless margin-top-5px">
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
                        <td><input class="btn-block" type="text" name="txtDesc" value=""/></td>
                    </tr>
                    <tr>
                        <td class="width-10">Thumbnail:</td>
                        <td>
                            <img id="imgThumbnail" width="350px" src="resources/img/thumbnailtmp.png"/><br><br>
                            <input class="btn-block" type="text" id="txtThumbnail" name="txtThumbnail" value="thumbnailtmp.png"/>
                            <input class="btn-block" type="hidden" id="txtLink" name="txtLink" value="resources/img/thumbnailtmp.png"/><br>
                            <a class="btn btn-info text-white" onclick="loadImg()">Load Thumbnail</a><br>
                            Please put thumbnail image in folder resources/img/ first, then enter the name of the image, including the extension (*.png, *.bmp, *.jpg, etc).
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><button class="btn btn-confirm btn-success" type="submit">Add Product</button>
                    </tr>
                </table>
            </form>
            *: Required
        </div>
    </body>
</html>