<%-- 
    Document   : printDebitVoucher
    Created on : Jan 4, 2015, 11:14:25 AM
    Author     : kakon
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String creditAccount = request.getParameter("creditAccount");
    String subAccountCredit = request.getParameter("subAccountCredit");
    String particular = request.getParameter("particular");
    String creditAmount = request.getParameter("creditAmount");
    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Credit Voucher</title>
    </head>
    <body>
        <div>
            <table  style="width: 100%">
            <tr>
                <td colspan="5" style="text-align: center">
                    <font size="8" color="red">M/S J. S. CORPORATION</font><br>130, Hazaribag Tannery Area, Dhaka-1209, Bangladesh.<br>Tel:+88-2-9664301 Mob:+88-01755599270, +88-1712229773 E-mail: sms@dhaka.net
                </td>
            </tr>
            </table>
            <table style="width: 100%" class="dvHead">
            <tr>
                <td style=" text-align: left; width: 15%">Transection ID:</td>
                <td style=" text-align: left; width: 10%">
                    <input id="trID" type="text" readonly size="15" name="transectionID">
                </td>
                <td style="width: 55%">

                </td>
                <td style=" text-align: right; width:10%">Voucher No:</td>
                <td style="text-align: right; width: 10%">
                    <input id="drVrNo" type="text" readonly size="15" name="voucherNo">
                </td>

            </tr>
            <tr>
                <td style=" text-align: left; width: 15%">Sub Ledger ID:</td>
                <td style=" text-align: left; width: 10%">
                    <input id="subLdrID" type="text" readonly size="15" name="subLedgerID">
                </td>
                <td style="font-size: 125%; color: olivedrab; width: 55%; text-align: center">
                    <span class="dv"><u>CREDIT VOUCHER</u></span>
                </td>
                <td style=" text-align: right; width: 10%">Date:</td>
                <td style=" text-align: right; width: 10%">
                    <input id="date" type="text" readonly size="15" name="date" value='<%= new SimpleDateFormat("yyy-MM-dd").format(new Date()) %>'>
                </td>

            </tr>

        </table>
        <br>
        <table border="1" style="width:100%" class="inventory">
            <tr>
                <th style=" font-weight: bold; width:20%; text-align: left">
                    DEBIT                    
                </th>
                <th style="width: 40%; text-align: left">
                    <%=creditAccount%>
                          
                                     
                </th>
                 <th style=" width: 40%; text-align: left">
                    Sub Ledger:<%=subAccountCredit%>
                </th>
             </tr>
        </table>
        <br>            
        <table border="1" style="width: 100%" class="inventory">
            <tr>
                <th style=" width: 80%; font-weight: bold; text-align: center">Particular</th>
                <th style=" width: 20%; font-weight: bold; text-align: center">Taka</th>
            </tr>
            <tr>
                <td style=" width: 80%; text-align:center; height: 200px; vertical-align: top"><%=particular %></td>
                <td style=" width: 20%;vertical-align: top; text-align: center; height: 200px"><%=creditAmount%></td>
            </tr>
            <tr>
                <td style=" width: 80%; font-weight: bold; text-align: left">In Word:</td>
                <td style=" width: 20%; font-weight: bold; text-align: center"><%=creditAmount%></td>
            </tr>

        </table>
                    <br>
                    <br>
                    <br>
        <table  style="width: 100%; text-align: center">
            <tr>
                <td>
                    <b><u>Prepared By</u></b>                    
                </td>
                <td>
                    <b><u>Accounts Officer</u></b>                    
                </td>
                <td>
                    <b><u>Proprietor</u></b>                    
                </td>
             </tr>
             
        </table>
        </div>
    </body>
</html>
