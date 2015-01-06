/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bisiness.Ledger;
import bisiness.SubAccount;
import bisiness.SubLedger;
import data.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
import sql.SQLHtml;

/**
 *
 * @author Ariba
 */
@WebServlet(name = "journalServlet", urlPatterns = {"/journalServlet"})
public class journalServlet extends HttpServlet {

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
            String  url = request.getParameter("url");
            
            int id = 0;
            Date date = null;

            String debitAccount = request.getParameter("debitAccount");
            String creditAccount = request.getParameter("creditAccount");
            double debitAmount = Double.parseDouble(request.getParameter("debitAmount"));
            double creditAmount = debitAmount;

            double debitBalance = debitAmount - 0.00;
            double creditBalance = 0.00-creditAmount;
            
            String subAccountDebit = request.getParameter("subAccountDebit");
            
            
            String subAccountCredit = request.getParameter("subAccountCredit");
            
            String particular = request.getParameter("particular");
            
            

            Ledger debitLeger = new Ledger(id, date, debitAccount, creditAccount, debitAmount, 0.00, debitBalance, particular);
            Ledger creditLeger = new Ledger(id, date, creditAccount, debitAccount, 0.00, creditAmount, creditBalance, particular);


            Database.insertIntoLedger(debitLeger);
            Database.insertIntoLedger(creditLeger);

            if(url.equalsIgnoreCase("debit"))
            url="/printDebitVoucher.jsp?creditAccount="+creditAccount+"&subAccountDebit="+subAccountDebit+"&particular="+particular+"&debitAmount="+debitAmount;
            
            if(url.equalsIgnoreCase("credit"))
            url="/printCreditVoucher.jsp?debitAccount="+debitAccount+"&subAccountCredit="+subAccountCredit+"&particular="+particular+"&creditAmount="+creditAmount;;
            
            if(url.equalsIgnoreCase("journal"))
            url="/printJournalVoucher.jsp?creditAccount="+creditAccount+"&subAccountDebit="+subAccountDebit+"&particular="+particular+"&debitAmount="+debitAmount+"&debitAccount="+debitAccount+"&subAccountCredit="+subAccountCredit+"&particular="+particular+"&creditAmount="+creditAmount;

            

            HttpSession session = request.getSession();


            
            
            int debitId = Database.numberOfRows()-1;
            int creditId= Database.numberOfRows();
            
            if(!(subAccountDebit==null||subAccountDebit==""))
            {
            SubLedger subLedgerDebit= new SubLedger(id, debitId, creditId, date, debitAccount, subAccountDebit, creditAccount, particular, 0, "", 0, debitAmount, 0, "", 0, 0.00, 0, "", 0, debitBalance);
            Database.insertIntoSubLedger(subLedgerDebit);
            }
            
            if(!(subAccountCredit==null||subAccountCredit==""))
            {
            SubLedger subLedgerCredit= new SubLedger(id, debitId, creditId, date,creditAccount, subAccountCredit, debitAccount, particular, 0, "", 0, 0, 0, "", 0, creditAmount, 0, "", 0, creditBalance);
                Database.insertIntoSubLedger(subLedgerCredit);
            }
                
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
            Logger.getLogger(journalServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(journalServlet.class.getName()).log(Level.SEVERE, null, ex);
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
