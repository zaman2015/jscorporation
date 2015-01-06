<%-- 
    Document   : inventoryInsert
    Created on : Jan 25, 2015, 3:01:03 AM
    Author     : BUDGET003
--%>

<%@page import="java.util.Iterator"%>
<%@page import="sql.InventoryPresentation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="data.InventoryData"%>
<%@page import="java.util.Date"%>
<%@page import="bisiness.Inventory"%>

<% 
    
    
    
    String transectionType= request.getParameter("salesOrPurhase");
    
    int id =0;
    Date date=null;
    
    String name = request.getParameter("name");
    String productName = request.getParameter("product");
    
    double quantity= Double.parseDouble(request.getParameter("quantity"));
    String measurement = request.getParameter("measurement");
    double rate= Double.parseDouble(request.getParameter("rate"));
    String memoNo = request.getParameter("memo");
    
    int invoiceNO =Integer.parseInt(request.getParameter("invoieceNo"));
    
    //String row="";
    
    
    
    
    
    session = request.getSession();
    ArrayList inventoryList = (ArrayList) session.getAttribute("inventoryList");
    
    
    double total= Double.valueOf(session.getAttribute("total").toString());
    
    
    
    
     if(transectionType.equalsIgnoreCase("sales"))
    {
        Inventory inventory = new Inventory();
        
        inventory.setId(id);
        inventory.setDate(date);
        inventory.setParticular(transectionType);
        inventory.setSalesInvoice(InventoryData.getLastSalesInvoiceNo()+1);
        inventory.setPurchaseInvoice(InventoryData.getLastPurchaseInvoiceNo());
        inventory.setCustomerOrSuppler(name);
        inventory.setProductName(productName);
        inventory.setPurchaseMemoNo(memoNo);
        
        inventory.setPurchaseQuantity(0);
        inventory.setPurchaseMeasurement(measurement);
        inventory.setPurchaseRate(0);
        inventory.setPurchaseTotal(0);
        
         
        
        inventory.setSalesAtSalesQuantity(quantity);
        inventory.setSalesAtSalesMeasurement(measurement);
        inventory.setSalesAtSalesRate(rate);
        inventory.setSalesAtSalesTotal(quantity*rate);
        
        
        Inventory inventoryForClBlNCRate = InventoryData.getClosingBalanceByProduct(productName);
        
        double closingBalanceRate = inventoryForClBlNCRate.getBalanceRate();
        double closingQuantity = inventoryForClBlNCRate.getBalanceQuantity();
        double totalSalesAtCost = quantity*closingBalanceRate;
        
        inventory.setSalesAtCostQuantity(quantity);
        inventory.setSalesAtCostMeasurement(measurement);
        inventory.setSalesAtCostRate(closingBalanceRate);
        inventory.setSalesAtCostTotal(totalSalesAtCost);
         
        double closingBlnQuantity = closingQuantity -  quantity;       
        double closingBlnTotal = closingBlnQuantity * closingBalanceRate;
        
        inventory.setBalanceQuantity(closingBlnQuantity);
        inventory.setBalanceMeasurement(measurement);
        inventory.setBalanceRate(closingBalanceRate);
        inventory.setBalanceTotal(closingBlnTotal);
       
        inventoryList.add(inventory);
        total = total + (quantity*rate);
        
        session.setAttribute("inventoryList", inventoryList);
        session.setAttribute("total", total);
        
        
        //InventoryData.insertIntoInventory(inventory);
        
        out.print("<table id='detailsTable' class='purcchase'>");
            out.print("<thead><tr>");
                out.print("<th>Product Name</th>" +
                "<th>Unit</th>"+
                "<th>Measurement</th>"+
                "<th>Rate</th>"+
                "<th>Total</th>");
            out.print("</tr></thead>");
            out.print("<tbody><tr>");
            
            
     Iterator iteratorPurchase = inventoryList.iterator();
     while(iteratorPurchase.hasNext())
     {
        Inventory inv = (Inventory) iteratorPurchase.next();
         
         out.print("<tr>");
        
                out.print("<td>"+inv.getProductName()+"</td>" +
                "<td>"+inv.getSalesAtSalesQuantity()+"</td>"+
                "<td>"+inv.getSalesAtSalesMeasurement()+"</td>"+
                "<td>"+inv.getSalesAtSalesRate()+"</td>"+
                "<td>"+inv.getSalesAtSalesTotal()+"</td>");
            
        out.print("</tr>");
     }
     out.print("</tbody>");
            
    out.print("</table>");
        
        
    } 
    
    
     if(transectionType.equalsIgnoreCase("purchase"))
    {
        Inventory inventory = new Inventory();
        
        inventory.setId(id);
        inventory.setDate(date);
        inventory.setParticular(transectionType);
        inventory.setSalesInvoice(InventoryData.getLastSalesInvoiceNo());
        inventory.setPurchaseInvoice(InventoryData.getLastPurchaseInvoiceNo()+1);
        inventory.setCustomerOrSuppler(name);
        inventory.setProductName(productName);
        inventory.setPurchaseMemoNo(memoNo);
        
        inventory.setPurchaseQuantity(quantity);
        inventory.setPurchaseMeasurement(measurement);
        inventory.setPurchaseRate(rate);
        inventory.setPurchaseTotal(quantity*rate);
        
        inventory.setSalesAtSalesQuantity(0);
        inventory.setSalesAtSalesMeasurement(measurement);
        inventory.setSalesAtSalesRate(0);
        inventory.setSalesAtSalesTotal(0);
        
        inventory.setSalesAtCostQuantity(0);
        inventory.setSalesAtCostMeasurement(measurement);
        inventory.setSalesAtCostRate(0);
        inventory.setSalesAtCostTotal(0);
        
        
        
        
        
        Iterator iterator = inventoryList.listIterator();
        ArrayList<Inventory> inventoryList2=new ArrayList();
        Inventory inventory3 = new Inventory();
        
        double balanceQuantity = 0.00;
        double balanceTotal = 0.00;
        double balanceRate = 0.00;
        
        
            
        while(iterator.hasNext())
        {
            Inventory inventory1 = (Inventory)iterator.next();
            if(inventory1.getProductName().equalsIgnoreCase(productName))
                inventoryList2.add(inventory1);
        }
        
        
        Iterator iterator2 = inventoryList2.listIterator();
        
        if(iterator2.hasNext())
        {
            while(iterator2.hasNext())
            {
                inventory3=(Inventory)iterator2.next();
            }

            //Inventory balanceInventory = InventoryData.getClosingBalanceByProductName(productName);
            balanceQuantity = inventory3.getBalanceQuantity()+ quantity;
            balanceTotal = inventory3.getBalanceTotal()+ (quantity*rate);
            balanceRate = balanceTotal/balanceQuantity;
        }
        else
        {
            Inventory inventoryForClosingBlance = InventoryData.getClosingBalanceByProduct(productName);
            balanceQuantity = inventoryForClosingBlance.getBalanceQuantity()+ quantity;
            balanceTotal = inventoryForClosingBlance.getBalanceTotal()+ (quantity*rate);
            balanceRate = balanceTotal/balanceQuantity;  
        }
        
        inventory.setBalanceQuantity(balanceQuantity);
        inventory.setBalanceMeasurement(measurement);
        inventory.setBalanceRate(balanceRate);
        inventory.setBalanceTotal(balanceTotal);
   
        inventoryList.add(inventory);
        total = total + (quantity*rate);
        
        session.setAttribute("inventoryList", inventoryList);
        session.setAttribute("total", total);
        
        //row = InventoryPresentation.getRowsOfPurchase(inventoryList);
        
        
        out.print("<table id='detailsTable' class='purcchase'>");
            out.print("<thead><tr>");
                out.print("<th>Product Name</th>" +
                "<th>Unit</th>"+
                "<th>Measurement</th>"+
                "<th>Rate</th>"+
                "<th>Total</th>");
            out.print("</tr></thead>");
            out.print("<tbody>");
            
     Iterator iteratorPurchase = inventoryList.iterator();
     while(iteratorPurchase.hasNext())
     {
        Inventory inv = (Inventory) iteratorPurchase.next();
         
         
        out.print("<tr>");
                out.print("<td>"+inv.getProductName()+"</td>" +
                "<td>"+inv.getPurchaseQuantity()+"</td>"+
                "<td>"+inv.getPurchaseMeasurement()+"</td>"+
                "<td>"+inv.getPurchaseRate()+"</td>"+
                "<td>"+inv.getPurchaseTotal()+"</td>");
            out.print("</tr>");
        
     }
      out.print("</tbody>");      
    out.print("</table>");
        
    }
     
     
     
    if(transectionType.equalsIgnoreCase("openingInventory"))
    {
        Inventory inventory = new Inventory();
        
        inventory.setId(id);
        inventory.setDate(date);
        inventory.setParticular("Openign Balance");
        inventory.setSalesInvoice(0);
        inventory.setPurchaseInvoice(0);
        inventory.setCustomerOrSuppler("Not Applicable");
        inventory.setProductName(productName);
        inventory.setPurchaseMemoNo("0");
        
        inventory.setPurchaseQuantity(quantity);
        inventory.setPurchaseMeasurement(measurement);
        inventory.setPurchaseRate(rate);
        inventory.setPurchaseTotal(quantity*rate);
        
        inventory.setSalesAtSalesQuantity(0);
        inventory.setSalesAtSalesMeasurement(measurement);
        inventory.setSalesAtSalesRate(0);
        inventory.setSalesAtSalesTotal(0);
        
        inventory.setSalesAtCostQuantity(0);
        inventory.setSalesAtCostMeasurement(measurement);
        inventory.setSalesAtCostRate(0);
        inventory.setSalesAtCostTotal(0);
        
        
        inventory.setBalanceQuantity(quantity);
        inventory.setBalanceMeasurement(measurement);
        inventory.setBalanceRate(rate);
        inventory.setBalanceTotal(quantity*rate);
   
        inventoryList.add(inventory);
        total = total + (quantity*rate);
        
        session.setAttribute("inventoryList", inventoryList);
        session.setAttribute("total", total);
        
        //row = InventoryPresentation.getRowsOfPurchase(inventoryList);
        
        
        out.print("<table id='detailsTable' border='1' style='width: 100%'>");
            out.print("<tr>");
                out.print("<td style=' width: 20%; font-weight: bold; text-align: center'>Product Name</td>" +
                "<td style=' width: 20%; font-weight: bold; text-align: center'>Unit</td>"+
                "<td style=' width: 20%; font-weight: bold; text-align: center'>Measurement</td>"+
                "<td style=' width: 20%; font-weight: bold; text-align: center'>Rate</td>"+
                "<td style=' width: 20%; font-weight: bold; text-align: center'>Total</td>");
            out.print("</tr>");
            
            
     Iterator iteratorPurchase = inventoryList.iterator();
     while(iteratorPurchase.hasNext())
     {
        Inventory inv = (Inventory) iteratorPurchase.next();
         
         
        out.print("<tr>");
                out.print("<td style=' width: 20%; font-weight: bold; text-align: center'>"+inv.getProductName()+"</td>" +
                "<td style=' width: 20%; font-weight: bold; text-align: center'>"+inv.getPurchaseQuantity()+"</td>"+
                "<td style=' width: 20%; font-weight: bold; text-align: center'>"+inv.getPurchaseMeasurement()+"</td>"+
                "<td style=' width: 20%; font-weight: bold; text-align: center'>"+inv.getPurchaseRate()+"</td>"+
                "<td style=' width: 20%; font-weight: bold; text-align: center'>"+inv.getPurchaseTotal()+"</td>");
            out.print("</tr>");
        
     }
            
    out.print("</table>");
        
    }
    
    
    if(transectionType.equalsIgnoreCase("Sales Return"))
    {
        Inventory inventory = new Inventory();
        
        inventory.setId(id);
        inventory.setDate(date);
        inventory.setParticular(transectionType);
        inventory.setSalesInvoice(0);
        inventory.setPurchaseInvoice(0);
        inventory.setCustomerOrSuppler(name);
        inventory.setProductName(productName);
        inventory.setPurchaseMemoNo(memoNo);
        
        inventory.setPurchaseQuantity(0);
        inventory.setPurchaseMeasurement(measurement);
        inventory.setPurchaseRate(0);
        inventory.setPurchaseTotal(0);
        
         
        
        inventory.setSalesAtSalesQuantity(quantity*-1);
        inventory.setSalesAtSalesMeasurement(measurement);
        inventory.setSalesAtSalesRate(rate);
        inventory.setSalesAtSalesTotal(quantity*rate*-1);
        
        
        Inventory inventoryAtSaleTimeCRate = InventoryData.getSalesReturnInvoice(productName, invoiceNO);
        
        double salesAtCotRate = inventoryAtSaleTimeCRate.getSalesAtCostRate();
        double totalSalesAtCost = quantity*salesAtCotRate;
        
        inventory.setSalesAtCostQuantity(quantity*-1);
        inventory.setSalesAtCostMeasurement(measurement);
        inventory.setSalesAtCostRate(salesAtCotRate);
        inventory.setSalesAtCostTotal(totalSalesAtCost*-1);
         
        Inventory inventoryForClBlNCRate = InventoryData.getClosingBalanceByProduct(productName);
        double closingQuantity = inventoryForClBlNCRate.getBalanceQuantity();
        double closingRate = inventoryForClBlNCRate.getBalanceRate();
        double closingBlnQuantity = closingQuantity +  quantity;       
        double closingBlnTotal = closingBlnQuantity * closingRate;
        
        
        
        inventory.setBalanceQuantity(closingBlnQuantity);
        inventory.setBalanceMeasurement(measurement);
        inventory.setBalanceRate(closingRate);
        inventory.setBalanceTotal(closingBlnTotal);
       
        inventoryList.add(inventory);
        total = total + (quantity*rate);
        
        session.setAttribute("inventoryList", inventoryList);
        session.setAttribute("total", total);
        
        
        //InventoryData.insertIntoInventory(inventory);
        
        out.print("<table id='detailsTable' class='purcchase'>");
            out.print("<thead><tr>");
                out.print("<th>Product Name</th>" +
                "<th>Unit</th>"+
                "<th>Measurement</th>"+
                "<th>Rate</th>"+
                "<th>Total</th>");
            out.print("</tr></thead>");
            out.print("<tbody><tr>");
            
            
     Iterator iteratorPurchase = inventoryList.iterator();
     while(iteratorPurchase.hasNext())
     {
        Inventory inv = (Inventory) iteratorPurchase.next();
         
         out.print("<tr>");
        
                out.print("<td>"+inv.getProductName()+"</td>" +
                "<td>"+inv.getSalesAtSalesQuantity()+"</td>"+
                "<td>"+inv.getSalesAtSalesMeasurement()+"</td>"+
                "<td>"+inv.getSalesAtSalesRate()+"</td>"+
                "<td>"+inv.getSalesAtSalesTotal()+"</td>");
            
        out.print("</tr>");
     }
     out.print("</tbody>");
            
    out.print("</table>");
        
        
    } 
     
    
    if(transectionType.equalsIgnoreCase("Purchase Return"))
    {
        Inventory inventory = new Inventory();
        
        inventory.setId(id);
        inventory.setDate(date);
        inventory.setParticular(transectionType);
        inventory.setSalesInvoice(0);
        inventory.setPurchaseInvoice(0);
        inventory.setCustomerOrSuppler(name);
        inventory.setProductName(productName);
        inventory.setPurchaseMemoNo(memoNo);
        
        inventory.setPurchaseQuantity(quantity*-1);
        inventory.setPurchaseMeasurement(measurement);
        inventory.setPurchaseRate(rate);
        inventory.setPurchaseTotal(quantity*rate*-1);
        
         
        
        inventory.setSalesAtSalesQuantity(0);
        inventory.setSalesAtSalesMeasurement(measurement);
        inventory.setSalesAtSalesRate(0);
        inventory.setSalesAtSalesTotal(0);
        
        
        
        
        inventory.setSalesAtCostQuantity(0);
        inventory.setSalesAtCostMeasurement(measurement);
        inventory.setSalesAtCostRate(0);
        inventory.setSalesAtCostTotal(0);
         
        Inventory inventoryForClBlNCRate = InventoryData.getClosingBalanceByProduct(productName);
        double closingQuantity = inventoryForClBlNCRate.getBalanceQuantity();
        double closingRate = inventoryForClBlNCRate.getBalanceRate();
        double closingBlnQuantity = closingQuantity -  quantity;       
        double closingBlnTotal = closingBlnQuantity * closingRate;
        
        
        
        inventory.setBalanceQuantity(closingBlnQuantity);
        inventory.setBalanceMeasurement(measurement);
        inventory.setBalanceRate(closingRate);
        inventory.setBalanceTotal(closingBlnTotal);
       
        inventoryList.add(inventory);
        total = total + (quantity*rate);
        
        session.setAttribute("inventoryList", inventoryList);
        session.setAttribute("total", total);
        
        
        //InventoryData.insertIntoInventory(inventory);
        
        out.print("<table id='detailsTable' class='purcchase'>");
            out.print("<thead><tr>");
                out.print("<th>Product Name</th>" +
                "<th>Unit</th>"+
                "<th>Measurement</th>"+
                "<th>Rate</th>"+
                "<th>Total</th>");
            out.print("</tr></thead>");
            out.print("<tbody><tr>");
            
            
     Iterator iteratorPurchase = inventoryList.iterator();
     while(iteratorPurchase.hasNext())
     {
        Inventory inv = (Inventory) iteratorPurchase.next();
         
         out.print("<tr>");
        
                out.print("<td>"+inv.getProductName()+"</td>" +
                "<td>"+inv.getPurchaseQuantity()+"</td>"+
                "<td>"+inv.getPurchaseMeasurement()+"</td>"+
                "<td>"+inv.getPurchaseRate()+"</td>"+
                "<td>"+inv.getPurchaseTotal()+"</td>");
            
        out.print("</tr>");
     }
     out.print("</tbody>");
            
    out.print("</table>");
        
        
    } 
    
    if(transectionType.equalsIgnoreCase("damage"))
    {
        Inventory inventory = new Inventory();
        
        inventory.setId(id);
        inventory.setDate(date);
        inventory.setParticular(transectionType);
        inventory.setSalesInvoice(0);
        inventory.setPurchaseInvoice(0);
        inventory.setCustomerOrSuppler("Not applicable");
        inventory.setProductName(productName);
        inventory.setPurchaseMemoNo(" ");
        
        inventory.setPurchaseQuantity(0);
        inventory.setPurchaseMeasurement(measurement);
        inventory.setPurchaseRate(0);
        inventory.setPurchaseTotal(0);
        
         
        
        inventory.setSalesAtSalesQuantity(quantity);
        inventory.setSalesAtSalesMeasurement(measurement);
        inventory.setSalesAtSalesRate(0);
        inventory.setSalesAtSalesTotal(quantity*rate);
        
        
        Inventory inventoryForClBlNCRate = InventoryData.getClosingBalanceByProduct(productName);
        
        double closingBalanceRate = inventoryForClBlNCRate.getBalanceRate();
        double closingQuantity = inventoryForClBlNCRate.getBalanceQuantity();
        double totalSalesAtCost = quantity*closingBalanceRate;
        
        inventory.setSalesAtCostQuantity(quantity);
        inventory.setSalesAtCostMeasurement(measurement);
        inventory.setSalesAtCostRate(0);
        inventory.setSalesAtCostTotal(totalSalesAtCost);
         
        double closingBlnQuantity = closingQuantity -  quantity;       
        double closingBlnTotal = closingBlnQuantity * closingBalanceRate;
        
        inventory.setBalanceQuantity(closingBlnQuantity);
        inventory.setBalanceMeasurement(measurement);
        inventory.setBalanceRate(closingBalanceRate);
        inventory.setBalanceTotal(closingBlnTotal);
       
        inventoryList.add(inventory);
        total = total + (quantity*closingBalanceRate);
        
        session.setAttribute("inventoryList", inventoryList);
        session.setAttribute("total", total);
        
        
        //InventoryData.insertIntoInventory(inventory);
        
        out.print("<table id='detailsTable' class='purcchase'>");
            out.print("<thead><tr>");
                out.print("<th>Product Name</th>" +
                "<th>Unit</th>"+
                "<th>Measurement</th>"+
                "<th>Rate</th>"+
                "<th>Total</th>");
            out.print("</tr></thead>");
            out.print("<tbody><tr>");
            
            
     Iterator iteratorPurchase = inventoryList.iterator();
     while(iteratorPurchase.hasNext())
     {
        Inventory inv = (Inventory) iteratorPurchase.next();
         
         out.print("<tr>");
        
                out.print("<td>"+inv.getProductName()+"</td>" +
                "<td>"+inv.getSalesAtSalesQuantity()+"</td>"+
                "<td>"+inv.getSalesAtSalesMeasurement()+"</td>"+
                "<td>"+inv.getSalesAtSalesRate()+"</td>"+
                "<td>"+inv.getSalesAtSalesTotal()+"</td>");
            
        out.print("</tr>");
     }
     out.print("</tbody>");
            
    out.print("</table>");
        
        
    } 
     
     
     
%>

