<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"></jsp:include>
    <body>
        <div>
            <from action="" method="POST">
                <H2>Login</H2>
                Username: <input type="text" name="txtUsername" id="txtUsername"/>
                Password: <input type="text" name="txtPassword" id="txtPassword"/>
                <button type="submit">Login</button>
            </from>
        </div>
    </body>
</html>
