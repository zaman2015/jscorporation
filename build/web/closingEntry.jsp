<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />
    <td style="width: 65%; vertical-align: top " >
        <div class="span">CLOSING ENTRY</div>
        As on :<%= new SimpleDateFormat("dd-MM-yy").format(new Date()) %>
        
        ${closingJournals}
        </form>

    </td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />
