<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body class="bg-lightblue">
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <a href="index">Home</a> >> <a href="">${requestScope.model.name}'s Details</a><br>
            <table class="table table-borderless">
                <tr>
                    <td class="width-350px"><img src="${requestScope.model.thumbnail}" width="350px"/></td>
                    <td>
                        <table class="table table-borderless">
                            <tr>
                                <td class="width-10 bg-dark text-white">ID:</td>
                                <td class="bg-light">${requestScope.model.id}</td>
                            </tr>
                            <tr>
                                <td class="width-10 bg-dark text-white">Product:</td>
                                <td class="bg-light">${requestScope.model.name}</td>
                            </tr>
                            <tr>
                                <td class="width-10 bg-dark text-white">Price</td>
                                <td class="bg-warning">${requestScope.model.price}</td>
                            </tr>
                            <tr>
                                <td class="width-10 bg-dark text-white">Stock left:</td>
                                <td class="bg-light">${requestScope.model.amount}</td>
                            </tr>
                            <tr>
                                <td class="width-10 bg-dark text-white">Category:</td>
                                <td class="bg-light"><a href="categorydetails?id=${requestScope.model.category.id}">${requestScope.model.category.name}</a></td>
                            </tr>
                            <tr>
                                <td colspan="100%" class="bg-lightblue">
                                    <a class="btn btn-info margin-top-5px btn-product-actions" href="productedit?id=${requestScope.model.id}">Edit Product's details</a>
                                    <a class="btn btn-danger margin-top-5px btn-product-actions" href="productdelete?id=${requestScope.model.id}">Delete Product</a>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td class="bg-dark text-white" colspan="100%">Description:</td>
                </tr>
                <tr>
                    <td class="bg-light" colspan="100%">${requestScope.model.description}</td>
                </tr>
            </table>
        </div>
    </body>
</html>
