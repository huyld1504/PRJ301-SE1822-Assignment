<%-- 
    Document   : ServiceTicketDetail.jsp
    Created on : Mar 8, 2025, 7:47:42 PM
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
            <form action="MainServlet" method="get" class="row g-3 p-4 mt-4 mx-5 border rounded shadow-lg bg-light">
                <input type="hidden" name="action" value="search-service-ticket"/>
                <input type="hidden" name="mechanicID" value="${MECHANIC.mechanicID}"/>
                <div class="col-md-4">
                    <label for="serialNumber" class="form-label fw-bold">Customer ID</label>
                    <input type="text" class="form-control" name="customerID" placeholder="Enter customer ID" value="${param.custID}"/>
                </div>
                <div class="col-md-4">
                    <label for="model" class="form-label fw-bold">Car ID</label>
                    <input type="text" class="form-control" name="carID" placeholder="Enter car ID" value="${param.carID}"/>
                </div>
                <div class="col-md-4">
                    <label for="year" class="form-label fw-bold">Date Received</label>
                    <input type="date" class="form-control" name="dateReceived" value="${param.dateReceived}"/>
                </div>
                <div class="col-12">
                    <button type="submit" class="btn btn-primary fw-bold px-5 py-2">Search</button>
                </div>
            </form>

            <form action="MainServlet" class="mx-5 mt-5">
                <input type="hidden" name="action" value="mechanic-dashboard"/>
                <button type="submit" class="btn btn-lg btn-primary">Back to dashboard</button>
            </form>
                
            <c:if test="${sessionScope.SERVICE_TICKET_LIST != null}">
                <div class="w-75 m-5">
                    <table class="table table-striped mt-5">
                        <tr class="fw-bold">
                            <td>Service ID</td>
                            <td>Hours</td>              
                            <td>Rate</td>
                            <td>Comment</td>
                            <td>Tools</td>
                        </tr>
                        <c:forEach items="${sessionScope.SERVICE_MECHANIC_LIST}" var="ticket">
                            <form action="MainServlet" accept-charset="UTF-8">
                                <input value="update-ticket-detail" name="action" type="hidden"/>
                                <input value="${ticket.serviceTicketID}" name="serviceTicketID" type="hidden"/>
                                <input value="${ticket.mechanicID}" name="mechanicID" type="hidden"/>
                                <input value="${ticket.serviceID}" name="serviceID" type="hidden"/>
                                <tr>
                                    <td>
                                        <a class="text-decoration-none text-dark" href="MainServlet?action=">${ticket.serviceID}</a>
                                    </td>
                                    <td>
                                        <input style="border: none" type="text" class="rounded-2 my-auto" value="${ticket.hour}" name="hours"/>
                                    </td>
                                    <td>
                                        <input style="border: none" type="text" class="rounded-2 my-auto" value="${ticket.rate}" name="rate"/>
                                    </td>
                                    <td class="w-50">
                                        <input style="border: none" type="text" class="rounded-2 my-auto w-100" value="${ticket.comment}" placeholder="Add comment" name="comment"/>
                                    </td>
                                    <td>
                                        <button type="submit" class="btn btn-primary btn-sm">
                                            <i class="fa-solid fa-pen-to-square fs-5"></i>
                                        </button>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </table>
                </div>
                
                <c:if test="${requestScope.ERROR != null}">
                    <div class="alert alert-danger alert-dismissible fade show mx-5 w-50 z-3" role="alert">
                        ${requestScope.ERROR}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <c:if test="${sessionScope.CUSTOMER_TICKET != null}">
                    <div class="card mt-5 w-25 mx-5" style="width: 18rem;">
                        <div class="card-header fw-bold text-center">
                            Customer Information
                        </div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">ID: ${sessionScope.CUSTOMER_TICKET.custID}</li>
                            <li class="list-group-item">Customer name: ${sessionScope.CUSTOMER_TICKET.custName}</li>
                            <li class="list-group-item">Customer phone: ${sessionScope.CUSTOMER_TICKET.phone}</li>
                        </ul>
                    </div>
                </c:if>

                <c:if test="${sessionScope.CAR_OF_CUSTOMER != null}">
                    <div class="card my-5 w-25 mx-5" style="width: 18rem;">
                        <div class="card-header fw-bold text-center">
                            Car Information
                        </div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">ID: ${sessionScope.CAR_OF_CUSTOMER.carID}</li>
                            <li class="list-group-item">Serial number: ${sessionScope.CAR_OF_CUSTOMER.serialNumber}</li>
                            <li class="list-group-item">Model: ${sessionScope.CAR_OF_CUSTOMER.model}</li>
                            <li class="list-group-item">Colour: ${sessionScope.CAR_OF_CUSTOMER.colour}</li>
                            <li class="list-group-item">Year: ${sessionScope.CAR_OF_CUSTOMER.year}</li>
                            <li class="list-group-item">Price: <fmt:formatNumber value="${requestScope.CAR_OF_CUSTOMER.price}" pattern="#,###" />VND</li>
                        </ul>
                    </div>
                </c:if>
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
