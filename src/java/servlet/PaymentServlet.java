/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bisiness.Ledger;
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
@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {

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
            
            
            String type = request.getParameter("type");
            
            if(type.equalsIgnoreCase("payment"))
            {
                String debitAccount = request.getParameter("debitAccount");
                String particular = request.getParameter("particular");
                double debitAmount = Double.parseDouble(request.getParameter("amount"));
                String cashOrCheque = request.getParameter("cashOrCheque");



                String  creditAccount="";
                double creditAmount = debitAmount;

                if(cashOrCheque.equalsIgnoreCase("cash"))
                {
                    int id = 0;
                    Date date = null;

                    creditAccount = "Cash A/C";

                    double debitBalance = debitAmount - 0.00;
                    double creditBalance = 0.00-creditAmount;

                    Ledger debitLeger = new Ledger(id, date, debitAccount, creditAccount, debitAmount, 0.00, debitBalance, particular);
                    Ledger creditLeger = new Ledger(id, date, creditAccount, debitAccount, 0.00, creditAmount, creditBalance, particular);

                    Database.insertIntoLedger(debitLeger);
                    Database.insertIntoLedger(creditLeger);
                }
                if(cashOrCheque.equalsIgnoreCase("Bank"))
                {
                    int id = 0;
                    Date date = null;

                    creditAccount = "Bank A/C";

                    double debitBalance = debitAmount - 0.00;
                    double creditBalance = 0.00-creditAmount;

                    Ledger debitLeger = new Ledger(id, date, debitAccount, creditAccount, debitAmount, 0.00, debitBalance, particular);
                    Ledger creditLeger = new Ledger(id, date, creditAccount, debitAccount, 0.00, creditAmount, creditBalance, particular);

                    Database.insertIntoLedger(debitLeger);
                    Database.insertIntoLedger(creditLeger);
                }
                String url="/payment.jsp";
                String successMsg = "Saved Successfully!";

                HttpSession session = request.getSession();
                session.setAttribute("successMsg", successMsg);

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
            if(type.equalsIgnoreCase("receipt"))
            {
                String creditAccount = request.getParameter("debitAccount");
                String particular = request.getParameter("particular");
                double debitAmount = Double.parseDouble(request.getParameter("amount"));
                String cashOrCheque = request.getParameter("cashOrCheque");



                String  debitAccount="";
                double creditAmount = debitAmount;

                if(cashOrCheque.equalsIgnoreCase("cash"))
                {
                    int id = 0;
                    Date date = null;

                    debitAccount = "Cash A/C";

                    double debitBalance = debitAmount - 0.00;
                    double creditBalance = 0.00-creditAmount;

                    Ledger debitLeger = new Ledger(id, date, debitAccount, creditAccount, debitAmount, 0.00, debitBalance, particular);
                    Ledger creditLeger = new Ledger(id, date, creditAccount, debitAccount, 0.00, creditAmount, creditBalance, particular);

                    Database.insertIntoLedger(debitLeger);
                    Database.insertIntoLedger(creditLeger);
                }
                if(cashOrCheque.equalsIgnoreCase("Bank"))
                {
                    int id = 0;
                    Date date = null;

                    debitAccount = "Bank A/C";

                    double debitBalance = debitAmount - 0.00;
                    double creditBalance = 0.00-creditAmount;

                    Ledger debitLeger = new Ledger(id, date, debitAccount, creditAccount, debitAmount, 0.00, debitBalance, particular);
                    Ledger creditLeger = new Ledger(id, date, creditAccount, debitAccount, 0.00, creditAmount, creditBalance, particular);

                    Database.insertIntoLedger(debitLeger);
                    Database.insertIntoLedger(creditLeger);
                }
                String url="/receipt.jsp";
                String successMsg = "Saved Successfully!";

                HttpSession session = request.getSession();
                session.setAttribute("successMsg", successMsg);

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
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
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
