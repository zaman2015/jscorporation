<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />
    <td style="width: 65%; vertical-align: top " >
        <b>SALES INVOICE</b><br>
        As on :<%= new SimpleDateFormat("dd-MM-yy").format(new Date()) %>
        ${salesInvoice}

    </td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />
