<%-- 
    Document   : grandTotal
    Created on : Jan 28, 2015, 2:42:14 AM
    Author     : Dhaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    double totalPriceAfterVat = Double.parseDouble(session.getAttribute("totalPriceAfterVat").toString());
    double paid = Double.parseDouble(request.getParameter("val"));
  
     double salesDueAmount = totalPriceAfterVat - paid;
         
    out.print(salesDueAmount);

%>