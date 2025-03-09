<%-- 
    Document   : LoginSale
    Created on : 04-03-2025, 14:11:57
    Author     : Thanh Vinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="loginSaleServlet" accept-charset="utf-8">
            <p><input type="text" name="txtname" required="">*</p>
            <p><input type="submit" value="login"/></p>
            
            <p>
                <a href="index.html" class="back-to-home-button">
                <input type="button" value="Back to Home" />
                </a>
            </p>
            
            <p>
                <%
                    if(request.getAttribute("ERROR") != null){
                        out.print(request.getAttribute("ERROR"));
                    }
                %>
            </p>
        </form>
    </body>
</html>

