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
 * @author USER
 */
public class Invoice implements Serializable{
    
    private Date date;
    private int invoiceNo;
    private String name;
    private String memoNo;
    private double total;
    

    public Invoice()
    {
        
        this.date=null;
        this.invoiceNo=0;
        this.name="";
        this.memoNo="";
        this.total= 0.00;
        
    }

    public Invoice(Date date, int invoiceNo, String name, String memoNo, double total)
    {
        
        this.date=date;
        this.invoiceNo=invoiceNo;
        this.name=name;
        this.memoNo=memoNo;
        this.total=total;
        
    }
    
    public void setDate(Date date)
    {
        this.date=date;
    }
    public Date getDate()
    {
        return date;
    }
    
    public void setInvoiceNo(int invoiceNo)
    {
        this.invoiceNo=invoiceNo;
    }
    public int getInvoiceNo()
    {
        return invoiceNo;
    }
    
    
    public void setName(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return name;
    }
    
    public void setMemoNo(String memoNo)
    {
        this.memoNo=memoNo;
    }
    public String getMemoNo()
    {
        return memoNo;
    }
    
    public void setTotal(double total)
    {
        this.total=total;
    }
    public double getTotal()
    {
        return total;
    }
    
}
