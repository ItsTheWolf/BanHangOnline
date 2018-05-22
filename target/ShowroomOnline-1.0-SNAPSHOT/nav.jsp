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
                if (!session.getAttribute("loggedRole").equals("customer")) {
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
        <td class="text-right">Contact: 69-1919-***</td>
    </tr>
</table>
<a href="index"><img src="resources/img/headerimgtmp.png" width="100%" alt="headerimgtmp"/></a>
<table class="bg-dark width-max">
    <tr>
        <td class="width-25"><a class="btn btn-block btn-light" href="index">Products</a></td>
        <td class="width-25"><a class="btn btn-block btn-light" href="">Categories</a></td>
        <td class="width-25"><a class="btn btn-block btn-light" href="userindex">Users</a></td>
        <td class="width-25"><a class="btn btn-block btn-light" href="roleindex">Roles</a></td>
    </tr>
</table>
