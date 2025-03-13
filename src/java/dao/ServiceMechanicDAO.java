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
import models.ServiceMeChanic;
import utils.DBUtils;

/**
 *
 * @author Asus
 */
public class ServiceMechanicDAO {

    public ArrayList<ServiceMeChanic> getServiceMechanicByMechanicID(String mechanicID) {
        ArrayList<ServiceMeChanic> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps;
        ResultSet table;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "SELECT [serviceTicketID]\n"
                        + "      ,[serviceID]\n"
                        + "      ,[mechanicID]\n"
                        + "      ,[hours]\n"
                        + "      ,[comment]\n"
                        + "      ,[rate]\n"
                        + "  FROM [dbo].[ServiceMechanic]\n"
                        + "  WHERE mechanicID = '?'";
                ps = conn.prepareStatement(sql);
                ps.setString(1, mechanicID);
                table = ps.executeQuery();

                while (table.next()) {
                    String serviceTicketID = table.getString("serviceTicketID");
                    String serviceID = table.getString("serviceID");
                    String comment = table.getString("comment");
                    int hours = table.getInt("hours");
                    double rate = table.getDouble("rate");
                    ServiceMeChanic se = new ServiceMeChanic(serviceTicketID, serviceID, mechanicID, hours, comment, rate);

                    result.add(se);
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

    public ArrayList<ServiceMeChanic> getServiceMechanicByServiceTicketID(String serviceTicketID) {
        ArrayList<ServiceMeChanic> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps;
        ResultSet table;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "SELECT [serviceTicketID]\n"
                        + "      ,[serviceID]\n"
                        + "      ,[mechanicID]\n"
                        + "      ,[hours]\n"
                        + "      ,[comment]\n"
                        + "      ,[rate]\n"
                        + "  FROM [dbo].[ServiceMechanic]\n"
                        + "  WHERE serviceTicketID = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, serviceTicketID);
                table = ps.executeQuery();

                while (table.next()) {
                    String mechanicID = table.getString("mechanicID");
                    String serviceID = table.getString("serviceID");
                    String comment = table.getString("comment");
                    int hours = table.getInt("hours");
                    double rate = table.getDouble("rate");
                    ServiceMeChanic se = new ServiceMeChanic(serviceTicketID, serviceID, mechanicID, hours, comment, rate);

                    result.add(se);
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

    public boolean updateServiceTicketDetail(String serviceTicketID, String mechanicID, String serviceID, String comment, int hours, double rate) {
        boolean isUpdated = false;
        Connection conn = null;
        PreparedStatement ps;
        int result = 0;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                conn.setAutoCommit(false);
                String sql = "UPDATE [dbo].[ServiceMechanic]\n"
                        + "   SET [hours] = ?\n"
                        + "      ,[comment] = ?\n"
                        + "      ,[rate] = ?\n"
                        + "WHERE [serviceTicketID] = ? \n"
                        + " and [serviceID] = ? \n"
                        + " and [mechanicID] = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, hours);
                ps.setString(2, comment);
                ps.setDouble(3, rate);
                ps.setString(4, serviceTicketID);
                ps.setString(5, serviceID);
                ps.setString(6, mechanicID);
                result = ps.executeUpdate();

                if (result > 0) {
                    isUpdated = true;
                    conn.commit();
                } else {
                    conn.rollback();
                }
            }
        } catch (Exception e) {
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

        return isUpdated;
    }

    public boolean deleteServiceMechanicByServiceID(String serviceID) {
        boolean isDeleted = false;
        Connection conn = null;
        PreparedStatement ps;
        int rows;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                String sql = "DELETE FROM [dbo].[ServiceMechanic]\n"
                        + "      WHERE [serviceID] = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, serviceID);
                rows = ps.executeUpdate();
                
                if(rows > 0) {
                    conn.commit();
                } else {
                    conn.rollback();
                }
                isDeleted = true;
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
        return isDeleted;
    }
}
