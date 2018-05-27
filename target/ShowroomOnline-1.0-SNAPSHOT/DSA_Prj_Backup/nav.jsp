<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<!DOCTYPE html>
<table class="table">
    <tr>
        <td>
            <%
                if (session.getAttribute("loggedName") == null) {
            %>
            <a href="login">Login</a> | <a href="register">Register</a>
            <%
            } else if (!session.getAttribute("loggedName").equals("admin")) {
                if (!session.getAttribute("loggedRole").equals("Customer")) {
            %>
            User: <a href="userdetails?username=<%=session.getAttribute("loggedName")%>"><%=session.getAttribute("loggedName")%></a> | 
            Role: <a href="roledetails?id=<%=session.getAttribute("loggedRoleId")%>"><%=session.getAttribute("loggedRole")%></a> | 
            <a href="usereditpw?username=<%=session.getAttribute("loggedName")%>">Change password</a> | 
            <a href='logout'>Logout</a>
            <%
            } else {
            %>
            User: <a href="userdetails?username=<%=session.getAttribute("loggedName")%>"><%=session.getAttribute("loggedName")%></a> | 
            <a href="usereditpw?username=<%=session.getAttribute("loggedName")%>">Change password</a> | 
            <a href='logout'>Logout</a>
            <%
                }
            } else {
            %>
            User: <a href="userdetails?username=<%=session.getAttribute("loggedName")%>"><%=session.getAttribute("loggedName")%></a> | 
            Role: <a href="roledetails?id=<%=session.getAttribute("loggedRoleId")%>"><%=session.getAttribute("loggedRole")%></a> | 
            <a href='logout'>Logout</a>
            <%
                }
            %>
        </td>
        <td class="text-right">Contact: 69-0721-1919</td>
    </tr>
</table>
<a href="index"><img src="resources/img/headerimgtmp.png" width="100%" alt="headerimgtmp"/></a>
<table class="bg-dark width-max">
    <c:if test="${sessionScope.loggedRoleId != null}">
        <tr>
            <c:choose>
                <c:when test="${sessionScope.loggedRoleId < 3}">
                    <td class="width-25"><a class="btn btn-block btn-info" href="index">Products</a></td>
                    <td class="width-25"><a class="btn btn-block btn-info" href="categoryindex">Categories</a></td>
                    <td class="width-25"><a class="btn btn-block btn-info" href="userindex">Users</a></td>
                    <td class="width-25"><a class="btn btn-block btn-info" href="roleindex">Roles</a></td>
                </c:when>
                <c:otherwise>
                    <td><a class="btn btn-block btn-info" href="index">Products</a></td>
                    <td class="width-25"><a class="btn btn-block btn-info" href="userindex">Users</a></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:if>
</table>
