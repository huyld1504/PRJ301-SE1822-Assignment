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
            /* TODO output your page here. You may use following sample code. */
            response.setCharacterEncoding("UTF-8");
            String partID = request.getParameter("part_id");
            String partName = request.getParameter("part_name");
            String purchasePriceStr= request.getParameter("purchase_price");
            String retailPriceStr = request.getParameter("retail_price");
            
            if(checkEmtyStr(partID)||checkEmtyStr(partName) ||checkEmtyStr(purchasePriceStr)||checkEmtyStr(retailPriceStr)){
                request.setAttribute("ERROR", "All fields are not be empty");
                request.getRequestDispatcher("AddPart.jsp");
            }
            double purchasePrice = Double.parseDouble(purchasePriceStr);
            double retailPrice = Double.parseDouble(retailPriceStr);
            PartDAO pd = new PartDAO();
            
            boolean isAdded = pd.creatPart(new Part(partID, partName, purchasePrice, retailPrice));
            if(isAdded){
                request.setAttribute("SUCCESS", "Part add successfully!!");
                request.getRequestDispatcher("PartList.jsp").forward(request, response);
                
            }else{
                request.setAttribute("ERROR", "Fail to add part");
            }
            request.getRequestDispatcher("AddPart.jsp").forward(request, response);
        }
    }
    public boolean checkEmtyStr(String str){
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
