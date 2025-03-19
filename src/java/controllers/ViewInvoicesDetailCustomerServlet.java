/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Car;
import models.Customer;
import models.SalesInvoice;
import models.SalesInvoiceDetail;
import models.SalesPerson;

/**
 *
 * @author Thanh Vinh
 */
public class ViewInvoicesDetailCustomerServlet extends HttpServlet {

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

            // kiểm tra session để xác định khách hàng đã đăng nhập chưa
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("CUSTOMER") == null) {
                request.setAttribute("ERROR", "Please log in first.");
                request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
                return;
            }

            // lấy invoiceID từ request
            int invoiceID = Integer.parseInt(request.getParameter("invoiceID"));

            // gọi DAO để lấy thông tin
            CustomerDAO customerDAO = new CustomerDAO();

            // lấy thông tin hóa đơn từ DAO
            SalesInvoice invoice = customerDAO.getSalesInvoiceById(invoiceID);
            if (invoice == null) {
                request.setAttribute("ERROR", "Invoice not found.");
                request.getRequestDispatcher("MainServlet?action=view-invoices-customer-page").forward(request, response);
                return;
            }

            // lấy thông tin khách hàng, xe, nhân viên bán hàng
            Customer customer = customerDAO.getCustomerInvoiceById(invoice.getCustID());
            Car car = customerDAO.getCarById(invoice.getCarID());
            SalesPerson salesPerson = customerDAO.getSalesPersonById(invoice.getSalesID());

            // lưu dữ liệu vào request để hiển thị trong JSP
            request.setAttribute("invoice", invoice);
            request.setAttribute("customer", customer);
            request.setAttribute("car", car);
            request.setAttribute("salesPerson", salesPerson);

            request.getRequestDispatcher("MainServlet?action=view-invoices-detail-customer-page").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "An error occurred while retrieving invoice details.");
            request.getRequestDispatcher("MainServlet?action=view-invoices-customer-page").forward(request, response);
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
