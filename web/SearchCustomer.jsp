<%-- 
    Document   : SearchCustomer
    Created on : 13-03-2025, 19:12:35
    Author     : Thanh Vinh
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="models.Customer" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Customer</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <c:if test="${sessionScope.SALE != null}">
            <jsp:useBean id="SALE" scope="session" class="models.SalesPerson"/>

            <nav class="navbar navbar-expand-lg bg-info text-white p-3 shadow-lg">
                <div class="container-fluid">
                    <h2 class="text-white fw-bold m-0">Search Customer</h2>
                    <a href="MainServlet?action=sale-dashboard" class="btn btn-outline-light fw-bold">Back to Dashboard</a>
                </div>
            </nav>

            <div class="container py-5">
                <form action="MainServlet?action=search-customer" method="post">
                    <div class="mb-3">
                        <label for="search_name" class="form-label">Customer Name</label>
                        <input type="text" class="form-control" name="search_name" id="search_name" placeholder="Enter customer name to search" />
                    </div>
                    <button type="submit" class="btn btn-info">Search</button>
                </form>
            </div>

            <c:if test="${not empty CUSTOMER_LIST}">
                <div class="container py-5">
                    <h2 class="text-center text-primary fw-bold">Customer List</h2>
                    <div class="table-responsive">
                        <table class="table table-hover table-bordered">
                            <thead class="table-info">
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
                                        <td>${customer.custID}</td>
                                        <td>${customer.custName}</td>
                                        <td>${customer.phone}</td>
                                        <td>${customer.sex}</td>
                                        <td>${customer.cusAddress}</td>
                                        <td>
                                            <a href="MainServlet?action=edit-customer&id=${customer.custID}" class="btn btn-warning btn-sm">
                                                <i class="fa-solid fa-pen"></i> Edit
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
                </div>
            </c:if>

        </c:if>

        <c:if test="${sessionScope.SALE == null}">
            <div class="alert alert-danger" role="alert">
                You don't have any permission to access this resource. Please <a href="MainServlet?action=login-sale-page" class="alert-link fw-bold">login here</a>!
            </div>
        </c:if>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>



