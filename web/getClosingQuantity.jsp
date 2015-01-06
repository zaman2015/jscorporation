<%-- 
    Document   : grandTotal
    Created on : Jan 28, 2015, 2:42:14 AM
    Author     : Dhaka
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="data.InventoryData"%>
<%@page import="bisiness.Inventory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    String productName = request.getParameter("productName");
    Inventory inventory = InventoryData.getClosingBalanceByProduct(productName);
    double quantity = inventory.getBalanceQuantity();
   
  
   out.print(String.format("%.2f", quantity));
    
    
   
    
%>