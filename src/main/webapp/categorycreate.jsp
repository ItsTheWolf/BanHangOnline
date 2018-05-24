<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="index">Home</a> >> <a href="">Add Category</a><br>
            <form action="" method="POST">
                <table class="table table-borderless margin-top-5px">
                    <tr>
                        <td class="width-10">*Category:</td>
                        <td><input class="btn-block" type="text" name="txtCategory" value=""/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><button class="btn btn-confirm btn-success" type="submit">Add Category</button>
                    </tr>
                </table>
            </form>
            *: Required
        </div>
    </body>
</html>
