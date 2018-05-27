<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="index">Home</a> >> <a href="">Login</a><br>
            <div class="container">
                <form action="login" method="POST">
                    <div class="container" style="background-color: #00a2e8; padding: 1px 5px 20px 5px">
                        <table class="table table-borderless bg-light margin-top-5px">
                            <tr>
                                <td>
                        <h2>Login</h2></td>
                            </tr>
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
                                </td>
                            </tr>
                        </table>
                        <div class="container">
                            <button class="btn btn-confirm btn-success" type="submit">Log in</button>
                            <a href="register" class="text-white">Don't have an account? Click here to Register.</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
