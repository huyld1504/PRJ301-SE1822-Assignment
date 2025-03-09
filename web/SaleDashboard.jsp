<%-- 
    Document   : SaleDashboard.jsp
    Created on : 04-03-2025, 16:23:22
    Author     : Thanh Vinh
--%>

<%@page import="models.SalesInvoice"%>
<%@page import="models.SalesPerson"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            SalesPerson kq =(SalesPerson) session.getAttribute("sale");
            if(kq==null){
                request.setAttribute("ERROR", "Chưa Login ba ơi ba");
                request.getRequestDispatcher("MainServlet?action=login-sale").forward(request, response);
            }
            else{
            %>
            <h1>welcome <%= kq.getSalesName() %></h1>
            <h2><a href="MainServlet?action=logout-sale">Logout</a></h2>
            <h1>DASHBOARD</h1>                   
        <%
            }
        %>
        
        <%
            ArrayList<SalesInvoice> list =(ArrayList) request.getAttribute("LIST_INVOICE");
            if(list != null && !list.isEmpty()){
            %>
            <table>
                <tr><th>    inv id  </th>
                    <th>    create date </th>
                </tr>
                <%
                    for(SalesInvoice i:list){
                %>
                <tr>
                    <td><%= i.getInvoiceID() %></td>
                    <td><%= i.getInvoiceDate().toString() %></td>
                    <td><input type="submit" value="detail"></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            <%
            }
            else{
                
            }

        %>
        
    </body>
</html>
