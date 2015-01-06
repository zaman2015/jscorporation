<%--
    Document   : home
    Created on : Jan 6, 2015, 2:44:40 AM
    Author     : BUDGET003
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="myStyle.css">
        <title>Home Page</title>
        
    </head>
    <body background="" >




        <table width="100%">
            <tr>
                <td width="100%" colspan="3" align="center"><font size="3" color="blue">WELCOME</font><font size="8" color="red">.</font><font size="8" color="green">.</font><font size="8" color="yellow">.</font><br></td>
            </tr>
            <tr>
                <td width="15%" height="70%">
                    
                </td>
                <td width="70%" height="70%" align="center"><img src="title.png">130, Hazaribag Tannery Area, Dhaka-1209, Bangladesh.<br>Tel:+88-2-9664301 Mob:+88-01755599270, +88-1712229773 E-mail: sms@dhaka.net<br><br></td>
                <td width="15%" height="70%">
                    
                </td>
            </tr>
            <tr>
                <td width="15%" height="70%">
                    
                </td>
                <td width="70%" height="70%" align="center">
                    <table width="80%">

                    <tr>
                        <td>
                           
                        </td>
                        <td align="left">
                            <ul class="accordion">

                    <li id="one" class="files"><a href="#one">Enter Transection<span>1</span></a>

                        <ul class="sub-menu">
                            <li><a href="debitVoucher.jsp"><em>01</em>Debit Voucher<span>1</span></a></li>
                            <li><a href="creditVoucher.jsp"><em>02</em>Credit Voucher<span>2</span></a></li>
                            <li><a href="journalVoucher.jsp"><em>03</em>Journal Voucher<span>3</span></a></li>
                            <li><a href="payment.jsp"><em>04</em>Payments<span>4</span></a></li>
                            <li><a href="receipt.jsp"><em>05</em>Receipts<span>5</span></a></li>
                            <li><a href="ClosingEntryServlet"><em>06</em>Closing Entry<span>6</span></a></li>
                        </ul>

                    </li>
                    <li id="two" class="sign"><a href="#two">Sales Purchase<span>2</span></a>
                        <ul class="sub-menu">
                            <li><a href="inventorySales.jsp"><em>01</em>Sales<span>1</span></a></li>
                            <li><a href="inventoryPurchase.jsp"><em>02</em>Purchase<span>2</span></a></li>
                            <li><a href="return.jsp"><em>03</em>Return<span>3</span></a></li>
                            <li><a href="damage.jsp"><em>04</em>Damage and Other Loss<span>4</span></a></li>
                            <li><a href=""><em>05</em><span>5</span></a></li>
                            <li><a href=""><em>06</em><span>6</span></a></li>
                        </ul>
                    </li>
                    
                    <li id="three" class="sign"><a href="#three">View Inventory<span>3</span></a>
                        <ul class="sub-menu">
                            
                            <li><a href="ClosingInventoryServlet"><em>01</em>View Closing Inventory<span>1</span></a></li>
                            <li><a href="selectProduct.jsp"><em>02</em>View Inventory By Product<span>2</span></a></li>
                            <li><a href="PurchaseInvoiceServlet"><em>03</em>Purchase Invoice<span>3</span></a></li>
                            <li><a href="SalesInvoiceServlet"><em>04</em>Sales Invoices<span>4</span></a></li>
                            <li><a href=""><em>05</em><span>5</span></a></li>
                            <li><a href=""><em>06</em><span>6</span></a></li>
                        </ul>
                    </li>
                    

                    <li id="four" class="mail"><a href="#four">View Reports<span>4</span></a>
                        <ul class="sub-menu">

                            <li><a href="accountBalanceQuery.jsp"><em>01</em>Accounts' Balance<span>1</span></a></li>
                            <li><a href="TrialBalanceServlet"><em>02</em>Trial Balance<span>2</span></a></li>
                            <li><a href="ProfitAndLossServlet"><em>03</em>Profit & Loss Account<span>3</span></a></li>
                            <li><a href="BalanceSheetServlet"><em>04</em>Balance Sheet<span>4</span></a></li>
                            <li><a href="subLedgerMainAccount.jsp"><em>05</em>Sub Ledger Balance<span>5</span></a></li>
                            <li><a href=""><em>06</em><span>6</span></a></li>
                        </ul>
                    </li>

                    <li id="five" class="cloud"><a href="#five">Edit<span>5</span></a>
                        <ul class="sub-menu">

                            <li><a href="editTransectionServlet"><em>01</em>Edit Transection<span>1</span></a></li>
                            <li><a href="editAccountNameSrvlet"><em>02</em>Edit Account's Name<span>2</span></a></li>
                            <li><a href="createNewAccount.jsp"><em>03</em>Create New Account<span>3</span></a></li>
                            <li><a href="createNewUser.jsp"><em>04</em>Create New User<span>4</span></a></li>
                            <li><a href="newProduct.jsp"><em>05</em>Insert New Product<span>5</span></a></li>
                            <li><a href=""><em>06</em><span>6</span></a></li>
                        </ul>
                    </li>
                    
                    <li id="six" class="sign"><a href="#six">Sign Out</a></li>

                </ul> </td>
                    </tr>


                    </table></td>
                    <td width="15%" height="70%">
                        
                    </td>
            </tr>
            <tr>
                <td style="text-align: center; width: 100%; height: 15%" colspan="3"><img src="mainlogo.png" ></td>
            </tr>
        </table>

    </body>
</html>
