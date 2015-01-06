/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import bisiness.DiscountVat;
import bisiness.Inventory;
import bisiness.Invoice;
import bisiness.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author BUDGET003
 */
public class InventoryData {
    
        private static final String url = "jdbc:mysql://localhost:3306/jscorporation?useUnicode=true&characterEncoding=UTF-8";
        private static final String user = "root";
        private static final String pass = "ariba";
        private static Connection connection=null;
        private static PreparedStatement ps=null;
        private static String query;
        private static ResultSet rs=null;



    public static void insertIntoInventory(ArrayList<Inventory> inventoryList) throws SQLException
    {



        try
        {

            connection = (Connection) DriverManager.getConnection(url, user, pass);
            
            Iterator inventoryListIterator = inventoryList.listIterator();
            while(inventoryListIterator.hasNext())
            {
                Inventory inventory = (Inventory) inventoryListIterator.next();
                    query = "Insert into Inventory (Date, Particular, ProductName, SalesInvoice, PurchaseInvoice, purchaseMemoNo, customerOrSupplier, purchaseQuantity, purchaseMeasurement, purchaseRate, purchaseTotal, salesAtSalesQuanty, salesAtSalesMeasurement, salesAtSalesRate, salesAtSalesTotal, salesAtCostQuanty, salesAtCostMeasurement, salesAtCostRate, salesAtCostTotal, balanceQuantity, balanceMeasurement, balanceRate, balanceTotal) values (CURDATE(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    ps = (PreparedStatement) connection.prepareStatement(query);

                    ps.setString(1, inventory.getParticular());
                    ps.setString(2, inventory.getProductName());
                    ps.setInt(3, inventory.getSalesInvoice());
                    ps.setInt(4, inventory.getPurchaseInvoice());
                    ps.setString(5, inventory.getPurchaseMemoNo());
                    ps.setString(6, inventory.getCustomerOrSuppler());

                    ps.setDouble(7, inventory.getPurchaseQuantity());
                    ps.setString(8, inventory.getPurchaseMeasurement());
                    ps.setDouble(9, inventory.getPurchaseRate());
                    ps.setDouble(10, inventory.getPurchaseTotal());

                    ps.setDouble(11, inventory.getSalesAtSalesQuantity());
                    ps.setString(12, inventory.getSalesAtSalesMeasurement());
                    ps.setDouble(13, inventory.getSalesAtSalesRate());
                    ps.setDouble(14, inventory.getSalesAtSalesTotal());

                    ps.setDouble(15, inventory.getSalesAtCostQuantity());
                    ps.setString(16, inventory.getSalesAtCostMeasurement());
                    ps.setDouble(17, inventory.getSalesAtCostRate());
                    ps.setDouble(18, inventory.getSalesAtCostTotal());

                    ps.setDouble(19, inventory.getBalanceQuantity());
                    ps.setString(20, inventory.getBalanceMeasurement());
                    ps.setDouble(21, inventory.getBalanceRate());
                    ps.setDouble(22, inventory.getBalanceTotal());


                    ps.executeUpdate();
            }

      }
       catch(SQLException e)
       {
       }
       finally
       {
           ps.close();
           connection.close();
       }
    }
    
    public static int getLastSalesInvoiceNo() throws SQLException
    {
      int lastSalesInvoiceNO =0;


        try
        {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);
            query = "Select SalesInvoice from Inventory where Particular='sales'";
            ps = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(query);
            rs = ps.executeQuery();
            rs.last();
            lastSalesInvoiceNO = rs.getInt(1);
            return lastSalesInvoiceNO;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return lastSalesInvoiceNO;
        }
        finally
        {
            rs.close();
            ps.close();
            connection.close();

        }  
    }
    public static int getLastPurchaseInvoiceNo() throws SQLException
    {
      int lastPurchaseInvoiceNO =0;


        try
        {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);
            query = "Select PurchaseInvoice from Inventory where Particular='purchase'";
            ps = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(query);
            rs = ps.executeQuery();
            rs.last();
            lastPurchaseInvoiceNO = rs.getInt(1);
            return lastPurchaseInvoiceNO;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return lastPurchaseInvoiceNO;
        }
        finally
        {
            rs.close();
            ps.close();
            connection.close();

        }  
    }
    
    public static Inventory getClosingBalanceByProduct(String productName) throws SQLException
    {
      Inventory inventoryObj = new Inventory();

        try
        {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);
            query = "Select balanceQuantity, balanceMeasurement, balanceRate, balanceTotal from Inventory where ProductName=? order by date ,id ";
            ps = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(query);
            
            ps.setString(1, productName);
            rs = ps.executeQuery();
            rs.last();//*****
            inventoryObj.setBalanceQuantity(rs.getDouble("balanceQuantity"));
            inventoryObj.setBalanceMeasurement(rs.getString("balanceMeasurement"));
            inventoryObj.setBalanceRate(rs.getDouble("balanceRate"));
            inventoryObj.setBalanceTotal(rs.getDouble("balanceTotal"));
            
            
            return inventoryObj;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return inventoryObj;
        }
        finally
        {
            rs.close();
            ps.close();
            connection.close();

        }  
    }
    
    public static ArrayList<Inventory> getIntrByPdName(String productName) throws SQLException
    {
        ArrayList<Inventory> inventoryList = new ArrayList();
        
        try
        {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);
            query = "Select * from Inventory where ProductName=? order by date ,id ";
            ps = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(query);
            
            ps.setString(1, productName);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                Inventory invtory = new Inventory();
                
                invtory.setId(rs.getInt("id"));
                invtory.setDate(rs.getDate("Date"));
                invtory.setParticular(rs.getString("Particular"));
                invtory.setProductName(rs.getString("ProductName"));
                
                invtory.setSalesInvoice(rs.getInt("SalesInvoice"));
                invtory.setPurchaseInvoice(rs.getInt("PurchaseInvoice"));
                invtory.setPurchaseMemoNo(rs.getString("purchaseMemoNo"));
                invtory.setCustomerOrSuppler(rs.getString("customerOrSupplier"));
                
                
                invtory.setPurchaseQuantity(rs.getDouble("purchaseQuantity"));
                invtory.setPurchaseMeasurement(rs.getString("purchaseMeasurement"));
                invtory.setPurchaseRate(rs.getDouble("purchaseRate"));
                invtory.setPurchaseTotal(rs.getDouble("purchaseTotal"));
                
                invtory.setSalesAtSalesQuantity(rs.getDouble("salesAtSalesQuanty"));
                invtory.setSalesAtSalesMeasurement(rs.getString("salesAtSalesMeasurement"));
                invtory.setSalesAtSalesRate(rs.getDouble("salesAtSalesRate"));
                invtory.setSalesAtSalesTotal(rs.getDouble("salesAtSalesTotal"));
    
    
                invtory.setSalesAtCostQuantity(rs.getDouble("salesAtCostQuanty"));
                invtory.setSalesAtCostMeasurement(rs.getString("salesAtCostMeasurement"));
                invtory.setSalesAtCostRate(rs.getDouble("salesAtCostRate"));
                invtory.setSalesAtCostTotal(rs.getDouble("salesAtCostTotal"));
    
                invtory.setBalanceQuantity(rs.getDouble("balanceQuantity"));
                invtory.setBalanceMeasurement(rs.getString("balanceMeasurement"));
                invtory.setBalanceRate(rs.getDouble("balanceRate"));
                invtory.setBalanceTotal(rs.getDouble("balanceTotal"));
    
    
                inventoryList.add(invtory);
    
    
            }
            
            return inventoryList;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return inventoryList;
        }
        finally
        {
            rs.close();
            ps.close();
            connection.close();

        }
        
    }
    public static ArrayList<Invoice> getSalesInvoice() throws SQLException
    {
        ArrayList<Invoice> invoiceList = new ArrayList();
        
        try
        {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);
            query = "SELECT date, salesInvoice, customerorSupplier, sum(salesAtSalesTotal) FROM `inventory` group by salesInvoice ";
            ps = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(query);
            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                Invoice invoice = new Invoice();
                
               
                invoice.setDate(rs.getDate("Date"));
                invoice.setInvoiceNo(rs.getInt("salesInvoice"));
                invoice.setName(rs.getString("customerorSupplier"));
                invoice.setTotal(rs.getDouble("sum(salesAtSalesTotal)"));
                
                invoiceList.add(invoice);
    
            }
            
            return invoiceList;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return invoiceList;
        }
        finally
        {
            rs.close();
            ps.close();
            connection.close();

        }
        
    }
    public static ArrayList<Invoice> getPurchaseInvoice() throws SQLException
    {
        ArrayList<Invoice> invoiceList = new ArrayList();
        
        try
        {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);
            query = "SELECT date, purchaseInvoice, customerorSupplier, purchaseMemoNo, sum(purchaseTotal) FROM `inventory` group by purchaseInvoice ";
            ps = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(query);
            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                Invoice invoice = new Invoice();
                
               
                invoice.setDate(rs.getDate("Date"));
                invoice.setInvoiceNo(rs.getInt("purchaseInvoice"));
                invoice.setName(rs.getString("customerorSupplier"));
                invoice.setMemoNo(rs.getString("purchaseMemoNo"));
                invoice.setTotal(rs.getDouble("sum(purchaseTotal)"));
                
                invoiceList.add(invoice);
    
            }
            
            return invoiceList;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return invoiceList;
        }
        finally
        {
            rs.close();
            ps.close();
            connection.close();

        }
        
    }
    public static double getClosignTotalInventory() throws SQLException
    {
        ArrayList<Product> productList = Database.selectAllProductName();;
            Iterator productIterator = productList.iterator();

        
        double totalBalance =0.00;
        while(productIterator.hasNext())
        {
            Product product = (Product) productIterator.next();
            String productName = product.getProductName();
            
            
            
            Inventory inventory = InventoryData.getClosingBalanceByProduct(productName);
 
                double total = inventory.getBalanceTotal();
                
                totalBalance=totalBalance+total; 
        }
        return totalBalance;
    }
    
    
    public static ArrayList<Inventory> getSalesInvoice( int invoiceNo) throws SQLException
    {
        ArrayList<Inventory> inventoryList = new ArrayList();
        
        try
        {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);
            query = "SELECT date, customerorSupplier, ProductName, salesAtSalesQuanty, salesAtSalesMeasurement, salesAtSalesRate, salesAtSalesTotal FROM `inventory` where salesInvoice=? ";
            ps = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(query);
            
            ps.setInt(1, invoiceNo);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                Inventory inventory = new Inventory();
                
               
                inventory.setDate(rs.getDate("Date"));
                inventory.setCustomerOrSuppler(rs.getString("customerorSupplier"));
                inventory.setProductName(rs.getString("ProductName"));
                inventory.setSalesAtSalesQuantity(rs.getDouble("salesAtSalesQuanty"));
                inventory.setSalesAtSalesMeasurement(rs.getString("salesAtSalesMeasurement"));
                inventory.setSalesAtSalesRate(rs.getDouble("salesAtSalesRate"));
                inventory.setSalesAtSalesTotal(rs.getDouble("salesAtSalesTotal"));
                                
                inventoryList.add(inventory);
    
            }
            
            return inventoryList;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return inventoryList;
        }
        finally
        {
            rs.close();
            ps.close();
            connection.close();

        }
        
    }
    public static ArrayList<Inventory> getPurchaseInvoice( int invoiceNo) throws SQLException
    {
        ArrayList<Inventory> inventoryList = new ArrayList();
        
        try
        {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);
            query = "SELECT date, customerorSupplier, ProductName, purchaseQuantity, purchaseMeasurement, purchaseRate, purchaseTotal FROM `inventory` where PurchaseInvoice=? ";
            ps = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(query);
            
            ps.setInt(1, invoiceNo);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                Inventory inventory = new Inventory();
                
               
                inventory.setDate(rs.getDate("Date"));
                inventory.setCustomerOrSuppler(rs.getString("customerorSupplier"));
                inventory.setProductName(rs.getString("ProductName"));
                inventory.setPurchaseQuantity(rs.getDouble("purchaseQuantity"));
                inventory.setPurchaseMeasurement(rs.getString("purchaseMeasurement"));
                inventory.setPurchaseRate(rs.getDouble("purchaseRate"));
                inventory.setPurchaseTotal(rs.getDouble("purchaseTotal"));
                                
                inventoryList.add(inventory);
    
            }
            
            return inventoryList;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return inventoryList;
        }
        finally
        {
            rs.close();
            ps.close();
            connection.close();

        }
        
    }
    
    
    public static void insertIntoDiscountVat(DiscountVat discountVat) throws SQLException
    {

        try
        {

            connection = (Connection) DriverManager.getConnection(url, user, pass);
            
                   query = "Insert into DiscountVat (salesInvoice, purchaseInvoice, total, discount, vat, paid, vatRate) values (?, ?, ?, ?, ?, ?, ?)";
                    ps = (PreparedStatement) connection.prepareStatement(query);

                    ps.setInt(1, discountVat.getSalesInvoice());
                    ps.setInt(2, discountVat.getPurchaseInvoice());
                    ps.setDouble(3, discountVat.getTotal());
                    ps.setDouble(4, discountVat.getDiscount());
                    ps.setDouble(5, discountVat.getVat());

                    ps.setDouble(6, discountVat.getPaid());
                    ps.setDouble(7, discountVat.getVatRate());
                    
                    ps.executeUpdate();
           

      }
       catch(SQLException e)
       {
       }
       finally
       {
           ps.close();
           connection.close();
       }
    }
    public static DiscountVat getDiscountVatInfo(int salesInvoice) throws SQLException
    {
        DiscountVat discountVat = new DiscountVat();
        try
        {

            connection = (Connection) DriverManager.getConnection(url, user, pass);
            
                   query = "select * from DiscountVat where salesInvoice=?";
                    ps = (PreparedStatement) connection.prepareStatement(query);

                    ps.setInt(1, salesInvoice);
                    rs = ps.executeQuery();
                    
                    while(rs.next())
                    {
                        discountVat.setId(rs.getInt("id"));
                        discountVat.setSalesInvoice(rs.getInt("salesInvoice"));
                        discountVat.setPurchaseInvoice(rs.getInt("purchaseInvoice"));
                        discountVat.setTotal(rs.getDouble("total"));
                        discountVat.setDiscount(rs.getDouble("discount"));
                        discountVat.setVat(rs.getDouble("vat"));
                        discountVat.setPaid(rs.getDouble("paid"));
                        discountVat.setVatRate(rs.getDouble("vatRate"));
                    }
                    return discountVat;
           
      }
       catch(SQLException e)
       {
       }
       finally
       {
           ps.close();
           connection.close();
       }
            return discountVat;
    }
    
    public static String getCustomerName(int invoiceNo) throws SQLException
    {
        String customerName="";
        
        try
        {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);
            query = "SELECT customerorSupplier FROM `inventory` where salesInvoice=? ";
            ps = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(query);
            
            ps.setInt(1, invoiceNo);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                
                customerName=rs.getString("customerorSupplier");
               
            }
            
            return customerName;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return customerName;
        }
        finally
        {
            rs.close();
            ps.close();
            connection.close();

        }
        
    }
    public static String getCustomer(int invoiceNo, String Particular) throws SQLException
    {
        String customerName="";
        
        try
        {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);
            query = "SELECT customerorSupplier FROM `inventory` where salesInvoice=? and  Particular=?";
            ps = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(query);
            
            ps.setInt(1, invoiceNo);
            ps.setString(2, Particular);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                
                customerName=rs.getString("customerorSupplier");
               
            }
            
            return customerName;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return customerName;
        }
        finally
        {
            rs.close();
            ps.close();
            connection.close();

        }
        
    }
    public static String getSupplier(int invoiceNo, String Particular) throws SQLException
    {
        String supplierName="";
        
        try
        {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);
            query = "SELECT customerorSupplier FROM `inventory` where purchaseInvoice=? and  Particular=?";
            ps = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(query);
            
            ps.setInt(1, invoiceNo);
            ps.setString(2, Particular);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                
                supplierName=rs.getString("customerorSupplier");
               
            }
            
            return supplierName;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return supplierName;
        }
        finally
        {
            rs.close();
            ps.close();
            connection.close();

        }
        
    }
    
    
    
    public static Inventory getSalesReturnInvoice(String productName, int invoiceNo) throws SQLException
    {
        Inventory inventory = new Inventory();
        
        try
        {
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);
            query = "Select * from Inventory where ProductName=? and SalesInvoice= ? and particular='sales'";
            ps = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(query);
            
            ps.setString(1, productName);
            ps.setInt(2, invoiceNo);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                
                
                inventory.setId(rs.getInt("id"));
                inventory.setDate(rs.getDate("Date"));
                inventory.setParticular(rs.getString("Particular"));
                inventory.setProductName(rs.getString("ProductName"));
                
                inventory.setSalesInvoice(rs.getInt("SalesInvoice"));
                inventory.setPurchaseInvoice(rs.getInt("PurchaseInvoice"));
                inventory.setPurchaseMemoNo(rs.getString("purchaseMemoNo"));
                inventory.setCustomerOrSuppler(rs.getString("customerOrSupplier"));
                
                
                inventory.setPurchaseQuantity(rs.getDouble("purchaseQuantity"));
                inventory.setPurchaseMeasurement(rs.getString("purchaseMeasurement"));
                inventory.setPurchaseRate(rs.getDouble("purchaseRate"));
                inventory.setPurchaseTotal(rs.getDouble("purchaseTotal"));
                
                inventory.setSalesAtSalesQuantity(rs.getDouble("salesAtSalesQuanty"));
                inventory.setSalesAtSalesMeasurement(rs.getString("salesAtSalesMeasurement"));
                inventory.setSalesAtSalesRate(rs.getDouble("salesAtSalesRate"));
                inventory.setSalesAtSalesTotal(rs.getDouble("salesAtSalesTotal"));
    
    
                inventory.setSalesAtCostQuantity(rs.getDouble("salesAtCostQuanty"));
                inventory.setSalesAtCostMeasurement(rs.getString("salesAtCostMeasurement"));
                inventory.setSalesAtCostRate(rs.getDouble("salesAtCostRate"));
                inventory.setSalesAtCostTotal(rs.getDouble("salesAtCostTotal"));
    
                inventory.setBalanceQuantity(rs.getDouble("balanceQuantity"));
                inventory.setBalanceMeasurement(rs.getString("balanceMeasurement"));
                inventory.setBalanceRate(rs.getDouble("balanceRate"));
                inventory.setBalanceTotal(rs.getDouble("balanceTotal"));
    
    
            }
            
            return inventory;
            

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return inventory;
        }
        finally
        {
            rs.close();
            ps.close();
            connection.close();

        }
        
    }
    
}

