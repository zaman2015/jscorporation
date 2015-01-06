/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bisiness;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class SubAccount implements Serializable {

    private int id;
    private String mainAccountName;
    private String subAaccountName;
    private String address;
    private String phoneNo;

    public SubAccount()
    {
        this.id=id;
        this.mainAccountName="";
        this.subAaccountName="";
        this.address="";
        this.phoneNo="";
    }

    public SubAccount(int id, String mainAccountName, String subAaccountName, String address, String phoneNo)
    {
        this.id=id;
        this.mainAccountName=mainAccountName;
        this.subAaccountName=subAaccountName;
        this.address=address;
        this.phoneNo=phoneNo;
    }

    public void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return id;
    }

    public void setMainAccountName(String mainAccountName)
    {
        this.mainAccountName=mainAccountName;
    }
    public String getMainAccountName()
    {
        return mainAccountName;
    }
    public void setSubAaccountName(String subAaccountName)
    {
        this.subAaccountName=subAaccountName;
    }
    public String getSubAccountName()
    {
        return subAaccountName;
    }
    public void setAddress(String address)
    {
        this.address=address;
    }
    public String getAddress()
    {
        return address;
    }
    public void setPhoneNo(String phoneNo)
    {
        this.phoneNo=phoneNo;
    }
    public String getPhoneNo()
    {
        return phoneNo;
    }
}


