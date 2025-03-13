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
public class EditCustomerServlet extends HttpServlet {

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

            // Lấy customerId từ URL
        String customerId = request.getParameter("id");

        // Kiểm tra nếu customerId là hợp lệ
        if (customerId == null || customerId.isEmpty()) {
            request.setAttribute("ERROR", "Customer ID is missing.");
            request.getRequestDispatcher("SaleDashboard.jsp").forward(request, response);
            return;
        }

        // Gọi DAO để lấy thông tin khách hàng
        SalePersonDAO salePersonDAO = new SalePersonDAO();
        Customer customer = salePersonDAO.getCustomerById(customerId);

        // Kiểm tra nếu không tìm thấy khách hàng
        if (customer == null) {
            request.setAttribute("ERROR", "Customer not found.");
            request.getRequestDispatcher("SaleDashboard.jsp").forward(request, response);
            return;
        }

        // Truyền đối tượng customer vào request
        request.setAttribute("customer", customer);

        // Chuyển hướng tới trang EditCustomer.jsp để hiển thị dữ liệu
        request.getRequestDispatcher("EditCustomer.jsp").forward(request, response);

    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("ERROR", "System error. Please try again.");
        request.getRequestDispatcher("SaleDashboard.jsp").forward(request, response);
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
