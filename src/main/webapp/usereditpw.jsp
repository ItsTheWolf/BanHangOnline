<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="index">Home</a> >> <a href="">Change ${requestScope.model.username}'s Password</a><br>
            <div class="container">
                <form action="" method="POST">
                    <div class="container" style="background-color: #f1f1f1; padding: 1px 5px 20px 5px">
                        <table class="table table-borderless bg-light margin-top-5px">
                            <tr>
                                <td colspan="100%"><h2>Change User Password</h2></td>
                            </tr>
                            <tr>
                                <td class="width-10">Username:</td>
                                <td>${requestScope.model.username}<input class="btn-block" type="hidden" name="txtUsername" readonly="true" value="${requestScope.model.username}"></td>
                            </tr>
                            <tr>
                                <td class="width-10">*New Password</td>
                                <td><input class="btn-block" type="password" name="txtPassword" value=""/></td>
                            </tr>
                            <tr>
                                <td class="width-10">*Confirm Password:</td>
                                <td><input class="btn-block" type="password" name="txtCPassword" value=""/></td>
                            </tr>
                        </table>
                        <div class="container">
                            *: Required<br><br>
                            <button class="btn btn-confirm btn-success" type="submit">Change password</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
