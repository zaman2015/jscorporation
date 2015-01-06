/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bisiness.Inventory;
import data.InventoryData;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ariba
 */
@WebServlet(name = "OpeningInvSetServlet", urlPatterns = {"/OpeningInvSetServlet"})
public class OpeningInvSetServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            
            ArrayList<Inventory> inventoryList= new ArrayList();
            
            String customerName= request.getParameter("customerName");
            String productName= request.getParameter("product");
            double unit= Double.parseDouble(request.getParameter("unit"));
            String measurementUnit= request.getParameter("measurementUnit");
            double rate= Double.parseDouble(request.getParameter("rate"));
            String particularOpen= request.getParameter("particularOpen");
            double total= unit* rate;
            
            Inventory inventory = new Inventory();
            
            inventory.setCustomerOrSuppler(customerName);
            inventory.setProductName(productName);
            inventory.setParticular(particularOpen);
            
            inventory.setPurchaseQuantity(0);
            inventory.setPurchaseMeasurement("");
            inventory.setPurchaseRate(0);
            inventory.setPurchaseTotal(0);
            
            inventory.setBalanceQuantity(unit);
            inventory.setBalanceMeasurement(measurementUnit);
            inventory.setBalanceRate(rate);
            inventory.setBalanceTotal(total);
            
            inventoryList.add(inventory);
            
            InventoryData.insertIntoInventory(inventoryList);
            
            
           String url = "/openingBalance.jsp";
        
      
            
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
                    
            
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(OpeningInvSetServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(OpeningInvSetServlet.class.getName()).log(Level.SEVERE, null, ex);
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
