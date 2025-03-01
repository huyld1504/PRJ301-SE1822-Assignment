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
        <p class="text-light fw-bold d-flex justify-content-center algin-items-center bg-info py-3" style="font-size: 50px;">Login Customer</p>
        <div style="
             box-shadow: 10px 10px 20px 0px rgba(122,122,122,1);
             -webkit-box-shadow: 10px 10px 20px 0px rgba(122,122,122,1);
             -moz-box-shadow: 10px 10px 20px 0px rgba(122,122,122,1);"
             class="w-50 h-100 border border-2 border-light-subtle container-fluid mt-5"
             >
            <form 
                accept-charset="UTF-8"
                class="bg-light bg-gradient py-5"
                action="MainServlet"
                method="get"
                >
                <div class="mb-3 ml-3">
                    <label class="form-label fs-5 fw-bold" for="customer_name">
                        Enter username:
                    </label>
                    <input type="text" id="customer_name" name="customer_name" class="form-control border-info border-2" placeholder="Enter here" value="${param.customer_name}"/>
                </div>

                <div class="mb-3 ml-3">
                    <label class="form-label fs-5 fw-bold" for="customer_name">
                        Enter phone number:
                    </label>
                    <input id="customer_phone" name="customer_phone" class="form-control border-info border-2" placeholder="Enter here" value="${param.customer_phone}"/>
                </div>
                <c:if test="${requestScope.ERROR != null}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        ${requestScope.ERROR}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <input type="hidden" name="action" value="login-customer"/>
                <div class="d-grid gap-2 col-6 mx-auto">
                    <button type="submit" class="btn btn-info fw-bold border-2 mt-3 fs-4 text-light">LOGIN</button>
                </div>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
