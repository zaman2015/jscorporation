<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />
<td style="width: 65%; vertical-align: top " >
        
    <form id="journalVoucher" action="journalServlet" method="post">
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
                    <span class="dv"><u>JOURNAL VOUCHER</u></span>
                </th>
                <th style=" text-align: right; width: 10%">Date:</th>
                <th style=" text-align: right; width: 10%">
                    <input id="date" type="text" readonly size="15" name="date" value='<%= new SimpleDateFormat("yyy-MM-dd").format(new Date()) %>'>
                </th>

            </tr>

        </table>
        <br>
        
                    
        <table border="1" style="width: 100%" class="inventory">
            <tr>
                <th style=" width: 60%; font-weight: bold; text-align: center">Particular</th>
                <th style=" width: 20%; font-weight: bold; text-align: center">Sub Ledger</th>
                <th style=" width: 20%; font-weight: bold; text-align: center">Taka</th>
            </tr>
            <tr>
                <td style=" width: 60%; text-align: left">Debit:
                    <select id="debitAccountID" name="debitAccount" required onchange="goToSubLedger(this.value)">
                        ${accountlist}  
                    </select>
                   
                </td>
                <td style=" width: 20%">
                    <select id='subAccount' name='subAccountDebit'></select>
                </td>
                <td style=" width: 20%">
                    <input id="debitAmountInDebit" name="debitAmount" type="number"  required min="0.00" step="any">
                </td>
                
            </tr>
            
            <tr>
                <td style=" width: 60%; text-align: left">
                    Credit:<select id="creditAccountID" name="creditAccount" required onchange="goToSubLedgerCredit(this.value)">
                        ${accountlist}  
                    </select>
                    
                </td>
                <td style=" width: 20%">
                    <select id='subAccountCredit' name='subAccountCredit'></select>
                </td>
                <td style=" width: 20%">
                    <input id="crditAmountInDebit" name="creditAmount" type="number" required min="0.00" step="any">
                </td>
                
            </tr>
            <tr>
                <td style=" width: 60%; text-align: left">
                   Total: 
                </td>
                <td style=" width: 20%">
                    
                </td>
                <td style=" width: 20%">
                    
                </td>
                
            </tr>

        </table>
                    <br>
                    
                    <table style="width: 100%" >
            <tr>
                
                <th style="width: 20%">Particular:</th>
                <th style="width: 80%"><textarea cols="70" rows="2" name="particular" required=""></textarea></th>
                
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
                    
                </td>
                <td>
                    
                </td>
                <td>
                    <input id="debitSaveBT" type="submit" value="SAVE">                   
                </td>
             </tr>
             
        </table>
                    <input type="hidden" name="url" value="journal">
    </form>
</td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />
