package bisiness;

import java.io.Serializable;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dhaka
 */


public class Account implements Serializable {

    private int id;
    private String accountName;
    private String accountType;

    public Account()
    {
        this.id=id;
        this.accountName="";
        this.accountType="";
    }

    public Account(int id, String accountName, String accountType)
    {
        this.id=id;
        this.accountName=accountName;
        this.accountType=accountType;
    }

    public void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return id;
    }

    public void setAccountName(String accountName)
    {
        this.accountName=accountName;
    }
    public String getAccountName()
    {
        return accountName;
    }
    public void setAccountType(String accountType)
    {
        this.accountType=accountType;
    }
    public String getAccountType()
    {
        return accountType;
    }
}


