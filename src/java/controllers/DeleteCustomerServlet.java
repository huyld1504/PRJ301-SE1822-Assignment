/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CarDAO;
import dao.SalePersonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.SalesPerson;

/**
 *
 * @author Thanh Vinh
 */
public class DeleteCustomerServlet extends HttpServlet {

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

            // kiểm tra session để xác định người dùng đã đăng nhập hay chưa
            HttpSession session = request.getSession(false);
            SalesPerson salesPerson = (SalesPerson) session.getAttribute("SALE");

            if (salesPerson == null) {
                request.setAttribute("ERROR", "Access not allowed! Please log in again.");
                request.getRequestDispatcher("MainServlet?action=login-sale-page").forward(request, response);
                return;
            }

            String custID = request.getParameter("id");
            SalePersonDAO customerDAO = new SalePersonDAO();

            // lấy tên khách hàng từ cơ sở dữ liệu trước khi xóa
            String customerName = customerDAO.getCustomerNameById(custID);
            boolean isDeleted = customerDAO.deleteCustomer(custID);
            
            // kiểm tra xóa thành công hay không
            if (isDeleted) {
                // nếu xóa thành công, thông báo thành công với tên khách hàng
                request.setAttribute("MESSAGE", "Customer " + customerName + " deleted successfully!");
            } else {
                // nếu xóa thất bại, thông báo lỗi
                request.setAttribute("ERROR", "Customer deletion failed. Please try again!");
            }

            // trả lại danh sách khách hàng và thông báo đến trang saledashboard thông qua readcustomerservlet
            request.getRequestDispatcher("MainServlet?action=sale-dashboard").forward(request, response);
        
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "An error occurred while deleting the customer. Please try again.");
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
