/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.InvoiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.SalesInvoice;

/**
 *
 * @author ADMIN
 */
public class InvoiceListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

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
        HttpSession session = request.getSession();
        String saleID = (String) session.getAttribute("sale_ID");

        if (saleID == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        InvoiceDAO dao = new InvoiceDAO();
        ArrayList<SalesInvoice> list = dao.getInvoicesBySaleID(saleID);
        
        String saleName =dao.getSalesPersonNameByID(saleID);
        session.setAttribute("saleName", saleName);
        request.setAttribute("LIST", list);
        request.setAttribute("sale_ID", session.getAttribute("sale_ID"));
        request.getRequestDispatcher("MainServlet?action=invoice-list-page").forward(request, response);
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
        HttpSession session = request.getSession();
        String saleID = (String) session.getAttribute("sale_ID");

        if (saleID == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String carID = request.getParameter("carID");
        String custID = request.getParameter("custID");

        InvoiceDAO invoiceDAO = new InvoiceDAO();
        int newInvoiceID = invoiceDAO.getNextInvoiceID();
        boolean success = invoiceDAO.createInvoice(newInvoiceID, saleID, carID, custID);

        if (success) {
            response.sendRedirect("invoiceList?success=true");
        } else {
            response.sendRedirect("invoiceList?error=true");
        }
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
