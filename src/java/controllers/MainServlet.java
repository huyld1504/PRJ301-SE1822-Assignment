/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Asus
 */
public class MainServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = "home";
            String base_url;

            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }

            switch (action) {
                case "home":
                    //Write the page that you want to view
                    base_url = "LoginCustomer.jsp";
                    break;
                case "login-customer":
                    base_url = "LoginCustomerServlet";
                    break;
                case "customer-dashboard":
                    base_url = "CustomerDashboard.jsp";
                    break;
                case "customer-logout":
                    base_url = "LogoutCustomerServlet";
                    break;
                case "customer-profile":
                    base_url = "CustomerProfile.jsp";
                    break;
                case "update-customer-profile":
                    base_url = "UpdateCustomerProfileServlet";
                    break;
                case "login-sale-page":
                    base_url = "LoginSale.jsp";
                    break;
                case "sale-dashboard":
                    base_url = "SaleDashboard.jsp";
                    break;
                case "mechanic-login-page":
                    base_url = "LoginMechanic.jsp";
                    break;
                case "mechanic-login":
                    base_url = "LoginMechanicServlet";
                    break;
                case "mechanic-dashboard":
                    base_url = "MechanicDashboard.jsp";
                    break;
                case "mechanic-logout":
                    base_url = "LogoutMechanic";
                    break;
                case "service-ticket":
                    base_url = "ServiceTicketListServlet";
                    break;
                case "ticket-detail":
                    base_url = "ServiceTicketDetailServlet";
                    break;
                case "ticket-detail-page":
                    base_url = "ServiceTicketDetail.jsp";
                    break;
                case "update-ticket-detail":
                    base_url = "UpdateServiceTicketServlet";
                    break;
                case "logout-sale":
                    base_url = "LogoutSalesServlet";
                    break;
                case "login-sale":
                    base_url = "LoginSaleServlet";
                    break;
                case "service-page":
                    base_url = "ServicePage.jsp";
                    break;
                case "get-service-list":
                    base_url = "ServicesListServlet";
                    break;
                case "update-service":
                    base_url = "UpdateServiceServlet";
                    break;
                case "delete-service":
                    base_url = "ServiceDeleteServlet";
                    break;
                case "delete-service-mechanic":
                    base_url = "ServiceMechanicDeleteServlet";
                    break;
                case "read-customer":
                    base_url = "ReadCustomerServlet";
                    break;
                case "read-customer-page":
                    base_url = "ReadCustomer.jsp";
                    break;
                case "create-customer-page":
                    base_url = "CreateCustomer.jsp";
                    break;
                case "create-customer":
                    base_url = "CreateCustomerServlet";
                    break;
                case "edit-customer-page":
                    base_url = "EditCustomer.jsp";
                    break;
                case "edit-customer":
                    base_url = "EditCustomerServlet";
                    break;
                case "delete-customer":
                    base_url = "DeleteCustomerServlet";
                    break;
                case "search-customer-page":
                    base_url = "SearchCustomer.jsp"; // Chuyển hướng đến servlet tìm kiếm
                    break;
                case "search-customer":
                    base_url = "SearchCustomerServlet"; // Chuyển hướng đến servlet tìm kiếm
                    break;
                case "up-edit-customer":
                    base_url = "UpEditCustomerServlet"; // Chuyển hướng đến servlet tìm kiếm
                    break;
                case "create-service-page":
                    base_url = "CreateServicePage.jsp";
                    break;
                case "create-service":
                    base_url = "ServiceCreateServlet";
                    break;
                case "get-customer-service-ticket":
                    base_url = "CustomerGetServiceTicketServlet";
                    break;
                case "get-customer-ticket-detail":
                    base_url = "CustomerGetServiceTicketDetailServlet";
                    break;
                case "customer-service-mechanic-detail-page":
                    base_url = "CustomerServiceMechanicDetailPage.jsp";
                    break;
                case "get-customer-service-mechanic-detail":
                    base_url = "GetCustomerServiceMechanicDetailServlet";
                    break;
                case "read-car-page":
                    base_url = "ReadCar.jsp";
                    break;
                case "read-car":
                    base_url = "ReadCarServlet";
                    break;    
                case "create-car-page":
                    base_url = "CreateCar.jsp";
                    break;    
                case "create-car":
                    base_url = "CreateCarServlet";
                    break;
                case "edit-car-page":
                    base_url = "UpdateCar.jsp";
                    break;    
                case "edit-car":
                    base_url = "UpdateCarServlet";
                    break;    
                case "up-edit-car":
                    base_url = "UpEditCarServlet";
                    break;    
                case "delete-car":
                    base_url = "DeleteCarServlet";
                    break;
                case "search-car-page":
                    base_url = "SearchCar.jsp";
                    break;
                case "search-car":
                    base_url = "SearchCarServlet";
                    break;
                case "search-service-ticket":
                    base_url = "SearchServiceTicketServlet";
                    break;
                default:
                    base_url = "index.html";
                    break;
            }

            request.getRequestDispatcher(base_url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
