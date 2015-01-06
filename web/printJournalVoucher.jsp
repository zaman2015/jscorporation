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
    String debitAccount = request.getParameter("debitAccount");
    String subAccountDebit = request.getParameter("subAccountDebit");
    String debitAmount = request.getParameter("debitAmount");
    
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
                    <span class="dv"><u>JOURNAL VOUCHER</u></span>
                </td>
                <td style=" text-align: right; width: 10%">Date:</td>
                <td style=" text-align: right; width: 10%">
                    <input id="date" type="text" readonly size="15" name="date" value='<%= new SimpleDateFormat("yyy-MM-dd").format(new Date()) %>'>
                </td>

            </tr>

        </table>
        <br>
        <table border="1" style="width: 100%; " class="inventory">
            <tr>
                <th style=" width: 60%; font-weight: bold; text-align: center">Particular</th>
                <th style=" width: 20%; font-weight: bold; text-align: center">Sub Ledger</th>
                <th style=" width: 20%; font-weight: bold; text-align: center">Taka</th>
            </tr>
            <tr>
                <td style=" width: 60%; text-align: left">Debit:
                    
                    <%=debitAccount%>
                    
                   
                </td>
                <td style=" width: 20%; height: 40px">
                    <%=subAccountDebit%>
                </td>
                <td style=" width: 20%">
                    <%=debitAmount%>
                </td>
                
            </tr>
            
            <tr>
                <td style=" width: 60%; text-align: left; height: 40px">
                    Credit:
                       <%=creditAccount%>
                   
                    
                </td>
                <td style=" width: 20%">
                    <%=subAccountCredit%>
                </td>
                <td style=" width: 20%">
                    <%=creditAmount%>
                </td>
                
            </tr>
            
            <tr>
                <td style=" width: 60%; text-align: left;" colspan="3">
                  In Word:
                </td>
                
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
