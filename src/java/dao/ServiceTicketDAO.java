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
import utils.StringUtils;

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

    public ServiceTicket getServiceTicketByID(String id) {
        ServiceTicket result = null;
        Connection conn = null;
        PreparedStatement ps;
        ResultSet table;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "SELECT [serviceTicketID]\n"
                        + "      ,[dateReceived]\n"
                        + "      ,[dateReturned]\n"
                        + "      ,[custID]\n"
                        + "      ,[carID]\n"
                        + "  FROM [dbo].[ServiceTicket]\n"
                        + "  WHERE [serviceTicketID] = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, id);
                table = ps.executeQuery();
                while (table.next()) {
                    String serviceTicketID = table.getString("serviceTicketID");
                    Date dateReceived = table.getDate("dateReceived");
                    Date dateReturned = table.getDate("dateReturned");
                    String cusID = table.getString("custID");
                    String carID = table.getString("carID");
                    result = new ServiceTicket(serviceTicketID, dateReceived, dateReturned, cusID, carID);
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

    public ArrayList<ServiceTicket> getServiceTicketByCustomerID(String customerID) {
        ArrayList<ServiceTicket> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps;
        ResultSet table;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "SELECT [serviceTicketID]\n"
                        + "      ,[dateReceived]\n"
                        + "      ,[dateReturned]\n"
                        + "      ,[custID]\n"
                        + "      ,[carID]\n"
                        + "  FROM [dbo].[ServiceTicket]\n"
                        + "  where [custID] = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, customerID);
                table = ps.executeQuery();

                while (table.next()) {
                    String serviceTicketID = table.getString("serviceTicketID");
                    Date dateReceived = table.getDate("dateReceived");
                    Date dateReturned = table.getDate("dateReturned");
                    String carID = table.getString("carID");
                    ServiceTicket s = new ServiceTicket(serviceTicketID, dateReceived, dateReturned, customerID, carID);

                    result.add(s);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<ServiceTicket> getServiceTicketByCarID(String carID) {
        ArrayList<ServiceTicket> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps;
        ResultSet table;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "SELECT [serviceTicketID]\n"
                        + "      ,[dateReceived]\n"
                        + "      ,[dateReturned]\n"
                        + "      ,[custID]\n"
                        + "      ,[carID]\n"
                        + "  FROM [dbo].[ServiceTicket]\n"
                        + "  where [carID] = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, carID);
                table = ps.executeQuery();

                while (table.next()) {
                    String serviceTicketID = table.getString("serviceTicketID");
                    Date dateReceived = table.getDate("dateReceived");
                    Date dateReturned = table.getDate("dateReturned");
                    String custID = table.getString("custID");
                    ServiceTicket s = new ServiceTicket(serviceTicketID, dateReceived, dateReturned, custID, carID);

                    result.add(s);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<ServiceTicket> getServiceTicketByDateReceived(Date dateReceived) {
        ArrayList<ServiceTicket> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps;
        ResultSet table;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "SELECT [serviceTicketID]\n"
                        + "      ,[dateReceived]\n"
                        + "      ,[dateReturned]\n"
                        + "      ,[custID]\n"
                        + "      ,[carID]\n"
                        + "  FROM [dbo].[ServiceTicket]\n"
                        + "    WHERE dateReceived >= ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, dateReceived.toString());
                table = ps.executeQuery();

                while (table.next()) {
                    String serviceTicketID = table.getString("serviceTicketID");
                    String custID = table.getString("custID");
                    Date dateReturned = table.getDate("dateReturned");
                    String carID = table.getString("carID");
                    ServiceTicket s = new ServiceTicket(serviceTicketID, dateReceived, dateReturned, custID, carID);

                    result.add(s);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<ServiceTicket> searchServiceTicket(String cusID, String carID, String dateReceived, String mechanicID) {
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
                        + "WHERE se.serviceTicketID = sm.serviceTicketID and sm.mechanicID = " + mechanicID + " AND 1=1\n";

                if (!StringUtils.checkEmpty(cusID)) {
                    sql += " AND [custID] = ? \n";
                }
                if (!StringUtils.checkEmpty(carID)) {
                    sql += " AND [carID] = ? \n";
                }
                if (!StringUtils.checkEmpty(dateReceived)) {
                    sql += " AND [dateReceived] >= ? \n";
                }
                ps = conn.prepareStatement(sql);
                int paramIndex = 1;
                if (!StringUtils.checkEmpty(cusID)) {
                    ps.setString(paramIndex, cusID);
                    paramIndex++;
                }
                if (!StringUtils.checkEmpty(carID)) {
                    ps.setString(paramIndex, carID);
                    paramIndex++;
                }
                if (!StringUtils.checkEmpty(dateReceived)) {
                    ps.setString(paramIndex, dateReceived);
                }
                table = ps.executeQuery();

                while (table.next()) {
                    String serviceTicketID = table.getString("serviceTicketID");
                    Date dateReceived_table = table.getDate("dateReceived");
                    Date dateReturned = table.getDate("dateReturned");
                    String custID_table = table.getString("custID");
                    String carID_table = table.getString("carID");
                    ServiceTicket s = new ServiceTicket(serviceTicketID, dateReceived_table, dateReturned, custID_table, carID_table);

                    result.add(s);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
