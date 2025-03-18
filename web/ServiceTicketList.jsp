<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="models.ServiceTicket" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    HttpSession session1 = request.getSession();
    String salesID = (String) session1.getAttribute("sale_ID");
    if (salesID == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Service Ticket List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" 
              integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" 
              crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body class="bg-light">

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="SaleDashboard.jsp">Car Service</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="SaleDashboard.jsp"><i class="fa-solid fa-house"></i> Home</a>
                    </li>
                    
                </ul>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container mt-4">
        <h2 class="text-center text-primary">Service Ticket List</h2>

        <table class="table table-bordered table-hover mt-4">
            <thead class="table-primary">
                <tr>
                    <th>Service Ticket ID</th>
                    <th>Date Received</th>
                    <th>Date Returned</th>
                    <th>Customer ID</th>
                    <th>Car ID</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty requestScope.ticketList}">
                        <c:forEach var="ticket" items="${requestScope.ticketList}">
                            <tr>
                                <td>${ticket.serviceTicketID}</td>
                                <td>${ticket.dateReceived}</td>
                                <td>${ticket.dateReaturned}</td>
                                <td>${ticket.custID}</td>
                                <td>${ticket.carID}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="5" class="text-center text-danger">No service tickets found.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        <a href="MainServlet?action=add-service-ticket-form" class="btn btn-success">Add Service Ticket</a>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
