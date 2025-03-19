/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.InvoiceDAO;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author ADMIN
 */
public class CreateInvoiceServlet extends HttpServlet {

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
         try {
          
            String salesID = request.getParameter("sale_ID");
            String invoiceDateStr = request.getParameter("invoiceDate");
            String carID = request.getParameter("carID");
            String custID = request.getParameter("custID");
            if (salesID == null || invoiceDateStr == null || carID == null || custID == null ||
                salesID.isEmpty() || invoiceDateStr.isEmpty() || carID.isEmpty() || custID.isEmpty()) {
//                response.sendRedirect("MainServlet?action=create-invoice-page");
                return;
            }

            LocalDate localDate = LocalDate.parse(invoiceDateStr);
            Date invoiceDate = Date.valueOf(localDate);

            
            InvoiceDAO dao = new InvoiceDAO();
            int invoiceID = dao.getNextInvoiceID();
            boolean success = dao.createInvoice(invoiceID, invoiceDate, salesID, carID, custID);

           
            if (success) {
                response.sendRedirect("MainServlet?action=get-invoice-list");
            } else {
                response.sendRedirect("MainServlet?action=create-invoice-page");
                System.out.println("ERROR");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
