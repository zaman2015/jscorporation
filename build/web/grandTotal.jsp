<%-- 
    Document   : grandTotal
    Created on : Jan 28, 2015, 2:42:14 AM
    Author     : Dhaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    double total = Double.parseDouble(session.getAttribute("total").toString());
    double discount = Double.parseDouble(request.getParameter("val"));
    double totalPrice= total- discount;
    
    session.setAttribute("totalPrice", totalPrice);
    
    out.print(totalPrice);

    
    
%>