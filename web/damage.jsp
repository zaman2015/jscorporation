<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />


        
<td style="width: 65%; vertical-align: top  " >
       
        
    <form name="inventorySales"  id="inventorySalesID" action="InsertInventoryServlet" method="post">
        <input id='salesOrPurchae' name='salesOrPurchae' type='hidden' value='damage'>
        <input id="memo" type="hidden"   name="memo" value="">
        <input id="invoieceNo" type="hidden" readonly size="15" name="invoieceNo" value=0>
        <input id="customerName" type="hidden" readonly size="15" name="customerName" value="Not applicable">
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
                    <input id="drVrNo" type="text" readonly size="15" name="voucherNo">
                </td>

            </tr>
            <tr>
                <td style=" text-align: left; width: 15%">Order No:</td>
                <td style=" text-align: left; width: 10%">
                    <input id="subLdrID" type="text" readonly size="15" name="subLedgerID">
                </td>
                <td style="font-size: 125%; color: olivedrab; width: 45%">
                    <u>Damage or Other Inventory Loss</u>
                </td>
                <td style=" text-align: right; width: 20%">Date:</td>
                <td style=" text-align: right; width: 10%">
                    <input id="date" type="text" readonly size="15" name="date" value='<%= new SimpleDateFormat("yyy-MM-dd").format(new Date()) %>'>
                </td>

            </tr>

        </table>
        <br>
        
              
        <table id='tb'  style="width: 100%; background: #336ca6;" >
            <tr>
                <td style=" width: 25%; font-weight: bold; text-align: center">Product Name</td>
                <td style=" width: 25%; font-weight: bold; text-align: center">Quantity</td>
                <td style=" width: 25%; font-weight: bold; text-align: center">Measurement</td>
                <td style=" width: 25%; font-weight: bold; text-align: center">Rate</td>
                              
            </tr>
            <tr id="tr">
                <td style=" width: 20%; font-weight: bold; text-align: center"><select id="product" required name='product' onchange='getMeasurementUnit(this.value)' onblur='getSaleableQuantity()' >${productlist}</select></td>
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
                                            <b>Total Loss:</b>                    
                                        </td>
                                        <td id='totalTD' style="text-align:right">
                                            <textarea id='totalID' readonly required name='salesTotal' rows="1" cols="10"  style="background-color:red; font-size: 18pt; color: blue; font-weight: bolder  "></textarea>                    
                                        </td>
                                    </tr>
                                    
                                        
                                        
                                                            
                                        
                                        
                                            <input id="discountlID" type="hidden" required name="discountSales" min="0.00" step="any" onblur="showGrandTotal(this.value)">                    
                                      
                                            <input id='gTotalID' type='hidden' readonly required name='grandTotal' min='0.00' step="any">                    
                                       
                                            <input id='vatRateID' type='hidden' required name='vatRat' value='' min='0.00' step="any" onblur='calculateVat(this.value)' >                  
                                        
                                            <input id='vatAmountID' type='hidden' readonly required name='vatAmount' min='0.00' step="any" value='' onblur='getTotalPriceAfterVat(this.value)'  >                    
                                       
                                        
                                            <input type="hidden" id='afterDiscInpt' readonly required name='totalAfterDisnt' rows="1" cols="10" style="background-color: #6b8f1a; font-size: 18pt; color: white; font-weight: bolder">                   
                                       
                                            <input id='paidID' type='hidden' required name='paidTotal' min='0.00' step="any" onblur='calculateSalesDue(this.value)'>                    
                                       
                                    
                                            <input id='dueID' type='hidden' readonly required name='dueTotal' min='0.00' step="any" value="">                    
                                       


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



