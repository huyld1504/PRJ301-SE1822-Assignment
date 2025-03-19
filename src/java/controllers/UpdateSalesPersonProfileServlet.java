/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.SalePersonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
public class UpdateSalesPersonProfileServlet extends HttpServlet {

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

            // kiểm tra session để xác thực người bán đã đăng nhập chưa
            HttpSession session = request.getSession(false);
            SalesPerson salesPerson = (SalesPerson) session.getAttribute("SALE");

            if (salesPerson == null) {
                request.setAttribute("ERROR", "Access not allowed! Please log in again.");
                request.getRequestDispatcher("MainServlet?action=login-sale-page").forward(request, response);
                return;
            }
            
            //lấy thông tin mới từ form cập nhật
            String salespersonID = request.getParameter("salesperson_id");
            String salespersonName = request.getParameter("salesperson_name");
            String salespersonSex = request.getParameter("salesperson_sex");
            String salespersonAddress = request.getParameter("salesperson_address");

            String salespersonBirthdayStr = request.getParameter("salesperson_birthday");
            Date salespersonBirthday = null; // khai báo biến trước khi kiểm tra

            if (salespersonBirthdayStr != null && !salespersonBirthdayStr.isEmpty()) {
                try {
                    salespersonBirthday = Date.valueOf(salespersonBirthdayStr);  // chuyển từ String thành Date
                } catch (IllegalArgumentException e) {
                    request.setAttribute("ERROR", "Invalid date format. Please use yyyy-MM-dd.");
                    request.getRequestDispatcher("MainServlet?action=sale-profile").forward(request, response);
                    return; // dừng lại nếu gặp lỗi
                }
            }

            // tạo đối tượng SalesPerson mới với thông tin đã được cập nhật
            SalesPerson newProfile = new SalesPerson(salespersonID, salespersonName, salespersonAddress, salespersonSex, salespersonBirthday);

        //    System.out.println("Birthday: " + newProfile.getBirthday());

            // gọi DAO để cập nhật thông tin SalesPerson trong cơ sở dữ liệu
            SalePersonDAO salesPersonDAO = new SalePersonDAO();
            boolean isUpdated = salesPersonDAO.update(salespersonID, newProfile);

            // kiểm tra kết quả cập nhật
            if (isUpdated) {
                // cập nhật thông tin SalesPerson trong session
                session = request.getSession(true);
                session.setAttribute("SALE", newProfile);
                request.setAttribute("MESSAGE", "Profile updated successfully!");
            } else {
                request.setAttribute("ERROR", "Failed to update profile!");
            }

            // quay lại trang Profile
            request.getRequestDispatcher("MainServlet?action=sale-profile").forward(request, response);
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
