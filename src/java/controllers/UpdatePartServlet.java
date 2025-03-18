/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.PartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Part;

/**
 *
 * @author ADMIN
 */
public class UpdatePartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             
        }
    }

    boolean isEmpty(String str) {
        return str.trim().isEmpty() || str == null;
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String partID = request.getParameter("partID");
        
        if (partID == null || partID.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Part ID is required!");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        
        PartDAO partDAO = new PartDAO();
        Part part = partDAO.getPartByID(partID);
        
        if (part == null) {
            request.setAttribute("errorMessage", "Part not found!");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            request.setAttribute("part", part);
            request.getRequestDispatcher("UpdatePart.jsp").forward(request, response);
        }
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String partID = request.getParameter("partID");
        String partName = request.getParameter("partName");
        String purchasePriceStr = request.getParameter("purchasePrice");
        String retailPriceStr = request.getParameter("retailPrice");
        
        if (isEmpty(partID) || isEmpty(partName) || isEmpty(purchasePriceStr) || isEmpty(retailPriceStr)) {
            request.setAttribute("errorMessage", "All fields are required!");
            request.getRequestDispatcher("UpdatePart.jsp").forward(request, response);
            return;
        }
        
        try {
            Double purchasePrice = Double.parseDouble(purchasePriceStr);
            Double retailPrice = Double.parseDouble(retailPriceStr);
            
            Part newPart = new Part(partID, partName, purchasePrice, retailPrice);
            PartDAO pd = new PartDAO();
            boolean isUpdated = pd.updatePart(partID, newPart);
            
            if (isUpdated) {
                request.setAttribute("message", "Update successfully");
                response.sendRedirect("MainServlet?action=get-part-page");
            } else {
                request.setAttribute("ERROR", "Update failed!");
                request.getRequestDispatcher("UpdatePart.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid price format!");
            request.getRequestDispatcher("UpdatePart.jsp").forward(request, response);
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
