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

    //ticket list by saleid
    public ArrayList<ServiceTicket> getServiceTicketBySalesID(String salesID) {
        ArrayList<ServiceTicket> tickets = new ArrayList<>();
        try {
            Connection cn = DBUtils.getConnection();
            if (cn != null) {
                String query = "SELECT st.*\n"
                        + "FROM ServiceTicket st\n"
                        + "WHERE st.custID IN (\n"
                        + "    SELECT DISTINCT si.custID \n"
                        + "    FROM SalesInvoice si \n"
                        + "    WHERE si.salesID = ?\n"
                        + ")";
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, salesID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    tickets.add(new ServiceTicket(
                            rs.getString("serviceTicketID"),
                            rs.getDate("dateReceived"),
                            rs.getDate("dateReturned"),
                            rs.getString("custID"),
                            rs.getString("carID")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public static String generateServiceTicketID() {
        String newID = "10110001";
        try {
            Connection cn = DBUtils.getConnection();
            if (cn != null) {
                String query = "SELECT MAX(serviceTicketID) FROM ServiceTicket";
                PreparedStatement ps = cn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String lastID = rs.getString(1);
                    if (lastID != null) {
                        int num = Integer.parseInt(lastID) + 1;
                        newID = String.valueOf(num);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newID;
    }

    public boolean AddServiceTicket(ServiceTicket ticket) {
        boolean success = false;
        try {
            Connection cn = DBUtils.getConnection();
            if (cn != null) {
                String query = "INSERT INTO ServiceTicket (serviceTicketID, dateReceived, dateReturned, custID, carID) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, ticket.getServiceTicketID());
                ps.setDate(2, (java.sql.Date) ticket.getDateReceived());
                ps.setDate(3, (java.sql.Date) ticket.getDateReaturned());
                ps.setString(4, ticket.getCustID());
                ps.setString(5, ticket.getCarID());
                success = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean isTicketIDExist(String ticketID) {
        boolean exists = false;
        String sql = "SELECT 1 FROM ServiceTicket WHERE TicketID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ticketID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                exists = true;
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }

    public ArrayList<ServiceTicket> getAllServiceTickets() {
        ArrayList<ServiceTicket> tickets = new ArrayList<>();
        String sql = "SELECT [serviceTicketID], [dateReceived], [dateReturned], [custID], [carID] FROM [dbo].[ServiceTicket]";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String serviceTicketID = rs.getString("serviceTicketID");
                Date dateReceived = rs.getDate("dateReceived");
                Date dateReturned = rs.getDate("dateReturned");
                String custID = rs.getString("custID");
                String carID = rs.getString("carID");

                ServiceTicket ticket = new ServiceTicket(serviceTicketID, dateReceived, dateReturned, custID, carID);
                tickets.add(ticket);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

   

}
