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
public class Measurement implements Serializable {
    
    private int id;
    private String measurement;

    public Measurement()
    {
        this.id=id;
        this.measurement="";
    }

    public Measurement(int id, String measurement)
    {
        this.id=id;
        this.measurement=measurement;
    }

    public void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return id;
    }

    public void setMeasurement(String measurement)
    {
        this.measurement=measurement;
    }
    public String getMeasurement()
    {
        return measurement;
    }
    
}
