<%-- 
    Document   : nameCheck
    Created on : Feb 1, 2015, 11:17:42 PM
    Author     : BUDGET003
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="bisiness.Ledger"%>
<%@page import="bisiness.SubLedger"%>
<%@page import="data.Database"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
            ArrayList inventoryList = (ArrayList) session.getAttribute("inventoryList");
           
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
   
%>