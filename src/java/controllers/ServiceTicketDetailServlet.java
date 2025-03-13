/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CustomerDAO;
import dao.ServiceMechanicDAO;
import dao.ServiceTicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Customer;
import models.ServiceMeChanic;
import models.ServiceTicket;

/**
 *
 * @author Asus
 */
public class ServiceTicketDetailServlet extends HttpServlet {

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
            String serviceTicketID = request.getParameter("serviceTicketID");

            ServiceMechanicDAO s = new ServiceMechanicDAO();
            ArrayList<ServiceMeChanic> list = s.getServiceMechanicByServiceTicketID(serviceTicketID);

            ServiceTicketDAO serviceTicketDao = new ServiceTicketDAO();
            ServiceTicket ser = serviceTicketDao.getServiceTicketByID(serviceTicketID);
            HttpSession session = request.getSession();
            if (ser != null) {
                CustomerDAO customerDao = new CustomerDAO();
                Customer cus = customerDao.getCustomerById(ser.getCustID());
                if (cus != null) {
                    session.setAttribute("CUSTOMER_TICKET", cus);
                } else {
                    request.setAttribute("MESSAGE", "Customer not found.");
                    request.getRequestDispatcher("MainServlet?action=ticket-detail-page&serviceTicketID=" + serviceTicketID).forward(request, response);
                }
            } else {
                request.setAttribute("MESSAGE", "Service ticket not found.");
                request.getRequestDispatcher("MainServlet?action=ticket-detail-page&serviceTicketID=" + serviceTicketID).forward(request, response);
            }

            if (list != null && !list.isEmpty()) {
                session.setAttribute("SERVICE_MECHANIC_LIST", list);
                response.sendRedirect("MainServlet?action=ticket-detail-page&serviceTicketID=" + serviceTicketID);
            } else {
                request.setAttribute("MESSAGE", "The ticket ID = " + serviceTicketID + " are no services here");
                request.getRequestDispatcher("MainServlet?action=mechanic-dashboard").forward(request, response);
            }
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
