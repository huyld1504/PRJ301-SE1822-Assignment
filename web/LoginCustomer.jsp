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
        <div class="w-100 h-100 my-5">
            <div style="
                 box-shadow: 10px 10px 20px 0px rgba(122,122,122,1);
                 -webkit-box-shadow: 10px 10px 20px 0px rgba(122,122,122,1);
                 -moz-box-shadow: 10px 10px 20px 0px rgba(122,122,122,1);"
                 class="w-50 border bg-light bg-gradient rounded-3 pt-5 mx-auto"
                 >
                <form 
                    accept-charset="UTF-8"
                    action="MainServlet"
                    style="height: 350px;"
                    class="d-flex flex-column justify-content-center"
                    method="get"
                    >
                    <p class="text-center fs-1 mb-0">Login Customer Form</p>
                    <hr/>
                    <div class="input-group my-3 mx-auto w-75">
                        <span class="input-group-text fw-bold bg-info text-light w-25" id="inputGroup-sizing-default">Username</span>
                        <input 
                            type="text" 
                            class="form-control" 
                            aria-label="Sizing example input" 
                            aria-describedby="inputGroup-sizing-default"
                            name="customer_name"
                            value="${param.customer_name}"
                            >
                    </div>

                    <div class="input-group my-3 mx-auto w-75">
                        <span class="input-group-text fw-bold bg-info text-light w-25" id="inputGroup-sizing-default">Phone</span>
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
                        <div class="toast align-items-center text-bg-danger border-0 fade show mx-auto w-75 my-3" role="alert" aria-live="assertive" aria-atomic="true">
                            <div class="d-flex">
                                <div class="toast-body">
                                    ${requestScope.ERROR}
                                </div>
                                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                            </div>
                        </div>
                    </c:if>
                    <input type="hidden" name="action" value="login-customer"/>
                    <div class="d-grid gap-2 col-6 mx-auto mb-5">
                        <button type="submit" class="btn btn-info btn-lg fw-bold border-2 mt-3 text-light">LOGIN</button>
                    </div>
                </form>
                <div class=" my-5 mx-auto w-100 rounded-3 d-flex justify-content-around align-items-center" style="height: 50px;">
                    <p class="fs-5">Click <a href="MainServlet?action=mechanic-login-page">here</a> if you are Mechanic</p>
                    <p class="fs-5">Click <a href="MainServlet?action=login-sale-page">here</a> if you are Sale</p>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
