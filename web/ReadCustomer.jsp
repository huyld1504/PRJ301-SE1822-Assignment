<%-- 
    Document   : ReadCustomer
    Created on : 09-03-2025, 22:04:42
    Author     : Thanh Vinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Customer List</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
</head>
<body class="bg-light">

    <div class="container py-5">
        <h2 class="text-center text-primary fw-bold mb-4">Customer List</h2>

        <!-- Kiểm tra nếu có thông báo lỗi -->
        <c:if test="${not empty ERROR}">
            <div class="alert alert-danger text-center">${ERROR}</div>
        </c:if>

        <!-- Bảng danh sách khách hàng -->
        <div class="table-responsive">
            <table class="table table-hover table-bordered shadow-lg bg-white">
                <thead class="table-info text-center">
                    <tr>
                        <th>Customer ID</th>
                        <th>Name</th>
                        <th>Phone</th>
                        <th>Sex</th>
                        <th>Address</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="customer" items="${CUSTOMER_LIST}">
                        <tr>
                            <td class="text-center">${customer.custID}</td>
                            <td>${customer.custName}</td>
                            <td class="text-center">${customer.phone}</td>
                            <td class="text-center">
                                <c:choose>
                                    <c:when test="${customer.sex eq 'M'}">
                                        <i class="fa-solid fa-mars text-primary"></i> Male
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fa-solid fa-venus text-danger"></i> Female
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${customer.cusAddress}</td>
                            <td class="text-center">
                                <a href="MainServlet?action=update-customer&id=${customer.custID}" class="btn btn-warning btn-sm">
                                    <i class="fa-solid fa-edit"></i> Edit
                                </a>
                                <a href="MainServlet?action=delete-customer&id=${customer.custID}" 
                                   class="btn btn-danger btn-sm" 
                                   onclick="return confirm('Are you sure you want to delete this customer?');">
                                    <i class="fa-solid fa-trash"></i> Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Nút quay lại trang Dashboard -->
        <div class="text-center mt-4">
            <a href="MainServlet?action=sale-dashboard" class="btn btn-secondary">
                <i class="fa-solid fa-arrow-left"></i> Back to Dashboard
            </a>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
