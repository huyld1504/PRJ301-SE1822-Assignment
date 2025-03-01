<%-- 
    Document   : LoginCustomer
    Created on : Feb 26, 2025, 7:14:47 PM
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
    </head>
    <body>
        <!--<p class="text-light fw-bold d-flex justify-content-center algin-items-center bg-info py-3" style="font-size: 50px;">Login Customer</p>-->
        <div style="
             box-shadow: 10px 10px 20px 0px rgba(122,122,122,1);
             -webkit-box-shadow: 10px 10px 20px 0px rgba(122,122,122,1);
             -moz-box-shadow: 10px 10px 20px 0px rgba(122,122,122,1);"
             class="w-50 h-100 border border-2 border-light-subtle container-fluid bg-light bg-gradient mt-5"
             >
            <form 
                accept-charset="UTF-8"
                action="MainServlet"
                style="height: 60vh;"
                class="d-flex flex-column justify-content-center"
                method="get"
                >
                <p class="text-center fs-1">Login Customer Form</p>
                <hr/>
                <div class="input-group mb-3">
                    <span class="input-group-text fw-bold bg-info text-light" id="inputGroup-sizing-default">Username</span>
                    <input 
                        type="text" 
                        class="form-control" 
                        aria-label="Sizing example input" 
                        aria-describedby="inputGroup-sizing-default"
                        name="customer_name"
                        value="${param.customer_name}"
                    >
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text fw-bold bg-info text-light" id="inputGroup-sizing-default">Phone</span>
                    <input 
                        type="text" 
                        class="form-control" 
                        aria-label="Sizing example input" 
                        aria-describedby="inputGroup-sizing-default"
                        name="customer_phone"
                        value="${param.customer_phone}"
                    >
                </div>
                <c:if test="${requestScope.ERROR != null}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        ${requestScope.ERROR}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <input type="hidden" name="action" value="login-customer"/>
                <div class="d-grid gap-2 col-6 mx-auto">
                    <button type="submit" class="btn btn-info btn-lg fw-bold border-2 mt-3 text-light">LOGIN</button>
                </div>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
