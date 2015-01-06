<%@ page import="java.util.*"  %>
<%@ page import="java.text.*"  %>

<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />


        
<td style="width: 65%; vertical-align: top " >
   
   
    
    <form name="inventoryPurchase"  id="inventorySalesID" action="InsertInventoryServlet" method="post">
        <input id='salesOrPurchae' name='salesOrPurchae' type='hidden' value='Purchase'>
        <input id="invoieceNo" type="hidden" readonly size="15" name="invoieceNo" value=0>
        
        <table style="width: 100%" class="purchase">
            
            <tr>
                <td style=" text-align: left; width: 15%">Memo/Invoice No:</td>
                <td  style=" text-align: left; width: 10%">
                 <input id="memo" type="text"  size="15" name="memo" value="">   
                </td>
                <td style="font-size: 125%; color: olivedrab; width: 45%">
                    <span class="span"><u>PURCHASE</u></span>
                </td>
                <td  style=" text-align: right; width: 20%">Date:</td>
                <td  style=" text-align: right; width: 10%">
                    <%= new SimpleDateFormat("yyy-MM-dd").format(new Date()) %>
                </td>

            </tr>

        </table>
        <br>
        <table  id="selectTable" style="width:100%" class="purchase">
            <tr>
                <td style=" font-weight: bold; width:30%; text-align: left">
                    Select Supplier From List                    
                </td>
                <td style="width: 70%; text-align: left">
                    <select id="customerListID" name="customerList" onchange="getInfo(this.value)" >${supplierList}</select>
                                          
                </td>
             </tr>
        </table>
        <br>
        <table class="purchase"  id='infoTable' style='width:100%'>
             <tr>
                <td style='font-weight: bold; width:30%; text-align: left'>
                    Name:                    
                </td>
                <td style='width: 70%; text-align: left'>
                    <input id='customerName' type='text' size='20' name='customerName' required value='' >
                                          
                </td>
             </tr>
             <tr>
                <td style='font-weight: bold; width:30%; text-align: left'>
                    Address:                    
                </td>
                <td style='width: 70%; text-align: left'>
                    <input id='customerAddress' name='customerAddress' type='text' size='75' value="" >
                                           
                </td>
             </tr>
             <tr>
                <td style='font-weight: bold; width:30%; text-align: left'>
                    Phone No:                    
                </td>
                <td style='width: 70%; text-align: left'>
                    <input id='phone' name='phone' type='text' size='20' value="" onblur= 'checkName()'>
                                           
                </td>
             </tr>
        </table>
        <br>            
        <table class="purchase" id='tb' style="width: 100%">
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
                <td style=" width: 20%; font-weight: bold; text-align: center"><input id="rate" type="number" required name="rate" min="0.00" step="any" onblur="insertIntoInventoryTable()"></td>
                <td style=" width: 20%; font-weight: bold; text-align: center"><input id="btn" type="button" name="btn" value="ENTER" onclick="impectTotal()" ></td>
            </tr>

        </table>
        
                    <br>
                    <table style=" width: 100%">
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
                                <table  style="width: 100%;" class="salesHead"> 
                                    <tr>
                                        <td style="width: 20%">
                                            
                                        </td>
                                        
                                        <td style="width: 60% ; text-align: right" >
                                            <b>Total price:</b>                    
                                        </td>
                                        <td id='totalTD' style="width: 20%; text-align: right">
                                            <textarea id='totalID' rows="1" cols="10"  readonly required name='salesTotal' style="background-color: red; font-size: 18pt; color: blue; font-weight: bolder"></textarea>                    
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 20%">
                                            
                                        </td>
                                        <td style="width: 60%; text-align: right">
                                            Less: Discount:                    
                                        </td>
                                        <td style="width: 20%; text-align: right">
                                            <input id="discountlID" type="number" required name="discountSales" min="0.00" step="any" onblur="showGrandTotal(this.value)">                    
                                        </td>
                                    </tr>

                                    <tr>
                                        <td style="width: 20%">
                                            
                                        </td>
                                        
                                        <td style="width: 60%; text-align: right">
                                            <b>Grand Total:</b>                    
                                        </td>
                                        <td id='grandTotalTD' style="width: 20%; text-align: right">
                                            <textarea id='gTotalID' rows="1" cols="10" readonly required name='grandTotal' style="background-color: #6b8f1a; font-size: 18pt; color: white; font-weight: bolder" ></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 20%">
                                            
                                        </td>
                                        
                                        <td style="width: 60%; text-align: right">
                                            <b>Paid:</b>                    
                                        </td>
                                        <td style="width: 20%; text-align: right">
                                            <input id='paidID' type='number' required name='paidTotal' min='0.00' step="any" onblur='calculateDue(this.value)'>                    
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 20%">
                                            
                                        </td>
                                        
                                        <td style="width: 60%; text-align: right">
                                            <b>Due:</b>                    
                                        </td>
                                        <td id='due' style="width: 20%; text-align: right">
                                            <input id='dueID' type='number' readonly required name='dueTotal' min='0.00' step="any" >                    
                                        </td>
                                    </tr>


                                     <tr>
                                         <td>
                                            
                                        </td>
                                        
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
                        <td style="width: 40%"></td><td style="width: 40%"></td><td style="width: 20%"><input id="cancel" type="button" onclick='cancelPurchase()' value="CANCEL"></td>
                    </tr>
                </table>
</td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />
