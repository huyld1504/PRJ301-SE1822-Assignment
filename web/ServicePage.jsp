<%-- 
    Document   : ServicePage
    Created on : Mar 9, 2025, 4:01:01 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:if test="${sessionScope.MECHANIC != null}">
            <%--Start nav bar--%>
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <div class="container-fluid bg-info" style="height: 10vh;">
                    <a class="navbar-brand fs-2" href="#">
                        Welcome ${MECHANIC.mechanicName}
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0 w-50 fs-5 d-flex justify-content-around align-items-center">
                            <li class="nav-item">
                                <a class="nav-link" aria-current="page" href="MainServlet?action=mechanic-dashboard">
                                    <i class="fa-solid fa-house"></i>
                                    Home
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="MainServlet?action=service-ticket&mechanicID=${MECHANIC.mechanicID}">
                                    <i class="fa-solid fa-ticket"></i>
                                    View Service Tickets
                                </a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa-solid fa-bell-concierge"></i>
                                    Services
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a class="dropdown-item my-2" href="MainServlet?action=service-page">
                                            <i class="fa-solid fa-border-all"></i>
                                            View all services
                                        </a>
                                    </li>
                                    <li>
                                        <a class="dropdown-item mb-2" href="MainServlet?action=mechanic-logout">
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
            <%--End nav bar--%>
            <%--Start service list table--%>
            <c:if test="${sessionScope.SERVICE_LIST != null and !sessionScope.SERVICE_LIST.isEmpty()}">
                <form action="MainServlet">
                    <input type="hidden" name="action" value="service-create-page"/>
                    <button class="btn btn-primary btn-sm mx-5 my-3 mr-5">
                        <i class="fa-solid fa-square-plus"></i>
                        Create
                    </button>
                </form>
                <div class="w-100">
                    <table class="table table-striped mx-5 fw-bold" style="max-width: 90%;">
                        <tr>
                            <td>Service ID</td>
                            <td>Service name</td>
                            <td>Hourly rate</td>
                            <td>Tools</td>
                        </tr>
                        <c:forEach items="${sessionScope.SERVICE_LIST}" var="service">
                            <form action="MainServlet" accept-charset="UTF-8">
                                <input value="update-service" name="action" type="hidden"/>
                                <input value="${service.serviceID}" name="serviceID" type="hidden"/>
                                <tr>
                                    <td>
                                        <a class="text-decoration-none text-dark" href="MainServlet?action=">${service.serviceID}</a>
                                    </td>
                                    <td>
                                        <input style="border: none" type="text" class="rounded-2 my-auto" value="${service.serviceName}" name="serviceName"/>
                                    </td>
                                    <td>
                                        <input style="border: none" type="text" class="rounded-2 my-auto" value="${service.hourlyRate}" name="hourlyRate"/>
                                    </td>
                                    <td>
                                        <button type="submit" class="btn btn-primary btn-sm">
                                            <i class="fa-solid fa-pen-to-square fs-5"></i>
                                        </button>
                                        <form>
                                            <input type="hidden" name="action" value="service-delete"/>
                                            <input type="hidden" name="serviceID" value="${service.serviceID}"/>
                                            <button type="submit" class="btn btn-danger btn-sm">
                                                <i class="fa-solid fa-trash fs-5"></i>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </table>
                </div>
            </c:if>

            <c:if test="${sessionScope.SERVICE_LIST == null and sessionScope.SERVICE_LIST.isEmpty()}">
                <div class="alert alert-danger" role="alert">
                    ${requestScope.MESSAGE}
                </div>
            </c:if>
        </c:if>

        <c:if test="${sessionScope.MECHANIC == null}">
            <div class="alert alert-danger" role="alert">
                You don't have any permission to access this resource. Please <a href="MainServlet?action=mechanic-login-page" class="alert-link fw-bold">login here</a>!
            </div>
        </c:if>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
