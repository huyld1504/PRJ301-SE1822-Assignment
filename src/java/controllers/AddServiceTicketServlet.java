/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ServiceTicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ServiceTicket;

/**
 *
 * @author ADMIN
 */
public class AddServiceTicketServlet extends HttpServlet {

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

            // Lấy thông tin từ form gửi lên
            String ticketID = request.getParameter("ticketID");
            String dateReceivedStr = request.getParameter("dateReceived");
            String dateReturnStr = request.getParameter("dateReturn");
            String custID = request.getParameter("custID");
            String carID = request.getParameter("carID");

            // Kiểm tra thông tin có bị thiếu không (không dùng hàm isNullOrEmpty nữa)
            if (ticketID == null || ticketID.trim().isEmpty()
                    || dateReceivedStr == null || dateReceivedStr.trim().isEmpty()
                    || dateReturnStr == null || dateReturnStr.trim().isEmpty()
                    || custID == null || custID.trim().isEmpty()
                    || carID == null || carID.trim().isEmpty()) {

                request.setAttribute("error", "pls fuse full form!");
                request.getRequestDispatcher("MainServlet?action=add-service-ticket-form").forward(request, response);
                return;
            }

            try {
                ServiceTicketDAO ticketDAO = new ServiceTicketDAO();

                // Kiểm tra trùng mã Ticket ID
                if (ticketDAO.isTicketIDExist(ticketID)) {
                    request.setAttribute("error", "Ticket ID is duplicate! Enter new id again!!");
                    request.getRequestDispatcher("MainServlet?action=add-service-ticket-form").forward(request, response);
                    return;
                }

                // Chuyển String sang Date (nếu format không đúng sẽ ném IllegalArgumentException)
                Date dateReceived = Date.valueOf(dateReceivedStr);
                Date dateReturn = Date.valueOf(dateReturnStr);

                // Tạo ServiceTicket object và set dữ liệu
                ServiceTicket ticket = new ServiceTicket();
                ticket.setServiceTicketID(ticketID);
                ticket.setDateReceived(dateReceived);
                ticket.setDateReaturned(dateReturn);
                ticket.setCustID(custID);
                ticket.setCarID(carID);

                // Thêm ticket vào DB
                boolean success = ticketDAO.AddServiceTicket(ticket);

                if (success) {
                    response.sendRedirect("MainServlet?action=view-service-ticket");
                } else {
                    request.setAttribute("error", "Thêm Service Ticket thất bại!");
                    request.getRequestDispatcher("MainServlet?action=add-service-ticket-form").forward(request, response);
                }

            } catch (IllegalArgumentException e) {
                request.setAttribute("error", "Ivalid format: yyyy-MM-dd");
                request.getRequestDispatcher("MainServlet?action=add-service-ticket-form").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "add service ticket fail");
                request.getRequestDispatcher("MainServlet?action=add-service-ticket-form").forward(request, response);
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
