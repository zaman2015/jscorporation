/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bisiness;

import java.io.Serializable;

/**
 *
 * @author Dhaka
 */
public class Product implements Serializable {
    private int id;
    private String productName;
    private String measurementUnit;

    public Product()
    {
        this.id=id;
        this.productName="";
        this.measurementUnit="";
    }

    public Product(int id, String productName, String measurementUnit)
    {
        this.id=id;
        this.productName=productName;
        this.measurementUnit=measurementUnit;
    }

    public void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return id;
    }

    public void setProductName(String productName)
    {
        this.productName=productName;
    }
    public String getProductName()
    {
        return productName;
    }
    public void setMeasurementUnit(String measurementUnit)
    {
        this.measurementUnit=measurementUnit;
    }
    public String getMeasurementUnit()
    {
        return measurementUnit;
    }
    
}
