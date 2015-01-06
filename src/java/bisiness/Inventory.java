/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bisiness;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author BUDGET003
 */
public class Inventory implements Serializable {
    
    private int id;
    private Date date;
    private String particular;
    private String productName;
    private int salesInvoice;
    private int purchaseInvoice;
    private String purchaseMemoNo;
    private String customerOrSuppler;
    
    
    private double purchaseQuantity;
    private String purchaseMeasurement;
    private double purchaseRate;
    private double purchaseTotal;
    
    private double salesAtSalesQuantity;
    private String salesAtSalesMeasurement;
    private double salesAtSalesRate;
    private double salesAtSalesTotal;
    
    private double salesAtCostQuantity;
    private String salesAtCostMeasurement;
    private double salesAtCostRate;
    private double salesAtCostTotal;
    
    private double balanceQuantity;
    private String balanceMeasurement;
    private double balanceRate;
    private double balanceTotal;
    
    
    public Inventory()
    {
        this.id=0;
        this.date=null;
        this.particular="";
        this.productName="";
        this.salesInvoice=0;
        this.purchaseInvoice=0;
        this.purchaseMemoNo="";
        this.customerOrSuppler="";


        this.purchaseQuantity=0.00;
        this.purchaseMeasurement="";
        this.purchaseRate=0.00;
        this.purchaseTotal=0.00;

        this.salesAtSalesQuantity=0.00;
        this.salesAtSalesMeasurement="";
        this.salesAtSalesRate=0.00;
        this.salesAtSalesTotal=0.00;

        this.salesAtCostQuantity=0.00;
        this.salesAtCostMeasurement="";
        this.salesAtCostRate=0.00;
        this.salesAtCostTotal=0.00;

        this.balanceQuantity=0.00;
        this.balanceMeasurement="";
        this.balanceRate=0.00;
        this.balanceTotal=0.00;
    }
    
    public Inventory(   int id,
                        Date date,
                        String particular,
                        String productName,
                        int salesInvoice,
                        int purchaseInvoice,
                        String purchaseMemoNo,
                        String customerOrSuppler,


                        double purchaseQuantity,
                        String purchaseMeasurement,
                        double purchaseRate,
                        double purchaseTotal,

                        double salesAtSalesQuantity,
                        String salesAtSalesMeasurement,
                        double salesAtSalesRate,
                        double salesAtSalesTotal,

                        double salesAtCostQuantity,
                        String salesAtCostMeasurement,
                        double salesAtCostRate,
                        double salesAtCostTotal,

                        double balanceQuantity,
                        String balanceMeasurement,
                        double balanceRate,
                        double balanceTotal)
    {
        this.id=id;
        this.date=date;
        this.particular=particular;
        this.productName=productName;
        this.salesInvoice=salesInvoice;
        this.purchaseInvoice=purchaseInvoice;
        this.purchaseMemoNo=purchaseMemoNo;
        this.customerOrSuppler=customerOrSuppler;


        this.purchaseQuantity=purchaseQuantity;
        this.purchaseMeasurement=purchaseMeasurement;
        this.purchaseRate=purchaseRate;
        this.purchaseTotal=purchaseTotal;

        this.salesAtSalesQuantity=salesAtSalesQuantity;
        this.salesAtSalesMeasurement=salesAtSalesMeasurement;
        this.salesAtSalesRate=salesAtSalesRate;
        this.salesAtSalesTotal=salesAtSalesTotal;

        this.salesAtCostQuantity=salesAtCostQuantity;
        this.salesAtCostMeasurement=salesAtCostMeasurement;
        this.salesAtCostRate=salesAtCostRate;
        this.salesAtCostTotal=salesAtCostTotal;

        this.balanceQuantity=balanceQuantity;
        this.balanceMeasurement=balanceMeasurement;
        this.balanceRate=balanceRate;
        this.balanceTotal=balanceTotal;
    }
    
    public void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return id;
    }
    public void setDate(Date date)
    {
        this.date=date;
    }
    public Date getDate()
    {
        return date;
    }
    public void setParticular(String particular)
    {
        this.particular=particular;
    }
    public String getParticular()
    {
        return particular;
    }
    
    public void setProductName(String productName)
    {
        this.productName=productName;
    }
    public String getProductName()
    {
        return productName;
    }
    
    
    public void setSalesInvoice(int salesInvoice)
    {
        this.salesInvoice=salesInvoice;
    }
    public int getSalesInvoice()
    {
        return salesInvoice;
    }
    
    public void setPurchaseInvoice(int purchaseInvoice)
    {
        this.purchaseInvoice=purchaseInvoice;
    }
    public int getPurchaseInvoice()
    {
        return purchaseInvoice;
    }
    
    
    public void setPurchaseMemoNo(String purchaseMemoNo)
    {
        this.purchaseMemoNo=purchaseMemoNo;
    }
    public String getPurchaseMemoNo()
    {
        return purchaseMemoNo;
    }
    
    
    public void setCustomerOrSuppler(String customerOrSuppler)
    {
        this.customerOrSuppler=customerOrSuppler;
    }
    public String getCustomerOrSuppler()
    {
        return customerOrSuppler;
    }
    
    
    public void setPurchaseQuantity(double purchaseQuantity)
    {
        this.purchaseQuantity=purchaseQuantity;
    }
    public double getPurchaseQuantity()
    {
        return purchaseQuantity;
    }
    
    
    public void setPurchaseMeasurement(String purchaseMeasurement)
    {
        this.purchaseMeasurement=purchaseMeasurement;
    }
    public String getPurchaseMeasurement()
    {
        return purchaseMeasurement;
    }
    
    
    public void setPurchaseRate(double purchaseRate)
    {
        this.purchaseRate=purchaseRate;
    }
    public double getPurchaseRate()
    {
        return purchaseRate;
    }
    
    
    public void setPurchaseTotal(double purchaseTotal)
    {
        this.purchaseTotal=purchaseTotal;
    }
    public double getPurchaseTotal()
    {
        return purchaseTotal;
    }
       
        
        
    public void setSalesAtSalesQuantity(double salesAtSalesQuantity)
    {
        this.salesAtSalesQuantity=salesAtSalesQuantity;
    }
    public double getSalesAtSalesQuantity()
    {
        return salesAtSalesQuantity;
    }
    
    
    public void setSalesAtSalesMeasurement(String salesAtSalesMeasurement)
    {
        this.salesAtSalesMeasurement=salesAtSalesMeasurement;
    }
    public String getSalesAtSalesMeasurement()
    {
        return salesAtSalesMeasurement;
    }
    
    
    public void setSalesAtSalesRate(double salesAtSalesRate)
    {
        this.salesAtSalesRate=salesAtSalesRate;
    }
    public double getSalesAtSalesRate()
    {
        return salesAtSalesRate;
    }
    
    
    public void setSalesAtSalesTotal(double salesAtSalesTotal)
    {
        this.salesAtSalesTotal=salesAtSalesTotal;
    }
    public double getSalesAtSalesTotal()
    {
        return salesAtSalesTotal;
    }
  
    public void setSalesAtCostQuantity(double salesAtCostQuantity)
    {
        this.salesAtCostQuantity=salesAtCostQuantity;
    }
    public double getSalesAtCostQuantity()
    {
        return salesAtCostQuantity;
    }
    
    
    public void setSalesAtCostMeasurement(String salesAtCostMeasurement)
    {
        this.salesAtCostMeasurement=salesAtCostMeasurement;
    }
    public String getSalesAtCostMeasurement()
    {
        return salesAtCostMeasurement;
    }
    
    
    public void setSalesAtCostRate(double salesAtCostRate)
    {
        this.salesAtCostRate=salesAtCostRate;
    }
    public double getSalesAtCostRate()
    {
        return salesAtCostRate;
    }
    
    
    public void setSalesAtCostTotal(double salesAtCostTotal)
    {
        this.salesAtCostTotal=salesAtCostTotal;
    }
    public double getSalesAtCostTotal()
    {
        return salesAtCostTotal;
    }

    
    public void setBalanceQuantity(double balanceQuantity)
    {
        this.balanceQuantity=balanceQuantity;
    }
    public double getBalanceQuantity()
    {
        return balanceQuantity;
    }
    
    
    public void setBalanceMeasurement(String balanceMeasurement)
    {
        this.balanceMeasurement=balanceMeasurement;
    }
    public String getBalanceMeasurement()
    {
        return balanceMeasurement;
    }
    
    
    public void setBalanceRate(double balanceRate)
    {
        this.balanceRate=balanceRate;
    }
    public double getBalanceRate()
    {
        return balanceRate;
    }
    
    
    public void setBalanceTotal(double balanceTotal)
    {
        this.balanceTotal=balanceTotal;
    }
    public double getBalanceTotal()
    {
        return balanceTotal;
    }
    
    
}
