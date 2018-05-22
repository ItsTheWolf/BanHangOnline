<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <%
            if (session.getAttribute("loggedName") != null) {
        %>
        <jsp:include page="nav.jsp"/>
        <%
            }
        %>
        <div class="container-fluid">
            <a href="productindex">Home</a> >> 
            <%
                if (session.getAttribute("loggedName") != null) {
            %>
            <a href="userindex">Users</a> >> <a href="">Add user</a><br>
            <%
            } else {
            %>
            <a href="">Register</a><br>
            <%
                }
            %>
            <form action="" method="POST">
                <table class="table table-borderless margin-top-5px">
                    <tr>
                        <td class="width-10">*Username:</td>
                        <td><input class="btn-block" type="text" name="txtUsername" value=""/></td>
                    </tr>
                    <tr>
                        <td class="width-10">*Password:</td>
                        <td><input class="btn-block" type="password" name="txtPassword" value=""/></td>
                    </tr>
                    <tr>
                        <td class="width-10">*Confirm Password:</td>
                        <td><input class="btn-block" type="password" name="txtCPassword" value=""/></td>
                    </tr>
                    <tr>
                        <td class="width-10">*Email:</td>
                        <td><input class="btn-block" type="text" name="txtEmail" value=""/></td>
                    </tr>
                    <tr>
                        <td class="width-10">Fullname:</td>
                        <td><input class="btn-block" type="text" name="txtFullname" value=""/></td>
                    </tr>
                    <tr>
                        <td class="width-10">Address:</td>
                        <td><input class="btn-block" type="text" name="txtAddress" value=""/></td>
                    </tr>
                    <%
                        if (!session.getAttribute("loggedRole").equals("Admin")) {
                        } else {
                    %>
                    <tr>
                        <td class="width-10">*Role:</td>
                        <td>
                            <select class="btn-block" name="txtRoleId">
                                <option value="0">--Choose Role--</option>
                                <c:forEach var="item" items="${requestScope.model}">
                                    <option value="${item.id}">${item.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    <tr>
                        <td></td>
                        <td><button class="btn btn-confirm btn-success" type="submit">Register</button>
                            <%
                                if (session.getAttribute("loggedName") == null) {
                            %>
                            <a href="login">Already have an account? Click here to Log in.</a>
                            <%
                                }
                            %>
                        </td>
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
            <div class="text-error">Password and Confirm Password don't match.</div>
            <%
                }
                if (request.getAttribute("ERROR").equals(3)) {
            %>
            <div class="text-error">Email isn't valid.</div>
            <%
                }
            %>
            --%>
        </div>
    </body>
</html>
