<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />
    <td style="width: 65%; vertical-align: top " >
        <form action="PaymentServlet" method="post">
            <h3 style="color: red">${successMsg}</h3>
            <h3>Sub Ledger</h3>
            <table style="text-align: left">
            <tr>
                <td>Date:</td><td><input id="date" name="date" readonly="true" size="20" value="<%= new SimpleDateFormat("dd-MM-yy").format(new Date()) %>"</td>
            </tr>
            <tr>
                <td>Sub Account's Name:</td><td><select id="debitAccount" name="subAccount" value="">${subAccountList}</select></td>
            </tr>
            <tr>
                <td>Particular:</td><td><input id="particular:" name="particular" type="text"  size="20" value=""></td>
            </tr>
            <tr>
                <td>Amount:</td><td><input id="amount:" name="amount" type="number" min="0.00"  size="20" value=""></td>
            </tr>
            <tr>
                <td>Cash/Bank:</td><td><select id="debitAccount" name="cashOrCheque" style="width: 120px"  value=""><option>--Select One--</option><option>Cash</option><option>Bank</option></select></td>
            </tr>
            <tr>
                <td></td><td><input type="button" value="Cancel"><input type="submit" value="SAVE"></td>
            </tr>
            
        </table>
            </form>

    </td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />
