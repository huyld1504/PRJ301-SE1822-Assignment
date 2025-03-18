<%-- 
    Document   : EditCustomer
    Created on : 13-03-2025, 15:46:29
    Author     : Thanh Vinh
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="models.Customer" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Customer</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <c:if test="${sessionScope.SALE != null}">
            <jsp:useBean id="SALE" scope="session" class="models.SalesPerson"/>

            <nav class="navbar navbar-expand-lg bg-info text-white p-3 shadow-lg">
                <div class="container-fluid">
                    <h2 class="text-white fw-bold m-0">Edit Customer</h2>
                    <a href="MainServlet?action=sale-dashboard" class="btn btn-outline-light fw-bold">Back to Home</a>
                </div>
            </nav>

            <div class="container py-5">
                <form action="MainServlet?action=up-edit-customer" method="post">
                    <!-- Trường ẩn cho Customer ID -->
                    <input type="hidden" name="customer_id" value="${customer.custID}" />

                    <!-- Hiển thị thông tin khách hàng để chỉnh sửa -->
                    <div class="mb-3">
                        <label for="customer_name" class="form-label">Customer Name</label>
                        <input type="text" class="form-control" name="customer_name" value="${customer.custName}" required />
                    </div>
                    <div class="mb-3">
                        <label for="customer_phone" class="form-label">Phone</label>
                        <input type="text" class="form-control" name="customer_phone" value="${customer.phone}" required />
                    </div>
                    <div class="mb-3">
                        <label for="customer_sex" class="form-label">Sex</label>
                        <input type="text" class="form-control" name="customer_sex" value="${customer.sex}" required />
                    </div>
                    <div class="mb-3">
                        <label for="customer_address" class="form-label">Address</label>
                        <input type="text" class="form-control" name="customer_address" value="${customer.cusAddress}" required />
                    </div>
                    <button type="submit" class="btn btn-info">Update</button>
                </form>
            </div>

            <!-- Hiển thị thông báo thành công hoặc lỗi -->
            <c:if test="${requestScope.MESSAGE != null}">
                <div class="alert alert-success alert-dismissible fade show w-25 z-3 position-absolute" role="alert">
                    ${requestScope.MESSAGE}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <c:if test="${requestScope.ERROR != null}">
                <div class="alert alert-danger alert-dismissible fade show w-25 z-3 position-absolute" role="alert">
                    ${requestScope.ERROR}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

        </c:if>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>





