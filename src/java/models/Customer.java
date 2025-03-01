/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class Customer implements Serializable{
    private String custID;
    private String custName;
    private String phone;
    private String cusAddress;
    private String sex;

    public Customer() {
    }

    public Customer(String custID, String custName, String phone, String cusAddress, String sex) {
        this.custID = custID;
        this.custName = custName;
        this.phone = phone;
        this.cusAddress = cusAddress;
        this.sex = sex;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
