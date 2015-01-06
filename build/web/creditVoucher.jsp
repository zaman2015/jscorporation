<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />
<td style="width: 65%; vertical-align: top " >
        
    <form id="creditVoucher" action="journalServlet" method="post">
        <table style="width: 100%" class="inventory">
            <tr>
                <th style=" text-align: left; width: 15%">Transection ID:</th>
                <th style=" text-align: left; width: 10%">
                    <input id="trID" type="text" readonly size="15" name="transectionID">
                </th>
                <th style="width: 55%">

                </th>
                <th style=" text-align: right; width:10%">Voucher No:</th>
                <th style="text-align: right; width: 10%">
                    <input id="drVrNo" type="text" readonly size="15" name="voucherNo">
                </th>

            </tr>
            <tr>
                <th style=" text-align: left; width: 15%">Sub Ledger ID:</th>
                <th style=" text-align: left; width: 10%">
                    <input id="subLdrID" type="text" readonly size="15" name="subLedgerID">
                </th>
                <th style="font-size: 125%; color: olivedrab; width: 55%">
                    <span class="dv"><u>CREDIT VOUCHER</u></span>
                </th>
                <th style=" text-align: right; width: 10%">Date:</th>
                <th style=" text-align: right; width: 10%">
                    <input id="date" type="text" readonly size="15" name="date" value='<%= new SimpleDateFormat("yyy-MM-dd").format(new Date()) %>'>
                </th>

            </tr>

        </table>
        <br>
        <table border="1" style="width:100%" class="inventory">
            <tr>
                <th style=" font-weight: bold; width:20%; text-align: left">
                    CREDIT                    
                </th>
                <th style="width: 40%; text-align: left">
                    <select id="debitAccountID" name="creditAccount" required onchange="goToSubLedger(this.value)">
                        ${accountlist}  
                    </select>                   
                </th>
                <th style=" width: 40%">
                    Sub Ledger:<select id='subAccount' name='subAccountDebit'></select>
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
                <td style=" width: 80%;"><textarea id="debitTextarea" rows="8" cols="60" name="particular" required></textarea></td>
                <td style=" width: 20%;vertical-align: top; text-align: center"><input id="debitAmountInDebit" name="debitAmount" type="number" required min="0.00" step="any"></td>
            </tr>
            <tr>
                <td style=" width: 80%; font-weight: bold; text-align: center"></td>
                <td style=" width: 20%; font-weight: bold; text-align: center"></td>
            </tr>

        </table>
                    <br>
                    <br>
                    <br>
        <table  style="width: 100%;">
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
             <tr>
                <td>
                    <input id="creditAccountIDinDebit" type="hidden" name="debitAccount" value="Cash A/C">
                </td>
                <td>
                    <input id="creditAmountIDinDebit" type="hidden" name="creditAmount" value="0.00">
                </td>
                <td>
                    <input id="debitSaveBT" type="submit" value="SAVE">                   
                </td>
             </tr>
        </table>
                    <input type="hidden" name="url" value="credit">
    </form>
</td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />
