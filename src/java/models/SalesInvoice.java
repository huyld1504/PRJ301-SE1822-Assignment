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
public class SalesInvoice {
    private int invoiceID;
    private Date invoiceDate;
    private String saleID;
    private String carID;
    private String custID;

    public SalesInvoice() {
    }

    public SalesInvoice(int invoiceID, Date invoiceDate, String saleID, String carID, String custID) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.saleID = saleID;
        this.carID = carID;
        this.custID = custID;
    }

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

    public String getSaleID() {
        return saleID;
    }

    public void setSaleID(String saleID) {
        this.saleID = saleID;
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
