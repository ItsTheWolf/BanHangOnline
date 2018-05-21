<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="roleindex">Role</a> >> <a href="">Role Details</a>
            <table class="table table-borderless margin-top-5px">
                <tr>
                    <td class="width-10 bg-dark text-white">ID:</td>
                    <td class="bg-light">${requestScope.model.id}</td>
                </tr>
                <tr>
                    <td class="bg-dark text-white">Role:</td>
                    <td class="bg-light">${requestScope.model.name}</td>
                </tr>
            </table>
            <table class="table table-borderless">
                <tr>
                    <td class="width-10 bg-dark text-white">Role's privilege:</td>
                    <td></td>
                </tr>
                <tr>
                    <td class="bg-light" colspan="100%">${requestScope.model.description}</td>
                </tr>
            </table>
        </div>
    </body>
</html>
