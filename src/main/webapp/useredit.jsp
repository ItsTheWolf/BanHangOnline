<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="index">Home</a> >> <a href="userindex">Users</a> >> <a href="">Edit ${requestScope.model.username}'s Information</a><br>
            <div class="container">
                <form action="" method="POST" enctype="multipart/form-data">
                    <div class="container" style="background-color: #f1f1f1; padding: 1px 5px 20px 5px">
                        <table class="table table-borderless bg-light margin-top-5px">
                            <tr>
                                <td colspan="100%"><h2>Edit User</h2></td>
                            </tr>
                            <tr>
                                <td class="width-10">Username:</td>
                                <td>${requestScope.model.username}<input class="btn-block" type="hidden" name="txtUsername" readonly="true" value="${requestScope.model.username}"></td>
                            </tr>
                            <tr>
                                <td class="width-10">*Email:</td>
                                <td><input class="btn-block" type="text" name="txtEmail" value="${requestScope.model.email}"/></td>
                            </tr>
                            <tr>
                                <td class="width-10">Fullname:</td>
                                <td><input class="btn-block" type="text" name="txtFullname" value="${requestScope.model.fullname}"/></td>
                            </tr>
                            <tr>
                                <td class="width-10">Address:</td>
                                <td><input class="btn-block" type="text" name="txtAddress" value="${requestScope.model.address}"/></td>
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
                                        <c:forEach var="item" items="${requestScope.listItem}">
                                            <c:choose>
                                                <c:when test = "${item.id == requestScope.model.role.id}">
                                                    <option value="${item.id}" selected="true">
                                                        <c:out value="${item.name}" />
                                                    </option>
                                                </c:when>    
                                                <c:otherwise>
                                                    <option value="${item.id}">
                                                        <c:out value="${item.name}" />
                                                    </option>
                                                </c:otherwise>
                                            </c:choose>                      
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
                            <button class="btn btn-confirm btn-success" type="submit">Edit User</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
