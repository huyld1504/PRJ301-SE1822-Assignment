/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CarDAO;
import dao.ServiceMechanicDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Car;
import models.ServiceMeChanic;

/**
 *
 * @author Asus
 */
@WebServlet(name = "CustomerGetServiceTicketDetailServlet", urlPatterns = {"/CustomerGetServiceTicketDetailServlet"})
public class CustomerGetServiceTicketDetailServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String serviceTicketID = request.getParameter("service_ticket_id");
            String carID = request.getParameter("car_id");

            ServiceMechanicDAO serviceMechanicDAO = new ServiceMechanicDAO();
            ArrayList<ServiceMeChanic> serviceMechanicList = serviceMechanicDAO.getServiceMechanicByServiceTicketID(serviceTicketID);

            //when have car dao
            CarDAO carDao = new CarDAO();
            Car c = carDao.getCarById(carID);

            if (serviceMechanicList != null && !serviceMechanicList.isEmpty()) {
                HttpSession s = request.getSession();
                s.setAttribute("car", c);
                s.setAttribute("SERVICE_MECHANIC_CUS_LIST", serviceMechanicList);
                response.sendRedirect("MainServlet?action=customer-dashboard");
            } else {
                request.setAttribute("MESSAGE", "Opps! Something went wrong.");
                request.getRequestDispatcher("MainServlet?action=customer-dashboard").forward(request, response);
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
