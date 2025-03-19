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
public class SearchCustomerServlet extends HttpServlet {

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
            
            // kiểm tra session để xác định người dùng đã đăng nhập hay chưa
            HttpSession session = request.getSession(false);
            SalesPerson salesPerson = (SalesPerson) session.getAttribute("SALE");

            if (salesPerson == null) {
                request.setAttribute("ERROR", "Access not allowed! Please log in again.");
                request.getRequestDispatcher("MainServlet?action=login-sale-page").forward(request, response);
                return;
            }
            
            // lấy thông tin tìm kiếm từ form
            String searchName = request.getParameter("search_name");

            // gọi DAO để thực hiện tìm
            SalePersonDAO salePersonDAO = new SalePersonDAO();
            ArrayList<Customer> customerList;

            if (searchName == null || searchName.trim().isEmpty()) {
                customerList = salePersonDAO.getAllCustomers();  // lấy tất cả khách hàng nếu không có tên tìm kiếm
            } else {
                customerList = salePersonDAO.searchCustomerByName(searchName);  // tìm khách hàng theo tên
            }

            // đưa danh sách khách hàng vào request
            request.setAttribute("CUSTOMER_LIST", customerList);

            // chuyển đến trang SearchCustomer.jsp để hiển thị kết quả
            request.getRequestDispatcher("MainServlet?action=search-customer-page").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Error during customer search.");
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
