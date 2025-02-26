/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Asus
 */
public class PartsUsed {
    private String serviceTicketID;
    private String partID;
    private int numberUsed;
    private Double price;

    public PartsUsed() {
    }

    public PartsUsed(String serviceTicketID, String partID, int numberUsed, Double price) {
        this.serviceTicketID = serviceTicketID;
        this.partID = partID;
        this.numberUsed = numberUsed;
        this.price = price;
    }

    public String getServiceTicketID() {
        return serviceTicketID;
    }

    public void setServiceTicketID(String serviceTicketID) {
        this.serviceTicketID = serviceTicketID;
    }

    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public int getNumberUsed() {
        return numberUsed;
    }

    public void setNumberUsed(int numberUsed) {
        this.numberUsed = numberUsed;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
