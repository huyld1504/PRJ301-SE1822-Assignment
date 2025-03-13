<%-- 
    Document   : CreateServicePage
    Created on : Mar 12, 2025, 10:25:18 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

            <%--Create form--%>
            <div 
                style="box-shadow: 10px 10px 20px 0px rgba(122,122,122,1);
                -webkit-box-shadow: 10px 10px 20px 0px rgba(122,122,122,1);
                -moz-box-shadow: 10px 10px 20px 0px rgba(122,122,122,1);
                height: 65vh"
                class="w-50 my-5 mx-auto my-5 rounded-3">
                <p class="fs-3 text-dark fw-bold text-center py-5">Service Form</p>
                <form action="MainServlet" accept-charset="UTF-8">
                    <input type="hidden" name="action" value="create-service"/>
                    <table class="table mx-auto mt-3" style="width: 80%">
                        <tr class="">
                            <td class="fw-bold">
                                <p class="w-100 text-end">Service ID: </p>
                            </td>
                            <td class="">
                                <input class="w-100" style="border: none" type="text" name="service_id" required="" value="${param.service_id}"/>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="fw-bold">
                                <p class="w-100 text-end">Service name: </p>
                            </td>
                            <td class="">
                                <input class="w-100" style="border: none" type="text" name="service_name" required="" value="${param.service_name}"/>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="fw-bold">
                                <p class="w-100 text-end">Hourly rate:</p>
                            </td>
                            <td class="">
                                <input class="w-100" style="border: none" type="text" name="hourly_rate" required="" value="${param.hourly_rate}"/>
                            </td>
                        </tr>
                    </table> 
                    <div class="d-grid gap-2 col-6 mx-auto">
                        <button type="submit" class="btn btn-info btn-lg fw-bold mt-1 border-2 text-light">CREATE</button>
                    </div>
                </form>
            </div>
            <c:if test="${requestScope.MESSAGE != null}">
                <div class="alert alert-sm alert-danger w-50 col-8 d-flex justify-content-between" role="alert">
                    ${requestScope.MESSAGE}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
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
