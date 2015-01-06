<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />
    <td style="width: 65%; vertical-align: top " >
        <form action="InvtByPrdNameServlet" method="post">
            <table style="width: 50%" border='1'>
                <tr>
                    <td colspan="3"><b>Select Product Name and Press ENTER</b></td>
                </tr>
                <tr>
                    <td>Product Name:</td>
                    <td><select id="balanceQueryID" name="productName" required="true" >${productlist}</select></td>
                    <td><input id='balanceQueryBTID' type="submit" value="ENTER"></td>
                </tr>
            </table>
        </form>

    </td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />
