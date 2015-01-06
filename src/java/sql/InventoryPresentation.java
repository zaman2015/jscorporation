/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import bisiness.DiscountVat;
import bisiness.Inventory;
import bisiness.Invoice;
import bisiness.Product;
import data.InventoryData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author Dhaka
 */
public class InventoryPresentation {
    
    public static String getClosingBalanceOfAll(ArrayList<Product> productList) throws SQLException
{
        StringBuffer htmlRows = new StringBuffer();
        Iterator productIterator = productList.iterator();

        htmlRows.append("<tr>");
        htmlRows.append("<th style='width: 20%; text-align: center; font-weight: bolder'>Product Name</th><th style='width: 20%; text-align: center; font-weight: bolder'>Quantity</th><th style='width: 20%; text-align: center; font-weight: bolder'>Measurement Unit</th><th style='width: 20%; text-align: center; font-weight: bolder'>Rate</th><th style='width: 20%; text-align: center; font-weight: bolder'>Total</th>");
        htmlRows.append("</tr>");
        
        double totalBalance =0.00;
        while(productIterator.hasNext())
        {
            Product product = (Product) productIterator.next();
            String productName = product.getProductName();
            
            
            
            Inventory inventory = InventoryData.getClosingBalanceByProduct(productName);

            
                
                double quantity =inventory.getBalanceQuantity();
                String measurementUnit  = inventory.getBalanceMeasurement();
                double rate =inventory.getBalanceRate();
                double total = inventory.getBalanceTotal();
                
                totalBalance=totalBalance+total;
                
                    htmlRows.append("<tr>");
                    htmlRows.append("<td style='width: 20%; text-align: left'>"+productName+"</td><td style='width: 20%; text-align: center'>"+String.format("%.2f", quantity)+"</td><td style='width: 20%; text-align: center'>"+measurementUnit+"</td><td style='width: 20%; text-align: right'>"+String.format("%.2f", rate)+"</td><td style='width: 20%; text-align: right'>"+String.format("%.2f", total)+"</td>");
                    htmlRows.append("</tr>");
               
        }
        htmlRows.append("<tr>");
        htmlRows.append("<td style='width: 20%; text-align: center; font-weight: bolder'>Total Closing Inventory</td><td style='width: 20%; text-align: center'></td><td style='width: 20%; text-align: center'></td><td style='width: 20%; text-align: center'></td><td style='width: 20%; text-align: right; font-weight: bolder'>"+String.format("%.2f", totalBalance)+"</td>");
        htmlRows.append("</tr>");
        
        return htmlRows.toString();
    
}
    
    
    public static String getProductTablByName(ArrayList<Inventory> productList) throws SQLException
{
        StringBuffer htmlRows = new StringBuffer();
        Iterator productIterator = productList.iterator();

        //htmlRows.append("<thead>");
        htmlRows.append("<tr >");
        htmlRows.append("<th rowspan='2'>ID</th><th  rowspan='2'>DATE</th><th  rowspan='2'>PARTICULAR</th><th  rowspan='2'>SUPPLIER/CUSTOMER NAME</th><th  colspan=4>RECEIPTS</th><th  colspan=4>ISSUES</th><th  colspan=4>BALANCE</th>");
        htmlRows.append("</tr>");
        htmlRows.append("<tr>");
        htmlRows.append("<th >QNT.</th><th >MSR.</th><th >RATE</th><th >TOTAL</th><th >QNT.</th><th >MSR.</th><th >RATE</th><th >TOTAL</th><th >QNT.</th><th >MSR.</th><th >RATE</th><th >TOTAL</th>");
        htmlRows.append("</tr>");
        //htmlRows.append("</thead>");
        //htmlRows.append("<tbody>");
        
        
        
        int id=0;
        Date date=null;
        String particular="";

        //int salesInvoice=0;
        //int purchaseInvoice=0;
        //String purchaseMemoNo="";
        String customerOrSuppler="";


        double purchaseQuantity=0.00;
        String purchaseMeasurement="";
        double purchaseRate=0.00;
        double purchaseTotal=0.00;


        double salesAtCostQuantity=0.00;
        String salesAtCostMeasurement="";
        double salesAtCostRate=0.00;
        double salesAtCostTotal=0.00;

        double balanceQuantity=0.00;
        String balanceMeasurement="";
        double balanceRate=0.00;
        double balanceTotal=0.00;
        
        
        
        
        double totalBalanceQnt =0.00;
        double totalBalance =0.00;
        double totalPurchaseQnt =0.00;
        double totalPurchase =0.00;
        double totalSalesQnt =0.00;
        double totalSales =0.00;
        
        
        
        
        while(productIterator.hasNext())
        {
            Inventory inventory = (Inventory) productIterator.next();
            
            
                id=inventory.getId();
                date=inventory.getDate();
                particular=inventory.getParticular();
                
                //salesInvoice=inventory.getSalesInvoice();
                //purchaseInvoice=inventory.getPurchaseInvoice();
                //purchaseMemoNo=inventory.getPurchaseMemoNo();
                customerOrSuppler=inventory.getCustomerOrSuppler();


                purchaseQuantity=inventory.getPurchaseQuantity();
                purchaseMeasurement=inventory.getPurchaseMeasurement();
                purchaseRate=inventory.getPurchaseRate();
                purchaseTotal=inventory.getPurchaseTotal();


                salesAtCostQuantity=inventory.getSalesAtCostQuantity();
                salesAtCostMeasurement=inventory.getSalesAtCostMeasurement();
                salesAtCostRate=inventory.getSalesAtCostRate();
                salesAtCostTotal=inventory.getSalesAtCostTotal();

                balanceQuantity=inventory.getBalanceQuantity();
                balanceMeasurement=inventory.getBalanceMeasurement();
                balanceRate=inventory.getBalanceRate();
                balanceTotal=inventory.getBalanceTotal();
                
                
                
                totalPurchaseQnt= totalPurchaseQnt+purchaseQuantity;
                totalPurchase=totalPurchase+purchaseTotal;
                totalSalesQnt=totalSalesQnt+salesAtCostQuantity;
                totalSales=totalSales+salesAtCostTotal;
                totalBalanceQnt=balanceQuantity;
                totalBalance=balanceTotal;
                
                
                
                
                    htmlRows.append("<tr >");
                    htmlRows.append("<td  >"+id
                            +"</td><td  >"+date
                            +"</td><td >"+particular
                            +"</td><td >"+customerOrSuppler
                            //+"</td><td >"+salesInvoice
                            //+"</td><td  >"+purchaseInvoice
                            //+"</td><td >"+purchaseMemoNo
                            +"</td><td >"+String.format("%.2f", purchaseQuantity)
                            +"</td><td >"+purchaseMeasurement
                            +"</td><td >"+String.format("%.2f", purchaseRate)
                            +"</td><td >"+String.format("%.2f", purchaseTotal)
                            +"</td><td >"+String.format("%.2f", salesAtCostQuantity)
                            +"</td><td >"+salesAtCostMeasurement
                            +"</td><td >"+String.format("%.2f", salesAtCostRate)
                            +"</td><td >"+String.format("%.2f", salesAtCostTotal)
                            +"</td><td >"+String.format("%.2f", balanceQuantity)
                            +"</td><td >"+balanceMeasurement
                            +"</td><td >"+String.format("%.2f", balanceRate)
                            +"</td><td >"+String.format("%.2f", balanceTotal)
                            +"</td>");
                    htmlRows.append("</tr>");
               
        }
                    htmlRows.append("<tr >");
                    htmlRows.append("<td  ></td>"
                            +"<td ></td>"
                            +"<td >"
                            +"TOTAL</td><td >"
                            +"</td><td >"+String.format("%.2f", totalPurchaseQnt)
                            +"</td><td >"+purchaseMeasurement
                            +"</td><td >"
                            +"</td><td >"+String.format("%.2f", totalPurchase)
                            +"</td><td >"+String.format("%.2f", totalSalesQnt)
                            +"</td><td >"+salesAtCostMeasurement
                            +"</td><td >"
                            +"</td><td >"+String.format("%.2f", totalSales)
                            +"</td><td >"+String.format("%.2f", totalBalanceQnt)
                            +"</td><td >"+balanceMeasurement
                            +"</td><td >"+String.format("%.2f", balanceRate)
                            +"</td><td >"+String.format("%.2f", totalBalance)
                            +"</td>");
                    htmlRows.append("</tr>");
                    //htmlRows.append("<tbody>");
        
        return htmlRows.toString();
    
}
    public static String getSalesInvoiceString() throws SQLException
    {
        
        ArrayList<Invoice> invoiceList=InventoryData.getSalesInvoice();
        
        
        Iterator iterator = invoiceList.iterator();
        StringBuffer htmlRows = new StringBuffer();

        htmlRows.append("<table class='salesInvoice' style='width:100%'>");
        htmlRows.append("<thead>");
        htmlRows.append("<tr>");
        htmlRows.append("<th>Date</th><th>Invoice</th><th>Customer Name</th><th>Taka</th><th>Print</th>");
        htmlRows.append("</tr>");
        htmlRows.append("</thead>");
        htmlRows.append("<tbody>");
        double grandTotal =0.00;

        while(iterator.hasNext())
        {
            Invoice invoice = (Invoice) iterator.next();
            Double total = invoice.getTotal();
            Date date = invoice.getDate();
            int invoiceNo = invoice.getInvoiceNo();
            String name = invoice.getName();
            grandTotal= grandTotal+total;
               
                    htmlRows.append("<tr>");
                    htmlRows.append("<td>"+date+"</td><td>"+invoiceNo+"</td><td>"+name+"</td><td>"+String.format("%.2f", total)+"</td><td><a href=BillPrintServlet?invoiceNo="+invoiceNo+"><input type='button' value='Bill'></a><a href=ChalanPrintServlet?invoiceNo="+invoiceNo+"><input type='button' value='Challan'></a>");
                    htmlRows.append("</tr>");
                    grandTotal= grandTotal+total;
            

        }
        htmlRows.append("<tr>");
        htmlRows.append("<td>Total</td><td></td><td></td><td>"+String.format("%.2f", grandTotal)+"</td><td></td>");
        htmlRows.append("</tr>");
        htmlRows.append("</tbody>");
        htmlRows.append("</table>");
        

        return htmlRows.toString();
        
    }
    public static String getPurchaseInvoiceString() throws SQLException
    {
        
        ArrayList<Invoice> invoiceList=InventoryData.getPurchaseInvoice();
        
        
        Iterator iterator = invoiceList.iterator();
        StringBuffer htmlRows = new StringBuffer();

        htmlRows.append("<table class='purchaseInvoice' style='width:100%' >");
        htmlRows.append("<thead>");
        htmlRows.append("<tr>");
        htmlRows.append("<th>Date</th><th>Invoice</th><th>Supplier Name</th><th>Memo No.</th><th>Taka</th><th>View</th>");
        htmlRows.append("</tr>");
        htmlRows.append("</thead>");
        htmlRows.append("<tbody>");
        double grandTotal =0.00;

        while(iterator.hasNext())
        {
            Invoice invoice = (Invoice) iterator.next();
            Double total = invoice.getTotal();
            Date date = invoice.getDate();
            int invoiceNo = invoice.getInvoiceNo();
            String name = invoice.getName();
            
            String memoNo = invoice.getMemoNo();
            grandTotal= grandTotal+total;
               
                    htmlRows.append("<tr>");
                    htmlRows.append("<td>"+date+"</td><td>"+invoiceNo+"</td><td>"+name+"</td><td>"+memoNo+"</td><td>"+String.format("%.2f", total)+"</td><td><a href=ChalanPrintServlet?invoiceNo="+invoiceNo+"><input type='button' value='Show'></td>");
                    htmlRows.append("</tr>");
                    
            

        }
        htmlRows.append("<tr>");
        htmlRows.append("<td>Total</td><td></td><td></td><td></td><td>"+String.format("%.2f", grandTotal)+"</td><td></td>");
        htmlRows.append("</tr>");
        htmlRows.append("</tbody>");
        htmlRows.append("</table>");
        

        return htmlRows.toString();
        
    }
    public static String getBill(int invoiceNO) throws SQLException
    {
        ArrayList<Inventory> inventoryList = InventoryData.getSalesInvoice(invoiceNO);
        Iterator iterator = inventoryList.listIterator();
        
        StringBuffer htmlRows = new StringBuffer();

        htmlRows.append("<table style='width:100%; text-align:center' border=1>");
        //htmlRows.append("<thead>");
        htmlRows.append("<tr >");
        htmlRows.append("<th style='width:5%' >SL. NO.</th><th style='width:60%'>DESCRIPTION</th><th style='width:10%'>QUANTITY</th><th style='width:5%'>MSR.</th><th style='width:10%'>RATE TAKA</th><th style='width:10%'>TOTAL TAKA</th>");
        htmlRows.append("</tr>");
        //htmlRows.append("</thead>");
        //htmlRows.append("<tbody>");
       
        DiscountVat discountVat = InventoryData.getDiscountVatInfo(invoiceNO);
        double totalSales = discountVat.getTotal();
        double discount = discountVat.getDiscount();
        double totalAfterDiscount = totalSales - discount;
        double vatRate = discountVat.getVatRate();
        double vat = discountVat.getVat();
        double totalAfterVat = totalAfterDiscount + vat;
        double paid = discountVat.getPaid();
        double due =totalAfterVat-paid; 
        
        
        
        double grandTotal =0.00;
        int slNo=0;

        while(iterator.hasNext())
        {
            
            
            Inventory  inventory = (Inventory) iterator.next();
            ++slNo;
            String productName = inventory.getProductName();
            double quantity = inventory.getSalesAtSalesQuantity();
            String msr = inventory.getSalesAtSalesMeasurement();
            double rate = inventory.getSalesAtSalesRate();
            double total = inventory.getSalesAtSalesTotal();
            
            grandTotal= grandTotal+total;
               
                    htmlRows.append("<tr>");
                    htmlRows.append("<td>"+slNo+"</td><td>"+productName+"</td><td>"+String.format("%.2f", quantity)+"</td><td>"+msr+"</td><td>"+String.format("%.2f", rate)+"</td><td>"+String.format("%.2f", total)+"</td>");
                    htmlRows.append("</tr>");
             
        }
        htmlRows.append("</table><br><br>");
        
        
        htmlRows.append("<table style='width:100%;'>");
        
        htmlRows.append("<tr>");       
        htmlRows.append("<td style='width:65%'></td>");
        htmlRows.append("<td style='text-align: right; font-weight: bolder; width:25%'></td>");
        htmlRows.append("<td style='font-weight: bolder;  text-align: right; width:10%'>Taka</td>");
        htmlRows.append("</tr>");
        
        htmlRows.append("<tr>");       
        htmlRows.append("<td style='width:70%'></td>");
        htmlRows.append("<td style='text-align: right; font-weight: bolder; width:20%'>Total:</td>");
        htmlRows.append("<td style='font-weight: bolder;  text-align: right; width:10%'>"+String.format("%.2f", totalSales)+"</td>");
        htmlRows.append("</tr>");
        
        htmlRows.append("<tr>");        
        htmlRows.append("<td></td>");
        htmlRows.append("<td style='text-align: right'>Less Discount:</td>");
        htmlRows.append("<td style='text-align: right'>"+String.format("%.2f", discount)+"</td>");
        htmlRows.append("</tr>");
        
        
        htmlRows.append("<tr>");             
        htmlRows.append("<td></td>");
        htmlRows.append("<td  style='text-align: right'>Total After Discount:</td>");
        htmlRows.append("<td style='text-align: right'>"+String.format("%.2f", totalAfterDiscount)+"</td>");
        htmlRows.append("</tr>");
        
        htmlRows.append("<tr>");    
        htmlRows.append("<td></td>");
        htmlRows.append("<td  style='text-align: right'>Add "+String.format("%.2f", vatRate)+"% VAT</td>");
        htmlRows.append("<td style='text-align: right'>"+String.format("%.2f", vat)+"</td>");
        htmlRows.append("</tr>");
        
        htmlRows.append("<tr>");       
        htmlRows.append("<td></td>");
        htmlRows.append("<td  style='text-align: right;font-weight: bolder;'>Grand Total:</td>");
        htmlRows.append("<td style='text-align: right; font-weight: bolder;'>"+String.format("%.2f", totalAfterVat)+"</td>");
        htmlRows.append("</tr>");
        
        
        htmlRows.append("<tr>");        
        htmlRows.append("<td></td>");
        htmlRows.append("<td  style='text-align: right'>Paid:</td>");
        htmlRows.append("<td style='text-align: right'>"+String.format("%.2f", paid)+"</td>");
        htmlRows.append("</tr>");
        
        htmlRows.append("<tr>");                
        htmlRows.append("<td style='font-weight: bolder'>In word:<input type=text size=50></td>");
        htmlRows.append("<td  style='text-align: right'>Due:</td>");
        htmlRows.append("<td style='text-align: right'>"+String.format("%.2f", due)+"</td>");
        htmlRows.append("</tr>");
    
    
        
        /*htmlRows.append("<tr>");
        htmlRows.append("<td>Total</td><td></td><td></td><td></td><td></td><td>"+String.format("%.2f", grandTotal)+"</td>");
        htmlRows.append("</tr>");
        //htmlRows.append("</tbody>");*/
        htmlRows.append("</table>");
        
        return htmlRows.toString();
    }
    public static String getChalan(int invoiceNO) throws SQLException
    {
        ArrayList<Inventory> inventoryList = InventoryData.getSalesInvoice(invoiceNO);
        Iterator iterator = inventoryList.listIterator();
        
        StringBuffer htmlRows = new StringBuffer();

        htmlRows.append("<table style='width:100%; text-align:center' border=1>");
        //htmlRows.append("<thead>");
        htmlRows.append("<tr >");
        htmlRows.append("<th style='width:10%' >SL. NO.</th><th style='width:70%'>DESCRIPTION</th><th style='width:10%'>QUANTITY</th><th style='width:10%'>MSR.</th>");
        htmlRows.append("</tr>");
        //htmlRows.append("</thead>");
        //htmlRows.append("<tbody>");
       
        
        
        
        
        
        int slNo=0;

        while(iterator.hasNext())
        {
            
            
            Inventory  inventory = (Inventory) iterator.next();
            ++slNo;
            String productName = inventory.getProductName();
            double quantity = inventory.getSalesAtSalesQuantity();
            String msr = inventory.getSalesAtSalesMeasurement();
            double rate = inventory.getSalesAtSalesRate();
            double total = inventory.getSalesAtSalesTotal();
            
           
                    htmlRows.append("<tr>");
                    htmlRows.append("<td>"+slNo+"</td><td>"+productName+"</td><td>"+String.format("%.2f", quantity)+"</td><td>"+msr+"</td>");
                    htmlRows.append("</tr>");
             
        }
        htmlRows.append("</table><br><br>");
        
        
        /*htmlRows.append("<tr>");
        htmlRows.append("<td>Total</td><td></td><td></td><td></td><td></td><td>"+String.format("%.2f", grandTotal)+"</td>");
        htmlRows.append("</tr>");
        //htmlRows.append("</tbody>");*/
        htmlRows.append("</table>");
        
        return htmlRows.toString();
    }
    
    
    public static String getMemo(int invoiceNO) throws SQLException
    {
        ArrayList<Inventory> inventoryList = InventoryData.getPurchaseInvoice(invoiceNO);
        Iterator iterator = inventoryList.listIterator();
        
        StringBuffer htmlRows = new StringBuffer();

        htmlRows.append("<table style='width:100%; text-align:center' border=1>");
        //htmlRows.append("<thead>");
        htmlRows.append("<tr >");
        htmlRows.append("<th style='width:5%' >SL. NO.</th><th style='width:60%'>DESCRIPTION</th><th style='width:10%'>QUANTITY</th><th style='width:5%'>MSR.</th><th style='width:10%'>RATE TAKA</th><th style='width:10%'>TOTAL TAKA</th>");
        htmlRows.append("</tr>");
        //htmlRows.append("</thead>");
        //htmlRows.append("<tbody>");
       
        DiscountVat discountVat = InventoryData.getDiscountVatInfo(invoiceNO);
        double totalSales = discountVat.getTotal();
        double discount = discountVat.getDiscount();
        double totalAfterDiscount = totalSales - discount;
        double vatRate = discountVat.getVatRate();
        double vat = discountVat.getVat();
        double totalAfterVat = totalAfterDiscount + vat;
        double paid = discountVat.getPaid();
        double due =totalAfterVat-paid; 
        
        
        
        double grandTotal =0.00;
        int slNo=0;

        while(iterator.hasNext())
        {
            
            
            Inventory  inventory = (Inventory) iterator.next();
            ++slNo;
            String productName = inventory.getProductName();
            double quantity = inventory.getPurchaseQuantity();
            String msr = inventory.getPurchaseMeasurement();
            double rate = inventory.getPurchaseRate();
            double total = inventory.getPurchaseTotal();
            
            grandTotal= grandTotal+total;
               
                    htmlRows.append("<tr>");
                    htmlRows.append("<td>"+slNo+"</td><td>"+productName+"</td><td>"+String.format("%.2f", quantity)+"</td><td>"+msr+"</td><td>"+String.format("%.2f", rate)+"</td><td>"+String.format("%.2f", total)+"</td>");
                    htmlRows.append("</tr>");
             
        }
        htmlRows.append("</table><br><br>");
        
        
        htmlRows.append("<table style='width:100%;'>");
        
        htmlRows.append("<tr>");       
        htmlRows.append("<td style='width:65%'></td>");
        htmlRows.append("<td style='text-align: right; font-weight: bolder; width:25%'></td>");
        htmlRows.append("<td style='font-weight: bolder;  text-align: right; width:10%'>Taka</td>");
        htmlRows.append("</tr>");
        
        htmlRows.append("<tr>");       
        htmlRows.append("<td style='width:70%'></td>");
        htmlRows.append("<td style='text-align: right; font-weight: bolder; width:20%'>Total:</td>");
        htmlRows.append("<td style='font-weight: bolder;  text-align: right; width:10%'>"+String.format("%.2f", totalSales)+"</td>");
        htmlRows.append("</tr>");
        
        htmlRows.append("<tr>");        
        htmlRows.append("<td></td>");
        htmlRows.append("<td style='text-align: right'>Less Discount:</td>");
        htmlRows.append("<td style='text-align: right'>"+String.format("%.2f", discount)+"</td>");
        htmlRows.append("</tr>");
        
        
        htmlRows.append("<tr>");             
        htmlRows.append("<td></td>");
        htmlRows.append("<td  style='text-align: right'>Total After Discount:</td>");
        htmlRows.append("<td style='text-align: right'>"+String.format("%.2f", totalAfterDiscount)+"</td>");
        htmlRows.append("</tr>");
        
        htmlRows.append("<tr>");    
        htmlRows.append("<td></td>");
        htmlRows.append("<td  style='text-align: right'>Add "+String.format("%.2f", vatRate)+"% VAT</td>");
        htmlRows.append("<td style='text-align: right'>"+String.format("%.2f", vat)+"</td>");
        htmlRows.append("</tr>");
        
        htmlRows.append("<tr>");       
        htmlRows.append("<td></td>");
        htmlRows.append("<td  style='text-align: right;font-weight: bolder;'>Grand Total:</td>");
        htmlRows.append("<td style='text-align: right; font-weight: bolder;'>"+String.format("%.2f", totalAfterVat)+"</td>");
        htmlRows.append("</tr>");
        
        
        htmlRows.append("<tr>");        
        htmlRows.append("<td></td>");
        htmlRows.append("<td  style='text-align: right'>Paid:</td>");
        htmlRows.append("<td style='text-align: right'>"+String.format("%.2f", paid)+"</td>");
        htmlRows.append("</tr>");
        
        htmlRows.append("<tr>");                
        htmlRows.append("<td style='font-weight: bolder'>In word:<input type=text size=50></td>");
        htmlRows.append("<td  style='text-align: right'>Due:</td>");
        htmlRows.append("<td style='text-align: right'>"+String.format("%.2f", due)+"</td>");
        htmlRows.append("</tr>");
    
    
        
        /*htmlRows.append("<tr>");
        htmlRows.append("<td>Total</td><td></td><td></td><td></td><td></td><td>"+String.format("%.2f", grandTotal)+"</td>");
        htmlRows.append("</tr>");
        //htmlRows.append("</tbody>");*/
        htmlRows.append("</table>");
        
        return htmlRows.toString();
    }
    public static String getSalesReturnStr(int invoiceNO) throws SQLException
    {
        ArrayList<Inventory> inventoryList = InventoryData.getSalesInvoice(invoiceNO);
        Iterator iterator = inventoryList.listIterator();
        
        StringBuffer htmlRows = new StringBuffer();

        htmlRows.append("<table style='width:100%; text-align:center' class='purchaseInvoice'>");
        //htmlRows.append("<thead>");
        htmlRows.append("<tr >");
        htmlRows.append("<th>SL. NO.</th><th>DESCRIPTION</th><th >QUANTITY</th><th>MSR.</th><th>Rate</th><th>Total</th>");
        htmlRows.append("</tr>");
        //htmlRows.append("</thead>");
        //htmlRows.append("<tbody>");
       
        int slNo=0;
        double grandTotal=0.00;

        while(iterator.hasNext())
        {
            
            
            Inventory  inventory = (Inventory) iterator.next();
            ++slNo;
            String productName = inventory.getProductName();
            double quantity = inventory.getSalesAtSalesQuantity();
            String msr = inventory.getSalesAtSalesMeasurement();
            double rate = inventory.getSalesAtSalesRate();
            double total = inventory.getSalesAtSalesTotal();
            grandTotal=grandTotal+total;
           
                    htmlRows.append("<tr>");
                    htmlRows.append("<td>"+slNo+"</td><td>"+productName+"</td><td>"+String.format("%.2f", quantity)+"</td><td>"+msr+"</td><td>"+String.format("%.2f", rate)+"</td><td>"+String.format("%.2f", total)+"</td>");
                    htmlRows.append("</tr>");
             
        }
        
        
        
        htmlRows.append("<tr>");
        htmlRows.append("<td></td><td>Total</td><td></td><td></td><td></td><td>"+String.format("%.2f", grandTotal)+"</td>");
        htmlRows.append("</tr>");
     
        htmlRows.append("</table>");
        
        return htmlRows.toString();
    }
    
    
    public static String getPurchaseReturnStr(int invoiceNO) throws SQLException
    {
        ArrayList<Inventory> inventoryList = InventoryData.getPurchaseInvoice(invoiceNO);
        Iterator iterator = inventoryList.listIterator();
        
        StringBuffer htmlRows = new StringBuffer();

        htmlRows.append("<table style='width:100%; text-align:center' class='purchaseInvoice'>");
        //htmlRows.append("<thead>");
        htmlRows.append("<tr >");
        htmlRows.append("<th>SL. NO.</th><th>DESCRIPTION</th><th >QUANTITY</th><th>MSR.</th><th>Rate</th><th>Total</th>");
        htmlRows.append("</tr>");
        //htmlRows.append("</thead>");
        //htmlRows.append("<tbody>");
       
        int slNo=0;
        double grandTotal=0.00;

        while(iterator.hasNext())
        {
            
            
            Inventory  inventory = (Inventory) iterator.next();
            ++slNo;
            String productName = inventory.getProductName();
            double quantity = inventory.getPurchaseQuantity();
            String msr = inventory.getPurchaseMeasurement();
            double rate = inventory.getPurchaseRate();
            double total = inventory.getPurchaseTotal();
            grandTotal=grandTotal+total;
           
                    htmlRows.append("<tr>");
                    htmlRows.append("<td>"+slNo+"</td><td>"+productName+"</td><td>"+String.format("%.2f", quantity)+"</td><td>"+msr+"</td><td>"+String.format("%.2f", rate)+"</td><td>"+String.format("%.2f", total)+"</td>");
                    htmlRows.append("</tr>");
             
        }
        htmlRows.append("<tr>");
        htmlRows.append("<td></td><td>Total</td><td></td><td></td><td></td><td>"+String.format("%.2f", grandTotal)+"</td>");
        htmlRows.append("</tr>");
     
        htmlRows.append("</table>");
        
        return htmlRows.toString();
    }
}
