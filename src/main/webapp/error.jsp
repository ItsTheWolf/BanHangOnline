<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <div class="container-fluid">
            <div class="text-error"><%=session.getAttribute("ERROR1")%></div>
            <div class="text-error"><%=session.getAttribute("ERROR2")%></div>
            <div class="text-error"><%=session.getAttribute("ERROR3")%></div>
            <div><%=session.getAttribute("BACK")%></div>
        </div>
    </body>
</html>
