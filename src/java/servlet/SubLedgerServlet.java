/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bisiness.SubLedger;
import data.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ariba
 */
@WebServlet(name = "SubLedgerServlet", urlPatterns = {"/SubLedgerServlet"})
public class SubLedgerServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            int id = 0;
            Date date = null;
            String mainAccountName="";
            String baseAccountName="";
            String accountName="";
            String particular="";

            double drUnit=0.00;
            String drMeasurement="";
            double drRate =0.00;
            double drTotal = drUnit*drRate;

            double crUnit=0.00;
            String crMeasurement="";
            double crRate=0.00;
            double crTotal=crUnit*crRate;

            double blUnit=0.00;
            String blMeasurement="";
            double blRate=0.00;
            double blTotal=blUnit*blRate;
            
            int debitId = Database.numberOfRows()-1;
            int creditId= Database.numberOfRows();
            
            double subLedgerBalance=0.00;

            HttpSession session = request.getSession();
            String subAccountType= session.getAttribute("subAccountType").toString();
            if(subAccountType.equalsIgnoreCase("debit"))
            {
                id = 0;
                
                date = null;
                mainAccountName=(String) session.getAttribute("debitAccount1");
                baseAccountName=request.getParameter("ledgerName");
                accountName=(String) session.getAttribute("creditAccount1");
                particular=request.getParameter("subLedgerParticular");

                drUnit=Double.parseDouble(request.getParameter("quantity"));
                drMeasurement=request.getParameter("measurement");
                drRate =Double.parseDouble(request.getParameter("rate"));
                drTotal = drUnit*drRate;

                crUnit=0.00;
                crMeasurement=request.getParameter("measurement");
                crRate=0.00;
                crTotal=crUnit*crRate;

                blUnit=drUnit-crUnit;
                blMeasurement=crMeasurement;
                blRate=drRate;
                blTotal=blUnit*blRate;

                SubLedger subLedger1= new SubLedger(id, debitId, creditId, date,mainAccountName, baseAccountName, accountName, particular, drUnit, drMeasurement, drRate, drTotal, crUnit, crMeasurement, crRate, crTotal, blUnit, blMeasurement, blRate, blTotal);
                Database.insertIntoSubLedger(subLedger1);
                subLedgerBalance = Database.getSubLedgerBalance(mainAccountName, debitId, creditId);
            }

            if(subAccountType.equalsIgnoreCase("credit"))
            {
                id = 0;
                date = null;
                mainAccountName=(String) session.getAttribute("creditAccount1");
                baseAccountName=request.getParameter("ledgerName");
                accountName=(String) session.getAttribute("debitAccount1");
                particular=request.getParameter("subLedgerParticular");

                drUnit=0.00;
                drMeasurement=request.getParameter("measurement");
                drRate =0.00;
                drTotal = drUnit*drRate;

                crUnit=Double.parseDouble(request.getParameter("quantity"));
                crMeasurement=request.getParameter("measurement");
                crRate=Double.parseDouble(request.getParameter("rate"));
                crTotal=crUnit*crRate;

                blUnit=drUnit-crUnit;
                blMeasurement=crMeasurement;
                blRate=crRate;
                blTotal=blUnit*blRate;

                SubLedger subLedger1= new SubLedger(id, debitId, creditId, date,mainAccountName, baseAccountName, accountName, particular, drUnit, drMeasurement, drRate, drTotal, crUnit, crMeasurement, crRate, crTotal, blUnit, blMeasurement, blRate, blTotal);
                Database.insertIntoSubLedger(subLedger1);
                subLedgerBalance = Database.getSubLedgerBalance(mainAccountName, debitId, creditId)*-1;
            }
            
            Database.updateLedgerDebit(debitId, subLedgerBalance);
            Database.updateLedgerCredit(creditId, subLedgerBalance);

            String url= "/subVoucher.jsp";


            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);


        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
            Logger.getLogger(SubLedgerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
            Logger.getLogger(SubLedgerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
