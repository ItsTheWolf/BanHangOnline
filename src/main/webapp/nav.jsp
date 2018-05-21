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
            } else {
            %>
            User:<%=session.getAttribute("loggedName")%> | Role:<%=session.getAttribute("loggedRole")%> | <a href='logout'>Logout</a>
            <%
                }
            %>
        </td>
        <td class="text-right">Contact: 69-1919-***</td>
    </tr>
</table>
<table class="bg-dark width-max">
    <tr>
        <td class="width-25"><a class="btn btn-block btn-light" href="">Products</a></td>
        <td class="width-25"><a class="btn btn-block btn-light" href="">Categories</a></td>
        <td class="width-25"><a class="btn btn-block btn-light" href="userindex">Users</a></td>
        <td class="width-25"><a class="btn btn-block btn-light" href="roleindex">Roles</a></td>
    </tr>
</table>
