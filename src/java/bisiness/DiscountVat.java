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
public class DiscountVat implements Serializable {
    private int id;
    
    private int salesInvoice;
    private int purchaseInvoice;
    
    
    private double total;
    private double discount;
    private double vat;
    private double vatRate;
    private double paid;
    
      
    
    public DiscountVat()
    {
        this.id=0;
        
        this.salesInvoice=0;
        this.purchaseInvoice=0;
        
        this.total=0.00;
        this.discount=0.00;
        this.vat=0.00;
        
        this.paid=0.00;
        this.vatRate=0.00;
        
    }
    
    public DiscountVat(   int id,
                        int salesInvoice,
                        int purchaseInvoice,
                        double total,
                        double discount,
                        double vat,
                        double paid,
                        double vatRate)
    {
        this.id=id;
        this.salesInvoice=salesInvoice;
        this.purchaseInvoice=purchaseInvoice;
        this.total=total;
        this.discount=discount;
        this.vat=vat;
        this.paid=paid;
        this.vatRate=vatRate;
    }
    
    public void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return id;
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
    
    
       
    
    public void setTotal(double total)
    {
        this.total=total;
    }
    public double getTotal()
    {
        return total;
    }
    
    public void setDiscount(double discount)
    {
        this.discount=discount;
    }
    public double getDiscount()
    {
        return discount;
    }
    public void setVat(double vat)
    {
        this.vat=vat;
    }
    public double getVat()
    {
        return vat;
    }
    public void setPaid(double paid)
    {
        this.paid=paid;
    }
    public double getPaid()
    {
        return paid;
    }
    public void setVatRate(double vatRate)
    {
        this.vatRate=vatRate;
    }
    public double getVatRate()
    {
        return vatRate;
    }
    
    
}
