<%-- 
    Document   : MechanicDashboard
    Created on : Mar 6, 2025, 4:34:15 PM
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
            <jsp:useBean scope="session" class="models.Mechanic" id="MECHANIC"/>
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
                                        <a class="dropdown-item my-2" href="MainServlet?action=get-service-list">
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
            <c:if test="${sessionScope.SERVICE_TICKET_LIST != null}">
                <table class="table table-striped w-75 mx-auto mt-5">
                    <tr class="fw-bold">
                        <td>Service Ticket ID</td>
                        <td>Date Received</td>
                        <td>Date Returned</td>
                        <td>Tools</td>
                    </tr>
                    <c:forEach items="${sessionScope.SERVICE_TICKET_LIST}" var="ticket">
                        <tr>
                            <td>${ticket.serviceTicketID}</td>
                            <td>${ticket.dateReceived.toString()}</td>
                            <td>${ticket.dateReaturned.toString()}</td>
                            <td>
                                <a href="MainServlet?action=ticket-detail&serviceTicketID=${ticket.serviceTicketID}"><i class="fa-solid fa-circle-info fs-4"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <c:if test="${requestScope.MESSAGE != null}">
                    <div class="alert alert-danger w-75" role="alert">
                        ${requestScope.MESSAGE}
                    </div>
                </c:if>
            </c:if>
            <c:if test="${sessionScope.SERVICE_TICKET_LIST.isEmpty() and sessionScope.SERVICE_TICKET_LIST == null}">
                <div class="alert alert-danger" role="alert">
                    ${requestScope.LIST_NOT_FOUND}
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
