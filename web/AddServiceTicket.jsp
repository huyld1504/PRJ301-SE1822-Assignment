<%@page import="models.Customer"%>
<%@page import="dao.SalePersonDAO"%>
<%@page import="dao.CarDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Car"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    HttpSession session1 = request.getSession();
    String salesID = (String) session1.getAttribute("sale_ID");
    if (salesID == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    CarDAO carDAO = new CarDAO();
    ArrayList<Car> list = (ArrayList<Car>) carDAO.getAllCars();
    SalePersonDAO pd = new SalePersonDAO();
    ArrayList<Customer> customerList = (ArrayList<Customer>) pd.getAllCustomers();
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

            <form action="MainServlet" method="post">
                <!-- Service Ticket ID -->
                <div class="mb-3">
                    <label for="ticketID" class="form-label">Service Ticket ID</label>
                    <input type="text" class="form-control" id="ticketID" name="ticketID" placeholder="Enter Service Ticket ID" required>
                </div>

                <!-- Date Received -->
                <div class="mb-3">
                    <label for="dateReceived" class="form-label">Date Received</label>
                    <input type="date" class="form-control" id="dateReceived" name="dateReceived" required>
                </div>

                <!-- Date Return -->
                <div class="mb-3">
                    <label for="dateReturn" class="form-label">Date Return</label>
                    <input type="date" class="form-control" id="dateReturn" name="dateReturn" required>
                </div>

                <!-- Customer ID -->
                <div class="mb-3">
                    <label class="form-label">Customer ID</label>
                    <select name="custID" id="custID" class="form-control" required>
                        <option value="">-- Select Customer --</option>
                        <% for (Customer cust : customerList) {%>
                        <option value="<%= cust.getCustID()%>">
                            <%= cust.getCustID()%> - <%= cust.getCustName()%>
                        </option>
                        <% } %>
                    </select>
                </div>

                <!-- Car ID -->
                <div class="mb-3">
                    <label class="form-label">Car ID</label>
                    <select name="carID" id="carID" class="form-control" required>
                        <option value="">-- Select Car --</option>
                        <% for (Car car : list) {%>
                        <option value="<%= car.getCarID()%>">
                            <%= car.getCarID()%> - <%= car.getModel()%> - <%= car.getColour()%> - <%= car.getYear()%> - $<%= car.getPrice()%>
                        </option>
                        <% }%>
                    </select>
                </div>

                <!-- Action -->
                <input type="hidden" name="action" value="add-service-ticket">

                <!-- Buttons -->
                <button type="submit" class="btn btn-success">Add Service Ticket</button>
                <a href="MainServlet?action=view-service-ticket" class="btn btn-secondary">Back</a>
            </form>
        </div>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
