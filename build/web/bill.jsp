<%-- 
    Document   : bill
    Created on : Feb 8, 2015, 11:16:38 PM
    Author     : BUDGET003
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="myStyle.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table  style="width: 100%">
            <tr>
                <td colspan="5" style="text-align: center">
                    <font size="8" color="red">M/S J. S. CORPORATION</font><br>130, Hazaribag Tannery Area, Dhaka-1209, Bangladesh.<br>Tel:+88-2-9664301 Mob:+88-01755599270, +88-1712229773 E-mail: sms@dhaka.net<br><br><br><h2>Bill/Cash Memo</h2>
                </td>
            </tr>
            <tr>
                <td style="width: 20%">
                    Name:
                </td>
                <td style="width: 60%">
                   ${name}
                </td>
                
                <td style="width: 10%; text-align: right">
                    Number:
                </td>
                <td style="width: 10%; text-align: left">
                    
                    00${invoiceNo}
                </td>
            </tr>
            <tr>
                <td>
                    Address:
                </td>
                <td>
                    ${address}
                </td>
                
                <td style="width: 10%; text-align: right">
                    Date:
                </td>
                <td style="width: 10%; text-align: left">
                    <%= new SimpleDateFormat("dd-MM-yy").format(new Date()) %>
                </td>
            </tr>
            <tr>
                <td>
                    Phone Number:
                </td>
                <td>
                    ${customerPhone}
                </td>
                <td>
                    
                </td>
                
                <td>
                    
                </td>
            </tr>
        </table>
        
        
        ${billOrChalan}
    
     
        
        <br><br><br><br>
        
        <table style="width: 100%; text-align: center; ">
            
            <tr>
                
                <td style="width: 40%; text-align: center">
                    Signature of Customer<br>
                    (Received the above goods in good condition)
                </td>
                <td style="width: 25%; text-align: center">
                    
                </td>
                
                <td style="width: 35%; text-align: center">
                    Signature of Authorised <br>
                    (On behalf of M/S J.S. Corporation)
                </td>
            </tr>
        </table>
        
    </body>
</html>
