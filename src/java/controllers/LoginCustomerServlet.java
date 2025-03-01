package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Customer;

/**
 *
 * @author Asus
 */
public class LoginCustomerServlet extends HttpServlet {

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
            String customer_name = request.getParameter("customer_name");
            String phone = request.getParameter("customer_phone");

            if (customer_name.trim().equals("")) {
                request.setAttribute("ERROR", "username can not be empty.");
                request.getRequestDispatcher("MainServlet?action=home&customer_name="+customer_name+"&customer_phone="+phone).forward(request, response);
            }

            if (phone.trim().equals("")) {
                request.setAttribute("ERROR", "phone can not be empty.");
                request.getRequestDispatcher("MainServlet?action=home&customer_name="+customer_name+"&customer_phone="+phone).forward(request, response);
            }

            if (!phone.matches("^\\d+$")) {
                request.setAttribute("ERROR", "phone is invalid format");
                request.getRequestDispatcher("MainServlet?action=home&customer_name="+customer_name+"&customer_phone="+phone).forward(request, response);
            }

            CustomerDAO c = new CustomerDAO();
            Customer customer = c.login(customer_name, phone);

            if (customer != null) {
                HttpSession s = request.getSession(true);
                s.setAttribute("CUSTOMER", customer);
                request.getRequestDispatcher("MainServlet?action=customer-dashboard").forward(request, response);
            } else {
                request.setAttribute("ERROR", "Customer not found.");
                request.getRequestDispatcher("MainServlet?action=home&customer_name="+customer_name+"&customer_phone="+phone).forward(request, response);
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
