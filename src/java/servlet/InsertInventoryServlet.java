/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bisiness.DiscountVat;
import bisiness.Ledger;
import bisiness.SubLedger;
import data.Database;
import data.InventoryData;
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

/**
 *
 * @author Ariba
 */
@WebServlet(name = "InsertInventoryServlet", urlPatterns = {"/InsertInventoryServlet"})
public class InsertInventoryServlet extends HttpServlet {

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
            HttpSession session = request.getSession();

            ArrayList inventoryList = (ArrayList) session.getAttribute("inventoryList");
        
        
            String salesOrPurchase = request.getParameter("salesOrPurchae");
            String url = "";   
        
        
        
        if(salesOrPurchase.equalsIgnoreCase("sales"))
        {
            String customerName = request.getParameter("customerName");
            String address = request.getParameter("customerAddress");
            String phoneNo = request.getParameter("phone");
            
            double totalSalesPrice = Double.parseDouble(request.getParameter("salesTotal"));
            double discount = Double.parseDouble(request.getParameter("discountSales"));
            double grandTotalAfterDisc = Double.parseDouble(request.getParameter("grandTotal"));
            double vat = Double.parseDouble(request.getParameter("vatAmount"));
            double totalAfterVat = Double.parseDouble(request.getParameter("totalAfterDisnt"));
            double cashPaid = Double.parseDouble(request.getParameter("paidTotal"));
            double due = Double.parseDouble(request.getParameter("dueTotal"));
            String supplierName = request.getParameter("customerName");
            double grandTotal = Double.parseDouble(request.getParameter("grandTotal"));
            
            double vatRat = Double.parseDouble(request.getParameter("vatRat"));
            
            InventoryData.insertIntoInventory(inventoryList);
            
            int salesInvoice = InventoryData.getLastSalesInvoiceNo();
            int purchaseInvoice = InventoryData.getLastPurchaseInvoiceNo();
            DiscountVat discountVat = new DiscountVat(0, salesInvoice, purchaseInvoice, totalSalesPrice, discount, vat, cashPaid, vatRat );
            InventoryData.insertIntoDiscountVat(discountVat);
           
            
            if(due<=0)
            {
                if(vat==0)
                {
                    int id = 0;
                    Date date = null;

                    String debitAccount = "Cash A/C";
                    String creditAccount = "Sales A/C";
                    double debitAmount = cashPaid;
                    double creditAmount = cashPaid;

                    double debitBalance = debitAmount - 0.00;
                    double creditBalance = 0.00-creditAmount;
                    String particular = "Cash Sales.";

                    Ledger debitLeger = new Ledger(id, date, debitAccount, creditAccount, debitAmount, 0.00, debitBalance, particular);
                    Ledger creditLeger = new Ledger(id, date, creditAccount, debitAccount, 0.00, creditAmount, creditBalance, particular);


                    Database.insertIntoLedger(debitLeger);
                    Database.insertIntoLedger(creditLeger);
                }
                else
                {
                    int idVat = 0;
                    Date dateVat = null;
                    String debitAccountVat = "Cash A/C";
                    String creditAccountVat = "VAT Payable A/C";
                    double debitAmountVat = vat;
                    double creditAmountVat = vat;
                    double debitBalanceVat = debitAmountVat - 0.00;
                    double creditBalanceVat = 0.00-creditAmountVat;
                    String particularVat = "VAT received from customer.";

                    Ledger debitLegerVat = new Ledger(idVat, dateVat, debitAccountVat, creditAccountVat, debitAmountVat, 0.00, debitBalanceVat, particularVat);
                    Ledger creditLegerVat = new Ledger(idVat, dateVat, creditAccountVat, debitAccountVat, 0.00, creditAmountVat, creditBalanceVat, particularVat);


                    Database.insertIntoLedger(debitLegerVat);
                    Database.insertIntoLedger(creditLegerVat);


                    int id = 0;
                    Date date = null;

                    String debitAccount = "Cash A/C";
                    String creditAccount = "Sales A/C";
                    double debitAmount = grandTotalAfterDisc;
                    double creditAmount = grandTotalAfterDisc;

                    double debitBalance = debitAmount - 0.00;
                    double creditBalance = 0.00-creditAmount;
                    String particular = "Cash Sales.";

                    Ledger debitLeger = new Ledger(id, date, debitAccount, creditAccount, debitAmount, 0.00, debitBalance, particular);
                    Ledger creditLeger = new Ledger(id, date, creditAccount, debitAccount, 0.00, creditAmount, creditBalance, particular);


                    Database.insertIntoLedger(debitLeger);
                    Database.insertIntoLedger(creditLeger);
                }
            }

            
            if(due>0)
            {
                if(vat==0)
                {
                    int id5 = 0;
                    Date date5 = null;

                    String debitAccount5 = "Cash A/C";
                    String creditAccount5 = "Sales A/C";
                    double debitAmount5 = cashPaid;
                    double creditAmount5 = cashPaid;

                    double debitBalance5 = debitAmount5 - 0.00;
                    double creditBalance5 = 0.00-creditAmount5;
                    String particular5 = "Partial cash received.";

                    Ledger debitLeger5 = new Ledger(id5, date5, debitAccount5, creditAccount5, debitAmount5, 0.00, debitBalance5, particular5);
                    Ledger creditLeger5 = new Ledger(id5, date5, creditAccount5, debitAccount5, 0.00, creditAmount5, creditBalance5, particular5);


                    Database.insertIntoLedger(debitLeger5);
                    Database.insertIntoLedger(creditLeger5);


                    //for due sales portion.

                    int id3 = 0;
                    Date date3 = null;
                    String debitAccount3 = "Accounts Receivable A/C";
                    String creditAccount3 = "Sales A/C";
                    double debitAmount3 = due;
                    double creditAmount3 = due;
                    double debitBalance3 = debitAmount3 - 0.00;
                    double creditBalance3 = 0.00-creditAmount3;
                    String particular3 = "Credit sales.";

                    Ledger debitLeger3 = new Ledger(id3, date3, debitAccount3, creditAccount3, debitAmount3, 0.00, debitBalance3, particular3);
                    Ledger creditLeger3 = new Ledger(id3, date3, creditAccount3, debitAccount3, 0.00, creditAmount3, creditBalance3, particular3);


                    Database.insertIntoLedger(debitLeger3);
                    Database.insertIntoLedger(creditLeger3);


                    int debitId1 = Database.numberOfRows()-1;
                    int creditId1= Database.numberOfRows();

                    int idSub1 = 0;
                    Date dateSub1 = null;
                    String mainAccountName1= "Accounts Receivable A/C";
                    String baseAccountName1=customerName;
                    String accountName1="Sales A/C";
                    String particularSub1="Credit sales.";

                    double drUnit1=1.00;
                    String drMeasurement1="Unit";
                    double drRate1 =due;
                    double drTotal1 = drUnit1*drRate1;

                    double crUnit1=0.00;
                    String crMeasurement1="Unit";
                    double crRate1=0.00;
                    double crTotal1=crUnit1*crRate1;

                    double blUnit1=drUnit1-crUnit1;
                    String blMeasurement1="Unit";
                    double blRate1=drRate1;
                    double blTotal1=blUnit1*blRate1;

                    SubLedger subLedger1= new SubLedger(idSub1, debitId1, creditId1, dateSub1, mainAccountName1, baseAccountName1, accountName1, particularSub1, drUnit1, drMeasurement1, drRate1, drTotal1, crUnit1, crMeasurement1, crRate1, crTotal1, blUnit1, blMeasurement1, blRate1, blTotal1);
                    Database.insertIntoSubLedger(subLedger1);
                }
                else
                {
                    if(vat<cashPaid)
                    {      
                        int id1 = 0;
                        Date date1 = null;
                        String debitAccount1 = "Cash A/C";
                        String creditAccount1 = "VAT Payable A/C";
                        double debitAmount1 = vat;
                        double creditAmount1 = vat;
                        double debitBalance1 = debitAmount1 - 0.00;
                        double creditBalance1 = 0.00-creditAmount1;
                        String particular1 = "VAT received from customer.";

                        Ledger debitLeger1 = new Ledger(id1, date1, debitAccount1, creditAccount1, debitAmount1, 0.00, debitBalance1, particular1);
                        Ledger creditLeger1 = new Ledger(id1, date1, creditAccount1, debitAccount1, 0.00, creditAmount1, creditBalance1, particular1);

                        Database.insertIntoLedger(debitLeger1);
                        Database.insertIntoLedger(creditLeger1);
                        
                        //for cash portion
                        int id5 = 0;
                        Date date5 = null;

                        String debitAccount5 = "Cash A/C";
                        String creditAccount5 = "Sales A/C";
                        double debitAmount5 = cashPaid-vat;
                        double creditAmount5 = cashPaid-vat;

                        double debitBalance5 = debitAmount5 - 0.00;
                        double creditBalance5 = 0.00-creditAmount5;
                        String particular5 = "Cash received partially.";

                        Ledger debitLeger5 = new Ledger(id5, date5, debitAccount5, creditAccount5, debitAmount5, 0.00, debitBalance5, particular5);
                        Ledger creditLeger5 = new Ledger(id5, date5, creditAccount5, debitAccount5, 0.00, creditAmount5, creditBalance5, particular5);


                        Database.insertIntoLedger(debitLeger5);
                        Database.insertIntoLedger(creditLeger5);


                        //for due sales portion.

                        int id3 = 0;
                        Date date3 = null;
                        String debitAccount3 = "Accounts Receivable A/C";
                        String creditAccount3 = "Sales A/C";
                        double debitAmount3 = due;
                        double creditAmount3 = due;
                        double debitBalance3 = debitAmount3 - 0.00;
                        double creditBalance3 = 0.00-creditAmount3;
                        String particular3 = "Credit sales.";

                        Ledger debitLeger3 = new Ledger(id3, date3, debitAccount3, creditAccount3, debitAmount3, 0.00, debitBalance3, particular3);
                        Ledger creditLeger3 = new Ledger(id3, date3, creditAccount3, debitAccount3, 0.00, creditAmount3, creditBalance3, particular3);


                        Database.insertIntoLedger(debitLeger3);
                        Database.insertIntoLedger(creditLeger3);


                        int debitId1 = Database.numberOfRows()-1;
                        int creditId1= Database.numberOfRows();

                        int idSub1 = 0;
                        Date dateSub1 = null;
                        String mainAccountName1= "Accounts Receivable A/C";
                        String baseAccountName1=customerName;
                        String accountName1="Sales A/C";
                        String particularSub1="Credit sales.";

                        double drUnit1=1.00;
                        String drMeasurement1="Unit";
                        double drRate1 =due;
                        double drTotal1 = drUnit1*drRate1;

                        double crUnit1=0.00;
                        String crMeasurement1="Unit";
                        double crRate1=0.00;
                        double crTotal1=crUnit1*crRate1;

                        double blUnit1=drUnit1-crUnit1;
                        String blMeasurement1="Unit";
                        double blRate1=due;
                        double blTotal1=blUnit1*blRate1;

                        SubLedger subLedger1= new SubLedger(idSub1, debitId1, creditId1, dateSub1, mainAccountName1, baseAccountName1, accountName1, particularSub1, drUnit1, drMeasurement1, drRate1, drTotal1, crUnit1, crMeasurement1, crRate1, crTotal1, blUnit1, blMeasurement1, blRate1, blTotal1);
                        Database.insertIntoSubLedger(subLedger1);

                    }
                    
                    if(vat==cashPaid)
                    {
                        int id1 = 0;
                        Date date1 = null;
                        String debitAccount1 = "Cash A/C";
                        String creditAccount1 = "VAT Payable A/C";
                        double debitAmount1 = cashPaid;
                        double creditAmount1 = cashPaid;
                        double debitBalance1 = debitAmount1 - 0.00;
                        double creditBalance1 = 0.00-creditAmount1;
                        String particular1 = "VAT received from customer.";

                        Ledger debitLeger1 = new Ledger(id1, date1, debitAccount1, creditAccount1, debitAmount1, 0.00, debitBalance1, particular1);
                        Ledger creditLeger1 = new Ledger(id1, date1, creditAccount1, debitAccount1, 0.00, creditAmount1, creditBalance1, particular1);

                        Database.insertIntoLedger(debitLeger1);
                        Database.insertIntoLedger(creditLeger1);
                    
                    //for partial cash receive
                    
                        int id4 = 0;
                        Date date4 = null;

                        String debitAccount4 = "Accounts Receivable A/C";
                        String creditAccount4 = "Sales A/C";
                        double debitAmount4 = due;
                        double creditAmount4 = due;

                        double debitBalance4 = debitAmount4 - 0.00;
                        double creditBalance4 = 0.00-creditAmount4;
                        String particular4 = "Cash received partially.";

                        Ledger debitLeger4 = new Ledger(id4, date4, debitAccount4, creditAccount4, debitAmount4, 0.00, debitBalance4, particular4);
                        Ledger creditLeger4 = new Ledger(id4, date4, creditAccount4, debitAccount4, 0.00, creditAmount4, creditBalance4, particular4);

                        Database.insertIntoLedger(debitLeger4);
                        Database.insertIntoLedger(creditLeger4); 
                     
                        
                        int debitId1 = Database.numberOfRows()-1;
                        int creditId1= Database.numberOfRows();

                        int idSub1 = 0;
                        Date dateSub1 = null;
                        String mainAccountName1= "Accounts Receivable A/C";
                        String baseAccountName1=customerName;
                        String accountName1="Sales A/C";
                        String particularSub1="Credit sales.";

                        double drUnit1=1;
                        String drMeasurement1="Unit";
                        double drRate1 =due;
                        double drTotal1 = drUnit1*drRate1;

                        double crUnit1=0;
                        String crMeasurement1="Unit";
                        double crRate1=0.00;
                        double crTotal1=crUnit1*crRate1;

                        double blUnit1=drUnit1-crUnit1;
                        String blMeasurement1="Unit";
                        double blRate1=due;
                        double blTotal1=blUnit1*blRate1;

                        SubLedger subLedger1= new SubLedger(idSub1, debitId1, creditId1, dateSub1, mainAccountName1, baseAccountName1, accountName1, particularSub1, drUnit1, drMeasurement1, drRate1, drTotal1, crUnit1, crMeasurement1, crRate1, crTotal1, blUnit1, blMeasurement1, blRate1, blTotal1);
                        Database.insertIntoSubLedger(subLedger1);
                
                    }
                
                if(vat>cashPaid)
                {                  
                    int id1 = 0;
                    Date date1 = null;
                    String debitAccount1 = "Cash A/C";
                    String creditAccount1 = "VAT Payable A/C";
                    double debitAmount1 = cashPaid;
                    double creditAmount1 = cashPaid;
                    double debitBalance1 = debitAmount1 - 0.00;
                    double creditBalance1 = 0.00-creditAmount1;
                    String particular1 = "VAT received from customer.";

                    Ledger debitLeger1 = new Ledger(id1, date1, debitAccount1, creditAccount1, debitAmount1, 0.00, debitBalance1, particular1);
                    Ledger creditLeger1 = new Ledger(id1, date1, creditAccount1, debitAccount1, 0.00, creditAmount1, creditBalance1, particular1);


                    Database.insertIntoLedger(debitLeger1);
                    Database.insertIntoLedger(creditLeger1);


                    //for vat due.
                    int id2 = 0;
                    Date date2 = null;
                    String debitAccount2 = "Accounts Receivable A/C";
                    String creditAccount2 = "VAT Payable A/C";
                    double debitAmount2 = vat- cashPaid;
                    double creditAmount2 = vat- cashPaid;
                    double debitBalance2 = debitAmount2 - 0.00;
                    double creditBalance2 = 0.00-creditAmount2;
                    String particular2 = "VAT receivable from customer.";

                    Ledger debitLeger2 = new Ledger(id2, date2, debitAccount2, creditAccount2, debitAmount2, 0.00, debitBalance2, particular2);
                    Ledger creditLeger2 = new Ledger(id2, date2, creditAccount2, debitAccount2, 0.00, creditAmount2, creditBalance2, particular2);

                    Database.insertIntoLedger(debitLeger2);
                    Database.insertIntoLedger(creditLeger2);
                    
                    int debitId = Database.numberOfRows()-1;
                    int creditId= Database.numberOfRows();
                    
                    int idSub = 0;
                    Date dateSub = null;
                    String mainAccountName= "Accounts Receivable A/C";
                    String baseAccountName=customerName;
                    String accountName="VAT Payable A/C";
                    String particularSub="VAT to be collected from customer.";
                    
                    double drUnit=1.00;
                    String drMeasurement="Unit";
                    double drRate =debitAmount2;
                    double drTotal = drUnit*drRate;
                    
                    double crUnit=0.00;
                    String crMeasurement="Unit";
                    double crRate=0.00;
                    double crTotal=crUnit*crRate;
                    
                    double blUnit=drUnit-crUnit;
                    String blMeasurement="Unit";
                    double blRate=debitAmount2;
                    double blTotal=blUnit*blRate;

                    SubLedger subLedger= new SubLedger(idSub, debitId, creditId, dateSub,mainAccountName, baseAccountName, accountName, particularSub, drUnit, drMeasurement, drRate, drTotal, crUnit, crMeasurement, crRate, crTotal, blUnit, blMeasurement, blRate, blTotal);
                    Database.insertIntoSubLedger(subLedger);
                    

                    int id3 = 0;
                    Date date3 = null;
                    String debitAccount3 = "Accounts Receivable A/C";
                    String creditAccount3 = "Sales A/C";
                    double debitAmount3 = grandTotalAfterDisc;
                    double creditAmount3 = grandTotalAfterDisc;
                    double debitBalance3 = debitAmount3 - 0.00;
                    double creditBalance3 = 0.00-creditAmount3;
                    String particular3 = "Credit sales.";

                    Ledger debitLeger3 = new Ledger(id3, date3, debitAccount3, creditAccount3, debitAmount3, 0.00, debitBalance3, particular3);
                    Ledger creditLeger3 = new Ledger(id3, date3, creditAccount3, debitAccount3, 0.00, creditAmount3, creditBalance3, particular3);


                    Database.insertIntoLedger(debitLeger3);
                    Database.insertIntoLedger(creditLeger3);
                    
                    
                    int debitId1 = Database.numberOfRows()-1;
                    int creditId1= Database.numberOfRows();
                    
                    int idSub1 = 0;
                    Date dateSub1 = null;
                    String mainAccountName1= "Accounts Receivable A/C";
                    String baseAccountName1=customerName;
                    String accountName1="Sales A/C";
                    String particularSub1="Credit sales.";
                    
                    double drUnit1=1.00;
                    String drMeasurement1="Unit";
                    double drRate1 =debitAmount3;
                    double drTotal1 = drUnit1*drRate1;
                    
                    double crUnit1=0.00;
                    String crMeasurement1="Unit";
                    double crRate1=0.00;
                    double crTotal1=crUnit1*crRate1;
                    
                    double blUnit1=drUnit1-crUnit1;
                    String blMeasurement1="Unit";
                    double blRate1=debitAmount3;
                    double blTotal1=blUnit1*blRate1;

                    SubLedger subLedger1= new SubLedger(idSub1, debitId1, creditId1, dateSub1, mainAccountName1, baseAccountName1, accountName1, particularSub1, drUnit1, drMeasurement1, drRate1, drTotal1, crUnit1, crMeasurement1, crRate1, crTotal1, blUnit1, blMeasurement1, blRate1, blTotal1);
                    Database.insertIntoSubLedger(subLedger1);

                }
                }
                
            }
            if(discount>0)
            {
                int id = 0;
                Date date = null;

                String debitAccount = "Discount Paid A/C";
                String creditAccount = "Sales A/C";
                double debitAmount = discount;
                double creditAmount = discount;

                double debitBalance = debitAmount - 0.00;
                double creditBalance = 0.00-creditAmount;
                String particular = "Discount paid.";

                Ledger debitLeger = new Ledger(id, date, debitAccount, creditAccount, debitAmount, 0.00, debitBalance, particular);
                Ledger creditLeger = new Ledger(id, date, creditAccount, debitAccount, 0.00, creditAmount, creditBalance, particular);


                Database.insertIntoLedger(debitLeger);
                Database.insertIntoLedger(creditLeger);
                
            }
                inventoryList.clear();
                double total =0.00;
                double totalPrice=0.00;
                double totalVat =0.00;
                double totalPriceAfterVat =0.00;
                
                session.setAttribute("inventoryList", inventoryList);
                session.setAttribute("total", total);// total product price for sales or purchase for an order of several Items. this value will be 0.00 after use.
                session.setAttribute("totalPrice", totalPrice);//for grand total after discount. this value will be 0.00 after use.
                session.setAttribute("totalVat", totalVat);
                session.setAttribute("totalPriceAfterVat", totalPriceAfterVat); //this value will be 0.00 after use.
            
                url = "/inventorySales.jsp";
               
        }                
        
        
        if(salesOrPurchase.equalsIgnoreCase("purchase"))
        {
            String supplierName = request.getParameter("customerName");
            String address = request.getParameter("customerAddress");
            String phoneNo = request.getParameter("phone");
            String memoNo = request.getParameter("memo");
            
            double totalSalesPrice = Double.parseDouble(request.getParameter("salesTotal"));
            double discount = Double.parseDouble(request.getParameter("discountSales"));
            double grandTotal = Double.parseDouble(request.getParameter("grandTotal"));
                        
            double cashPaid = Double.parseDouble(request.getParameter("paidTotal"));
            double due = Double.parseDouble(request.getParameter("dueTotal"));
            
            
            
            
            InventoryData.insertIntoInventory(inventoryList);
            
            
            if(due<=0)
            {
            int id = 0;
            Date date = null;

            String debitAccount = "Purchase A/C";
            String creditAccount = "Cash A/C";
            double debitAmount = grandTotal;
            double creditAmount = grandTotal;

            double debitBalance = debitAmount - 0.00;
            double creditBalance = 0.00-creditAmount;
            String particular = "Cash Purchasae.";

            Ledger debitLeger = new Ledger(id, date, debitAccount, creditAccount, debitAmount, 0.00, debitBalance, particular);
            Ledger creditLeger = new Ledger(id, date, creditAccount, debitAccount, 0.00, creditAmount, creditBalance, particular);


            Database.insertIntoLedger(debitLeger);
            Database.insertIntoLedger(creditLeger);
            }
            
            
            
            if(due>0)
            {
              //for cash
                
              int id = 0;
            Date date = null;
            //for due portion.
            String debitAccount = "Purchase A/C";
            String creditAccount = "Cash A/C";
            double debitAmount = cashPaid;
            double creditAmount = cashPaid;

            double debitBalance = debitAmount - 0.00;
            double creditBalance = 0.00-creditAmount;
            String particular = "Pirtial Payment of Credit Purchasae.";

            Ledger debitLeger = new Ledger(id, date, debitAccount, creditAccount, debitAmount, 0.00, debitBalance, particular);
            Ledger creditLeger = new Ledger(id, date, creditAccount, debitAccount, 0.00, creditAmount, creditBalance, particular);


            Database.insertIntoLedger(debitLeger);
            Database.insertIntoLedger(creditLeger);  
                
              
                
                    
            int id1 = 0;
            Date date1 = null;
            //for due portion.
            String debitAccount1 = "Purchase A/C";
            String creditAccount1 = "Accounts Payable A/C";
            double debitAmount1 = due;
            double creditAmount1 = due;

            double debitBalance1 = debitAmount1 - 0.00;
            double creditBalance1 = 0.00-creditAmount1;
            String particular1 = "Credit Purchasae.";

            Ledger debitLeger1 = new Ledger(id1, date1, debitAccount1, creditAccount1, debitAmount1, 0.00, debitBalance1, particular1);
            Ledger creditLeger1 = new Ledger(id1, date1, creditAccount1, debitAccount1, 0.00, creditAmount1, creditBalance1, particular1);


            Database.insertIntoLedger(debitLeger1);
            Database.insertIntoLedger(creditLeger1);
            
            
            
                int debitId = Database.numberOfRows()-1;
                int creditId= Database.numberOfRows();
            
                int idSub = 0;
                Date dateSub = null;
                String mainAccountName= "Accounts Payable A/C";
                String baseAccountName=supplierName;
                String accountName="Purchase A/C";
                String particularSub="Credit Purchase.";

                double drUnit=0;
                String drMeasurement="Unit";
                double drRate =0.00;
                double drTotal = drUnit*drRate;

                double crUnit=1;
                String crMeasurement="Unit";
                double crRate=due;
                double crTotal=crUnit*crRate;

                double blUnit=drUnit-crUnit;
                String blMeasurement="Unit";
                double blRate=crRate;
                double blTotal=blUnit*blRate;

                SubLedger subLedger1= new SubLedger(idSub, debitId, creditId, dateSub,mainAccountName, baseAccountName, accountName, particularSub, drUnit, drMeasurement, drRate, drTotal, crUnit, crMeasurement, crRate, crTotal, blUnit, blMeasurement, blRate, blTotal);
                Database.insertIntoSubLedger(subLedger1);
                
            
            
            }
            
            if(discount>0)
            {
            int id = 0;
            Date date = null;

            String debitAccount = "Purchase A/C";
            String creditAccount = "Discount Received A/C";
            double debitAmount = discount;
            double creditAmount = discount;

            double debitBalance = debitAmount - 0.00;
            double creditBalance = 0.00-creditAmount;
            String particular = "Discount received.";

            Ledger debitLeger = new Ledger(id, date, debitAccount, creditAccount, debitAmount, 0.00, debitBalance, particular);
            Ledger creditLeger = new Ledger(id, date, creditAccount, debitAccount, 0.00, creditAmount, creditBalance, particular);


            Database.insertIntoLedger(debitLeger);
            Database.insertIntoLedger(creditLeger);
            }
        
            
                        
            
                inventoryList.clear();
                double total =0.00;
                double totalPrice=0.00;
                double totalVat =0.00;
                double totalPriceAfterVat =0.00;
                
                session.setAttribute("inventoryList", inventoryList);
                session.setAttribute("total", total);// total product price for sales or purchase for an order of several Items. this value will be 0.00 after use.
                session.setAttribute("totalPrice", totalPrice);//for grand total after discount. this value will be 0.00 after use.
                session.setAttribute("totalVat", totalVat);
                session.setAttribute("totalPriceAfterVat", totalPriceAfterVat); //this value will be 0.00 after use.
            
                url = "/inventoryPurchase.jsp";
        
        }//end of purchase
        
        if(salesOrPurchase.equalsIgnoreCase("Sales Return"))
        {
            //double total = Double.parseDouble((String) session.getAttribute("total"));
            InventoryData.insertIntoInventory(inventoryList);
            
            String customerName = request.getParameter("customerName");
            String address = request.getParameter("customerAddress");
            String phoneNo = request.getParameter("phone");
            
            double totalSalesPrice = Double.parseDouble(request.getParameter("salesTotal"));
            double discount = Double.parseDouble(request.getParameter("discountSales"));
            double grandTotalAfterDisc = Double.parseDouble(request.getParameter("grandTotal"));
            double vat = Double.parseDouble(request.getParameter("vatAmount"));
            double totalAfterVat = Double.parseDouble(request.getParameter("totalAfterDisnt"));
            double cashPaid = Double.parseDouble(request.getParameter("paidTotal"));
            double due = Double.parseDouble(request.getParameter("dueTotal"));
            String supplierName = request.getParameter("customerName");
            double grandTotal = Double.parseDouble(request.getParameter("grandTotal"));
            
            double vatRat = Double.parseDouble(request.getParameter("vatRat"));
            
          
            
            
            
            int id = 0;
            Date date = null;

            String debitAccount = "Sales A/C";
            String creditAccount = "Accounts Receivable A/C";
            double debitAmount = totalSalesPrice;
            double creditAmount = totalSalesPrice;

            double debitBalance = debitAmount - 0.00;
            double creditBalance = 0.00-creditAmount;
            String particular = "Sales Return";

            Ledger debitLeger = new Ledger(id, date, debitAccount, creditAccount, debitAmount, 0.00, debitBalance, particular);
            Ledger creditLeger = new Ledger(id, date, creditAccount, debitAccount, 0.00, creditAmount, creditBalance, particular);
            Database.insertIntoLedger(debitLeger);
            Database.insertIntoLedger(creditLeger);
            
            int debitId = Database.numberOfRows()-1;
                int creditId= Database.numberOfRows();
            
                int idSub = 0;
                Date dateSub = null;
                String mainAccountName= "Accounts Receivable A/C";
                String baseAccountName=customerName;
                String accountName="Sales A/C";
                String particularSub="Sales Return.";

                double drUnit=0;
                String drMeasurement="Unit";
                double drRate =0.00;
                double drTotal = drUnit*drRate;

                double crUnit=1;
                String crMeasurement="Unit";
                double crRate=totalSalesPrice;
                double crTotal=crUnit*crRate;

                double blUnit=drUnit-crUnit;
                String blMeasurement="Unit";
                double blRate=crRate;
                double blTotal=blUnit*blRate;

                SubLedger subLedger1= new SubLedger(idSub, debitId, creditId, dateSub,mainAccountName, baseAccountName, accountName, particularSub, drUnit, drMeasurement, drRate, drTotal, crUnit, crMeasurement, crRate, crTotal, blUnit, blMeasurement, blRate, blTotal);
                Database.insertIntoSubLedger(subLedger1);
                
                
                if(vat>0)
            {
            Ledger debitLegerVatDr = new Ledger(id, date, "VAT Payable A/C", "Accounts Receivable A/C", vat, 0.00, (vat-0.00), particular);
            Ledger debitLegerVatCr = new Ledger(id, date, "Accounts Receivable A/C", "VAT Payable A/C", 0.00, vat, (0.00 - vat), particular);
            
            Database.insertIntoLedger(debitLegerVatDr);
            Database.insertIntoLedger(debitLegerVatCr);
            
                int debitId2 = Database.numberOfRows()-1;
                int creditId2= Database.numberOfRows();
            
                int idSub2 = 0;
                Date dateSub2 = null;
                String mainAccountName2= "Accounts Receivable A/C";
                String baseAccountName2=customerName;
                String accountName2=" VAT Payable A/C";
                String particularSub2="Sales Return.";

                double drUnit2=0;
                String drMeasurement2="Unit";
                double drRate2 =0;
                double drTotal2 = drUnit*drRate;

                double crUnit2=1;
                String crMeasurement2="Unit";
                double crRate2=vat;
                double crTotal2=crUnit2*crRate2;

                double blUnit2=drUnit2-crUnit2;
                String blMeasurement2="Unit";
                double blRate2=crRate2;
                double blTotal2=blUnit2*blRate2;

                SubLedger subLedger2= new SubLedger(idSub2, debitId2, creditId2, dateSub2, mainAccountName2, baseAccountName2, accountName2, particularSub2, drUnit2, drMeasurement2, drRate2, drTotal2, crUnit2, crMeasurement2, crRate2, crTotal2, blUnit2, blMeasurement2, blRate2, blTotal2);
                Database.insertIntoSubLedger(subLedger2);
            }
                
               
                
            if(discount>0)
            {
            Ledger debitLegerDisDr = new Ledger(id, date, "Accounts Receivable A/C", "Discount Paid A/C", discount, 0.00, (discount-0.00), particular);
            Ledger debitLegerDisCr = new Ledger(id, date, "Discount Paid A/C", "Accounts Receivable A/C", 0.00, discount, (0.00 - discount), particular);
            
            Database.insertIntoLedger(debitLegerDisDr);
            Database.insertIntoLedger(debitLegerDisCr);
            
                int debitId3 = Database.numberOfRows()-1;
                int creditId3= Database.numberOfRows();
            
                int idSub3 = 0;
                Date dateSub3 = null;
                String mainAccountName3= "Accounts Receivable A/C";
                String baseAccountName3=customerName;
                String accountName3=" Discount Paid A/C";
                String particularSub3="Sales Return.";

                double drUnit3=1;
                String drMeasurement3="Unit";
                double drRate3 =discount;
                double drTotal3 = drUnit3*drRate3;

                double crUnit3=0;
                String crMeasurement3="Unit";
                double crRate3=0;
                double crTotal3=crUnit3*crRate3;

                double blUnit3=drUnit3-crUnit3;
                String blMeasurement3="Unit";
                double blRate3=drRate3;
                double blTotal3=blUnit3*blRate3;

                SubLedger subLedger3= new SubLedger(idSub3, debitId3, creditId3, dateSub3, mainAccountName3, baseAccountName3, accountName3, particularSub3, drUnit3, drMeasurement3, drRate3, drTotal3, crUnit3, crMeasurement3, crRate3, crTotal3, blUnit3, blMeasurement3, blRate3, blTotal3);
                Database.insertIntoSubLedger(subLedger3);
            }
                
                
                if(cashPaid>0)
                {
                    Ledger debitLegerCashPd= new Ledger(id, date, "Accounts Receivable A/C", "Cash A/C", cashPaid, 0.00, (cashPaid-0.00), particular);
                    Ledger creditLegerCashPd  = new Ledger(id, date, "Cash A/C", "Accounts Receivable A/C", 0.00, cashPaid, (0.00-cashPaid), particular);
                    
                    Database.insertIntoLedger(debitLegerCashPd);
                    Database.insertIntoLedger(creditLegerCashPd);
            
                int debitId1 = Database.numberOfRows()-1;
                int creditId1= Database.numberOfRows();
            
                int idSub1 = 0;
                Date dateSub1 = null;
                String mainAccountName1= "Accounts Receivable A/C";
                String baseAccountName1=customerName;
                String accountName1="Cash A/C";
                String particularSub1="Cash paid";

                double drUnit1=1;
                String drMeasurement1="Unit";
                double drRate1 =cashPaid;
                double drTotal1 = drUnit1*drRate1;

                double crUnit1=0;
                String crMeasurement1="Unit";
                double crRate1=0;
                double crTotal1=crUnit1*crRate1;

                double blUnit1=drUnit1-crUnit1;
                String blMeasurement1="Unit";
                double blRate1=drRate1;
                double blTotal1=blUnit1*blRate1;

                SubLedger subLedger= new SubLedger(idSub1, debitId1, creditId1, dateSub1, mainAccountName1, baseAccountName1, accountName1, particularSub1, drUnit1, drMeasurement1, drRate1, drTotal1, crUnit1, crMeasurement1, crRate1, crTotal1, blUnit1, blMeasurement1, blRate1, blTotal1);
                Database.insertIntoSubLedger(subLedger);
                }
            
            
            
            
            
                
                                    
            
            
                inventoryList.clear();
                double total =0.00;
                double totalPrice=0.00;
                double totalVat =0.00;
                double totalPriceAfterVat =0.00;
                
                session.setAttribute("inventoryList", inventoryList);
                session.setAttribute("total", total);// total product price for sales or purchase for an order of several Items. this value will be 0.00 after use.
                session.setAttribute("totalPrice", totalPrice);//for grand total after discount. this value will be 0.00 after use.
                session.setAttribute("totalVat", totalVat);
                session.setAttribute("totalPriceAfterVat", totalPriceAfterVat); //this value will be 0.00 after use.
            
                url = "/return.jsp";
        
        }//end of purchase
        if(salesOrPurchase.equalsIgnoreCase("purchase return"))
        {
            String supplierName = request.getParameter("customerName");
            String address = request.getParameter("customerAddress");
            String phoneNo = request.getParameter("phone");
            String memoNo = request.getParameter("memo");
            
            double totalSalesPrice = Double.parseDouble(request.getParameter("salesTotal"));
            double discount = Double.parseDouble(request.getParameter("discountSales"));
            double grandTotal = Double.parseDouble(request.getParameter("grandTotal"));
                        
            double cashPaid = Double.parseDouble(request.getParameter("paidTotal"));
            double due = Double.parseDouble(request.getParameter("dueTotal"));
            
            
            
            
            InventoryData.insertIntoInventory(inventoryList);
            
            
          
            
            int id = 0;
            Date date = null;

            String debitAccount = "Accounts Payable A/C" ;
            String creditAccount = "Purchase A/C";
            double debitAmount = totalSalesPrice;
            double creditAmount = totalSalesPrice;

            double debitBalance = debitAmount - 0.00;
            double creditBalance = 0.00-creditAmount;
            String particular = "Cash Purchasae.";

            Ledger debitLeger = new Ledger(id, date, debitAccount, creditAccount, debitAmount, 0.00, debitBalance, particular);
            Ledger creditLeger = new Ledger(id, date, creditAccount, debitAccount, 0.00, creditAmount, creditBalance, particular);


            Database.insertIntoLedger(debitLeger);
            Database.insertIntoLedger(creditLeger);
           
            
                int debitId = Database.numberOfRows()-1;
                int creditId= Database.numberOfRows();
            
                int idSub = 0;
                Date dateSub = null;
                String mainAccountName= "Accounts Payable A/C";
                String baseAccountName=supplierName;
                String accountName="Purchase A/C";
                String particularSub="Purchase Return.";

                double drUnit=1;
                String drMeasurement="Unit";
                double drRate =totalSalesPrice;
                double drTotal = drUnit*drRate;

                double crUnit=0;
                String crMeasurement="Unit";
                double crRate=0;
                double crTotal=crUnit*crRate;

                double blUnit=drUnit-crUnit;
                String blMeasurement="Unit";
                double blRate=drRate;
                double blTotal=blUnit*blRate;

                SubLedger subLedger= new SubLedger(idSub, debitId, creditId, dateSub,mainAccountName, baseAccountName, accountName, particularSub, drUnit, drMeasurement, drRate, drTotal, crUnit, crMeasurement, crRate, crTotal, blUnit, blMeasurement, blRate, blTotal);
                Database.insertIntoSubLedger(subLedger);
                
            
            
           
            
            if(discount>0)
            {
            
            Ledger debitLeger1 = new Ledger(id, date, "Discount Received A/C", "Accounts Payable A/C", discount, 0.00, (discount- 0.00), particular);
            Ledger creditLeger1 = new Ledger(id, date, "Accounts Payable A/C", "Discount Received A/C",0.00, discount,(0.00- discount), particular);


            Database.insertIntoLedger(debitLeger1);
            Database.insertIntoLedger(creditLeger1);
      
                int debitId1 = Database.numberOfRows()-1;
                int creditId1= Database.numberOfRows();
            
                int idSub1 = 0;
                Date dateSub1 = null;
                String mainAccountName1= "Accounts Payable A/C";
                String baseAccountName1=supplierName;
                String accountName1="Discount A/C";
                String particularSub1="Discount Return.";

                double drUnit1=0;
                String drMeasurement1="Unit";
                double drRate1 =0;
                double drTotal1 = drUnit1*drRate1;

                double crUnit1=1;
                String crMeasurement1="Unit";
                double crRate1=discount;
                double crTotal1=crUnit1*crRate1;

                double blUnit1=drUnit1-crUnit1;
                String blMeasurement1="Unit";
                double blRate1=crRate1;
                double blTotal1=blUnit1*blRate1;

                SubLedger subLedger1= new SubLedger(idSub1, debitId1, creditId1, dateSub1,mainAccountName1, baseAccountName1, accountName1, particularSub1, drUnit1, drMeasurement1, drRate1, drTotal1, crUnit1, crMeasurement1, crRate1, crTotal1, blUnit1, blMeasurement1, blRate1, blTotal1);
                Database.insertIntoSubLedger(subLedger1);
            }
            
            if(cashPaid>0)
            {
            
            Ledger debitLeger2 = new Ledger(id, date, "Cash A/C", "Accounts Payable A/C", cashPaid, 0.00, (cashPaid- 0.00), particular);
            Ledger creditLeger2 = new Ledger(id, date, "Accounts Payable A/C", "Cash A/C",0.00, cashPaid,(0.00- cashPaid), particular);


            Database.insertIntoLedger(debitLeger2);
            Database.insertIntoLedger(creditLeger2);
      
                int debitId2 = Database.numberOfRows()-1;
                int creditId2= Database.numberOfRows();
            
                int idSub2 = 0;
                Date dateSub2 = null;
                String mainAccountName2= "Accounts Payable A/C";
                String baseAccountName2=supplierName;
                String accountName2="Cash A/C";
                String particularSub2="Purchase Return.";

                double drUnit2=0;
                String drMeasurement2="Unit";
                double drRate2 =0;
                double drTotal2 = drUnit2*drRate2;

                double crUnit2=1;
                String crMeasurement2="Unit";
                double crRate2=cashPaid;
                double crTotal2=crUnit2*crRate2;

                double blUnit2=drUnit2-crUnit2;
                String blMeasurement2="Unit";
                double blRate2=crRate2;
                double blTotal2=blUnit2*blRate2;

                SubLedger subLedger2= new SubLedger(idSub2, debitId2, creditId2, dateSub2,mainAccountName2, baseAccountName2, accountName2, particularSub2, drUnit2, drMeasurement2, drRate2, drTotal2, crUnit2, crMeasurement2, crRate2, crTotal2, blUnit2, blMeasurement2, blRate2, blTotal2);
                Database.insertIntoSubLedger(subLedger2);
            }
            
            
                inventoryList.clear();
                double total =0.00;
                double totalPrice=0.00;
                double totalVat =0.00;
                double totalPriceAfterVat =0.00;
                
                session.setAttribute("inventoryList", inventoryList);
                session.setAttribute("total", total);// total product price for sales or purchase for an order of several Items. this value will be 0.00 after use.
                session.setAttribute("totalPrice", totalPrice);//for grand total after discount. this value will be 0.00 after use.
                session.setAttribute("totalVat", totalVat);
                session.setAttribute("totalPriceAfterVat", totalPriceAfterVat); //this value will be 0.00 after use.
            
                url = "/return.jsp";
        
        }
        if(salesOrPurchase.equalsIgnoreCase("damage"))
        {
            
            
            InventoryData.insertIntoInventory(inventoryList);
             
            
                inventoryList.clear();
                double total =0.00;
                double totalPrice=0.00;
                double totalVat =0.00;
                double totalPriceAfterVat =0.00;
                
                session.setAttribute("inventoryList", inventoryList);
                session.setAttribute("total", total);// total product price for sales or purchase for an order of several Items. this value will be 0.00 after use.
                session.setAttribute("totalPrice", totalPrice);//for grand total after discount. this value will be 0.00 after use.
                session.setAttribute("totalVat", totalVat);
                session.setAttribute("totalPriceAfterVat", totalPriceAfterVat); //this value will be 0.00 after use.
            
                url = "/return.jsp";
        
        }
            
            
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        
        }//end of try
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
            Logger.getLogger(BalanceQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BalanceQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
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
