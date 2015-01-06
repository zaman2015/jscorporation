<%-- 
    Document   : customerSupplier
    Created on : Jan 23, 2015, 5:11:18 PM
    Author     : Dhaka
--%>

<%@page import="bisiness.Product"%>
<%@page import="bisiness.Measurement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%@page import="data.Database" %>
<%@page import="bisiness.SubAccount" %>

<%
    String measurementName = request.getParameter("val");
    Product measurement=Database.selectMeasurementByProduct(measurementName);
    

    out.print(measurement.getMeasurementUnit());
    
    
%>











