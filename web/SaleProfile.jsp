<%-- 
    Document   : SaleProfile
    Created on : 17-03-2025, 13:24:54
    Author     : Thanh Vinh
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- Thêm khai báo fmt -->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Salesperson Profile</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>

        <style>
            input[type="date"]::-webkit-calendar-picker-indicator {
                display: none;
            }
            input[type="date"] {
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
            }
        </style>

    </head>
    <body class="bg-light">

        <c:if test="${sessionScope.SALE != null}">
            <jsp:useBean id="SALE" scope="session" class="models.SalesPerson"/>

            <nav class="navbar navbar-expand-lg bg-info text-white p-3 shadow-lg">
                <div class="container-fluid d-flex justify-content-between align-items-center">
                    <h2 class="text-white fw-bold m-0">Welcome ${sessionScope.SALE.salesName}</h2>
                    <a href="MainServlet?action=sale-dashboard" class="btn btn-outline-light fw-bold">Back to Home</a>
                </div>
            </nav>

            <div class="container mt-5 d-flex justify-content-center">
                <div class="card shadow-lg rounded-3" style="width: 50%;">
                    <div class="card-header text-center fs-3 fw-bold bg-info text-white">
                        Salesperson Profile
                    </div>
                    <div class="card-body">
                        <form action="MainServlet" method="POST" accept-charset="UTF-8">
                            <input type="hidden" name="action" value="update-sale-profile"/>

                            <div class="mb-3">
                                <label for="salesperson_id" class="form-label">Salesperson ID:</label>
                                <input type="text" class="form-control" id="salesperson_id" name="salesperson_id" value="${SALE.salesID}" readonly/>
                            </div>
                            <div class="mb-3">
                                <label for="salesperson_name" class="form-label">Salesperson Name:</label>
                                <input type="text" class="form-control" id="salesperson_name" name="salesperson_name" value="${SALE.salesName}"/>
                            </div>
                            <div class="mb-3">
                                <label for="salesperson_sex" class="form-label">Salesperson Sex:</label>
                                <input type="text" class="form-control" id="salesperson_sex" name="salesperson_sex" value="${SALE.sex}"/>
                            </div>
                            <div class="mb-3">
                                <label for="salesperson_address" class="form-label">Salesperson Address:</label>
                                <input type="text" class="form-control" id="salesperson_address" name="salesperson_address" value="${SALE.salesAddress}"/>
                            </div>
                            <div class="mb-3">
                                <label for="salesperson_birthday" class="form-label">Birthday:</label>
                                <input type="date" class="form-control" id="salesperson_birthday" name="salesperson_birthday" 
                                       value="${SALE.birthday != null ? SALE.birthday.toLocalDate().toString() : ''}" />
                            </div>
                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-info btn-lg fw-bold text-light">UPDATE</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Display Success/Error Message -->
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

        <c:if test="${sessionScope.SALE == null}">
            <div class="alert alert-danger" role="alert">
                You don't have permission to access this resource. Please <a href="MainServlet?action=login-sale-page" class="alert-link fw-bold">login here</a>!
            </div>
        </c:if>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>





