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
                    <tr>
                        <td></td>
                        <td><button class="btn btn-confirm btn-success" type="submit">Update information</button></td>
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
