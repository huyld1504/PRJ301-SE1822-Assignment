/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.SalePersonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Customer;
import models.SalesPerson;

/**
 *
 * @author Thanh Vinh
 */
public class UpEditCustomerServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            // Kiểm tra session và lấy thông tin SalesPerson
            HttpSession session = request.getSession(false);
            SalesPerson salesPerson = (SalesPerson) session.getAttribute("SALE");

            if (salesPerson == null) {
                // Nếu không có session, thông báo lỗi và quay về login
                request.setAttribute("ERROR", "Access not allowed! Please log in again.");
                request.getRequestDispatcher("MainServlet?action=login-sale-page").forward(request, response);
                return;
            }

            String customerId = request.getParameter("customer_id");
            String customerName = request.getParameter("customer_name");
            String customerPhone = request.getParameter("customer_phone");
            String customerSex = request.getParameter("customer_sex");
            String customerAddress = request.getParameter("customer_address");

            // Kiểm tra nếu customerId rỗng
            if (customerId == null || customerId.isEmpty()) {
                request.setAttribute("ERROR", "Customer ID cannot be empty.");
                request.getRequestDispatcher("MainServlet?action=edit-customer-page").forward(request, response);
                return;
            }

            // Tạo đối tượng Customer mới để cập nhật
            Customer updatedCustomer = new Customer(customerId, customerName, customerPhone, customerAddress, customerSex);
            SalePersonDAO salePersonDAO = new SalePersonDAO();
            boolean isUpdated = salePersonDAO.updateCustomer(customerId, updatedCustomer); // Gọi phương thức updateCustomer trong SalePersonDAO

            if (isUpdated) {
                request.setAttribute("MESSAGE", "Customer updated successfully!");
            } else {
                request.setAttribute("ERROR", "Error updating customer. Please try again.");
            }

            request.getRequestDispatcher("MainServlet?action=edit-customer-page").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "System error, please try again.");
            request.getRequestDispatcher("MainServlet?action=sale-dashboard").forward(request, response);
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
