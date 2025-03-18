/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Customer;
import models.SalesInvoice;
import models.SalesInvoiceDetail;

/**
 *
 * @author Thanh Vinh
 */
public class ViewInvoicesCustomerServlet extends HttpServlet {

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

            // Kiểm tra session để xác định khách hàng đã đăng nhập chưa
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("CUSTOMER") == null) {
                request.setAttribute("ERROR", "Please log in first.");
                request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
                return;
            }

            // Lấy thông tin khách hàng từ session
            Customer customer = (Customer) session.getAttribute("CUSTOMER");
            String custID = customer.getCustID();

            // Lấy danh sách hóa đơn từ CustomerDAO
            CustomerDAO customerDAO = new CustomerDAO();
            List<SalesInvoice> invoices = customerDAO.getSalesInvoicesByCustomerId(custID);

            // Nếu không có hóa đơn, trả về thông báo lỗi
            if (invoices == null || invoices.isEmpty()) {
                request.setAttribute("ERROR", "No invoices found for the customer.");
                request.getRequestDispatcher("MainServlet?action=customer-dashboard").forward(request, response);
                return;
            }

            // Lưu danh sách hóa đơn vào request và forward đến trang hiển thị
            request.setAttribute("invoices", invoices);
            request.getRequestDispatcher("MainServlet?action=view-invoices-customer-page").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "An error occurred while retrieving the invoices. Please try again.");
            request.getRequestDispatcher("MainServlet?action=customer-dashboard").forward(request, response);
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
