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
            <form action="" method="POST">
                <table class="table table-borderless margin-top-5px">
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
                    <tr>
                        <td></td>
                        <td><button class="btn btn-confirm btn-success" type="submit">Change password</button></td>
                    </tr>
                </table>
            </form>
            *: Required
            <%--
            <%
                if (request.getAttribute("ERROR").equals(1)) {
            %>
            <div class="text-error">Please fill the required fields.</div>
            <%
                }
                if (request.getAttribute("ERROR").equals(2)) {
            %>
            <div class="text-error">Email isn't valid.</div>
            <%
                }
            %>
            --%>
        </div>
    </body>
</html>
