<%-- 
    Document   : nameCheck
    Created on : Feb 1, 2015, 11:17:42 PM
    Author     : BUDGET003
--%>

<%@page import="java.util.Iterator"%>
<%@page import="sql.SQLHtml"%>
<%@page import="bisiness.SubAccount"%>
<%@page import="java.util.ArrayList"%>
<%@page import="data.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String mainAccountName = request.getParameter("mainAccountName");
    
    ArrayList<SubAccount> subAccounts= Database.selectSubAccountName(mainAccountName);
    
        
    Iterator iterator = subAccounts.iterator();
    
    out.print("<select id='subAccount' name='subAccount'>");
    
    while(iterator.hasNext())
    {
        
        SubAccount subAccount = (SubAccount) iterator.next();
        String subAccountName = subAccount.getSubAccountName();
        out.println("<option value=\""+subAccountName+"\">"+subAccountName);
        
        
    }
    out.print("</select>");
    
    
%>