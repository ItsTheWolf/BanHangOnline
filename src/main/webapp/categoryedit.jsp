<%-- 
    Document   : categoryedit
    Created on : May 24, 2018, 9:25:30 AM
    Author     : danie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="index">Home</a> >> <a href="categoryindex">Categories</a> >> <a href="">Edit ${requestScope.model.name}</a><br>
            <form action="" method="POST">
                <table class="table table-borderless margin-top-5px">
                    <tr>
                        <td class="width-10">ID:</td>
                        <td>${requestScope.model.id}<input class="btn-block" type="hidden" name="txtId" readonly="true" value="${requestScope.model.id}"></td>
                    </tr>
                    <tr>
                        <td class="width-10">*Category:</td>
                        <td><input class="btn-block" type="text" name="txtCategory" value="${requestScope.model.name}"/></td>
                    </tr><tr>
                        <td></td>
                        <td><button class="btn btn-confirm btn-success" type="submit">Edit Category</button>
                    </tr>
                </table>
            </form>
            *: Required
        </div>
    </body>
</html>
