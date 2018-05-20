<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <table class="bg-dark width-max">
            <tr>
                <td class="bg-dark text-white cell-title">PHÒNG BAN</td>
                <td><a class="btn btn-block btn-light" href="">Nhân viên</a></td>
            </tr>
        </table>
        <div class="container-fluid">
            <table class="table table-borderless margin-top-5px">
                <thead class="bg-dark text-white">
                    <tr>
                        <th class="width-5">Mã số</th>
                        <th>Tên chức vụ</th>
                        <th class="width-25">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${requestScope.model}">
                        <tr>
                            <td>${row.id}</td>
                            <td>${row.name}</td>
                            <td>
                                <a class="btn btn-actions-divide btn-info" href="roledetails?id=${row.id}">Chi tiết</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
