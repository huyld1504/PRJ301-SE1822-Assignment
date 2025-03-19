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
public class ServiceTicket implements Serializable{
    private String serviceTicketID;
    private Date dateReceived;
    private Date dateReaturned;
    private String custID;
    private String carID;

    public ServiceTicket() {
    }

    public ServiceTicket(String serviceTicketID, Date dateReceived, Date dateReaturned, String custID, String carID) {
        this.serviceTicketID = serviceTicketID;
        this.dateReceived = dateReceived;
        this.dateReaturned = dateReaturned;
        this.custID = custID;
        this.carID = carID;
    }

    public String getServiceTicketID() {
        return serviceTicketID;
    }

    public void setServiceTicketID(String serviceTicketID) {
        this.serviceTicketID = serviceTicketID;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public Date getDateReaturned() {
        return dateReaturned;
    }

    public void setDateReaturned(Date dateReaturned) {
        this.dateReaturned = dateReaturned;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
