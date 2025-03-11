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
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
                    // Write the page that you want to view
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
                case "get-all-parts-list":
                    base_url = "PartListServlet";
                    break;
                case "add-part":
                    base_url = "AddPartServlet";
                    break;
                case "update-part":
                    base_url = "UpdatePartServlet";
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
                default:
                    base_url = "index.html";
                    break;
            }
            request.getRequestDispatcher(base_url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
