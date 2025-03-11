<%@page import="dao.PartDAO"%>
<%@page import="models.Part"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Update Part</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
            }
            .form-container {
                max-width: 500px;
                margin: 50px auto;
            }
        </style>
    </head>
    <body>

        <div class="container form-container">
            <div class="card shadow-sm p-4 rounded-3">
                <h3 class="text-center mb-4">Update Part</h3>

                <form action="UpdatePartServlet" method="post">
                    <%
                        Part part = (Part) session.getAttribute("part");
                        if (part == null) {
                            response.sendRedirect("MainServlet?action=manage-parts"); 
                            return;
                        }
                    %>
                    <input type="hidden" name="part_id" value="<%= part.getPartID() %>">

                    <div class="mb-3">
                        <label for="partName" class="form-label">Part Name</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="fa-solid fa-cogs"></i></span>
                            <input type="text" class="form-control" id="partName" name="partName" placeholder="Enter the part name" required>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="purchasePrice" class="form-label">Purchase Price</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="fa-solid fa-dollar-sign"></i></span>
                            <input type="number" class="form-control" id="purchasePrice" name="purchasePrice" placeholder="Enter the purchase price" required>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="retailPrice" class="form-label">Retail Price</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="fa-solid fa-tag"></i></span>
                            <input type="number" class="form-control" id="retailPrice" name="retailPrice" placeholder="Enter the retail price" required>
                        </div>
                    </div>
                    <div class="d-flex justify-content-between">
                        <button type="button" class="btn btn-secondary" onclick="window.history.back();">
                            <i class="fa-solid fa-arrow-left"></i> Cancel
                        </button>
                        <button class="btn btn-primary" type="submit">
                            <i class="fa-solid fa-check"></i> Submit
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
