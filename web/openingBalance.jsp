<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />


        
<td style="width: 65%; vertical-align: top " >
   
   
    
    <form id="inventorySalesID" action="OpeningInvSetServlet" method="post">
        
        <input id='salesOrPurchae' name='salesOrPurchae' type='hidden' value='openingBalance'>
        <input id="customerName" name="customerName" type='hidden' value="Not Applicable"  >
        <input id="memo" name='memo' type='hidden' value='0'>
        <input id="particularOpen" name="particularOpen" type='hidden' value="Opening Balance"  >
                   
        <table class="customer" id='tb' border="1" style="width: 100%">
            <tr>
                <td style=" width: 20%; font-weight: bold; text-align: center">Product Name</td>
                <td style=" width: 20%; font-weight: bold; text-align: center">Quantity</td>
                <td style=" width: 20%; font-weight: bold; text-align: center">Measurement</td>
                <td style=" width: 20%; font-weight: bold; text-align: center">Rate</td>
                <td style=" width: 20%; font-weight: bold; text-align: center"></td>
                              
            </tr>
            <tr id="tr">
                <td style=" width: 20%; font-weight: bold; text-align: center"><select id="product" required name='product' onchange='getMeasurementUnit(this.value)'>${productlist}</select></td>
                <td style=" width: 20%; font-weight: bold; text-align: center"><input id="unit" type="number" required name="unit" min="0.00" step="any"></td>
                <td id='measurementTD' style=" width: 20%; font-weight: bold; text-align: center"><input id='measurementUnit' required type='text' name='measurementUnit' readonly size='15'></td>
                <td style=" width: 20%; font-weight: bold; text-align: center"><input id="rate" type="number" required name="rate" min="0.00" step="any"></td>
                <td><input id="debitSaveBT" type="submit" value="SAVE"></td> 
                
            </tr>

        </table>
        
                    
    </form>
</td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />