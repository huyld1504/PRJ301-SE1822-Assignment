<%-- 
    Document   : ViewInvoicesCustomer
    Created on : 18-03-2025, 19:27:52
    Author     : Thanh Vinh
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Invoices</title>

        <!-- CSS Bootstrap link -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">

        <!-- Font awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
    </head>
    <body>

        <c:if test="${sessionScope.CUSTOMER != null}">
            <jsp:useBean scope="session" class="models.Customer" id="CUSTOMER"/>
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <div class="container-fluid bg-info" style="height: 10vh;">
                    <a class="navbar-brand fs-2" href="#">Welcome ${CUSTOMER.custName}</a>
                    <a href="MainServlet?action=customer-dashboard" class="btn btn-outline-light fw-bold">Back to Dashboard</a>
                </div>
            </nav>

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

            <div class="container mt-4">
                <h1 class="text-center text-primary mb-4">Your Invoices</h1>

                <!-- Table for displaying invoices -->
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover shadow-lg rounded">
                        <thead class="table-light">
                            <tr class="fw-bold">
                                <th>Invoice ID</th>
                                <th>Date</th>
                                <th>Sales ID</th>
                                <th>Car ID</th>
                                <th>Cust ID</th>
                                <th>Details</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="invoice" items="${invoices}">
                                <tr>
                                    <td>${invoice.invoiceID}</td>
                                    <td>${invoice.invoiceDate}</td>
                                    <td>${invoice.salesID}</td>
                                    <td>${invoice.carID}</td>
                                    <td>${invoice.custID}</td>
                                    <td>
                                        <!-- Form for redirecting to View Invoice Detail -->
                                        <form action="MainServlet" method="get">
                                            <input type="hidden" name="action" value="view-invoicesdetail-customer">
                                            <input type="hidden" name="invoiceID" value="${invoice.invoiceID}">
                                            <button type="submit" class="btn btn-primary btn-sm">View Details</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </c:if>

        <c:if test="${sessionScope.CUSTOMER == null}">
            <div class="alert alert-danger" role="alert">
                You don't have any permission to access this resource. Please <a href="MainServlet?action=home" class="alert-link fw-bold">login here</a>!
            </div>
        </c:if>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    </body>
</html>







