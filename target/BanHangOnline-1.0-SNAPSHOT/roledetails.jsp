<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <table class="table table-borderless margin-top-5px">
                <tr>
                    <td class="width-10 bg-dark text-white">ID:</td>
                    <td>${requestScope.model.id}</td>
                </tr>
                <tr>
                    <td class="width-10 bg-dark text-white">Role:</td>
                    <td>${requestScope.model.name}</td>
                </tr>
                <tr>
                    <td class="width-10 bg-dark text-white">Role's privilege:</td>
                    <td>${requestScope.model.description}</td>
                </tr>
            </table>
        </div>
    </body>
</html>
