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
import models.Service;
import utils.DBUtils;

/**
 *
 * @author Asus
 */
public class ServiceDAO {

    public Service getServiceByID(String serviceID) {
        Service result = null;
        Connection conn = null;
        PreparedStatement ps;
        ResultSet table;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "SELECT [serviceID]\n"
                        + "      ,[serviceName]\n"
                        + "      ,[hourlyRate]\n"
                        + "  FROM [dbo].[Service]\n"
                        + "  WHERE [serviceID] = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, serviceID);
                table = ps.executeQuery();

                while (table.next()) {
                    String serviceName = table.getString("serviceName");
                    double hourlyRate = table.getDouble("hourlyRate");
                    result = new Service(serviceID, serviceName, hourlyRate);
                }
            }
        } catch (Exception e) {
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

    public ArrayList<Service> getAllServices() {
        ArrayList<Service> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps;
        ResultSet table;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "SELECT [serviceID]\n"
                        + "      ,[serviceName]\n"
                        + "      ,[hourlyRate]\n"
                        + "  FROM [dbo].[Service]";
                ps = conn.prepareStatement(sql);
                table = ps.executeQuery();

                while (table.next()) {
                    String serviceID = table.getString("serviceID");
                    String serviceName = table.getString("serviceName");
                    double hourlyRate = table.getDouble("hourlyRate");
                    String formattedRate = String.format("%.0f", hourlyRate);
                    System.out.println(formattedRate);
                    Service s = new Service(serviceID, serviceName, hourlyRate);
                    list.add(s);
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
        return list;
    }

    public boolean updateServiceByID(String serviceID, String serviceName, double hourlyRate) {
        boolean isUpdated = false;
        Connection conn = null;
        PreparedStatement ps;
        int tableRows = 0;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                conn.setAutoCommit(false);
                String sql = "UPDATE [dbo].[Service]\n"
                        + "   SET [serviceName] = ?\n"
                        + "      ,[hourlyRate] = ?\n"
                        + " WHERE [serviceID] = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, serviceName);
                ps.setDouble(2, hourlyRate);
                ps.setString(3, serviceID);
                tableRows = ps.executeUpdate();

                if (tableRows > 0) {
                    isUpdated = true;
                    conn.commit();
                } else {
                    conn.rollback();
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

        return isUpdated;
    }

    public boolean deleteServiceByID(String serviceID) {
        boolean isDeleted = false;
        Service serviceFound = this.getServiceByID(serviceID);
        boolean isExisted = serviceFound != null;
        Connection conn = null;

        if (isExisted) {
            try {
                conn = DBUtils.getConnection();
                if (conn != null) {
                    conn.setAutoCommit(false);
                    String sql = "DELETE FROM [dbo].[ServiceMechanic]\n"
                            + "      WHERE [serviceID] = ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, serviceID);
                    int rows = ps.executeUpdate();
                    
                    System.out.println("rows in service: " +rows);

                    if (rows > 0) {
                        isDeleted = true;
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
                } catch (Exception e) {
                }
            }
        }

        return isDeleted;
    }
}
