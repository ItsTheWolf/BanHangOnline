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
            <a href="index">Home</a> >> 
            <%
                if (session.getAttribute("loggedName") != null) {
            %>
            <a href="userindex">Users</a> >> <a href="">Create Category</a><br>
            <%
            } else {
            %>
            <a href="">Create Category</a><br>
            <%
                }
            %>
            <form action="" method="POST">
                <table class="table table-borderless margin-top-5px">
                    <tr>
                        <td class="width-10">Category:</td>
                        <td><input class="btn-block" type="text" name="txtCategory" value=""/></td>
                    </tr>
                    
                    <tr>
                        <td></td>
                        <td><button class="btn btn-confirm btn-success" type="submit">Create</button>
                            
                        </td>
                    </tr>
                </table>
            </form>
          
        </div>
    </body>
</html>
