<%-- 
    Document   : nameCheck
    Created on : Feb 1, 2015, 11:17:42 PM
    Author     : BUDGET003
--%>

<%@page import="sql.SQLHtml"%>
<%@page import="bisiness.SubAccount"%>
<%@page import="java.util.ArrayList"%>
<%@page import="data.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String custmerName = request.getParameter("custmerName");
    String customerAddress = request.getParameter("customerAddress");
    String phone = request.getParameter("phone");
    String salesOrPurhase = request.getParameter("salesOrPurhase");
    String msg = "";
    
    if(salesOrPurhase.equalsIgnoreCase("Purchase"))
    {
        String mainAccountName="Accounts Payable A/C";
        SubAccount subAccount = Database.selectSubAccountNameBySub(custmerName);
        String mainAccountName1 = subAccount.getMainAccountName();
        
        if(subAccount!= null && mainAccountName1.equalsIgnoreCase("Accounts Payable A/C"))
        {
            msg = "Name Exist! Please Insert an Unique Name";
            out.print(msg);
        }
        else
        {
            SubAccount subAccount1 = new SubAccount(0, mainAccountName, custmerName, customerAddress, phone );
            Database.insertIntoSubAccount(subAccount1);
            out.print("Supplier's Name, Address and Phone Number have been saved successfully!");
        }
        
    } 
        
    
    
        if(salesOrPurhase.equalsIgnoreCase("Sales"))
    {
        String mainAccountName="Accounts Receivable A/C";
        SubAccount subAccount = Database.selectSubAccountNameBySub(custmerName);
        String mainAccountName1 = subAccount.getMainAccountName();
        
        if(subAccount!= null && mainAccountName1.equalsIgnoreCase("Accounts Receivable A/C"))
        {
            msg = "Name Exist! Please Insert an Unique Name";
            out.print(msg);
        }
        else
        {
            SubAccount subAccount1 = new SubAccount(0, mainAccountName, custmerName, customerAddress, phone );
            Database.insertIntoSubAccount(subAccount1);
            out.print("Customer's Name, Address and Phone Number have been saved successfully!");
        }
        
    }
        
        String customerList= SQLHtml.getSubAccountNames(Database.selectSubAccountName("Accounts Receivable A/C"));
        String supplierList= SQLHtml.getSubAccountNames(Database.selectSubAccountName("Accounts Payable A/C"));
        session.setAttribute("supplierList", supplierList);
        session.setAttribute("customerList", customerList);
%>