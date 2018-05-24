<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <div class="container-fluid">
            <a href="index">Home</a> >> <a href="">Login</a><br>
            <form action="login" method="POST">
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
                        <td></td>
                        <td>
                            <button class="btn btn-confirm btn-success" type="submit">Log in</button>
                            <a href="register">Don't have an account? Click here to Register.</a>
                        </td>
                    </tr>
                </table>
            </form>
            <div class="text-error"><%=session.getAttribute("ERROR")%></div>
        </div>
    </body>
</html>
