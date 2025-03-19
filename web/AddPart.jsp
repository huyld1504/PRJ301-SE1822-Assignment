<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add parts</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" rel="stylesheet">
        <style>
            .bg-fullscreen {
                background: url('https://phukienxedep.com/wp-content/uploads/2020/10/oo-to.jpg') no-repeat center center fixed;
                background-size: cover;
                min-height: 100vh; 
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 20px;
            }
            .form-container {
                background: rgba(255, 255, 255, 0.9); 
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
                width: 100%;
                max-width: 800px;
            }
        </style>
    </head>
    <body class="bg-fullscreen">
        <div class="container form-container">
            <div class="card shadow-sm p-4 rounded-3">
                <h3 class="text-center mb-4">ADD THE PARTS</h3>

                <c:if test="${not empty ERROR}">
                    <div class="alert alert-danger" role="alert">
                        <i class="fa-solid fa-exclamation-circle"></i> ${ERROR}
                    </div>
                </c:if>

                <form action="MainServlet" method="get">
                    <div class="mb-3">
                        <label for="partID" class="form-label">Part ID</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="fa-solid fa-cogs"></i></span>
                            <input type="number" class="form-control" id="partID" name="partID" placeholder="Enter the part id" required
                                   value="${param.partID}">
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="partName" class="form-label">Part Name</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="fa-solid fa-cogs"></i></span>
                            <input type="text" class="form-control" id="partName" name="partName" placeholder="Enter the part name" required
                                   value="${param.partName}">
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="purchasePrice" class="form-label">Purchase Price</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="fa-solid fa-dollar-sign"></i></span>
                            <input type="number" class="form-control" id="purchasePrice" name="purchasePrice" placeholder="Enter the purchase price" required
                                   value="${param.purchasePrice}">
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="retailPrice" class="form-label">Retail Price</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="fa-solid fa-tag"></i></span>
                            <input type="number" class="form-control" id="retailPrice" name="retailPrice" placeholder="Enter the retail price" required
                                   value="${param.retailPrice}">
                        </div>
                    </div>
                    <input type="hidden" name="action" value="add-part-page">
                    <div class="d-flex justify-content-between">
                        <button type="button" class="btn btn-secondary" onclick="window.history.back();">
                            <i class="fa-solid fa-arrow-left"></i> Cancel
                        </button>
                        <button class="btn btn-primary" type="submit">
                            <i class="fa-solid fa-check"></i> Submit
                        </button>
                    </div>
                </form>
                <c:if test="${not empty errorMessage}">
                    <div style="color:red;">${errorMessage}</div>
                </c:if>
            </div>
        </div>
    </body>
</html>
