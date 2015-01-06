<%-- 
    Document   : inventoryByproductName
    Created on : Jan 31, 2015, 9:55:04 AM
    Author     : Ariba
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="myStyle.css">
        <title>JSP Page</title>
    </head>
    <body>
        <table style="width: 100%">
            <tr>
                <td style="text-align: center">
                    <h1 style="color: red">M/S J. S. CORPORATION</h1>130, Hazaribag Tannery Area, Dhaka-1209, Bangladesh.<br>Tel:+88-2-9664301 Mob:+88-01755599270, +88-1712229773 E-mail: sms@dhaka.net
                </td>
            </tr>
            <tr>
                <td style="text-align: center">
                    <br><b>Store Ledger Account</b> (Weighted Average)<br>
                    <b>${productNameSession}</b>
                    
                    <table class="inventory" style="width: 100%">
                        ${productTablByName}

                    </table>
                </td>
            </tr>
        </table>
                 
    </body>
</html>
