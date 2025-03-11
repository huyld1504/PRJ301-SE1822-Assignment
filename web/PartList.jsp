<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.PartDAO"%>
<%@page import="models.Part"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Part List</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            .custom-navbar {
                background-color: darkblue; 
                border: 2px solid #0056b3; 
            }
            .container-mt3 {
                max-width: 800px; 
                margin: 20px auto; 
            }
            .navbar-brand{
                text-align: center;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg custom-navbar">
            <div class="container-fluid d-flex align-items-center">
                <a class="navbar-brand text-light" href="#">The Parts List</a>
                
                <a class="btn btn-success ms-3 me-3" href="AddPart.jsp">
                    <i class="fa-solid fa-plus"></i> Add Part
                </a>

               
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Search..." aria-label="Search">
                    <button class="btn btn-outline-light" type="submit">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </button>
                </form>

                <a class="nav-link text-light ms-auto" href="home.jsp">
                    <i class="fa-solid fa-house"></i> Home
                </a>
            </div>
        </nav>

        
        <%-- the part list --%>
        <div class="container-mt3">
            <%
                //PartDAO dao = new PartDAO();
                ArrayList<Part> list = (ArrayList<Part>)request.getAttribute("partList");
                //ArrayList<Part> list = dao.getAllPart();
            %>
            <table class="table table-bordered table-striped">
                <thead class="table-info">
                    <tr>
                        <th>Part ID</th>
                        <th>Part Name</th>
                        <th>Purchase Price</th>
                        <th>Retail Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (list== null) { %>
                    <tr>
                        <td colspan="5" class="text-center text-danger">No part to print!</td>
                    </tr>
                    <% } else {
                        for (Part part : list) {%>
                    <tr>
                        <td><%= part.getPartID()%></td>
                        <td><%= part.getPartName()%></td>
                        <td><%= part.getPurchasePrice()%></td>
                        <td><%= part.getRetailPrice()%></td>
                        <td>
                            <a class="btn btn-info" role="button" href="UpdatePart.jsp?partID=<%= part.getPartID()%>">
                                <i class="fa-solid fa-pen-to-square"></i>
                            </a>
                            <a class="btn btn-danger" role="button" href="deletePart?partID=<%= part.getPartID()%>" onclick="return confirm('Do you want to delete?')">
                                <i class="fa-regular fa-trash-can"></i>
                            </a>
                        </td>
                    </tr>
                    <%  }
                        }%>
                </tbody>
            </table>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>