/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ServiceTicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.ServiceTicket;
import utils.StringUtils;

/**
 *
 * @author Asus
 */
@WebServlet(name = "SearchServiceTicketServlet", urlPatterns = {"/SearchServiceTicketServlet"})
public class SearchServiceTicketServlet extends HttpServlet {

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
            String custID = request.getParameter("customerID");
            String carID = request.getParameter("carID");
            String mechanicID = request.getParameter("mechanicID");

            String dateReceived = request.getParameter("dateReceived");

            ServiceTicketDAO ser = new ServiceTicketDAO();
            ArrayList<ServiceTicket> list = ser.searchServiceTicket(custID, carID, dateReceived, mechanicID);
            HttpSession s = request.getSession();
            s.setAttribute("SERVICE_TICKET_LIST", list);

            if (list != null && !list.isEmpty()) {
                response.sendRedirect("MainServlet?action=mechanic-dashboard");
            } else {
                request.setAttribute("MESSAGE", "Service tickets not found.");
                request.getRequestDispatcher("MainServlet?action=mechanic-dashboard&custID" + custID + "&carID=" + carID + "&ateReceived=" + dateReceived).forward(request, response);
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
