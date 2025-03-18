<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <title>Add Service Ticket</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <div class="container mt-5">
        <h2 class="text-center text-primary">Add Service Ticket</h2>

        <form action="AddServiceTicketServlet" method="post">
            <div class="mb-3">
                <label for="custID" class="form-label">Customer ID</label>
                <input type="text" class="form-control" id="custID" name="custID" required>
            </div>
            <div class="mb-3">
                <label for="carID" class="form-label">Car ID</label>
                <input type="text" class="form-control" id="carID" name="carID" required>
            </div>
            <div class="mb-3">
                <label for="dateReceived" class="form-label">Date Received</label>
                <input type="date" class="form-control" id="dateReceived" name="dateReceived" required>
            </div>

            <button type="submit" class="btn btn-success">Add Service Ticket</button>
            <a href="ServiceTicketList.jsp" class="btn btn-secondary">Back</a>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
