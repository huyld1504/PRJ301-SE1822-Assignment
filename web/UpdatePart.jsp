<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Update Part</title>
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
                max-width: 500px;
                margin: 50px auto;
            }
        </style>
    </head>
    <body class="bg-fullscreen">

        <div class="container form-container">
            <div class="card shadow-sm p-4 rounded-3">
                <h3 class="text-center mb-4">Update Part</h3>

                <c:if test="${empty requestScope.part}">
                    <div class="alert alert-danger text-center">
                        <strong>OOPS! Part not found.</strong>
                    </div>
                </c:if>

                <c:if test="${not empty requestScope.part}">
                    <form action="MainServlet" method="post">
                        <input type="hidden" name="action" value="update-part"> 
                        <div class="mb-3">
                            <label for="partID" class="form-label">Part ID:</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="fa-solid fa-hashtag"></i></span>
                                <input type="text" class="form-control" name="partID" 
                                       value="${requestScope.part.partID}" readonly>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="partName" class="form-label">Part Name:</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="fa-solid fa-cogs"></i></span>
                                <input type="text" class="form-control" id="partName" name="partName" 
                                       value="${requestScope.part.partName}" required>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="purchasePrice" class="form-label">Purchase Price:</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="fa-solid fa-dollar-sign"></i></span>
                                <input type="number" class="form-control" id="purchasePrice" name="purchasePrice" 
                                       value="${requestScope.part.purchasePrice}" required step="0.01">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="retailPrice" class="form-label">Retail Price:</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="fa-solid fa-tag"></i></span>
                                <input type="number" class="form-control" id="retailPrice" name="retailPrice" 
                                       value="${requestScope.part.retailPrice}" required step="0.01">
                            </div>
                        </div>

                        <div class="d-flex justify-content-between">
                            <button type="button" class="btn btn-secondary" onclick="window.history.back();">
                                <i class="fa-solid fa-arrow-left"></i> Cancel
                            </button>
                            <button class="btn btn-primary" type="submit">
                                <input type="hidden" name="action" value="update-part"/>
                                <i class="fa-solid fa-check"></i> Submit
                            </button>
                        </div>
                    </form>
                </c:if>
            </div>
            ${ERROR}
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
