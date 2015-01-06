/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bisiness;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ARIBA
 */
public class Ledger implements Serializable{
    private int id;
    private Date date;
    private String baseAccountName;
    private String accountName;
    private double debitAmount;
    private double creditAmount;
    private double balance;
    private String particular;

    public Ledger()
    {
        this.id=0;
        this.date=null;
        this.baseAccountName="";
        this.accountName="";
        this.debitAmount=0.00;
        this.creditAmount = 0.00;
        this.balance= 0.00;
        this.particular="";
    }

    public Ledger(int id, Date date,String baseAccountName, String accountName, double debitAmount, double creditAmount, double balance, String particular)
    {
        this.id=id;
        this.date=date;
        this.baseAccountName=baseAccountName;
        this.accountName=accountName;
        this.debitAmount=debitAmount;
        this.creditAmount = creditAmount;
        this.balance= balance;
        this.particular=particular;
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
    public void setBaseAccountName(String baseAccountName)
    {
        this.baseAccountName=baseAccountName;
    }
    public String getBaseAccountName()
    {
        return baseAccountName;
    }
    public void setAccountName(String accountName)
    {
        this.accountName=accountName;
    }
    public String getAccountName()
    {
        return accountName;
    }
    public void setDebitAmount(double debitAmount)
    {
        this.debitAmount=debitAmount;
    }
    public double getDebitAmount()
    {
        return debitAmount;
    }
    public void setCreditAmount(double creditAmount)
    {
        this.creditAmount=creditAmount;
    }
    public double getCreditAmount()
    {
        return creditAmount;
    }
    public void setBalance(double balance)
    {
        this.balance=balance;
    }
    public double getBalance()
    {
        return balance;
    }
    public void setParticular(String particular)
    {
        this.particular=particular;
    }
    public String getParticular()
    {
        return particular;
    }


}
