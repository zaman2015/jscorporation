<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />
    <td style="width: 65%; vertical-align: top " >
        <div class="payment">PAYMENTS</div>
        <form action="PaymentServlet" method="post">
            <input id="typeId" type="hidden" name="type" value="payment">
            <h3 style="color: red">${successMsg}</h3>
            <span id="msg" style="color: red"></span>
            <table style="width: 100%">
                <tr>
                    <td>
                        <table style="width: 100%" class="receipt">
                            <tr>
                                <th colspan="2">Main Ledger</th>
                            </tr>
                            <tr>
                                <td>Date:</td><td><input id="date" name="date" readonly size="20" value="<%= new SimpleDateFormat("dd-MM-yy").format(new Date()) %>"></td>
                            </tr>
                            <tr>
                                <td>Account's Name:</td><td><select id="debitAccount" name="debitAccount" onchange="goToSubLedger(this.value)">${accountlist}</select></td>
                            </tr>
                            <tr>
                                <td>Particular:</td><td><input id="particular" name="particular" type="text"  size="20" value=""></td>
                            </tr>
                            <tr>
                                <td>Amount:</td><td><input id="amount" name="amount" type="number" min="0.00" step="any" value=""></td>
                            </tr>
                            <tr>
                                <td>Cash/Bank:</td><td><select id="cashOrCheque" name="cashOrCheque" style="width: 120px" ><option>--Select One--</option><option>Cash</option><option>Bank</option></select></td>
                            </tr>
                            <tr>
                                <td></td><td><input type="button" value="Cancel"><input type="submit" value="SAVE"></td>
                            </tr>
            
                        </table>
                    </td>
                    <td>
                        <table style="width: 100%" class="receipt">
                            <tr>
                                <th colspan="2">Sub Ledger</th>
                            </tr>
                            <tr>
                                <td>Date:</td><td><input id="date" name="date" readonly size="20" value="<%= new SimpleDateFormat("dd-MM-yy").format(new Date()) %>"></td>
                            </tr>

                            <tr>
                                <td>Sub Account's Name:</td><td><select id='subAccount' name='subAccount'></select></td>
                            </tr>
                            <tr>
                                <td>Particular:</td><td><input id="subParticular" name="subParticular:" type="text"  size="20" value=""></td>
                            </tr>
                            <tr>
                                <td>Amount:</td><td><input id="subAmount" name="subAmount" type="number" min="0.00" step="any" value=""></td>
                            </tr>
                            <tr>
                                <td>Cash/Bank:</td><td><select id="subCashOrCheque" name="subCashOrCheque" style="width: 120px"><option>--Select One--</option><option>Cash</option><option>Bank</option></select></td>
                            </tr>
                            <tr>
                                <td></td><td><input type="button" value="Cancel"><input type="button" value="SAVE" onclick="savePaymentData()"></td>
                            </tr>

                        </table>
                    </td>
                </tr>
            </table>
            
            </form>

    </td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />
