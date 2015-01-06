<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%
    double totalPrice = Double.parseDouble(session.getAttribute("totalPrice").toString());
    double vat = Double.parseDouble(request.getParameter("val"));
    double totalVat = 0.00;
    if(vat>=0)
    {
        totalVat =totalPrice*vat/100;
    }
    double totalPriceAfterVat =  totalPrice+totalVat;
    
    
    session.setAttribute("totalVat", totalVat);
    session.setAttribute("totalPriceAfterVat", totalPriceAfterVat);
        
    out.print(totalVat);
        

    
    
%>