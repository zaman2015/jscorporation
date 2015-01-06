<%-- 
    Document   : customerSupplier
    Created on : Jan 23, 2015, 5:11:18 PM
    Author     : Dhaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="data.Database" %>
<%@page import="bisiness.SubAccount" %>

<%
    String subAccountName = request.getParameter("val");
    SubAccount subAccount=Database.selectSubAccountNameBySub(subAccountName);
    
   
    out.print("<tr>");
    out.print("<td style='font-weight: bold; width:30%; text-align: left'>");
        out.print("Name:");
    out.print("</td>");
    out.print("<td style='width: 70%; text-align: left'>");
         out.print("<input id='customerName' type='text' name='customerName' size='20' required='true' value=\""+subAccount.getSubAccountName()+"\">");                                        
    out.print("</td>");
out.print("</tr>");
out.print("<tr>");
    out.print("<td style=' font-weight: bold; width:30%; text-align: left'>");             
        out.print("Address:");                
    out.print("</td>");
    out.print("<td style='width: 70%; text-align: left'>");
        out.print("<input id='customerAddress' name='customerAddress' type='text' size='75' value='"+subAccount.getAddress()+"'>");                
    out.print("</td>");
out.print("</tr>");
out.print("<tr>");
    out.print("<td style=' font-weight: bold; width:30%; text-align: left'>");             
        out.print("Phone No:");
    out.print("</td>");
    out.print("<td style='width: 70%; text-align: left'>");
        out.print("<input id='phone' name='phone' type='text' size='20' value='"+subAccount.getPhoneNo()+"' onblur='checkName()'>");
out.print("</td>");
out.print("</tr>");
          
%>








