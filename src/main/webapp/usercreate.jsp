<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <form action="" method="POST">
                <table class="table table-borderless margin-top-5px">
                    <tr>
                        <td class="width-10">Username:</td>
                        <td><input class="btn-block" type="text" name="txtUsername" value=""/></td>
                    </tr>
                    <tr>
                        <td class="width-10">Password:</td>
                        <td><input class="btn-block" type="password" name="txtPassword" value=""/></td>
                    </tr>
                    <tr>
                        <td class="width-10">Confirm Password:</td>
                        <td><input class="btn-block" type="password" name="txtCPassword" value=""/></td>
                    </tr>
                    <tr>
                        <td class="width-10">Email:</td>
                        <td><input class="btn-block" type="text" name="txtEmail" value=""/></td>
                    </tr>
                    <tr>
                        <td class="width-10">Fullname::</td>
                        <td><input class="btn-block" type="number" name="txtFullname" value=""/></td>
                    </tr>
                    <tr>
                        <td class="width-10">Address:</td>
                        <td><input class="btn-block" type="text" name="txtAddress" value=""/></td>
                    </tr>
                    <tr>
                        <td class="width-10">Role:</td>
                        <td>
                            <select class="btn-block" name="txtRoleId">
                                <option value="0">--Choose Role--</option>
                                <c:forEach var="item" items="${requestScope.model}">
                                    <option value="${item.id}">
                                    <c:out value="${item.name}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><button class="btn btn-confirm btn-success" type="submit">Register</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
