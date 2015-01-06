<%@page import="bisiness.SubAccount"%>
<%@page import="data.Database"%>
<%@page import="sql.InventoryPresentation"%>
<%@page import="data.InventoryData"%>
<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />

<%
    int salesInvoice = Integer.parseInt( request.getParameter("invoiceNumber"));
    ArrayList salesList= InventoryData.getSalesInvoice(salesInvoice);
    String salesReturnStr = InventoryPresentation.getSalesReturnStr(salesInvoice);
    String customerName = InventoryData.getCustomer(salesInvoice, "sales");
    SubAccount subAccount = Database.selectSubAccountNameBySub(customerName);
    String address = subAccount.getAddress();
    String phoneNO = subAccount.getPhoneNo();
    

%>

        
<td style="width: 65%; vertical-align: top  " >
       
        
    <form name="inventorySales"  id="inventorySalesID" action="InsertInventoryServlet" method="post">
        <input id='salesOrPurchae' name='salesOrPurchae' type='hidden' value='Sales Return'>
        <input id="memo" type="hidden"   name="memo" value="">
        <table style="width: 100%; background: #336ca6;" >
            <tr>
                <td style=" text-align: left; width: 15%">Transection ID:</td>
                <td style=" text-align: left; width: 10%">
                    <input id="trID" type="text" readonly size="15" name="transectionID">
                </td>
                <td style="width: 45%">

                </td>
                <td style=" text-align: right; width:20%"><b>BILL/CASH MEMO NO:</b></td>
                <td style="text-align: right; width: 10%">
                    <input id="invoieceNo" type="text" readonly size="15" name="invoieceNo" value="<%=salesInvoice %>">
                </td>

            </tr>
            <tr>
                <td style=" text-align: left; width: 15%">Order No:</td>
                <td style=" text-align: left; width: 10%">
                    <input id="subLdrID" type="text" readonly size="15" name="subLedgerID">
                </td>
                <td style="font-size: 125%; color: olivedrab; width: 45%">
                    <u>SALES RETURN</u>
                </td>
                <td style=" text-align: right; width: 20%">Date:</td>
                <td style=" text-align: right; width: 10%">
                    <input id="date" type="text" readonly size="15" name="date" value='<%= new SimpleDateFormat("yyy-MM-dd").format(new Date()) %>'>
                </td>

            </tr>

        </table>
                <br>
                <table id='infoTable' style='width:100%; background: #336ca6;'>
             <tr>
                <td style='font-weight: bold; width:30%; text-align: left'>
                    Name:                    
                </td>
                <td style='width: 70%; text-align: left'>
                    <input id='customerName' type='text' size='20' name='customerName' required value="<%=customerName %>" >
                                          
                </td>
             </tr>
             <tr>
                <td style='font-weight: bold; width:30%; text-align: left'>
                    Address:                    
                </td>
                <td style='width: 70%; text-align: left'>
                    <input id='customerAddress' name='customerAddress' type='text' size='75' value="<%=address %> " >
                                           
                </td>
             </tr>
             <tr>
                <td style='font-weight: bold; width:30%; text-align: left'>
                    Phone No:                    
                </td>
                <td style='width: 70%; text-align: left'>
                    <input id='phone' name='phone' type='text' size='20' value="<%=phoneNO %>">
                                           
                </td>
             </tr>
        </table>
                <br>
        
        <%=salesReturnStr %>
        
        <br>            
        <table id='tb'  style="width: 100%; background: #336ca6;" >
            
            <tr>
                <td style=" width: 25%; font-weight: bold; text-align: center">Product Name</td>
                <td style=" width: 25%; font-weight: bold; text-align: center">Quantity</td>
                <td style=" width: 25%; font-weight: bold; text-align: center">Measurement</td>
                <td style=" width: 25%; font-weight: bold; text-align: center">Rate</td>
                              
            </tr>
            <tr id="tr">
                <td style=" width: 20%; font-weight: bold; text-align: center"><select id="product" required name='product' onchange='getMeasurementUnit(this.value)' >${productlist}</select></td>
                <td id='msrUnitTD' style= ' width: 20%; font-weight: bold; text-align: center'><input id='unit' type='number' required name='unit' min='0.00' step="any" value=''></td>
                <td id='measurementTD' style=" width: 20%; font-weight: bold; text-align: center"><input id='measurementUnit' required type='text' name='measurementUnit' readonly size='15' value=''></td>
                <td style=" width: 20%; font-weight: bold; text-align: center"><input id="rate" type="number" required name="rate" min="0.00" step="any" onblur="insertIntoInventoryTable()"></td>
                <td style=" width: 20%; font-weight: bold; text-align: center"><input id="btn" type="button" name="btn" value="ENTER" onclick="impectTotal()" class=" btn btn-success" ></td>
            </tr>

        </table>
        
                    <br>
                    
                    <table style="width: 100%" >
                        <tr>
                            <td style="vertical-align: top; width: 50%">
                                <table id='detailsTable' class="purcchase" style="width: 100%">
                                    <thead>
                                        <tr>
                                            <th>Product Name</th>
                                            <th>Unit</th>
                                            <th>Measurement</th>
                                            <th>Rate</th>
                                            <th>Total</th>

                                        </tr>
                                    </thead>
                                </table>
                            </td>
                            <td style="vertical-align: top; width: 50%">
                                <table  style="width:100%; text-align: right" class="calculation" > 
                                    <tr>
                                        
                                        <td style="text-align:right">
                                            <b>Total price:</b>                    
                                        </td>
                                        <td id='totalTD' style="text-align:right">
                                            <textarea id='totalID' readonly required name='salesTotal' rows="1" cols="10"  style="background-color:red; font-size: 18pt; color: blue; font-weight: bolder  "></textarea>                    
                                        </td>
                                    </tr>
                                    <tr>
                                        
                                        <td style="text-align:right">
                                           Less:Discount:                    
                                        </td>
                                        <td style="text-align:right">
                                            <input id="discountlID" type="number" required name="discountSales" min="0.00" step="any" onblur="showGrandTotal(this.value)">                    
                                        </td>
                                    </tr>
                                    <tr>
                                        
                                        <td style="text-align:right">
                                            <b>Total Price After Discount:</b>                    
                                        </td>
                                        <td id='grandTotalTD' style="text-align:right">
                                            <input id='gTotalID' type='number' readonly required name='grandTotal' min='0.00' step="any">                    
                                        </td>
                                    </tr>

                                    <tr>
                                        <td style="text-align:right">
                                            <input id='vatRateID' type='number' required name='vatRat' value='' min='0.00' step="any" onblur='calculateVat(this.value)' >% VAT:                    
                                        </td>
                                        
                                        <td  id='vatAmt' style="text-align:right">
                                            <input id='vatAmountID' type='number' readonly required name='vatAmount' min='0.00' step="any" value='' onblur='getTotalPriceAfterVat(this.value)'  >                    
                                        </td>
                                    </tr>

                                    <tr>
                                        
                                        <td style="text-align:right">
                                            <b>Grand Total:</b>                    
                                        </td>
                                        <td id='afterVat' style="text-align:right">
                                            <textarea id='afterDiscInpt' readonly required name='totalAfterDisnt' rows="1" cols="10" style="background-color: #6b8f1a; font-size: 18pt; color: white; font-weight: bolder"></textarea>                    
                                        </td>
                                    </tr>
                                    <tr>
                                        
                                        <td style="text-align:right">
                                            <b>Paid:</b>                    
                                        </td>
                                        <td style="text-align:right">
                                            <input id='paidID' type='number' required name='paidTotal' min='0.00' step="any" onblur='calculateSalesDue(this.value)'>                    
                                        </td>
                                    </tr>
                                    <tr>
                                        
                                        <td style="text-align:right">
                                            <b>Due:</b>                    
                                        </td>
                                        <td  id='due' style="text-align:right">
                                            <input id='dueID' type='number' readonly required name='dueTotal' min='0.00' step="any" value="">                    
                                        </td>
                                    </tr>


                                     <tr>
                                        
                                        <td>
                                            <input id="creditAccountIDinDebit" type="hidden" name="creditAccount" value="Cash A/C"><input id="creditAmountIDinDebit" type="hidden" name="creditAmount" value="0.00">
                                        </td>
                                        <td>
                                            <input id="debitSaveBT" type="submit" value="SAVE">                   
                                        </td>
                                     </tr>
                                </table>
                            </td>
                            
                        </tr>
                    </table>
        
    </form>
                <table style="width: 100%">
                    <tr>
                        <td style="width: 80%"></td><td style="width: 20%"><input id="cancel" type="button" onclick='cancel()' value="CANCEL"></td>
                    </tr>
                </table>
                
</td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />



