<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="index">Home</a> >> 
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
            <div class="container">
                <form action="register" method="POST">
                    <div class="container" style="background-color: #f1f1f1; padding: 1px 5px 20px 5px">
                        <table class="table table-borderless bg-light margin-top-5px">
                            <tr><%
                                if (session.getAttribute("loggedName") != null) {
                                %>
                                <td colspan="100%"><h2>Add User</h2></td>
                                <%
                                } else {
                                %>
                                <td><h2>Register</h2></td>
                                <%
                                    }
                                %>
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
                        </table>
                        <div class="container">
                            *: Required<br><br>
                            <button class="btn btn-confirm btn-success" type="submit">Register</button>
                            <%
                                if (session.getAttribute("loggedName") == null) {
                            %>
                            <a href="login">Already have an account? Click here to Log in.</a>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
