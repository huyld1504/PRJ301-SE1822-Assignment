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
import javax.servlet.http.HttpSession;
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
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String partID = request.getParameter("part_id");
            String partName = request.getParameter("part_name");
            String purchasePriceStr = request.getParameter("purchase_price");
            String retailPriceStr = request.getParameter("retail_price");

            
            if(checkEmptyStr(partID) || checkEmptyStr(partName)||checkEmptyStr(purchasePriceStr) || checkEmptyStr(retailPriceStr)){
                request.setAttribute("ERROR", "All fields are not be empty");
                request.getRequestDispatcher("MainsPartServlet?action=saler-profile").forward(request, response);
            }
            //change
            double purchasePrice = Double.parseDouble(purchasePriceStr);
            double retailPrice = Double.parseDouble(retailPriceStr);
            //
            Part newPart = new Part(partID, partName, purchasePrice, retailPrice);

            PartDAO dao = new PartDAO();
            
            boolean isUpdated = dao.updatePart(partID,newPart);

            if (isUpdated) {
                HttpSession s = request.getSession(true);
                s.setAttribute("part", newPart);
                request.setAttribute("MESSAGE", "Updated successfully!");
            } else {
                request.setAttribute("ERROR", "Failed to update!");
            }
            request.getRequestDispatcher("MainServlet?action=UpdatePart.jsp").forward(request, response);
        }
    }
    public boolean checkEmptyStr(String str){
        boolean isEmpty = str.trim().equals("");
        return isEmpty;
         
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
