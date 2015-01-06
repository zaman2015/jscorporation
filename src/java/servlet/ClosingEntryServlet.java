/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bisiness.Account;
import bisiness.Ledger;
import data.Database;
import data.InventoryData;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
@WebServlet(name = "ClosingEntryServlet", urlPatterns = {"/ClosingEntryServlet"})
public class ClosingEntryServlet extends HttpServlet {

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
            
            double profitAndLoss=0.00;
            double closingInventory = InventoryData.getClosignTotalInventory();
            ArrayList<Ledger> profitAndLossLedger= new ArrayList();

            double openingBalanceOfInventory = Database.getBalance("Inventory A/C");
            Ledger openingInventoryLedgerDR= new Ledger(0, null, "Profit and Loss A/C", "Inventory A/C",openingBalanceOfInventory,00,openingBalanceOfInventory, "Closing Entry" );
            profitAndLossLedger.add(openingInventoryLedgerDR);
            
            Ledger openingInventoryLedgerCR= new Ledger(0, null, "Inventory A/C", "Profit and Loss A/C",00,openingBalanceOfInventory,openingBalanceOfInventory*-1, "Closing Entry" );
            profitAndLossLedger.add(openingInventoryLedgerCR);

            profitAndLoss=profitAndLoss+openingBalanceOfInventory;

            ArrayList<Account> accountList=Database.selectAllAccountName();
            ArrayList<Account> revenueAccoutList=new ArrayList();
            Iterator iterator = accountList.listIterator();
            while(iterator.hasNext())
            {
                Account revenueAccount = (Account) iterator.next();
                if(revenueAccount.getAccountType().equalsIgnoreCase("Revenue"))
                {
                    revenueAccoutList.add(revenueAccount);
                }
            }

            Iterator revenueListIterator = revenueAccoutList.listIterator();
            
            while(revenueListIterator.hasNext())
            {
                Account account = (Account) revenueListIterator.next();
                String accountName = account.getAccountName();
                double accountBalance = Database.getBalance(accountName);
                
                if(accountBalance > 0 && !accountName.equalsIgnoreCase("Profit and Loss A/C"))
                {
                    profitAndLossLedger.add(new Ledger(0, null, "Profit and Loss A/C", accountName, accountBalance, 0.00, accountBalance-0.00, "Closing Entry"));
                    profitAndLossLedger.add(new Ledger(0, null,accountName, "Profit and Loss A/C", 0.00, accountBalance, 0.00-accountBalance, "Closing Entry"));
                    profitAndLoss = profitAndLoss+accountBalance; 
                }
                if(accountBalance<0 && !accountName.equalsIgnoreCase("Profit and Loss A/C"))
                {
                    profitAndLossLedger.add(new Ledger(0, null,accountName, "Profit and Loss A/C", accountBalance*-1, 00.00, (accountBalance*-1)-0.00, "Closing Entry"));
                    profitAndLossLedger.add(new Ledger(0, null, "Profit and Loss A/C", accountName,  0.00, accountBalance*-1, 0.00- accountBalance*-1, "Closing Entry"));
                    profitAndLoss = profitAndLoss+accountBalance;
                }
            }

            Ledger closingInventoryLedgerCR= new Ledger(0, null, "Profit and Loss A/C", "Inventory A/C",0.00, closingInventory,closingInventory*-1, "Closing Entry" );
            profitAndLossLedger.add(closingInventoryLedgerCR);
            Ledger closingInventoryLedgerDR= new Ledger(0, null, "Inventory A/C", "Profit and Loss A/C", closingInventory, 00, closingInventory, "Closing Entry" );
            profitAndLossLedger.add(closingInventoryLedgerDR);
            profitAndLoss = profitAndLoss+closingInventory*-1;

            
            if(profitAndLoss<0)
            {
                Ledger profitandLossCapital1 = new Ledger(0,null, "Profit and Loss A/C", "Capital A/C", profitAndLoss*-1, 0.00, profitAndLoss*-1, "Net Profit transferred to Capital A/C" );
                profitAndLossLedger.add(profitandLossCapital1);
                Ledger profitandLossCapital2 = new Ledger(0,null, "Capital A/C", "Profit and Loss A/C", 0.00, profitAndLoss*-1, 00.00-profitAndLoss*-1, "Net Profit transferred to Capital A/C" );
                profitAndLossLedger.add(profitandLossCapital2);
            }
            if(profitAndLoss>0)
            {
                Ledger profitandLossCapital1 = new Ledger(0,null, "Profit and Loss A/C", "Capital A/C",0.00, profitAndLoss, 0.00-profitAndLoss, "Net Loss transferred to Capital A/C" );
                profitAndLossLedger.add(profitandLossCapital1);
                Ledger profitandLossCapital2 = new Ledger(0,null, "Capital A/C", "Profit and Loss A/C",profitAndLoss, 0.00, profitAndLoss - 0.00, "Net Loss transferred to Capital A/C" );
                profitAndLossLedger.add(profitandLossCapital2);
            }
            
            
            String closingJournals=SQLHtml.getClosinJournalString(profitAndLossLedger); 

            String url = "/closingEntry.jsp";
            HttpSession session = request.getSession();
            session.setAttribute("closingJournals", closingJournals);
            session.setAttribute("profitAndLossLedger", profitAndLossLedger);
           
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

        } finally {
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
            Logger.getLogger(ProfitAndLossServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ProfitAndLossServlet.class.getName()).log(Level.SEVERE, null, ex);
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
