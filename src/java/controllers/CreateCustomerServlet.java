/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.SalePersonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class CreateCustomerServlet extends HttpServlet {

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
            
            // Lấy thông tin SalesPerson từ session
            HttpSession session = request.getSession(false);
            SalesPerson salesPerson = (SalesPerson) session.getAttribute("SALE");

            if (salesPerson == null) {
                request.setAttribute("ERROR", "Access not allowed! Please log in again.");
                request.getRequestDispatcher("MainServlet?action=login-sale-page").forward(request, response);
                return;
            }

            // Lấy thông tin từ form
            String custName = request.getParameter("custName");
            String phone = request.getParameter("phone");
            String sex = request.getParameter("sex");
            String cusAddress = request.getParameter("cusAddress");

            // Tạo đối tượng Customer
            Customer newCustomer = new Customer(null, custName, phone, cusAddress, sex);

            // Gọi DAO để thêm khách hàng
            SalePersonDAO saleDAO = new SalePersonDAO();
            boolean isAdded = saleDAO.addCustomer(newCustomer);

            if (isAdded) {
                request.setAttribute("SUCCESS", "Customer created successfully!");
            } else {
                request.setAttribute("ERROR", "Customer creation failed. Please try again!");
            }

            // Cập nhật danh sách khách hàng và quay lại SaleDashboard.jsp
            ArrayList<Customer> customers = saleDAO.getCustomersBySalesID(salesPerson.getSalesID());
            request.setAttribute("CUSTOMER_LIST", customers);
            request.getRequestDispatcher("MainServlet?action=sale-dashboard").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "System error. Please try again!");
            request.getRequestDispatcher("MainServlet?action=sale-dashboard&ERROR").forward(request, response);
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
