<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />
    <td style="width: 65%; vertical-align: top " >
        <div class='span'>PROFIT AND LOSS ACCOUNT</div>
        For the year ended:<%= new SimpleDateFormat("dd-MM-yy").format(new Date()) %>
        ${profitAndLossString1}
    </td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />
