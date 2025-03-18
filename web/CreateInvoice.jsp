<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
            <c:if test="${param.success eq 'true'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    Invoice created successfully!
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <c:if test="${param.error eq 'true'}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    Failed to create invoice. Please try again.
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
            <form action="InvoiceListServlet" method="POST">
                <div class="mb-3">
                    <label class="form-label">Sale ID</label>
                    <input type="text" class="form-control" name="saleID" 
                           value="${sessionScope.sale_ID}" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label">Invoice Date</label>
                    <input type="date" name="invoiceDate" class="form-control" value="<%= java.time.LocalDate.now()%>" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Car ID</label>
                    <select name="carID" class="form-control" required>
                        <option value="">-- Select Car --</option>
                        <c:forEach var="car" items="${CAR_LIST}">
                            <option value="${car.carID}">
                                ${car.carID} - ${car.model} - ${car.colour} - ${car.year}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label">Customer ID</label>
                    <input type="text" name="custID" class="form-control" placeholder="Enter Customer ID" required>
                </div>

                <button type="submit" class="btn btn-success"><i class="fa-solid fa-plus"></i> Create Invoice</button>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
