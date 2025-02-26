/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class SalesPerson {
    private String salesID;
    private String salesName;
    private String salesAddress;
    private String sex;
    private Date birthday;

    public SalesPerson() {
    }

    public SalesPerson(String salesID, String salesName, String salesAddress, String sex, Date birthday) {
        this.salesID = salesID;
        this.salesName = salesName;
        this.salesAddress = salesAddress;
        this.sex = sex;
        this.birthday = birthday;
    }

    public String getSalesID() {
        return salesID;
    }

    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getSalesAddress() {
        return salesAddress;
    }

    public void setSalesAddress(String salesAddress) {
        this.salesAddress = salesAddress;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
