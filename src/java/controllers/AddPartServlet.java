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
public class AddPartServlet extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String partID = request.getParameter("partID");
        String partName = request.getParameter("partName");
        String purchasePriceStr = request.getParameter("purchasePrice");
        String retailPriceStr = request.getParameter("retailPrice");

        if (isEmpty(partID) || isEmpty(partName) || isEmpty(purchasePriceStr) || isEmpty(retailPriceStr)) {
            request.setAttribute("errorMessage", "All fields are required!");
            request.getRequestDispatcher("AddPart.jsp").forward(request, response);
            return;
        }

        PartDAO pd = new PartDAO();

        // ✅ Kiểm tra trùng partID
        if (pd.isDuplicatePartID(partID)) {
            request.setAttribute("errorMessage", "Part ID already exists!");
            request.getRequestDispatcher("AddPart.jsp").forward(request, response);
            return;
        }

        try {
            double purchasePrice = Double.parseDouble(purchasePriceStr);
            double retailPrice = Double.parseDouble(retailPriceStr);

            Part newPart = new Part(partID, partName, purchasePrice, retailPrice);

            boolean isAdded = pd.creatPart(newPart);
            if (isAdded) {
                response.sendRedirect("MainServlet?action=get-part-page");
            } else {
                request.setAttribute("errorMessage", "Failed to add part");
                request.getRequestDispatcher("AddPart.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Price fields must be numbers!");
            request.getRequestDispatcher("AddPart.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An unexpected error occurred!");
            request.getRequestDispatcher("AddPart.jsp").forward(request, response);
        }
    }
}
    boolean isEmpty(String str){
        return str.trim().isEmpty() || str==null;
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
