<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />
    <td style="width: 65%; vertical-align: top " >
        <form action="BalanceQueryServlet" method="post">
            <table style="width: 100%" class="inventory">
                <tr>
                    <th colspan="3">Select Account's Name and Press ENTER</th>
                </tr>
                <tr>
                    <td>Account's Name:</td>
                    <td><select id="balanceQueryID" name="ledgerName" required >${accountlist}</select></td>
                    <td><input id='balanceQueryBTID' type="submit" value="ENTER"></td>
                </tr>
            </table>
        </form>

    </td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />
