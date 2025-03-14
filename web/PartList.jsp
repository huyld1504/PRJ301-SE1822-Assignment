<%@page import="java.util.ArrayList"%>
<%@page import="dao.PartDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Part List</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" 
              integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" 
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            .custom-navbar {
                background-color: darkblue; 
                border: 2px solid #0056b3; 
            }
            .container-mt3 {
                max-width: 800px; 
                margin: 20px auto; 
            }
            .navbar-brand {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg custom-navbar">
            <div class="container-fluid d-flex align-items-center">
                <a class="navbar-brand text-light" href="#">The Parts List</a>
                
                <a class="btn btn-success ms-3 me-3" href="AddPart.jsp">
                    <i class="fa-solid fa-plus"></i> Add Part
                </a>

                <!-- Search Form -->
                <form class="d-flex" action="MainServlet" method="post">
                    <input type="hidden" name="action" value="search-part"/>
                    <input class="form-control me-2" type="text" name="partName" 
                           placeholder="Enter the part name" 
                           value="${param.partName}" aria-label="Search">
                    <button class="btn btn-outline-light" type="submit">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </button>
                </form>

                <a class="nav-link text-light ms-auto" href="SaleDashboard.jsp">
                    <i class="fa-solid fa-house"></i> Home
                </a>
            </div>
        </nav>
                    <%--search--%>
        <div class="container-mt3">
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
                    <c:choose>
                        <c:when test="${not empty requestScope.RESULT}">
                            <c:forEach var="part" items="${requestScope.RESULT}">
                                <tr>
                                    <td>${part.partID}</td>
                                    <td>${part.partName}</td>
                                    <td>${part.purchasePrice}</td>
                                    <td>${part.retailPrice}</td>
                                    <td>
                                        <div class="d-flex gap-2">
                                            <%--upddate--%>
                                            <form action="MainServlet" method="get" class="d-flex flex-fill">
                                                <input type="hidden" name="action" value="update-part">
                                                <input type="hidden" name="partID" value="${part.partID}">
                                                <button type="submit" class="btn btn-info flex-fill">
                                                    <i class="fa-solid fa-pen-to-square"></i> Edit
                                                </button>
                                            </form>
                                                <script>
                                                    function confirmUpdate(){
                                                       return confirm("Do you want update this part!?")
                                                    }
                                                </script>
                                            <%--delete--%>
                                            <form class="flex-fill"  action="MainServlet" method="post" onsubmit="return confirmDelete()">
                                                <input type="hidden" name="action" value="delete-part">
                                                <input type="hidden" name="partID" value="${part.partID}">
                                                <button type="submit" class="btn btn-danger" ><i class="fa-regular fa-trash-can"></i> Delete</button> 
                                            </form>
                                        </div>
                                        <script>
                                            function confirmDelete(){
                                                return confirm("Do u want delete this part!?")
                                            }
                                        </script>

                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>

                     
                        <c:when test="${not empty sessionScope.partList}">
                            <c:forEach var="part" items="${sessionScope.partList}">
                                <tr>
                                    <td>${part.partID}</td>
                                    <td>${part.partName}</td>
                                    <td>${part.purchasePrice}</td>
                                    <td>${part.retailPrice}</td>
                                    <td>
                                         <div class="d-flex gap-2">
                                            <%--upddate--%>
                                            <form action="MainServlet" method="get" class="d-flex flex-fill">
                                                <input type="hidden" name="action" value="update-part">
                                                <input type="hidden" name="partID" value="${part.partID}">
                                                <button type="submit" class="btn btn-info flex-fill">
                                                    <i class="fa-solid fa-pen-to-square"></i> Edit
                                                </button>
                                            </form>
                                                <script>
                                                    function confirmUpdate(){
                                                       return confirm("Do you want update this part!?")
                                                    }
                                                </script>
                                            <%--delete--%>
                                            <form class="flex-fill"  action="MainServlet" method="post" onsubmit="return confirmDelete()">
                                                <input type="hidden" name="action" value="delete-part">
                                                <input type="hidden" name="partID" value="${part.partID}">
                                                <button type="submit" class="btn btn-danger" ><i class="fa-regular fa-trash-can"></i> Delete</button> 
                                            </form>
                                        </div>
                                        <script>
                                            function confirmDelete(){
                                                return confirm("Do u want delete this part!?")
                                            }
                                        </script>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>

                        <c:otherwise>
                            <tr>
                                <td colspan="5" class="text-center text-danger">No part to print!</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
               
        
        <!-- Bootstrap Scripts -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
                crossorigin="anonymous"></script>
    </body>
</html>
