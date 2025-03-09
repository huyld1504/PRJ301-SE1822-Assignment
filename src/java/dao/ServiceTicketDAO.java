/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import models.ServiceTicket;
import utils.DBUtils;

/**
 *
 * @author Asus
 */
public class ServiceTicketDAO {

    public ArrayList<ServiceTicket> getServiceTicketByMechanicID(String mechanicID) {
        ArrayList<ServiceTicket> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps;
        ResultSet table;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "SELECT distinct se.[serviceTicketID]\n"
                        + "      ,[dateReceived]\n"
                        + "      ,[dateReturned]\n"
                        + "      ,[custID]\n"
                        + "      ,[carID]\n"
                        + "  FROM [dbo].[ServiceTicket] se, dbo.ServiceMechanic sm\n"
                        + "  WHERE se.serviceTicketID = sm.serviceTicketID and sm.mechanicID = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, mechanicID);
                table = ps.executeQuery();
                while (table.next()) {
                    String serviceTicketID = table.getString("serviceTicketID");
                    Date dateReceived = table.getDate("dateReceived");
                    Date dateReturned = table.getDate("dateReturned");
                    String cusID = table.getString("custID");
                    String carID = table.getString("carID");
                    ServiceTicket s = new ServiceTicket(serviceTicketID, dateReceived, dateReturned, cusID, carID);
                    result.add(s);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }

        return result;
    }
}
