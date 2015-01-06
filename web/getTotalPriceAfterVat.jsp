<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    double totalPriceAfterVat = Double.parseDouble(session.getAttribute("totalPriceAfterVat").toString());
        
                      
    out.print(totalPriceAfterVat);

    
    
%>