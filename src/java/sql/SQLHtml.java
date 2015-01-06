/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sql;


import bisiness.Account;
import bisiness.Ledger;
import bisiness.Measurement;
import bisiness.Product;

import bisiness.SubAccount;
import bisiness.SubLedger;
import data.Database;
import data.InventoryData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


/**
 *
 * @author ARIBA
 */
 public class SQLHtml {

    public static String getAccountNames()
    {
        StringBuffer htmlRows = new StringBuffer();
        htmlRows.append("<option value=''>--Select One--</option>");
        ArrayList<Account> accontListString = Database.selectAllAccountName();
        Iterator iterator = accontListString.listIterator();
        while(iterator.hasNext())
        {
            Account account= (Account) iterator.next();
            String accountName = account.getAccountName();

            htmlRows.append("<option value="+"\""+accountName+"\""+">"+accountName+"</option>");
        }
        return htmlRows.toString();
    }
    public static String getSubAccountNames(ArrayList<SubAccount> subAccountList)
    {
        StringBuffer htmlRows = new StringBuffer();
        Iterator iterator = subAccountList.listIterator();
        htmlRows.append("<option value=''>--Select One--</option>");
        while(iterator.hasNext())
        {
            SubAccount account= (SubAccount) iterator.next();
            String accountName = account.getSubAccountName();

            htmlRows.append("<option value="+"\""+accountName+"\""+">"+accountName+"</option>");
        }
        return htmlRows.toString();
    }
    public static String getAccountNameAndType()
    {
        StringBuffer htmlRows = new StringBuffer();
        ArrayList<Account> accontListString = Database.selectAllAccountName();
        Iterator iterator = accontListString.listIterator();
        htmlRows.append("<table width=100% class='inventory'>");
        htmlRows.append("<tr><th>ID</th><th>Account Name</th><th>Type</th></tr>");

        while(iterator.hasNext())
        {
            Account account= (Account) iterator.next();

            htmlRows.append("<tr><td style='text-align:center'>"+account.getId()+"</td><td style='text-align:left'>"+account.getAccountName()+"</td><td style='text-align:left'>"+account.getAccountType()+"</td></tr>");
        }
        htmlRows.append("</table>");
        return htmlRows.toString();
    }
    public static String getHtmlTrialBalanceTable(ArrayList<Account> account) throws SQLException
{
        StringBuffer htmlRows = new StringBuffer();
        Iterator iterator = account.iterator();

        htmlRows.append("<table width=100% class='inventory'>");
        htmlRows.append("<tr>");
        htmlRows.append("<th width=60%>Account Name</th><th width=20%>Debit Taka</th><th width=20%>Credit Taka</th>");
        htmlRows.append("</tr>");
        double grandTotalDebit =0.00;
        double grandTotlaCredit =0.00;
        while(iterator.hasNext())
        {
            Account accountList = (Account) iterator.next();
            String accountName = accountList.getAccountName();

            double total = Database.getBalance(accountName);
            double totalDebit=0.00;
            double totalCredit=0.00;

            if(total<0)
            {
                totalDebit=0.00;
                totalCredit=total*-1;
                grandTotalDebit=grandTotalDebit+totalDebit;
                grandTotlaCredit= grandTotlaCredit+totalCredit;
                htmlRows.append("<tr>");
                htmlRows.append("<td width=60% style='text-align:left'><a href=BalanceQueryServlet?ledgerName="+accountName.replaceAll(" ", "+")+">"+accountName+"</a></td><td width=20% style='text-align:right'>"+String.format("%.2f", totalDebit)+"</td><td width=20% style='text-align: right'>"+String.format("%.2f", totalCredit)+"</td>");
                htmlRows.append("</tr>");
            }
            if(total>0)
            {
                totalDebit=total;
                totalCredit=0.00;
                grandTotalDebit=grandTotalDebit+totalDebit;
                grandTotlaCredit= grandTotlaCredit+totalCredit;
                htmlRows.append("<tr>");
                htmlRows.append("<td width=60% style='text-align:left'><a href=BalanceQueryServlet?ledgerName="+accountName.replaceAll(" ", "+")+">"+accountName+"</a></td><td width=20% style='text-align:right'>"+String.format("%.2f", totalDebit)+"</td><td width=20% style='text-align: right'>"+String.format("%.2f", totalCredit)+"</td>");
                htmlRows.append("</tr>");
            }

            
        }
        htmlRows.append("<tr>");
        htmlRows.append("<td width=60% style='text-align:center'><b>Total</b></td><td width=20% style='text-align:right'><b>"+String.format("%.2f", grandTotalDebit)+"</b></td><td width=20% style='text-align:right'><b>"+String.format("%.2f", grandTotlaCredit)+"</td></b>");
        htmlRows.append("</tr>");
        htmlRows.append("</table>");
        return htmlRows.toString();
    }

    public static String showLedgerTable(ArrayList<Ledger> result) throws SQLException
    {
        StringBuffer htmlRows = new StringBuffer();

        htmlRows.append("<table class='inventory' style='width: 100%'>");


        htmlRows.append("<tr>");

        htmlRows.append("<th >ID</th><th>DATE</th><th>ACCOUNT TITLE</th><th>PARTICULAR</th><th>DEBIT TAKA</th><th>CREDIT TAKA</th><th>BALANCE</th>");
        htmlRows.append("</tr>");
        Iterator<Ledger> iterator = result.listIterator();

        double debitAmount =0.00;
        double creditAmount =0.00;
        double totalDebit=0.00;
        double totalCredit=0.00;
        double balance=0.00;

        while(iterator.hasNext())
        {

            Ledger ledger = iterator.next();
            debitAmount= ledger.getDebitAmount();
            creditAmount= ledger.getCreditAmount();
            balance = balance+ledger.getBalance();
            totalDebit = totalDebit + debitAmount;
            totalCredit = totalCredit + creditAmount;

            htmlRows.append("<tr>");


            htmlRows.append("<td style='text-align: left'>"+ledger.getId()+"</td>"+
                            "<td>"+ledger.getDate()+"</td>"+
                            "<td style='text-align: left'><a href=BalanceQueryServlet?ledgerName="+ledger.getAccountName().replaceAll(" ", "+")+">"+ledger.getAccountName()+"</a></td>"+
                            "<td style='text-align: left'><a href=BalanceQueryServlet?ledgerName="+ledger.getAccountName().replaceAll(" ", "+")+">"+ledger.getParticular()+"</a></td>"+
                            "<td style='text-align: right'>"+ String.format("%.2f", debitAmount)+"</td>"+
                            "<td style='text-align: right'>"+ String.format("%.2f", creditAmount)+"</td>"+
                            "<td style='text-align: right'>"+ String.format("%.2f", balance)+"</td>");
            htmlRows.append("</tr>");
        }
        htmlRows.append("<tr>");

        htmlRows.append("<td style='text-align: right'><b></b></td><td style='text-align: right'><b></b></td><td style='text-align: right'><b></b></td><td style='text-align: center'><b>TOTAL</b></td><td style='text-align: right'><b>"+String.format("%.2f", totalDebit)+"</b></td><td style='text-align: right'><b>"+String.format("%.2f", totalCredit)+"</b></td><td style='text-align: right'><b>"+String.format("%.2f", balance)+"</b></td>");
        htmlRows.append("</tr>");

        htmlRows.append("</table>");
        return htmlRows.toString();

    }

    public static String getBalanceSheetAssets(ArrayList<Account> account) throws SQLException
{
        StringBuffer htmlRows = new StringBuffer();
        Iterator iterator = account.iterator();

        htmlRows.append("<table style='width: 100%' class='inventory'>");
        htmlRows.append("<tr>");
        htmlRows.append("<th style='width: 60%'>Assets</th><th style='width: 20%'>Taka</th><th style='width: 20%'>Taka</th>");
        htmlRows.append("</tr>");
        double grandTotal =0.00;

        while(iterator.hasNext())
        {
            Account accountList = (Account) iterator.next();
            double closingInventoy = InventoryData.getClosignTotalInventory();

            if(accountList.getAccountType().equalsIgnoreCase("Capital"))
            {
                String accountName = accountList.getAccountName();
                double total = Database.getBalance(accountName);
                if(total>0&& !accountName.equalsIgnoreCase("Inventory A/C"))
                {
                    htmlRows.append("<tr>");
                    htmlRows.append("<td style='width: 60%; text-align: left'><a href=BalanceQueryServlet?ledgerName="+accountName.replaceAll(" ", "+")+">"+accountName+"</a></td><td style='width: 20%; text-align: right'></td><td style='width: 20%; text-align: right'>"+String.format("%.2f", total)+"</td>");
                    htmlRows.append("</tr>");
                    grandTotal= grandTotal+total;
                }
                if(total>0 && accountName.equalsIgnoreCase("Inventory A/C"))
                {
                    htmlRows.append("<tr>");
                    htmlRows.append("<td style='width: 60%; text-align: left'><a href=BalanceQueryServlet?ledgerName="+accountName.replaceAll(" ", "+")+">"+accountName+"</a></td><td style='width: 20%; text-align: right'></td><td style='width: 20%; text-align: right'>"+String.format("%.2f", closingInventoy)+"</td>");
                    htmlRows.append("</tr>");
                    grandTotal= grandTotal+closingInventoy;
                }
                
            }

        }
        htmlRows.append("<tr>");
        htmlRows.append("<td style='width: 60%; text-align: center'><b>Total</b></td><td style='width: 20%; text-align: right'><b></b></td><td style='width: 20%; text-align: right'><b>"+String.format("%.2f", grandTotal)+"</b></td>");
        htmlRows.append("</tr>");
        htmlRows.append("</table>");
        htmlRows.append("<br>");
        htmlRows.append("<br>");




        return htmlRows.toString();
    }

    public static String getBalanceSheetLiabilities(ArrayList<Account> account) throws SQLException
{
        StringBuffer htmlRows = new StringBuffer();
        Iterator iterator = account.iterator();

        htmlRows.append("<table style='width: 100%' class='inventory'>");
        htmlRows.append("<tr>");
        htmlRows.append("<th style='width: 60%'>Liabilities</th><th style='width: 20%'>Taka</th><th style='width: 20%'>Taka</th>");
        htmlRows.append("</tr>");
        double grandTotal =0.00;

        while(iterator.hasNext())
        {
            Account accountList = (Account) iterator.next();

            double profitAndLoss= Database.getProfitAndLoss();
            
            if(accountList.getAccountType().equalsIgnoreCase("Capital"))
            {
                
                
                String accountName = accountList.getAccountName();
                
                double total = Database.getBalance(accountName);
                if(total<0)
                {
                    if(accountName.equalsIgnoreCase("Capital A/C"))
                    {
                        if(profitAndLoss>=0)
                        {
                            htmlRows.append("<tr>");
                            htmlRows.append("<td style='width: 60%; text-align: left'><a href=BalanceQueryServlet?ledgerName="+accountName.replaceAll(" ", "+")+">"+accountName+"</a></td><td style='width: 20%; text-align: right'>"+String.format("%.2f", total*-1)+"</td><td style='width: 20%; text-align: right'></td>");
                            htmlRows.append("</tr>"); 
                            htmlRows.append("<tr>");
                            htmlRows.append("<td style='width: 60%; text-align: left'><a href=BalanceQueryServlet?ledgerName='Profit and Loss A/C'</a>Add: Net Profit</td><td style='width: 20%; text-align: right'>"+String.format("%.2f", profitAndLoss)+"</td><td style='width: 20%; text-align: right'></td>");
                            htmlRows.append("</tr>");
                            htmlRows.append("<tr>");
                            htmlRows.append("<td style='width: 60%; text-align: left'></td><td style='width: 20%; text-align: right'></td><td style='width: 20%; text-align: right'>"+String.format("%.2f", (total*-1)+profitAndLoss)+"</td>");
                            htmlRows.append("</tr>");
                            grandTotal= grandTotal+(total*-1)+profitAndLoss;
                        }
                        else
                        {
                            htmlRows.append("<tr>");
                            htmlRows.append("<td style='width: 60%; text-align: left'><a href=BalanceQueryServlet?ledgerName="+accountName.replaceAll(" ", "+")+">"+accountName+"</a></td><td style='width: 20%; text-align: right'>"+String.format("%.2f", total*-1)+"</td><td style='width: 20%; text-align: right'></td>");
                            htmlRows.append("</tr>"); 
                            htmlRows.append("<tr>");
                            htmlRows.append("<td style='width: 60%; text-align: left'><a href=BalanceQueryServlet?ledgerName='Profit and Loss A/C'</a>Less: Net Loss</td><td style='width: 20%; text-align: right'>"+String.format("%.2f", profitAndLoss)+"</td><td style='width: 20%; text-align: right'></td>");
                            htmlRows.append("</tr>");
                            htmlRows.append("<tr>");
                            htmlRows.append("<td style='width: 60%; text-align: left'></td><td style='width: 20%; text-align: right'></td><td style='width: 20%; text-align: right'>"+String.format("%.2f", (total*-1)-profitAndLoss)+"</td>");
                            htmlRows.append("</tr>");
                            grandTotal= grandTotal+(total*-1)-profitAndLoss;
                        }
                        
                    }
                    else
                    {
                    
                    htmlRows.append("<tr>");
                    htmlRows.append("<td style='width: 60%; text-align: left'><a href=BalanceQueryServlet?ledgerName="+accountName.replaceAll(" ", "+")+">"+accountName+"</a></td><td style='width: 20%; text-align: right'></td><td style='width: 20%; text-align: right'>"+String.format("%.2f", total*-1)+"</td>");
                    htmlRows.append("</tr>");
                    grandTotal= grandTotal+total*-1;
                    }
                }
            }

        }
        htmlRows.append("<tr>");
        htmlRows.append("<td style='width: 60%; text-align: center'><b>Total</b></td><td style='width: 20%; text-align: right'><b></b></td><td style='width: 20%; text-align: right'><b>"+String.format("%.2f", grandTotal)+"</b></td>");
        htmlRows.append("</tr>");
        htmlRows.append("</table>");

        return htmlRows.toString();
    }

    public static String getProfitAndLoss(double closingInventory) throws SQLException
{
        StringBuffer htmlRows = new StringBuffer();
        
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

            htmlRows.append("<table style='width:100%' class='inventory'>");

        htmlRows.append("<tr><th style='width: 60%;'>Particular</th><th style='width: 20%'>Taka</th><th style='width: 20%'>Taka</th></tr>");
        
        Iterator iterator1 = revenueAccoutList.listIterator();
        Iterator iterator2 = revenueAccoutList.listIterator();
        Iterator iterator3 = revenueAccoutList.listIterator();
        Iterator iterator4 = revenueAccoutList.listIterator();



        double incomeBalance=0.00;
        double openingInventoryBalance=0.00;
        double purchaseBalance=0.00;
        double totalPurchaseAndInventory=0.00;
        double closingInventoryBalance=closingInventory;
        double costOfGoodsSold=0.00;
        double grossProfit=0.00;
        String grossPofitOrLoss="";
        double totalOfficeExpenses=0.00;
        String netPofitOrLoss="";
        double netProfitandLoss=0.00;

        while(iterator1.hasNext())
        {

            Account account = (Account) iterator1.next();
            String accountName = account.getAccountName();
            double balance= Database.getBalance(accountName);
            if(balance<0)
            {
                if(accountName.equalsIgnoreCase("Inventory A/C"))
                    continue;
                incomeBalance=incomeBalance+balance*-1;
                
            }
        }
        htmlRows.append("<tr><td style='width: 60%; text-align:left'>Sales and Others Income</td><td style='width: 20%; text-align:right'></td><td style='width: 20%; text-align:right'>"+String.format("%.2f", incomeBalance)+"</td></tr>");


        while(iterator2.hasNext())
        {
            Account account =  (Account) iterator2.next();
            String accountName = account.getAccountName();
            
            
            if(accountName.equalsIgnoreCase("Purchase A/C"))
            {
                
                    openingInventoryBalance = Database.getBalance("Inventory A/C");
                
                    purchaseBalance = Database.getBalance(accountName);
                    htmlRows.append("<tr><td style='width: 60%; text-align:left'>Opening Inventory</td><td style='width: 20%; text-align:right'>"+String.format("%.2f", openingInventoryBalance)+"</td><td style='width: 20%; text-align:right'></td></tr>");
                    htmlRows.append("<tr><td style='width: 60%; text-align:left'>Purchase</td><td style='width: 20%; text-align:right'>"+String.format("%.2f", purchaseBalance)+"</td><td style='width: 20%; text-align:right'></td></tr>");
                

            }
            }

        totalPurchaseAndInventory=openingInventoryBalance+ purchaseBalance;
        htmlRows.append("<tr><td style='width: 60%; text-align:left'><b>Goods Available</b></td><td style='width: 20%; text-align:right'><b>"+String.format("%.2f", totalPurchaseAndInventory)+"</b></td><td style='width: 20%; text-align:right'></td></tr>");
        

        htmlRows.append("<tr><td style='width: 60%; text-align:left'>Less: Closing Inventory</td><td style='width: 20%; text-align:right'>"+String.format("%.2f", closingInventoryBalance)+"</td><td style='width: 20%; text-align:right'></td></tr>");
        costOfGoodsSold=totalPurchaseAndInventory- closingInventoryBalance;
        htmlRows.append("<tr><td style='width: 60%; text-align:left'><b>Cost of Goods Sold</b></td><td style='width: 20%; text-align:right'></td><td style='width: 20%; text-align:right'><b>"+String.format("%.2f", costOfGoodsSold)+"</b></td></tr>");

        if(incomeBalance>=costOfGoodsSold)
           grossPofitOrLoss = "Gross Profit";
        else
            grossPofitOrLoss = "Gross Loss";

        grossProfit=incomeBalance-costOfGoodsSold;

        htmlRows.append("<tr><td style='width: 60%; text-align:center'><b>"+grossPofitOrLoss+"</b></td><td style='width: 20%; text-align:right'></td><td style='width: 20%; text-align:right'><b>"+String.format("%.2f", grossProfit)+"</b></td></tr>");
        htmlRows.append("<tr><td style='width: 60%; text-align:left'><b>Less: Office and Administrative Expenses</b></td><td style='width: 20%; text-align:right'></td><td style='width: 20%; text-align:right'></td></tr>");

        while(iterator4.hasNext())
        {
            Account account = (Account) iterator4.next();
            String accountName = account.getAccountName();
            double balance= Database.getBalance(accountName);
            
            if(balance>0)
            {
            if(accountName.equalsIgnoreCase("Purchase A/C")||accountName.equalsIgnoreCase("Inventory A/C")||accountName.equalsIgnoreCase("Capital A/C"))
            continue;
                
                
                totalOfficeExpenses = totalOfficeExpenses+balance;


                htmlRows.append("<tr><td style='width: 60%; text-align:left'>"+accountName+"</td><td style='width: 20%; text-align:right'>"+String.format("%.2f", balance)+"</td><td style='width: 20%; text-align:right'></td></tr>");
            
          }
          
            }

        if(grossProfit>totalOfficeExpenses)
                    netPofitOrLoss="Net Profit";
                else
                    netPofitOrLoss="Net Loss";
        netProfitandLoss= grossProfit-totalOfficeExpenses;

        htmlRows.append("<tr><td style='width: 60%; text-align:left'><b>Total Office and Administrative Expenses</b></td><td style='width: 20%; text-align:right'></td><td style='width: 20%; text-align:right'><b>"+String.format("%.2f", totalOfficeExpenses)+"</b></td></tr>");
        htmlRows.append("<tr><td style='width: 60%; text-align:center'><b>"+netPofitOrLoss+"</b></td><td style='width: 20%; text-align:right'></td><td style='width: 20%; text-align:right'><b>"+String.format("%.2f", netProfitandLoss)+"</b></td></tr>");

        htmlRows.append("</table>");
        return htmlRows.toString();
       
    }
    public static String getSubAccountNameWithMain()
    {
        StringBuffer htmlRows = new StringBuffer();
        ArrayList<SubAccount> accontListString = Database.selectSubAccountName();
        Iterator iterator = accontListString.listIterator();

        htmlRows.append("<tr><th>ID</th><th>Sub Account Name</th><th>Main Account Name</th></tr>");

        while(iterator.hasNext())
        {
            SubAccount account= (SubAccount) iterator.next();

            htmlRows.append("<tr><td style='text-align: center'>"+account.getId()+"</td><td style='text-align: left'>"+account.getSubAccountName()+"</td><td style='text-align: left'>"+account.getMainAccountName()+"</td></tr>");
        }
        return htmlRows.toString();
    }
    public static String getMainAccount()
    {
        StringBuffer htmlRows = new StringBuffer();
        ArrayList<String> mainAccountList= Database.selectMainAccountName();
        Iterator iterator = mainAccountList.listIterator();
        htmlRows.append("<option value=''>--Select One--</option>");
        while(iterator.hasNext())
        {
            String account= (String) iterator.next();

            
            htmlRows.append("<option value="+"\""+account+"\""+">"+account+"</option>");
        }
        return htmlRows.toString();
    }

    public static String getAllSubLedgerBalance(ArrayList<String> account) throws SQLException
{
        StringBuffer htmlRows = new StringBuffer();
        Iterator iterator = account.iterator();

        htmlRows.append("<table style='width: 100%' class='inventory'>");
        htmlRows.append("<tr>");
        htmlRows.append("<th style='width: 60%'>Sub Account Name</th><th style='width: 20%'>Debit Taka</th><th style='width: 20%'>Credit Taka</th>");
        htmlRows.append("</tr>");
        double grandTotalDebit =0.00;
        double grandTotlaCredit =0.00;
        while(iterator.hasNext())
        {

            String accountName = (String) iterator.next();

            double total = Database.getSubLedgerBalance(accountName);
            double totalDebit=0.00;
            double totalCredit=0.00;

            if(total<0)
            {totalDebit=0.00;
             totalCredit=total*-1;
             grandTotalDebit=grandTotalDebit+totalDebit;
             grandTotlaCredit= grandTotlaCredit+totalCredit;
            }
            if(total>=0)
            {totalDebit=total;
             totalCredit=0.00;
             grandTotalDebit=grandTotalDebit+totalDebit;
             grandTotlaCredit= grandTotlaCredit+totalCredit;
            }

            htmlRows.append("<tr>");
            htmlRows.append("<td style='width: 60%; text-align: left'><a href=SubLedgerBlnQryServlet?subAccountName="+accountName.replaceAll(" ", "+")+">"+accountName+"</a></td><td style='width: 20%; text-align: right'>"+String.format("%.2f", totalDebit)+"</td><td style='width: 20%; text-align: right'>"+String.format("%.2f", totalCredit)+"</td>");
            htmlRows.append("</tr>");
        }
        htmlRows.append("<tr>");
        htmlRows.append("<td style='width: 60%; text-align: center'><b>Total</b></td><td style='width: 20%; text-align: right'><b>"+String.format("%.2f", grandTotalDebit)+"</b></td><td style='width: 20%; text-align: right'><b>"+String.format("%.2f", grandTotlaCredit)+"</td></b>");
        htmlRows.append("</tr>");
        htmlRows.append("</table>");
        return htmlRows.toString();
    }
    public static String getProductNames() throws SQLException
    {
        StringBuffer htmlRows = new StringBuffer();
        htmlRows.append("<option value=''>--Select One--</option>");
        ArrayList<Product> productListString = Database.selectAllProductName();
        Iterator iterator = productListString.listIterator();
        while(iterator.hasNext())
        {
            Product product= (Product) iterator.next();
            String productName = product.getProductName();

            htmlRows.append("<option value="+"\""+productName+"\""+">"+productName+"</option>");
        }
        return htmlRows.toString();
    }
    
    public static String getProductNameAndUnit() throws SQLException
    {
        StringBuffer htmlRows = new StringBuffer();
        ArrayList<Product> productList = Database.selectAllProductName();
        Iterator iterator = productList.listIterator();
        htmlRows.append("<table width=100% class='inventory'>");
        htmlRows.append("<tr><th>ID</th><th>Product Name</th><th>Measurement Unit</th></tr>");

        while(iterator.hasNext())
        {
            Product product= (Product) iterator.next();

            htmlRows.append("<tr><td style='text-align:center'>"+product.getId()+"</td><td style='text-align:left'>"+product.getProductName()+"</td><td style='text-align:left'>"+product.getMeasurementUnit()+"</td></tr>");
        }
        htmlRows.append("</table>");
        return htmlRows.toString();
    }
    public static String getMeasurements() throws SQLException
    {
        StringBuffer htmlRows = new StringBuffer();
        htmlRows.append("<option value=''>--Select One--</option>");
        ArrayList<Measurement> measurementString = Database.selectAllMeasurementUnit();
        Iterator iterator = measurementString.listIterator();
        while(iterator.hasNext())
        {
            Measurement measurement= (Measurement) iterator.next();
            String MeasurementName = measurement.getMeasurement();

            htmlRows.append("<option value="+"\""+MeasurementName+"\""+">"+MeasurementName+"</option>");
        }
        return htmlRows.toString();
    }
    public static String getMeasurementsTable() throws SQLException
    {
        StringBuffer htmlRows = new StringBuffer();
        ArrayList<Measurement> measurementList = Database.selectAllMeasurementUnit();
        Iterator iterator = measurementList.listIterator();
        htmlRows.append("<table width=100% class='inventory'");
        htmlRows.append("<tr><th>ID</th><th>Measuement Unit</th></tr>");

        while(iterator.hasNext())
        {
            Measurement measurement= (Measurement) iterator.next();

            htmlRows.append("<tr><td style='text-align:center'>"+measurement.getId()+"</td><td style='text-align:left'>"+measurement.getMeasurement()+"</td></tr>");
        }
        htmlRows.append("</table>");
        return htmlRows.toString();
    }
    
    public static String getClosinJournalString(ArrayList<Ledger> ledgerList)
    {
        StringBuffer htmlRows = new StringBuffer();
        Iterator iterator = ledgerList.iterator();
        htmlRows.append("<form action='InsertClosingEntrySrvlet' method='post'>");
        htmlRows.append("<table width=100% class='inventory'>");
        htmlRows.append("<tr>");
        htmlRows.append("<th width=10%>Account Name</th><th width=30%>Account Name</th><th width=30%>Particular</th><th width=15%>Debit Taka</th><th width=15%>Credit Taka</th>");
        htmlRows.append("</tr>");
        double grandTotalDebit =0.00;
        double grandTotlaCredit =0.00;
        while(iterator.hasNext())
        {
            Ledger ledger = (Ledger) iterator.next();
            
            Date date = new Date();
            String accountName =ledger.getBaseAccountName();
            String particular =ledger.getParticular();
            double totalDebit=ledger.getDebitAmount();
            double totalCredit=ledger.getCreditAmount();

            
                htmlRows.append("<tr>");
                htmlRows.append("<td width=10% style='text-align:left'>"+new SimpleDateFormat("dd-MM-yy").format(date)+"<td width=30% style='text-align:left'><a href=BalanceQueryServlet?ledgerName="+accountName.replaceAll(" ", "+")+">"+accountName+"</a></td><td width=30% style='text-align:right'>"+particular+"</td><td width=15% style='text-align:right'>"+String.format("%.2f", totalDebit)+"</td><td width=15% style='text-align: right'>"+String.format("%.2f", totalCredit)+"</td>");
                htmlRows.append("</tr>");
           
        }
             
        htmlRows.append("</table>");
        htmlRows.append("<input type='button' value='Cancel'><input type='submit' value='Save'>");
        htmlRows.append("</form>");
        return htmlRows.toString();
        
    }
    
    
    public static String getSubLedgerTable(String subAccount) throws SQLException
    {
        ArrayList<SubLedger> list= Database.getSubLedger(subAccount);
        StringBuffer htmlRows = new StringBuffer();

        htmlRows.append("<table class='inventory' style='width: 100%'>");


        htmlRows.append("<tr>");

        htmlRows.append("<th >ID</th><th>DATE</th><th>ACCOUNT TITLE</th><th>PARTICULAR</th><th>DEBIT TAKA</th><th>CREDIT TAKA</th><th>BALANCE</th>");
        htmlRows.append("</tr>");
        Iterator iterator = list.listIterator();

        double debitAmount =0.00;
        double creditAmount =0.00;
        double totalDebit=0.00;
        double totalCredit=0.00;
        double balance=0.00;

        while(iterator.hasNext())
        {

            SubLedger subledger = (SubLedger) iterator.next();
            debitAmount= subledger.getDrTotal();
            creditAmount= subledger.getCrTotal();
            balance = balance+subledger.getBlTotal();
            totalDebit = totalDebit + debitAmount;
            totalCredit = totalCredit + creditAmount;

            htmlRows.append("<tr>");


            htmlRows.append("<td style='text-align: left'>"+subledger.getId()+"</td>"+
                            "<td>"+subledger.getDate()+"</td>"+
                            "<td style='text-align: left'>"+subledger.getAccountName()+"</td>"+
                            "<td style='text-align: left'>"+subledger.getParticular()+"</td>"+
                            "<td style='text-align: right'>"+ String.format("%.2f", debitAmount)+"</td>"+
                            "<td style='text-align: right'>"+ String.format("%.2f", creditAmount)+"</td>"+
                            "<td style='text-align: right'>"+ String.format("%.2f", balance)+"</td>");
            htmlRows.append("</tr>");
        }
        htmlRows.append("<tr>");

        htmlRows.append("<td style='text-align: right'><b></b></td><td style='text-align: right'><b></b></td><td style='text-align: right'><b></b></td><td style='text-align: center'><b>TOTAL</b></td><td style='text-align: right'><b>"+String.format("%.2f", totalDebit)+"</b></td><td style='text-align: right'><b>"+String.format("%.2f", totalCredit)+"</b></td><td style='text-align: right'><b>"+String.format("%.2f", balance)+"</b></td>");
        htmlRows.append("</tr>");

        htmlRows.append("</table>");
        return htmlRows.toString();

    }



}
