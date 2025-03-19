/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CarDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Car;
import models.SalesPerson;

/**
 *
 * @author Thanh Vinh
 */
public class UpEditCarServlet extends HttpServlet {

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
            
            // kiểm tra session và lấy thông tin SalesPerson
            HttpSession session = request.getSession(false);
            SalesPerson salesPerson = (SalesPerson) session.getAttribute("SALE");

            if (salesPerson == null) {
                request.setAttribute("ERROR", "Access not allowed! Please log in again.");
                request.getRequestDispatcher("MainServlet?action=login-sale-page").forward(request, response);
                return;
            }

            // lấy carID và các tham số từ form
            String carId = request.getParameter("carID");
            String serialNumber = request.getParameter("serialNumber");
            String model = request.getParameter("model");
            String colour = request.getParameter("colour");
            int year = Integer.parseInt(request.getParameter("year"));
            double price = Double.parseDouble(request.getParameter("price"));  // Lấy giá trị price từ form

            if (carId == null || carId.isEmpty()) {
                request.setAttribute("ERROR", "Car ID cannot be empty.");
                request.getRequestDispatcher("MainServlet?action=sale-dashboard").forward(request, response);
                return;
            }

            // tạo đối tượng Car mới và gọi DAO để cập nhật
            Car updatedCar = new Car(carId, serialNumber, model, colour, year, price);
            CarDAO carDAO = new CarDAO();
            boolean isUpdated = carDAO.updateCar(updatedCar);

            // kiểm tra cập nhật được hay không
            if (isUpdated) {
                request.setAttribute("MESSAGE", "Car updated successfully!");
            } else {
                request.setAttribute("ERROR", "Error updating car. Please try again.");
            }

            request.getRequestDispatcher("MainServlet?action=read-car").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "System error, please try again.");
            request.getRequestDispatcher("MainServlet?action=read-car").forward(request, response);
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
