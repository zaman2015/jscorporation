/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bisiness;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author BUDGET003
 */
public class SubLedger implements Serializable {
    private int id =0;
    private int debitId=0;
    private int creditId=0;
    private Date date;
    private String mainAccount;
    private String baseAccountName;
    private String accountName;
    private String particular;

    private double drUnit;
    private String drMeasurement;
    private double drRate;
    private double drTotal;

    private double crUnit;
    private String crMeasurement;
    private double crRate;
    private double crTotal;

    private double blUnit;
    private String blMeasurement;
    private double blRate;
    private double blTotal;

    public SubLedger()
    {
        this.id=0;
        this.debitId=0;
        this.creditId=0;
        this.date=null;
        this.mainAccount=null;
        this.baseAccountName=null;
        this.accountName=null;
        this.particular=null;

        this.drUnit=0.00;
        this.drMeasurement=null;
        this.drRate=0.00;
        this.drTotal=0.00;

        this.crUnit=0.00;
        this.crMeasurement=null;
        this.crRate=0.00;
        this.crTotal=0.00;

        this.blUnit=0.00;
        this.blMeasurement=null;
        this.blRate=0.00;
        this.blTotal=0.00;

    }
    public SubLedger(int id,
                        int debitId,
                        int creditId,
                        Date date,
                        String mainAccount,
                        String baseAccountName,
                        String accountName,
                        String particular,

                        double drUnit,
                        String drMeasurement,
                        double drRate,
                        double drTotal,

                        double crUnit,
                        String crMeasurement,
                        double crRate,
                        double crTotal,

                        double blUnit,
                        String blMeasurement,
                        double blRate,
                        double blTotal)
    {
        this.id=id;
        this.debitId=debitId;
        this.creditId=creditId;
        this.date=date;
        this.mainAccount=mainAccount;
        this.baseAccountName=baseAccountName;
        this.accountName=accountName;
        this.particular=particular;

        this.drUnit=drUnit;
        this.drMeasurement=drMeasurement;
        this.drRate=drRate;
        this.drTotal=drTotal;

        this.crUnit=crUnit;
        this.crMeasurement=crMeasurement;
        this.crRate=crRate;
        this.crTotal=crTotal;

        this.blUnit=blUnit;
        this.blMeasurement=blMeasurement;
        this.blRate=blRate;
        this.blTotal=blTotal;

    }
    public void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return id;
    }
    
    public void setDebitId(int debitId)
    {
        this.debitId=debitId;
    }
    public int getDebitId()
    {
        return debitId;
    }
    
    public void setCreditId(int creditId)
    {
        this.creditId=creditId;
    }
    public int getCreditId()
    {
        return creditId;
    }
    
    public void setDate(Date date)
    {
        this.date=date;
    }
    public Date getDate()
    {
        return date;
    }

    public void setMainAccount(String mainAccount)
    {
        this.mainAccount=mainAccount;
    }
    public String getMainAccount()
    {
        return mainAccount;
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


    public void setParticular(String particular)
    {
        this.particular=particular;
    }
    public String getParticular()
    {
        return particular;
    }


    
    public void setDrUnit(double drUnit)
    {
        this.drUnit=drUnit;
    }
    public double getDrUnit()
    {
        return drUnit;
    }
    public void setDrMeasurement(String drMeasurement)
    {
        this.drMeasurement=drMeasurement;
    }
    public String getDrMeasurement()
    {
        return drMeasurement;
    }
    public void setDrRate(double drRate)
    {
        this.drRate= drRate;
    }
    public double getDrRate()
    {
        return drRate;
    }
    public void setDrTotal(double drTotal)
    {
        this.drTotal=drTotal;
    }
    public double getDrTotal()
    {
        return drTotal;
    }



    public void setCrUnit(double crUnit)
    {
        this.crUnit=crUnit;
    }
    public double getCrUnit()
    {
        return crUnit;
    }
    public void setCrMeasurement(String crMeasurement)
    {
        this.crMeasurement=crMeasurement;
    }
    public String getCrMeasurement()
    {
        return crMeasurement;
    }
    public void setCrRate(double crRate)
    {
        this.crRate= crRate;
    }
    public double getCrRate()
    {
        return crRate;
    }
    public void setCrTotal(double crTotal)
    {
        this.crTotal=crTotal;
    }
    public double getCrTotal()
    {
        return crTotal;
    }



    public void setBlUnit(double blUnit)
    {
        this.blUnit=blUnit;
    }
    public double getBlUnit()
    {
        return blUnit;
    }
    public void setBlMeasurement(String blMeasurement)
    {
        this.blMeasurement=blMeasurement;
    }
    public String getBlMeasurement()
    {
        return blMeasurement;
    }
    public void setBlRate(double blRate)
    {
        this.blRate= blRate;
    }
    public double getBlRate()
    {
        return blRate;
    }
    public void setBlTotal(double blTotal)
    {
        this.blTotal=blTotal;
    }
    public double getBlTotal()
    {
        return blTotal;
    }



}
