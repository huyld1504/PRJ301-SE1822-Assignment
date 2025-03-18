<%@page import="dao.SalePersonDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.CarDAO"%>
<%@page import="models.Car"%>
<%@page import="models.Customer"%>

<%@page import="java.util.ArrayList"%>

<%
    CarDAO carDAO = new CarDAO();
    ArrayList<Car> list = (ArrayList<Car>)carDAO.getAllCars();
%>
<%

    SalePersonDAO pd = new SalePersonDAO();
    ArrayList<Customer> customerList =(ArrayList<Customer>) pd.getAllCustomers();
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Invoice</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <style>
            body {
                background-color: #f8f9fa;
            }
            .container {
                max-width: 500px;
                margin-top: 50px;
                background: white;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            }
            .btn-custom {
                width: 100%;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <h2 class="text-center text-primary">Create New Invoice</h2>
            
            <% if ("true".equals(request.getParameter("success"))) { %>
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    Invoice created successfully!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            <% } %>

            <% if ("true".equals(request.getParameter("error"))) { %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    Failed to create invoice. Please try again.
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            <% } %>

            <form action="MainServlet" method="POST">
                <div class="mb-3">
                    <label class="form-label">Sale ID</label>
                    <input type="text" class="form-control" name="sale_ID" id="sale_ID" 
                           value="<%= session.getAttribute("sale_ID") %>" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label">Invoice Date</label>
                    <input type="date" name="invoiceDate" class="form-control" id="invoiceDate"
                           value="<%= java.time.LocalDate.now() %>" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Car ID</label>
                    <select name="carID" id="carID" class="form-control" required>
                        <option value="">-- Select Car --</option>
                        <% for (Car car : list) { %>
                            <option value="<%= car.getCarID() %>">
                                <%= car.getCarID() %> - <%= car.getModel() %> - <%= car.getColour() %> - <%= car.getYear() %> - $<%= car.getPrice() %>
                            </option>
                        <% } %>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label">Customer ID</label>
                    <select name="custID" id="custID" class="form-control" required>
                        <option value="">-- Select Customer --</option>
                        <% for (Customer cust : customerList) { %>
                            <option value="<%= cust.getCustID() %>">
                                <%= cust.getCustID() %> - <%= cust.getCustName() %>
                            </option>
                        <% } %>
                    </select>
                </div>
                <div class="d-flex justify-content-between">
                    <input type="hidden" name="action" value="create-invoice">
                 <button type="button" class="btn btn-secondary" onclick="window.history.back();">
                            <i class="fa-solid fa-arrow-left"></i> Cancel
                        </button>
                <button type="submit" class="btn btn-success">
                    <i class="fa-solid fa-plus"></i> Create Invoice
                </button>
                </div>
            </form>
        </div>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
