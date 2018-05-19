<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-secondary">
        <table class="bg-dark width-max">
            <tr>
                <td class="bg-dark text-white cell-title">PHÒNG BAN</td>
                <td><a class="btn btn-block btn-light" href="">Nhân viên</a></td>
            </tr>
        </table>
        <div class="container-fluid">
            <a class="btn btn-info btn-distance" href="rolecreate">Thêm</a>
            <table class="table table-borderless">
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
                            <td class="text-white">${row.id}</td>
                            <td class="text-white">${row.name}</td>
                            <td>
                                <a class="btn btn-actions-divide btn-info" href="roledetails?id=${row.id}">Chi tiết</a>
                                <a class="btn btn-actions-divide btn-info" href="roleedit?id=${row.id}">Chỉnh sửa</a>
                                <a class="btn btn-actions-divide btn-danger" href="roledelete?id=${row.id}">Xóa</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
