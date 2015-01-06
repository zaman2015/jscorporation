
<%@page import="sql.SQLHtml"%>
<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />
<%
    String mainAccountList = SQLHtml.getMainAccount();
%>

    <td style="width: 65%; vertical-align: top " >
        <form id='closingID' action="SubLedgerBalaceServlet" method="post">
            <table style="width: 100%" class='inventory'>
                <tr>
                    <th colspan="3">Insert Main Account's Name and Press ENTER</th>
                </tr>
                <tr>
                    <td>Main Account's Name:</td>
                    <td><select id='mainID' required name="mainAccountName" ><%=mainAccountList %></select></td>
                    <td><input id='mainBTID' type="submit" value="ENTER"></td>
                </tr>
            </table>
        </form>

    </td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />
