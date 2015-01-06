<%-- 
    Document   : nameCheck
    Created on : Feb 1, 2015, 11:17:42 PM
    Author     : BUDGET003
--%>
<%@page import="bisiness.Ledger"%>
<%@page import="bisiness.SubLedger"%>
<%@page import="data.Database"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String type = request.getParameter("type");
    if(type.equalsIgnoreCase("payment"))
    {
        int id = 0;
        Date date = null;
        String mainAccountName=request.getParameter("debitAccount");
        String baseAccountName=request.getParameter("subAccount");

        String accountName =request.getParameter("subCashOrCheque"); 

        String particular=request.getParameter("subParticular"); 

        double drUnit=1.00;
        String drMeasurement="";
        double drRate =Double.parseDouble(request.getParameter("subAmount"));
        double drTotal = drUnit*drRate;

        double crUnit=0.00;
        String crMeasurement="";
        double crRate=0.00;
        double crTotal=crUnit*crRate;

        double blUnit=drUnit-crUnit;
        String blMeasurement="";
        double blRate=drRate;
        double blTotal=blUnit*blRate;

        int debitId = Database.numberOfRows()+1;
        int creditId= Database.numberOfRows()+2;



            SubLedger subLedger1= new SubLedger(id, debitId, creditId, date,mainAccountName, baseAccountName, accountName, particular, drUnit, drMeasurement, drRate, drTotal, crUnit, crMeasurement, crRate, crTotal, blUnit, blMeasurement, blRate, blTotal);
            Database.insertIntoSubLedger(subLedger1);




                String debitAccount = request.getParameter("debitAccount");
                String particularMain = request.getParameter("subParticular");
                double debitAmount = drRate;
                String cashOrCheque = request.getParameter("subCashOrCheque");

                String  creditAccount="";
                double creditAmount = debitAmount;

                if(cashOrCheque.equalsIgnoreCase("cash"))
                {
                    int idMain = 0;
                    Date dateMain = null;

                    creditAccount = "Cash A/C";

                    double debitBalance = debitAmount - 0.00;
                    double creditBalance = 0.00-creditAmount;

                    Ledger debitLeger = new Ledger(idMain, dateMain, debitAccount, creditAccount, debitAmount, 0.00, debitBalance, particular);
                    Ledger creditLeger = new Ledger(idMain, dateMain, creditAccount, debitAccount, 0.00, creditAmount, creditBalance, particular);

                    Database.insertIntoLedger(debitLeger);
                    Database.insertIntoLedger(creditLeger);
                }
                if(cashOrCheque.equalsIgnoreCase("Bank"))
                {
                    int idMain = 0;
                    Date dateMain = null;

                    creditAccount = "Bank A/C";

                    double debitBalance = debitAmount - 0.00;
                    double creditBalance = 0.00-creditAmount;

                    Ledger debitLeger = new Ledger(idMain, dateMain, debitAccount, creditAccount, debitAmount, 0.00, debitBalance, particular);
                    Ledger creditLeger = new Ledger(idMain, dateMain, creditAccount, debitAccount, 0.00, creditAmount, creditBalance, particular);

                    Database.insertIntoLedger(debitLeger);
                    Database.insertIntoLedger(creditLeger);
                }
         out.print("Saved Successfully!");
    }
    if(type.equalsIgnoreCase("receipt"))
    {
        int id = 0;
        Date date = null;
        String mainAccountName=request.getParameter("debitAccount");
        String baseAccountName=request.getParameter("subAccount");

        String accountName =request.getParameter("subCashOrCheque"); 

        String particular=request.getParameter("subParticular"); 

        double drUnit=0.00;
        String drMeasurement="";
        double drRate =0.00;
        double drTotal = drUnit*drRate;

        double crUnit=1.00;
        String crMeasurement="";
        double crRate=Double.parseDouble(request.getParameter("subAmount"));
        double crTotal=crUnit*crRate;

        double blUnit=drUnit-crUnit;
        String blMeasurement="";
        double blRate=crRate;
        double blTotal=blUnit*blRate;

        int debitId = Database.numberOfRows()+1;
        int creditId= Database.numberOfRows()+2;



            SubLedger subLedger1= new SubLedger(id, debitId, creditId, date,mainAccountName, baseAccountName, accountName, particular, drUnit, drMeasurement, drRate, drTotal, crUnit, crMeasurement, crRate, crTotal, blUnit, blMeasurement, blRate, blTotal);
            Database.insertIntoSubLedger(subLedger1);




                String creditAccount = request.getParameter("debitAccount");
                String particularMain = request.getParameter("subParticular");
                double debitAmount = crRate;
                String cashOrCheque = request.getParameter("subCashOrCheque");

                String  debitAccount="";
                double creditAmount = debitAmount;

                if(cashOrCheque.equalsIgnoreCase("cash"))
                {
                    int idMain = 0;
                    Date dateMain = null;

                    debitAccount = "Cash A/C";

                    double debitBalance = debitAmount - 0.00;
                    double creditBalance = 0.00-creditAmount;

                    Ledger debitLeger = new Ledger(idMain, dateMain, debitAccount, creditAccount, debitAmount, 0.00, debitBalance, particular);
                    Ledger creditLeger = new Ledger(idMain, dateMain, creditAccount, debitAccount, 0.00, creditAmount, creditBalance, particular);

                    Database.insertIntoLedger(debitLeger);
                    Database.insertIntoLedger(creditLeger);
                }
                if(cashOrCheque.equalsIgnoreCase("Bank"))
                {
                    int idMain = 0;
                    Date dateMain = null;

                    debitAccount = "Bank A/C";

                    double debitBalance = debitAmount - 0.00;
                    double creditBalance = 0.00-creditAmount;

                    Ledger debitLeger = new Ledger(idMain, dateMain, debitAccount, creditAccount, debitAmount, 0.00, debitBalance, particular);
                    Ledger creditLeger = new Ledger(idMain, dateMain, creditAccount, debitAccount, 0.00, creditAmount, creditBalance, particular);

                    Database.insertIntoLedger(debitLeger);
                    Database.insertIntoLedger(creditLeger);
                }
         out.print("Saved Successfully!");
    }
%>