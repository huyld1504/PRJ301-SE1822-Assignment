/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.MechanicDAO;
import dao.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Mechanic;
import models.Service;

/**
 *
 * @author Asus
 */
@WebServlet(name = "GetCustomerServiceMechanicDetailServlet", urlPatterns = {"/GetCustomerServiceMechanicDetailServlet"})
public class GetCustomerServiceMechanicDetailServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String serviceID = request.getParameter("serviceID");
            String mechanicID = request.getParameter("mechanicID");

            ServiceDAO serviceDAO = new ServiceDAO();
            MechanicDAO mechanicDAO = new MechanicDAO();

            Mechanic m = mechanicDAO.getMechanicByID(mechanicID);
            Service s = serviceDAO.getServiceByID(serviceID);

            if (s != null && m != null) {
                request.setAttribute("mechanic", m);
                request.setAttribute("service", s);
                request.getRequestDispatcher("MainServlet?action=customer-service-mechanic-detail-page").forward(request, response);
            } else {
                request.setAttribute("MESSAGE", "Opps! Something went wrong.");
                request.getRequestDispatcher("MainServlet?action=customer-service-mechanic-detail-page").forward(request, response);
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
