<%-- 
    Document   : CustomerDashboard
    Created on : Mar 1, 2025, 10:14:20 AM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <!--CSS Bootstrap link-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

        <!--Font awesome-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>    
    </head>
    <body>
        <c:if test="${sessionScope.CUSTOMER != null}">
            <jsp:useBean scope="session" class="models.Customer" id="CUSTOMER"/>
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <div class="container-fluid bg-info" style="height: 10vh;">
                    <a class="navbar-brand fs-2" href="#">
                        Welcome ${CUSTOMER.custName}
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0 w-50 fs-5 d-flex justify-content-around align-items-center">
                            <li class="nav-item">
                                <a class="nav-link" aria-current="page" href="MainServlet?action=customer-dashboard">
                                    <i class="fa-solid fa-house"></i>
                                    Home
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    <i class="fa-solid fa-car"></i>
                                    View Cars
                                </a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa-solid fa-gear"></i>
                                    Settings
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a class="dropdown-item my-2" href="MainServlet?action=customer-profile">
                                            <i class="fa-solid fa-user"></i>
                                            Profile
                                        </a>
                                    </li>
                                    <li>
                                        <a class="dropdown-item mb-2" href="MainServlet?action=logout">
                                            <i class="fa-solid fa-right-from-bracket"></i>
                                            Logout
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <form class="d-flex" role="search">
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-primary" type="submit">Search</button>
                        </form>
                    </div>
                </div>
            </nav>
        </c:if>

        <c:if test="${sessionScope.CUSTOMER == null}">
            <div class="alert alert-danger" role="alert">
                You don't have any permission to access this resource. Please <a href="MainServlet?action=mechanic-login-page" class="alert-link fw-bold">login here</a>!
            </div>
        </c:if>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
