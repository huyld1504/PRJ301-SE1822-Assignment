/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Thanh Vinh
 */

import java.io.Serializable;
import java.util.Date;



public class SalesInvoiceDetail implements Serializable {

    private int invoiceID;
    private Date invoiceDate;
    private String salesID;
    private String carID;
    private String custID;
    private Customer customer;
    private Car car;
    private SalesPerson salesPerson;

    // Constructor
    public SalesInvoiceDetail(int invoiceID, Date invoiceDate, String salesID, String carID, String custID,
            Customer customer, Car car, SalesPerson salesPerson) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.salesID = salesID;
        this.carID = carID;
        this.custID = custID;
        this.customer = customer;
        this.car = car;
        this.salesPerson = salesPerson;
    }

    // Getters and Setters
    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getSalesID() {
        return salesID;
    }

    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public SalesPerson getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(SalesPerson salesPerson) {
        this.salesPerson = salesPerson;
    }

    @Override
    public String toString() {
        return "SalesInvoiceDetail [invoiceID=" + invoiceID + ", invoiceDate=" + invoiceDate + ", salesID=" + salesID
                + ", carID=" + carID + ", custID=" + custID + ", customer=" + customer + ", car=" + car + ", salesPerson=" + salesPerson + "]";
    }
}
