<%-- 
    Document   : grandTotal
    Created on : Jan 28, 2015, 2:42:14 AM
    Author     : Dhaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    double totalPrice = Double.parseDouble(session.getAttribute("totalPrice").toString());
    double paid = Double.parseDouble(request.getParameter("val"));
  
    double dueAmount = totalPrice - paid;
                      
    out.print(dueAmount);

%>