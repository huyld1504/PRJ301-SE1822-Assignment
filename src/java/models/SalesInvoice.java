/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Asus
 */

public class SalesInvoice implements Serializable {
    private int invoiceID;
    private Date invoiceDate;
    private String salesID;
    private String carID;
    private String custID;

    // Constructor mặc định
    public SalesInvoice() {
    }

    // Constructor có tham số
    public SalesInvoice(int invoiceID, Date invoiceDate, String salesID, String carID, String custID) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.salesID = salesID;
        this.carID = carID;
        this.custID = custID;
    }

    // Getter và Setter
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
}

