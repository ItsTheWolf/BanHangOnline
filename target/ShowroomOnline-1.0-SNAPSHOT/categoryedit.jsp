<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="index">Home</a> >> <a href="categoryindex">Categories</a> >> <a href="">Edit ${requestScope.model.name}</a><br>
            <div class="container">
                <form action="" method="POST">
                    <div class="container" style="background-color: #f1f1f1; padding: 1px 5px 20px 5px">
                        <table class="table table-borderless bg-light margin-top-5px">
                            <tr>
                                <td colspan="100%"><h2>Edit Category</h2></td>
                            </tr>
                            <tr>
                                <td class="width-10">ID:</td>
                                <td>${requestScope.model.id}<input class="btn-block" type="hidden" name="txtId" readonly="true" value="${requestScope.model.id}"></td>
                            </tr>
                            <tr>
                                <td class="width-10">*Category:</td>
                                <td><input class="btn-block" type="text" name="txtCategory" value="${requestScope.model.name}"/></td>
                            </tr>
                        </table>
                        <div class="container">
                            *: Required<br><br>
                            <button class="btn btn-confirm btn-success" type="submit">Edit Category</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
