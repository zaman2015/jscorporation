<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />
<td style="width: 65%; vertical-align: top " >
        
    <form id="subVoucherID" action="SubLedgerServlet" method="post">

        
            <table style="width: 80%">
                <tr>
                    <td style="width: 70%"><input type="text" size="25" readonly value='${debitAccount1}'></td>
                    <td style="width: 15%">DEBIT</td>
                    <td style="width: 15%"><input type="text" readonly value="${debitAmount}"></td>
                </tr>
                <tr>
                    <td style="width: 70%"><input type="text" size="25" readonly value='${creditAccount1}'></td>
                    <td style="width: 15%">CREDIT</td>
                    <td style="width: 15%"><input type="text" readonly value="${creditAmount}"></td>
                </tr>
            </table>
                <br>
                <br>
                
                
                <b>Select Sub Account's Name & Press ENTER</b><br><br>
                <table style="width:60%">
                    <tr>
                        <td style="width: 70%; text-align: right">Sub Account's Name:</td>
                        <td style="width: 30%; text-align: left"><select id="subAccountID" required name="ledgerName" >${subAccountList}</select></td>
                    </tr>
                    <tr>
                        <td style="width: 70%; text-align: right">Particular:</td>
                        <td style="width: 30%; text-align: left"><textarea id="subAcctPrtID" name="subLedgerParticular" rows="3" cols="20" >${particular}</textarea></td>
                    </tr>
                    <tr>
                        <td style="width: 70%; text-align: right">Quantity:</td>
                        <td style="width: 30%; text-align: left"><input id="subAcctQntID" type="number" required min="0.00" step="any" name="quantity"></td>      
                    </tr>
                    <tr>
                        <td style="width: 70%; text-align: right">Measurement:</td>
                        <td style="width: 30%; text-align: left">
                            <select id="subAcctMesrID" required name="measurement">
                                ${measurementlist}
                            </select></td>
                    </tr>
                    <tr>
                        <td style="width: 70%; text-align: right">Rate:</td>
                        <td style="width: 30%; text-align: left"><input id="subAcctRateID" type="number" required min="0.00" step="any" name="rate"></td>      
                    </tr>
                    <tr>
                        <td style="width: 70%; text-align: right"></td>
                        <td style="width: 30%; text-align: left"><input id="subAcctSubmitBT" type="submit" value="SAVE"></td>      
                    </tr>
                    
                </table>
                          
        </form>
</td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />
