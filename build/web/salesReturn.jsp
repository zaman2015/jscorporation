<%-- 
    Document   : salesReturn
    Created on : Feb 15, 2015, 6:11:06 PM
    Author     : USER
--%>

<%@page import="sql.InventoryPresentation"%>

<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />
<form action="salesReturnInput.jsp" method="post">
    <td style="width: 65%; vertical-align: top " >
        <%
            String salesInvoice = InventoryPresentation.getSalesInvoiceString();          
        %>
        
        <%= salesInvoice %>
        Invoice Number:<input type="number" name="invoiceNumber" required min="0"><input type="submit" value="ENTER" class="btn btn-success">
</form>
    </td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />

