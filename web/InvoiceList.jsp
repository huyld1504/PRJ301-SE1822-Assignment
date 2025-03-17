<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    HttpSession sess = request.getSession();
    String saleID = (String) sess.getAttribute("saleID"); 
    if (saleID == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Invoice List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2 class="text-center">Invoice List</h2>

        <c:if test="${not empty param.success}">
            <div class="alert alert-success text-center">Create invoice successfully</div>
        </c:if>
        <c:if test="${not empty param.error}">
            <div class="alert alert-danger text-center">Failed to create invoice</div>
        </c:if>

        <table class="table table-bordered table-hover mt-3">
            <thead class="table-dark">
                <tr>
                    <th>Invoice ID</th>
                    <th>Invoice Date</th>
                    <th>Sale ID</th>
                    <th>Car ID</th>
                    <th>Customer ID</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="invoice" items="${invoiceList}">
                    <tr>
                        <td>${invoice.invoiceID}</td>
                        <td>${invoice.invoiceDate}</td>
                        <td>${invoice.saleID}</td>
                        <td>${invoice.carID}</td>
                        <td>${invoice.custID}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Form tạo hóa đơn -->
        <div class="mt-5">
            <h3 class="text-center">Create New Invoice</h3>
            <form action="invoiceList" method="post">
                <div class="mb-3">
                    <label class="form-label">Sales ID</label>
                    <input type="text" name="saleID" class="form-control" value="<%= saleID %>" readonly>
                </div>
                <div class="mb-3">
                    <label class="form-label">Car ID</label>
                    <input type="text" name="carID" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Customer ID</label>
                    <input type="text" name="custID" class="form-control" required>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Create Invoice</button>
                </div>
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
